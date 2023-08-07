package com.example.kafkawebservice.service;

import com.example.kafkawebservice.model.DataModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MonitoringPredictionKafkaListener {

    @Autowired
    private DemoMongoDBRepository demoMongoDBRepository;

    @KafkaListener(topics = "monitoring-actual-predicted-topic")
    public void readDataStream(String record) {
        TempDataModel object = null;
        try {
            object = new ObjectMapper().readValue(record, TempDataModel.class);
            String tmpPayload = object.getPayload();
            String tmpTimestamp = tmpPayload.substring(tmpPayload.lastIndexOf("timetamp: ") + 10, tmpPayload.lastIndexOf(", actual_cpu1:"));
            String tmpActualCpu1 = tmpPayload.substring(tmpPayload.lastIndexOf("actual_cpu1: ") + 13, tmpPayload.lastIndexOf(", predicted_cpu1:"));
            String tmpActualCpu2 = tmpPayload.substring(tmpPayload.lastIndexOf("actual_cpu2: ") + 13, tmpPayload.lastIndexOf(", predicted_cpu2:"));
            String tmpActualCpu3 = tmpPayload.substring(tmpPayload.lastIndexOf("actual_cpu3: ") + 13, tmpPayload.lastIndexOf(", predicted_cpu3:"));
            String tmpActualCpu4 = tmpPayload.substring(tmpPayload.lastIndexOf("actual_cpu4: ") + 13, tmpPayload.lastIndexOf(", predicted_cpu4:"));
            String tmpPredictedCpu1 = tmpPayload.substring(tmpPayload.lastIndexOf("predicted_cpu1: ") + 16, tmpPayload.lastIndexOf(", actual_cpu2:"));
            String tmpPredictedCpu2 = tmpPayload.substring(tmpPayload.lastIndexOf("predicted_cpu2: ") + 16, tmpPayload.lastIndexOf(", actual_cpu3:"));
            String tmpPredictedCpu3 = tmpPayload.substring(tmpPayload.lastIndexOf("predicted_cpu3: ") + 16, tmpPayload.lastIndexOf(", actual_cpu4:"));
            String tmpPredictedCpu4 = tmpPayload.substring(tmpPayload.lastIndexOf("predicted_cpu4: ") + 16);

//            System.out.println("Current timestamp is: " + tmpTimestamp);
//            System.out.println("Current actual cpu 1 is: " + tmpActualCpu1);
//            System.out.println("Current actual cpu 2 is: " + tmpActualCpu2);
//            System.out.println("Current actual cpu 3 is: " + tmpActualCpu3);
//            System.out.println("Current actual cpu 4 is: " + tmpActualCpu4);
//            System.out.println("Current predicted cpu 1 is: " + tmpPredictedCpu1);
//            System.out.println("Current predicted cpu 2 is: " + tmpPredictedCpu2);
//            System.out.println("Current predicted cpu 3 is: " + tmpPredictedCpu3);
//            System.out.println("Current predicted cpu 4 is: " + tmpPredictedCpu4);

            DataModel another_object = new DataModel();
//            another_object.setId(object.getId());
//            another_object.setEpoch(object.getEpoch());
//
//            String tmpTime = object.getTimestamp();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
            Date anotherTime = sdf.parse(tmpTimestamp);
            another_object.setTimestamp(anotherTime);

            long epoch = anotherTime.getTime();
            String epochString = Long.toString(epoch);
//            System.out.println(epochString);
            another_object.setEpoch(epochString);

            another_object.setActualCpu1(Integer.valueOf(tmpActualCpu1));
            another_object.setActualCpu2(Integer.valueOf(tmpActualCpu2));
            another_object.setActualCpu3(Integer.valueOf(tmpActualCpu3));
            another_object.setActualCpu4(Integer.valueOf(tmpActualCpu4));

            another_object.setPredictedCpu1(Integer.valueOf(tmpPredictedCpu1));
            another_object.setPredictedCpu2(Integer.valueOf(tmpPredictedCpu2));
            another_object.setPredictedCpu3(Integer.valueOf(tmpPredictedCpu3));
            another_object.setPredictedCpu4(Integer.valueOf(tmpPredictedCpu4));

            demoMongoDBRepository.save(another_object);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static class TempDataModel {

        String payload;

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }
    }
}

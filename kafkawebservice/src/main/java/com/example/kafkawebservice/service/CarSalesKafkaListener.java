package com.example.kafkawebservice.service;

import com.example.kafkawebservice.model.DataModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CarSalesKafkaListener {

    @Autowired
    private DemoMongoDBRepository demoMongoDBRepository;

    @KafkaListener(topics = "my-test-topic")
    public void readDataStream(String record) {
        TempDataModel object = null;
        try {
            object = new ObjectMapper().readValue(record, TempDataModel.class);
            System.out.println(record.toString());
            DataModel another_object = new DataModel();
            another_object.setId(object.getId());
            another_object.setEpoch(object.getEpoch());

            String tmpTime = object.getTimestamp();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
            Date anotherTime = sdf.parse(tmpTime);
            another_object.setTimestamp(anotherTime);

            String cpuRealVm1 = object.getCpu_real_vm1();
            Integer numberCpuRealVm1 = Integer.parseInt(cpuRealVm1);
            String cpuPredictedVm1 = object.getCpu_predicted_vm1();
            Integer numberCpuPredictedVm1 = Integer.parseInt(cpuPredictedVm1);

            String cpuRealVm2 = object.getCpu_real_vm2();
            Integer numberCpuRealVm2 = Integer.parseInt(cpuRealVm2);
            String cpuPredictedVm2 = object.getCpu_predicted_vm2();
            Integer numberCpuPredictedVm2 = Integer.parseInt(cpuPredictedVm2);


            another_object.setPercentageCpuRealVm1(numberCpuRealVm1);
            another_object.setPercentageCpuPredictedVm1(numberCpuPredictedVm1);
            another_object.setPercentageCpuRealVm2(numberCpuRealVm2);
            another_object.setPercentageCpuPredictedVm2(numberCpuPredictedVm2);

            demoMongoDBRepository.save(another_object);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static class TempDataModel {

        @Id
        private String id;

        String timestamp;

        String epoch;

        String cpu_real_vm1;

        String cpu_predicted_vm1;

        String cpu_real_vm2;

        String cpu_predicted_vm2;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getEpoch() {
            return epoch;
        }

        public void setEpoch(String epoch) {
            this.epoch = epoch;
        }

        public String getCpu_real_vm1() {
            return cpu_real_vm1;
        }

        public void setCpu_real_vm1(String cpu_real_vm1) {
            this.cpu_real_vm1 = cpu_real_vm1;
        }

        public String getCpu_predicted_vm1() {
            return cpu_predicted_vm1;
        }

        public void setCpu_predicted_vm1(String cpu_predicted_vm1) {
            this.cpu_predicted_vm1 = cpu_predicted_vm1;
        }

        public String getCpu_real_vm2() {
            return cpu_real_vm2;
        }

        public void setCpu_real_vm2(String cpu_real_vm2) {
            this.cpu_real_vm2 = cpu_real_vm2;
        }

        public String getCpu_predicted_vm2() {
            return cpu_predicted_vm2;
        }

        public void setCpu_predicted_vm2(String cpu_predicted_vm2) {
            this.cpu_predicted_vm2 = cpu_predicted_vm2;
        }
    }
}

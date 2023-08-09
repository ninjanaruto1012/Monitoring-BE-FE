package com.example.kafkawebservice.service;

import com.example.kafkawebservice.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class WebSocketStream {

    @Value("cars")
    private String stompTopic;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private DataService dataService;

    @Scheduled(fixedRate = 5000)
    public void streamCarSalesData() {
        DataModel jsonObject = dataService.getLatestData();
        String myString = "{\"Timestamp\": \"" + jsonObject.getTimestamp() + "\", \"Epoch\": \""
                + jsonObject.getEpoch() + "\", \"CpuRealVm1\": \""
                + jsonObject.getActualCpu1() + "\", \"CpuPredictedVm1\": \""
                + jsonObject.getPredictedCpu1() + "\", \"CpuRealVm2\": \""
                + jsonObject.getActualCpu2() + "\", \"CpuPredictedVm2\": \""
                + jsonObject.getPredictedCpu2() + "\", \"CpuRealVm3\": \""
                + jsonObject.getActualCpu3() + "\", \"CpuPredictedVm3\": \""
                + jsonObject.getPredictedCpu3() + "\", \"CpuRealVm4\": \""
                + jsonObject.getActualCpu4() + "\", \"CpuPredictedVm4\": \""
                + jsonObject.getPredictedCpu4() + "\", \"Mse\": \""
                + jsonObject.getMse() + "\"}";
        System.out.println("Message sent to Websocket is: " + myString);
        messagingTemplate.convertAndSend("/monitor/abc",myString);
    }
}

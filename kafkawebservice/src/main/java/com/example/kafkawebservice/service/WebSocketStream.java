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
                + jsonObject.getPercentageCpuRealVm1() + "\", \"CpuPredictedVm1\": \""
                + jsonObject.getPercentageCpuPredictedVm1() + "\", \"CpuRealVm2\": \""
                + jsonObject.getPercentageCpuRealVm2() + "\", \"CpuPredictedVm2\": \""
                + jsonObject.getPercentageCpuPredictedVm2() + "\"}";
        messagingTemplate.convertAndSend("/cars/abc",myString);
    }
}

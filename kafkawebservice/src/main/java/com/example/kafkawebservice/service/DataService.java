package com.example.kafkawebservice.service;

import com.example.kafkawebservice.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Autowired
    public DemoMongoDBRepository demoMongoDBRepository;

    public DataModel getLatestData() {
        DataModel object = demoMongoDBRepository.findTop1ByOrderByTimestampDesc();
        System.out.println("Timestamp: " + object.getTimestamp() + " and Id: " + object.getId());
        return object;
    }
}

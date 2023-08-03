package com.example.kafkawebservice.service;

import com.example.kafkawebservice.model.DataModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DemoMongoDBRepository  extends MongoRepository<DataModel, String> {
    //Supports native JSON query string
    DataModel findTop1ByOrderByTimestampDesc();
}

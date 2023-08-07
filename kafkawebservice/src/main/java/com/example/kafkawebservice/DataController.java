package com.example.kafkawebservice;

import com.example.kafkawebservice.model.DataModel;
import com.example.kafkawebservice.service.DataService;
import com.example.kafkawebservice.service.DemoMongoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class DataController {
    @Autowired
    private DemoMongoDBRepository demoMongoDBRepository;

    @Autowired
    DataService dataService;

    @PostMapping("/carsales")
    public List<DataModel> addCarSale(@RequestBody final List<DataModel> carSales) {
        return demoMongoDBRepository.saveAll(carSales);
    }

    @GetMapping("/carsales")
    public List<DataModel> findCarSales() {
        return demoMongoDBRepository.findAll();
    }

    @GetMapping("/lastcarsales")
    public DataModel findLastCarSales() {return dataService.getLatestData(); }

    @DeleteMapping("/carsales")
    public String removeData() {return dataService.deleteData(); }
}

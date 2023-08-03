package com.example.kafkawebservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "carsales")
public class DataModel {


    @Id
    private String id;

    Date timestamp;

    String epoch;

    Integer percentageCpuRealVm1;

    Integer percentageCpuPredictedVm1;

    Integer percentageCpuRealVm2;

    Integer percentageCpuPredictedVm2;

    public Integer getPercentageCpuPredictedVm1() {

        return percentageCpuPredictedVm1;
    }

    public void setPercentageCpuPredictedVm1(Integer percentageCpuPredictedVm1) {
        this.percentageCpuPredictedVm1 = percentageCpuPredictedVm1;
    }

    public Integer getPercentageCpuRealVm2() {
        return percentageCpuRealVm2;
    }

    public void setPercentageCpuRealVm2(Integer percentageCpuRealVm2) {
        this.percentageCpuRealVm2 = percentageCpuRealVm2;
    }

    public Integer getPercentageCpuPredictedVm2() {
        return percentageCpuPredictedVm2;
    }

    public void setPercentageCpuPredictedVm2(Integer percentageCpuPredictedVm2) {
        this.percentageCpuPredictedVm2 = percentageCpuPredictedVm2;
    }

    public Integer getPercentageCpuRealVm1() {
        return percentageCpuRealVm1;
    }

    public void setPercentageCpuRealVm1(Integer percentageCpuRealVm1) {
        this.percentageCpuRealVm1 = percentageCpuRealVm1;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }


}

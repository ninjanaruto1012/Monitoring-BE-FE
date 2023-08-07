package com.example.kafkawebservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "monitoring-actual-predict")
public class DataModel {


    @Id
    private String id;

    Date timestamp;

    String epoch;

    Integer actualCpu1;

    Integer predictedCpu1;

    Integer actualCpu2;

    Integer predictedCpu2;

    Integer actualCpu3;

    Integer predictedCpu3;

    Integer actualCpu4;

    Integer predictedCpu4;


    public Integer getActualCpu3() {
        return actualCpu3;
    }

    public void setActualCpu3(Integer actualCpu3) {
        this.actualCpu3 = actualCpu3;
    }

    public Integer getPredictedCpu3() {
        return predictedCpu3;
    }

    public void setPredictedCpu3(Integer predictedCpu3) {
        this.predictedCpu3 = predictedCpu3;
    }

    public Integer getActualCpu4() {
        return actualCpu4;
    }

    public void setActualCpu4(Integer actualCpu4) {
        this.actualCpu4 = actualCpu4;
    }

    public Integer getPredictedCpu4() {
        return predictedCpu4;
    }

    public void setPredictedCpu4(Integer predictedCpu4) {
        this.predictedCpu4 = predictedCpu4;
    }

    public Integer getPredictedCpu1() {

        return predictedCpu1;
    }

    public void setPredictedCpu1(Integer predictedCpu1) {
        this.predictedCpu1 = predictedCpu1;
    }

    public Integer getActualCpu2() {
        return actualCpu2;
    }

    public void setActualCpu2(Integer actualCpu2) {
        this.actualCpu2 = actualCpu2;
    }

    public Integer getPredictedCpu2() {
        return predictedCpu2;
    }

    public void setPredictedCpu2(Integer predictedCpu2) {
        this.predictedCpu2 = predictedCpu2;
    }

    public Integer getActualCpu1() {
        return actualCpu1;
    }

    public void setActualCpu1(Integer actualCpu1) {
        this.actualCpu1 = actualCpu1;
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

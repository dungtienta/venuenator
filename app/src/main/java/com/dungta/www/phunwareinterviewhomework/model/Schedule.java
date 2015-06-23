package com.dungta.www.phunwareinterviewhomework.model;

import com.google.gson.annotations .Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class generated from http://www.jsonschema2pojo.org/ from JSON provided at
 * https://s3.amazonaws.com/jon-hancock-phunware/nflapi-static.json
 */
public class Schedule {

    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("start_date")
    @Expose
    private String startDate;

    /**
     *
     * @return
     * The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     * The end_date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     * The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     * The start_date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

}
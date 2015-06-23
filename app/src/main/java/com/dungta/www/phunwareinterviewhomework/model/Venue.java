package com.dungta.www.phunwareinterviewhomework.model;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class generated from http://www.jsonschema2pojo.org/ from JSON provided at
 * https://s3.amazonaws.com/jon-hancock-phunware/nflapi-static.json
 */
public class Venue {

    @Expose
    private String zip;
    @Expose
    private String phone;
    @SerializedName("ticket_link")
    @Expose
    private String ticketLink;
    @Expose
    private String state;
    @Expose
    private Integer pcode;
    @Expose
    private String city;
    @Expose
    private Long id;
    @Expose
    private String tollfreephone;
    @Expose
    private List<Schedule> schedule = new ArrayList<Schedule>();
    @Expose
    private String address;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @Expose
    private String description;
    @Expose
    private String name;
    @Expose
    private Double longitude;
    @Expose
    private Double latitude;

    /**
     *
     * @return
     * The zip
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     * The zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The ticketLink
     */
    public String getTicketLink() {
        return ticketLink;
    }

    /**
     *
     * @param ticketLink
     * The ticket_link
     */
    public void setTicketLink(String ticketLink) {
        this.ticketLink = ticketLink;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The pcode
     */
    public Integer getPcode() {
        return pcode;
    }

    /**
     *
     * @param pcode
     * The pcode
     */
    public void setPcode(Integer pcode) {
        this.pcode = pcode;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The tollfreephone
     */
    public String getTollfreephone() {
        return tollfreephone;
    }

    /**
     *
     * @param tollfreephone
     * The tollfreephone
     */
    public void setTollfreephone(String tollfreephone) {
        this.tollfreephone = tollfreephone;
    }

    /**
     *
     * @return
     * The schedule
     */
    public List<Schedule> getSchedule() {
        return schedule;
    }

    /**
     *
     * @param schedule
     * The schedule
     */
    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     *
     * @param imageUrl
     * The image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
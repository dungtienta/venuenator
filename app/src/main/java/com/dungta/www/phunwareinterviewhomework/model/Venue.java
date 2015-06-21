package com.dungta.www.phunwareinterviewhomework.model;

/**
 * Model class taken from jonofhancock's gist.
 * Modified to use with retrofit library
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venue {
    /**
     * TODO: use fieldnamingstrategy instead of @serialized name
     * for cleaner code in future
     */
    // Core fields
    @SerializedName(value="id")
    private long mId;
    @SerializedName(value="pcode")
    private int mPcode;
    @SerializedName(value="latitude")
    private double mLatitude;
    @SerializedName(value="longitude")
    private double mLongitude;
    @SerializedName(value="name")
    private String mName;
    @SerializedName(value="address")
    private String mAddress;
    @SerializedName(value="city")
    private String mCity;
    @SerializedName(value="state")
    private String mState;
    @SerializedName(value="zip")
    private String mZip;
    @SerializedName(value="phone")
    private String mPhone;

    // Super Bowl venue fields
    //@SerializedName(value="tollfreephone")
    private String mTollFreePhone;
    @SerializedName(value="url")
    private String mUrl;
    @SerializedName(value="description")
    private String mDescription;
    //@SerializedName(value="ticket_link")
    private String mTicketLink;
    @SerializedName(value="image_url")
    private String mImageUrl;
    @SerializedName(value="schedule")
    @Expose
    private List<ScheduleItem> mSchedule;

    // computed fields
    private float mDistance;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getTicketLink() {
        return mTicketLink;
    }

    public void setTicketLink(String ticketLink) {
        mTicketLink = ticketLink;
    }

    public List<ScheduleItem> getSchedule() {
        return mSchedule;
    }

    public void setSchedule(List<ScheduleItem> schedule) {
        mSchedule = schedule;
    }

    public String getTollFreePhone() {
        return mTollFreePhone;
    }

    public void setTollFreePhone(String tollFreePhone) {
        mTollFreePhone = tollFreePhone;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Venue() {

    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getZip() {
        return mZip;
    }

    public void setZip(String zip) {
        mZip = zip;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public float getDistance() {
        return mDistance;
    }

    public void setDistance(float distance) {
        mDistance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Venue && ((Venue) o).getId() == mId) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(mId).hashCode();
    }

    public int getPcode() {
        return mPcode;
    }

    public void setPcode(int pcode) {
        mPcode = pcode;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

}

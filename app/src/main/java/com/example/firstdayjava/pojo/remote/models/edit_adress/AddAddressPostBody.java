package com.example.firstdayjava.pojo.remote.models.edit_adress;

public class AddAddressPostBody {
    String userCode;
    String streetName;
    String latitude;
    String longitude;
    String building;
    String addressDetails;
    String floor;
    String apartment;
    String mobile;
    String landlinenumber;
    String landmark;

    //Auto Values
    String locationtype = "null";
    String cityCode = "Blr";
    String countryCode = "+91";
    String postalCode = "";
    String provinceCode = "ka";
    String regionCode = "jayanaagr";


    public AddAddressPostBody(String userCode, String streetName, String addressDetails, String latitude, String longitude, String building, String floor, String apartment, String mobile, String landlinenumber, String landmark) {
        this.userCode = userCode;
        this.streetName = streetName;
        this.addressDetails = addressDetails;
        this.latitude = latitude;
        this.longitude = longitude;
        this.building = building;
        this.floor = floor;
        this.apartment = apartment;
        this.mobile = mobile;
        this.landlinenumber = landlinenumber;
        this.landmark = landmark;
        this.locationtype = locationtype;
    }

    public AddAddressPostBody() {
    }


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getLocationtype() {
        return locationtype;
    }

    public void setLocationtype(String locationtype) {
        this.locationtype = locationtype;
    }

    public String getLandlinenumber() {
        return landlinenumber;
    }

    public void setLandlinenumber(String landlinenumber) {
        this.landlinenumber = landlinenumber;
    }
}

package com.example.firstdayjava.pojo.remote.models.edit_adress;

public class UpdateAddressPostBody extends AddAddressPostBody {
    String addressID;

    public UpdateAddressPostBody() {
    }

    public UpdateAddressPostBody(String addressID, AddAddressPostBody postBody) {
        super(postBody.getUserCode(), postBody.getStreetName(), postBody.getAddressDetails(),
                postBody.getLatitude(), postBody.getLongitude(), postBody.getBuilding(),
                postBody.getFloor(), postBody.getApartment(), postBody.getMobile(),
                postBody.getLandlinenumber(), postBody.getLandmark());
        this.addressID = addressID;
    }

    public UpdateAddressPostBody(String userCode, String streetName, String addressDetails,
                                 String latitude, String longitude, String building,
                                 String floor, String apartment, String mobile, String addressID,
                                 String landlinenumber, String landmark) {
        super(userCode, streetName, addressDetails, latitude, longitude, building, floor, apartment, mobile, landlinenumber, landmark);
        this.addressID = addressID;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }
}

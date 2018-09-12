package ar.edu.itba.paw2018b.models;

import java.util.List;

public class TransactionRequest {
    private String userDNI;
    private int screeningID;
    private List<String> seatNames;
    private List<String> foodDetails;

    public TransactionRequest(){}

    public TransactionRequest(String userDNI, int screeningID, List<String> seatNames, List<String> foodDetails) {
        this.userDNI = userDNI;
        this.screeningID = screeningID;
        this.seatNames = seatNames;
        this.foodDetails = foodDetails;
    }

    public String getUserDNI() {
        return userDNI;
    }

    public void setUserDNI(String userDNI) {
        this.userDNI = userDNI;
    }

    public int getScreeningID() {
        return screeningID;
    }

    public void setScreeningID(int screeningID) {
        this.screeningID = screeningID;
    }

    public List<String> getSeatNames() {
        return seatNames;
    }

    public void setSeatNames(List<String> seatNames) {
        this.seatNames = seatNames;
    }

    public List<String> getFoodDetails() {
        return foodDetails;
    }

    public void setFoodDetails(List<String> foodDetails) {
        this.foodDetails = foodDetails;
    }
}

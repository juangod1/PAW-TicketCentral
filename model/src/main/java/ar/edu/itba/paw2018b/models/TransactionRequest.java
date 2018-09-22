package ar.edu.itba.paw2018b.models;

import java.util.List;

public class TransactionRequest {
    private int userId;
    private int screeningID;
    private List<String> seatNames;
    private List<String> foodDetails;

    public TransactionRequest(){}

    public TransactionRequest(int userId, int screeningID, List<String> seatNames, List<String> foodDetails) {
        this.userId = userId;
        this.screeningID = screeningID;
        this.seatNames = seatNames;
        this.foodDetails = foodDetails;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(String userDNI) {
        this.userId = userId;
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

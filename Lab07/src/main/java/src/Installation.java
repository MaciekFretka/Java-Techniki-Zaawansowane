package src;

public class Installation {
    public Installation(int ROUTERID, String address, String type, int userID) {
        this.ROUTERID = ROUTERID;
        this.address = address;
        this.type = type;
        this.userID = userID;
    }

    public int getROUTERID() {
        return ROUTERID;
    }

    public void setROUTERID(int ROUTERID) {
        this.ROUTERID = ROUTERID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    private int ROUTERID;
    private String address;
    private String type;
    private int userID;
}

package Classes;

public class Coordinates {
    public Integer longitude;
    public Integer latitude;
    public Integer height;

    //Constructor
    public Coordinates(Integer longitude, Integer latitude, Integer height){
        this.longitude = longitude;
        this.height = height;
        this.latitude = latitude;
    }

    //Getters
    public Integer getHeight() {
        return this.height;
    }

    public Integer getLatitude() {
        return this.latitude;
    }

    public Integer getLongitude() {
        return this.longitude;
    }
}
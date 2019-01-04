package com.example.yt.myapplication;

public class TodayWeather {
    private String city;
    private String updatetime;
    private String wendu;
    private String fengli;
    private String high;
    private String low;
    private String type;
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }


    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString(){
        return "today-weather{"+
                "city='"+city+'\''+
                ",updatetime='"+updatetime+'\''+
                "wendu='"+wendu+'\''+
                "fengli='"+fengli+'\''+
                "high='"+high+'\''+
                "low='"+low+'\''+
                "type='"+type+'\''+
                '}';
    }
}

package com.example.yt.myapplication;

public class TodayWeather {
    private String city;
    private String updatetime;
    private String wendu;
    private String fengli;
    private String high;
    private String low;
    private String type;

    private String data1;
    private String low1;
    private String high1;
    private String type1;
    private String fengli1;

    private String data2;

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getLow1() {
        return low1;
    }

    public void setLow1(String low1) {
        this.low1 = low1;
    }

    public String getHigh1() {
        return high1;
    }

    public void setHigh1(String high1) {
        this.high1 = high1;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getFengli1() {
        return fengli1;
    }

    public void setFengli1(String fengli1) {
        this.fengli1 = fengli1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getLow2() {
        return low2;
    }

    public void setLow2(String low2) {
        this.low2 = low2;
    }

    public String getHigh2() {
        return high2;
    }

    public void setHigh2(String high2) {
        this.high2 = high2;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getFengli2() {
        return fengli2;
    }

    public void setFengli2(String fengli2) {
        this.fengli2 = fengli2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getLow3() {
        return low3;
    }

    public void setLow3(String low3) {
        this.low3 = low3;
    }

    public String getHigh3() {
        return high3;
    }

    public void setHigh3(String high3) {
        this.high3 = high3;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getFengli3() {
        return fengli3;
    }

    public void setFengli3(String fengli3) {
        this.fengli3 = fengli3;
    }

    private String low2;
    private String high2;
    private String type2;
    private String fengli2;

    private String data3;
    private String low3;
    private String high3;
    private String type3;
    private String fengli3;
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

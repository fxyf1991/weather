package com.test.weather.model;

/**
 * Created by LT on 2016/10/19.
 */

public class District {
    private int id;
    private String districtName;
    private String districtCode;
    private int cityId;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDistrictName(){
        return districtName;
    }

    public void setDistrictName(String distinctName){
        this.districtName = distinctName;
    }

    public String getDistrictCode(){
        return districtCode;
    }

    public void setDistrictCode(String districtCode){
        this.districtCode = districtCode;
    }

    public int getCityId(){
        return cityId;
    }

    public void setCityId(int cityId){
        this.cityId = cityId;
    }
}

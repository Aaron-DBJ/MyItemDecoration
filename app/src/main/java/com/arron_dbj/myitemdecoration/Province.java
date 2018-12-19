package com.arron_dbj.myitemdecoration;

import android.graphics.Bitmap;

public class Province {
    private int provinceId;
    private String provinceName;
    //recyclerView中每个itemview的位置，也就是城市的位置序号
    private int cityPosition;
    private int provinceLength;
    private Bitmap provinceBitmap;
    private String provinceBackground;

    public Province(int provinceId, String provinceName) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
    }

    public Bitmap getProvinceBitmap() {
        return provinceBitmap;
    }

    public void setProvinceBitmap(Bitmap provinceBitmap) {
        this.provinceBitmap = provinceBitmap;
    }

    public String getProvinceBackground() {
        return provinceBackground;
    }

    public void setProvinceBackground(String provinceBackground) {
        this.provinceBackground = provinceBackground;
    }


    public boolean isFirstCityInProvince(){
        if(cityPosition == 0){
            return true;
        }
        return false;
    }

    public boolean isLastCityInProvince(){
        if (cityPosition == provinceLength - 1 && cityPosition >=0){
            return true;
        }
        return false;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getCityPosition() {
        return cityPosition;
    }

    public void setCityPosition(int position) {
        this.cityPosition = position;
    }

    public int getProvinceLength() {
        return provinceLength;
    }

    public void setProvinceLength(int provinceLength) {
        this.provinceLength = provinceLength;
    }
}

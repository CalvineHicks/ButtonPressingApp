package com.medical.anschutz.cu.buttonpressingapp.model.statistics;

import java.io.Serializable;

public class ClickAttempt implements Serializable {
    private float clickStartLocationY;
    private float clickStartLocationX;
    private float clickEndLocationY;
    private float clickEndLocationX;
    private double distanceFromSuccessCenter;
    private float pressure;
    private long timeToComplete = 0;
    private float fingerFootprint;
    private long clickStart = 0;
    private String clickType = "Screen";

    public long getTimeToComplete() {
        return timeToComplete;
    }

    public String getTimeToCompleteFormatted() {
        long second = (timeToComplete / 1000) % 60;
        long minute = (timeToComplete / (1000 * 60)) % 60;
        long hour = (timeToComplete / (1000 * 60 * 60)) % 24;

        String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, timeToComplete);
        return time;
    }

    public void setTimeToComplete(long timeToComplete) {
        this.timeToComplete = timeToComplete;
    }


    public float getClickStartLocationY() {
        return clickStartLocationY;
    }

    public void setClickStartLocationY(float clickStartLocationY) {
        this.clickStartLocationY = clickStartLocationY;
    }

    public float getClickStartLocationX() {
        return clickStartLocationX;
    }

    public void setClickStartLocationX(float clickStartLocationX) {
        this.clickStartLocationX = clickStartLocationX;
    }

    public float getClickEndLocationY() {
        return clickEndLocationY;
    }

    public void setClickEndLocationY(float clickEndLocationY) {
        this.clickEndLocationY = clickEndLocationY;
    }

    public float getClickEndLocationX() {
        return clickEndLocationX;
    }

    public void setClickEndLocationX(float clickEndLocationX) {
        this.clickEndLocationX = clickEndLocationX;
    }

    public double getDistanceFromSuccessCenter() {
        return distanceFromSuccessCenter;
    }

    public void setDistanceFromSuccessCenter(double distanceFromSuccessCenter) {
        this.distanceFromSuccessCenter = distanceFromSuccessCenter;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getFingerFootprint() {
        return fingerFootprint;
    }

    public void setFingerFootprint(float fingerFootprint) {
        this.fingerFootprint = fingerFootprint;
    }

    public long getClickStart() {
        return clickStart;
    }

    public void setClickStart(long clickStart) {
        this.clickStart = clickStart;
    }


    public String getClickType() {
        return clickType;
    }

    public void setClickType(String clickType) {
        this.clickType = clickType;
    }
}
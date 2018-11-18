package com.medical.anschutz.cu.buttonpressingapp.model.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScreenStatistics implements Serializable {

    private int screenNum;
    private int failures = 0;
    private boolean failureLimitReached = false;
    private List<ClickAttempt> clickAttempts = null;
    private long timeToComplete = 0;

    private double successXLocation = 0;
    private double successYLocation = 0;

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


    public ScreenStatistics(int screenNum) {
        this.screenNum = screenNum;
    }

    public int getScreenNum() {
        return screenNum;
    }

    public void setScreenNum(int screenNum) {
        this.screenNum = screenNum;
    }

    public int incrementFailues() {
        this.failures++;
        return failures;
    }

    public Integer getFailures() {
        return failures;
    }

    public boolean isFailureLimitReached() {
        return failureLimitReached;
    }

    public void setFailureLimitReached(boolean failureLimitReached) {
        this.failureLimitReached = failureLimitReached;
    }
    public ClickAttempt addClickAttempt(float startX, float startY){
        ClickAttempt click = new ClickAttempt();
        if(null == this.clickAttempts){
            this.clickAttempts = new ArrayList<>();
        }
        click.setClickStartLocationX(startX);
        click.setClickStartLocationY(startY);
        this.clickAttempts.add(click);
        return click;
    }

    public List<ClickAttempt> getClickAttempts(){
        return this.clickAttempts;
    }

    public double getSuccessXLocation() {
        return successXLocation;
    }

    public void setSuccessXLocation(double successXLocation) {
        this.successXLocation = successXLocation;
    }

    public double getSuccessYLocation() {
        return successYLocation;
    }

    public void setSuccessYLocation(double successYLocation) {
        this.successYLocation = successYLocation;
    }
}

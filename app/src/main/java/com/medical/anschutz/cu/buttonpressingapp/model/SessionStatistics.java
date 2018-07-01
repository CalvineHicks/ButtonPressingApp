package com.medical.anschutz.cu.buttonpressingapp.model;

import java.io.Serializable;

public class SessionStatistics implements Serializable{

    private long timeToComplete = 0;

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
}

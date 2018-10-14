package com.medical.anschutz.cu.buttonpressingapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SessionStatistics implements Serializable{

    private long timeToComplete = 0;
    private String sessionID;

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

    public String getSessionID() {
        return sessionID;
    }


    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public void setTimeToComplete(long timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    private List<ScreenStatistics> screenStatistics = null;

    public ScreenStatistics addScreen(int screenNum){
        if(null == this.screenStatistics){
            this.screenStatistics = new LinkedList<>();
        }
        ScreenStatistics screenStat = new ScreenStatistics(screenNum);
        this.screenStatistics.add(screenNum, screenStat);
        return screenStat;
    }

    public List<ScreenStatistics> getScreenStatistics(){
        return this.screenStatistics;
    }

    public List<List<String>> generateCSVReport(){
        List<List<String>> results = new ArrayList<List<String>>();
        List<String> headers = Arrays.asList("Session ID","Screen Number","Time To Complete Screen","Attempt #",
                "Distance From Success Center", "Pressure", "Finger Footprint");
        results.add(headers);
        for(ScreenStatistics s : screenStatistics){
            for(int i = 0; i < s.getClickAttempts().size(); i++){
                ScreenStatistics.ClickAttempt c = s.getClickAttempts().get(i);
                List<String> screenResult = new ArrayList<String>();
                screenResult.add(sessionID);
                screenResult.add(Integer.toString(s.screenNum));
                screenResult.add(s.getTimeToCompleteFormatted());
                screenResult.add(Integer.toString(i));
                screenResult.add(Double.toString(c.distanceFromSuccessCenter));
                screenResult.add(Float.toString(c.pressure));
                screenResult.add(Float.toString(c.fingerFootprint));
                results.add(screenResult);
            }
        }
        return results;
    }

    public class ScreenStatistics implements Serializable{

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

        public class ClickAttempt implements Serializable{
            private float clickStartLocationY;
            private float clickStartLocationX;
            private float clickEndLocationY;
            private float clickEndLocationX;
            private double distanceFromSuccessCenter;
            private float pressure;
            private long timeToComplete = 0;
            private float fingerFootprint;

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
        }
    }
}

package com.medical.anschutz.cu.buttonpressingapp.model.statistics;

import java.io.Serializable;
import java.util.ArrayList;
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

    public List<String[]> generateCSVReportArrays(){
        List<String[]> results = new ArrayList<>();
        String[] headers = {"Session ID","Screen Number","Total Session Time","Time To Complete Screen","Attempt #",
                "Distance From Success Center", "Pressure", "Finger Footprint","Click Start X", "Click Start Y", "Click End X", "Click End Y", "Click Duration"};
        results.add(headers);
        for(ScreenStatistics s : screenStatistics){
            for(int i = 0; i < s.getClickAttempts().size(); i++){
                ClickAttempt c = s.getClickAttempts().get(i);
                String[] screenResult = new String[headers.length];
                screenResult[0] = sessionID;
                screenResult[1] = Integer.toString(s.getScreenNum() + 1);
                screenResult[2] = getTimeToCompleteFormatted();
                screenResult[3] = s.getTimeToCompleteFormatted();
                screenResult[4] = Integer.toString(i + 1);
                screenResult[5] = Double.toString(c.getDistanceFromSuccessCenter());
                screenResult[6] = Float.toString(c.getPressure());
                screenResult[7] = Float.toString(c.getFingerFootprint());
                screenResult[8] = Float.toString(c.getClickStartLocationX());
                screenResult[9] = Float.toString(c.getClickStartLocationY());
                screenResult[10] = Float.toString(c.getClickEndLocationX());
                screenResult[11] = Float.toString(c.getClickEndLocationY());
                screenResult[12] = c.getTimeToCompleteFormatted();
                results.add(screenResult);
            }
        }
        return results;
    }


}

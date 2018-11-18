package com.medical.anschutz.cu.buttonpressingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medical.anschutz.cu.buttonpressingapp.R;
import com.medical.anschutz.cu.buttonpressingapp.model.statistics.ClickAttempt;
import com.medical.anschutz.cu.buttonpressingapp.model.statistics.ScreenStatistics;
import com.medical.anschutz.cu.buttonpressingapp.model.statistics.SessionStatistics;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

public class SessionComplete extends AppCompatActivity {

    private SessionStatistics stats = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_complete);
        this.stats = (SessionStatistics) getIntent().getSerializableExtra("SessionStatistics");

        TextView sessionID = (TextView) findViewById(R.id.sessionID);
        sessionID.setText(sessionID.getText() + stats.getSessionID());

        TextView totalTime = (TextView) findViewById(R.id.totalTime);
        totalTime.setText(totalTime.getText() + stats.getTimeToCompleteFormatted());

        LinearLayout linearLayout = findViewById(R.id.screenReport);
        if(null != stats.getScreenStatistics()) {
            for (ScreenStatistics screenStats : stats.getScreenStatistics()) {
                TextView t = new TextView(this);
                String screenReport = "Screen #" + screenStats.getScreenNum();
                screenReport += "\n Time To Complete : " + screenStats.getTimeToCompleteFormatted();
                screenReport += "\n Number of Failures : " + screenStats.getFailures();
                int i = 0;
                if(null != screenStats.getClickAttempts() && screenStats.getClickAttempts().size() > 0) {
                    for (ClickAttempt clickAttempt : screenStats.getClickAttempts()) {
                        i++;
                        screenReport += "\n Click#" + i;
                        screenReport += "\n\t Click Duration : " + clickAttempt.getTimeToCompleteFormatted();
                        screenReport += "\n\t Pressure : " + clickAttempt.getPressure();
                        screenReport += "\n\t Footprint : " + clickAttempt.getFingerFootprint();
                        screenReport += "\n\t Distance From Success Center : " + clickAttempt.getDistanceFromSuccessCenter();
                    }
                    t.setText(screenReport);
                    linearLayout.addView(t);
                }
                else{
                    t.setText("No Click Attempts Made");
                }
            }
        }
        else{
            TextView t = new TextView(this);
            t.setText("No Statistics Collected");
        }
    }

    public void downloadClick(View v) {
    //get path to reports folder
        CSVWriter writer = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/ButtonPressingApp/reports");
        //if the reports dir does not exist, create it
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            writer = new CSVWriter(new FileWriter(directory.getAbsolutePath() + "/Session_" + stats.getSessionID() + ".csv"));
            writer.writeAll(stats.generateCSVReportArrays());
            writer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doneClick(View v){
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
    }

}


package com.medical.anschutz.cu.buttonpressingapp.activities;

import android.app.ActionBar;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.medical.anschutz.cu.buttonpressingapp.R;
import com.medical.anschutz.cu.buttonpressingapp.model.ScreenConfig;
import com.medical.anschutz.cu.buttonpressingapp.model.SessionStatistics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.screenReport);
        for(SessionStatistics.ScreenStatistics screenStats : stats.getScreenStatistics()){
            TextView t = new TextView(this);
            String screenReport = "Screen #"+screenStats.getScreenNum();
            screenReport+="\n Time To Complete : "+screenStats.getTimeToCompleteFormatted();
            screenReport+="\n Number of Failures : "+screenStats.getFailures();
            int i = 0;
            for(SessionStatistics.ScreenStatistics.ClickAttempt clickAttempt: screenStats.getClickAttempts()){
                i++;
                screenReport += "\n Click#"+i;
                screenReport+="\n\t Click Duration : "+clickAttempt.getTimeToCompleteFormatted();
                screenReport += "\n\t Pressure : "+clickAttempt.getPressure();
            }
            t.setText(screenReport);
            linearLayout.addView(t);
        }
    }

    public void downloadClick(View v) {
    //get path to reports folder
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/ButtonPressingApp/reports");
        //if the reports dir does not exist, create it
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //convert our report to a JSON string for now
        String report = new GsonBuilder().create().toJson(stats, SessionStatistics.class);
        System.out.println(report);
        //create the file and close output stream
        File file = new File(directory, "sessionReport.txt");
        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(report.getBytes());
            stream.close();
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


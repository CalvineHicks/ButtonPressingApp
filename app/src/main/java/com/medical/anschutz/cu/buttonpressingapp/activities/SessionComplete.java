package com.medical.anschutz.cu.buttonpressingapp.activities;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.medical.anschutz.cu.buttonpressingapp.R;
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

        TextView textView = (TextView) findViewById(R.id.totalTime);
        textView.setText(textView.getText() + stats.getTimeToCompleteFormatted());
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

package com.medical.anschutz.cu.buttonpressingapp.activities;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

        //eventually this will download the file to /downloads folder as if it were donloaded from the internet
        //right now when you go to open it says file not found :(
        /*File dir = new File("//sdcard//Download//");
        dir.mkdirs();

        File file = new File(dir, "SessionResults.txt");

        try
        {
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append("this is some junk");

            myOutWriter.close();

            fOut.flush();
            fOut.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        file.mkdirs();

        DownloadManager downloadManager = (DownloadManager) this.getSystemService(DOWNLOAD_SERVICE);

        downloadManager.addCompletedDownload(file.getName(), file.getName(), true, "text/plain",file.getAbsolutePath(),file.length(),true);*/
    }

    public void doneClick(View v){
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
    }

}

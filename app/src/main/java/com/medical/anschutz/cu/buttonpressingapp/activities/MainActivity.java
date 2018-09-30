package com.medical.anschutz.cu.buttonpressingapp.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.medical.anschutz.cu.buttonpressingapp.R;
import com.medical.anschutz.cu.buttonpressingapp.model.SessionConfig;
import com.medical.anschutz.cu.buttonpressingapp.services.dataTransformers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSession(View view){
        Intent intent = new Intent(view.getContext(), Session.class);
        String confString = null;
        try {
            confString = getConfigFileString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SessionConfig config = null;
        if(confString != null)
            config = SessionConfig.fromJson(confString);

        intent.putExtra("config", config);
        startActivity(intent);
        finish();
    }

    private String getConfigFileString() throws IOException {
        //get config directory on sdcard
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/ButtonPressingApp");
        //if our app directory does not exist, create it
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //get the config file from the directory
        File file = new File(directory, "session_config.json");
        InputStream is = null;
        Writer writer = new StringWriter();
        //if the file does not exist fall back to the internal one
        if (file.exists()) {
            try {
                is = new FileInputStream(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            is = getResources().openRawResource(R.raw.session_config);
        }

        //read the file into an internal var
        char[] buffer = new char[1024];
        try {
            Reader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        String jsonString = writer.toString();
        return jsonString;
    }
}

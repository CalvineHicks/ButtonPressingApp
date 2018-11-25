package com.medical.anschutz.cu.buttonpressingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.medical.anschutz.cu.buttonpressingapp.R;
import com.medical.anschutz.cu.buttonpressingapp.model.SessionConfig;
import com.medical.anschutz.cu.buttonpressingapp.model.defaults.GlobalDefaults;

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
import java.util.ArrayList;
import java.util.List;

public class SessionSelect extends AppCompatActivity {
    RadioGroup radioGroup = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_select);

        radioGroup = findViewById(R.id.checkboxGroup);


        List<String> filenames = getConfigList();
        for(String filename : filenames){
            RadioButton radio = new RadioButton(this);
            radio.setMinimumWidth(radioGroup.getWidth());
            radio.setText(filename);
            radio.setBottom(5);
            radioGroup.addView(radio);
        }
        //get list of filenames from directory
        //add filenames to list for display
        //load selected config and begin session
    }

    public void startSession(View v){
        Intent intent = new Intent(v.getContext(), Session.class);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int idx = radioGroup.indexOfChild(radioButton);
        RadioButton r = (RadioButton) radioGroup.getChildAt(idx);
        String filename = r.getText().toString();

        String confString = null;
        try {
            confString = getConfigFileString(filename);
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

    private String getConfigFileString(String filename) throws IOException {
        //get config directory on sdcard
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + GlobalDefaults.SCREEN_CONFIG_DIR);
        //if our app directory does not exist, create it
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //get the config file from the directory
        File file = new File(directory, filename);
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

    private List<String> getConfigList(){
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + GlobalDefaults.SCREEN_CONFIG_DIR);
        List<String> filenames = new ArrayList<>();
        if (!directory.exists()) {
            return filenames;
        }
        //get the config file from the directory
        File[] files = directory.listFiles();
        for(int i = 0; i < files.length; i++){
            filenames.add(files[i].getName());
        }
        return filenames;
    }

}

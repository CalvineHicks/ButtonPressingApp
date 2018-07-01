package com.medical.anschutz.cu.buttonpressingapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.medical.anschutz.cu.buttonpressingapp.R;
import com.medical.anschutz.cu.buttonpressingapp.model.ButtonConfig;
import com.medical.anschutz.cu.buttonpressingapp.model.ExtendedButton;
import com.medical.anschutz.cu.buttonpressingapp.model.ScreenConfig;
import com.medical.anschutz.cu.buttonpressingapp.model.SessionConfig;
import com.medical.anschutz.cu.buttonpressingapp.model.SessionStatistics;
import com.medical.anschutz.cu.buttonpressingapp.model.Defaults;

import java.util.List;

public class Session extends AppCompatActivity {

    private int currentScreenNum = 0;
    private SessionConfig config = null;
    private SessionStatistics stats = new SessionStatistics();
    private long startTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        this.config = (SessionConfig) getIntent().getSerializableExtra("config");

        if(this.config == null){
            System.out.println("config is null");
            this.config = new SessionConfig();
        }
        else{
            System.out.println("config loaded successfully");
        }

        //iterate through screens one at a time, start at first screen
        generateScreen(0);
        this.startTime = System.currentTimeMillis();

    }

    private void generateScreen(int screenNum){
        ScreenConfig screenConfig = config.getScreenConfigs().get(screenNum);
        TableLayout buttonContainer = findViewById(R.id.buttonContainer);
        buttonContainer.removeAllViews();

        //add button rows and butttons
        List<ScreenConfig.RowConfig> rowConfigs = screenConfig.getRowConfigs();
        for(ScreenConfig.RowConfig rowConfig : rowConfigs){
            TableRow buttonRow = new TableRow(this);
            buttonRow.setMinimumHeight(screenConfig.getButtonRowMinHeight());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            //set's margin for a set of columns
            lp.setMargins(0, 0, 0, 0);
            for(ButtonConfig buttonConfig : rowConfig.getButtonConfigs()){
                final ExtendedButton button = new ExtendedButton(this, buttonRow, buttonConfig);
                button.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v) {
                        if(button.eventType.equals("success")){
                            successClick(v);
                        }
                        else if(button.eventType.equals("failure")){
                            failureClick(v);
                        }
                    }
                });
                buttonRow.addView(button);

            }
            buttonContainer.addView(buttonRow);
        }
    }
    public void successClick(View view){
        if(config.getScreenConfigs().size() > (currentScreenNum + 1)) {
            currentScreenNum += 1;
            generateScreen(currentScreenNum);
        }
        else {
            Intent myIntent = new Intent(this, SessionComplete.class);
            this.stats.setTimeToComplete(System.currentTimeMillis() - this.startTime);
            myIntent.putExtra("SessionStatistics", stats);
            this.startActivity(myIntent);
        }
    }

    public void failureClick(View view){
        if(config.getProgressionRule().equals(Defaults.PROGRESSION_RULE.PROGRESS_ON_BUTTON_PRESS)){
            successClick(view);
        }
    }

           //TODO handle any screen press
}

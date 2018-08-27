package com.medical.anschutz.cu.buttonpressingapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
    private long screenStartTime = 0;
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
        final SessionStatistics.ScreenStatistics screenStats = this.stats.addScreen(screenNum);

        //add button rows and butttons
        List<ScreenConfig.RowConfig> rowConfigs = screenConfig.getRowConfigs();
        for(ScreenConfig.RowConfig rowConfig : rowConfigs){
            LinearLayout buttonRow = new LinearLayout(this);
            //sets horix orientation
            buttonRow.setOrientation(LinearLayout.HORIZONTAL);
            buttonRow.setMinimumHeight(rowConfig.getButtonRowMinHeight());
            for(ButtonConfig buttonConfig : rowConfig.getButtonConfigs()){
                final ExtendedButton button = new ExtendedButton(this, buttonRow, buttonConfig);
               /* button.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v) {

                    }
                });*/
                button.setOnTouchListener(new View.OnTouchListener() {
                    Rect rect = null;
                    SessionStatistics.ScreenStatistics.ClickAttempt click = null;
                    long clickStartTime = 0;
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                // PRESSED
                                rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                                click = screenStats.addClickAttempt(event.getX(), event.getY());
                                click.setPressure(event.getPressure());
                                clickStartTime = System.currentTimeMillis();
                                return false; // if you want to handle the touch event
                            case MotionEvent.ACTION_UP:
                                // RELEASED
                                click.setClickEndLocationX(event.getX());
                                click.setClickEndLocationY(event.getY());
                                click.setTimeToComplete(System.currentTimeMillis() - clickStartTime);
                                if (null != rect && !rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {

                                } else {
                                    v.performClick();
                                    if (button.eventType.equals("success")) {
                                        successClick(v, screenStats);
                                        return false;
                                    } else if (button.eventType.equals("failure")) {
                                        failureClick(v, screenStats);
                                        return false;
                                    }
                                }

                        }
                        return false;
                    }
                });
                buttonRow.addView(button);

            }
            //TODO : figure out scrolling (do we need to account for scrolling???
            if(rowConfig.isScrollEnabled()) {
                ScrollView scrollView = new ScrollView(this);
                scrollView.setFillViewport(true);
                scrollView.addView(buttonRow);
                scrollView.setHorizontalScrollBarEnabled(true);
                buttonContainer.addView(scrollView);
            }
            else {
                //TODO: button row is not wrapping content either...
                buttonRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                buttonContainer.addView(buttonRow);
            }
        }
        this.screenStartTime = System.currentTimeMillis();
       // this.recursiveLoopChildren(buttonContainer);
    }

    public void recursiveLoopChildren(ViewGroup parent) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                recursiveLoopChildren((ViewGroup) child);
                child.invalidate();
            } else {
                if (child != null) {
                    child.invalidate();
                }
            }
        }
    }

    public void successClick(View view, SessionStatistics.ScreenStatistics screenStats){
        screenStats.setTimeToComplete(System.currentTimeMillis() - this.screenStartTime);
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
        //TODO : once the success button is clicked OR if we reach the failure overwritre, calculate each click attempts distance from the success.
    }

    public void failureClick(View view, SessionStatistics.ScreenStatistics screenStats){
        screenStats.incrementFailues();
        if(config.getProgressionRule().equals(Defaults.PROGRESSION_RULE.PROGRESS_ON_BUTTON_PRESS)){
            screenStats.setFailureLimitReached(true);
            successClick(view, screenStats);
        }
    }

           //TODO handle any screen press
}

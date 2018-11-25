package com.medical.anschutz.cu.buttonpressingapp.activities;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

import com.medical.anschutz.cu.buttonpressingapp.R;
import com.medical.anschutz.cu.buttonpressingapp.model.ButtonConfig;
import com.medical.anschutz.cu.buttonpressingapp.model.ButtonPressTracker;
import com.medical.anschutz.cu.buttonpressingapp.model.ExtendedButton;
import com.medical.anschutz.cu.buttonpressingapp.model.ScreenClickListener;
import com.medical.anschutz.cu.buttonpressingapp.model.ScreenConfig;
import com.medical.anschutz.cu.buttonpressingapp.model.SessionConfig;
import com.medical.anschutz.cu.buttonpressingapp.model.statistics.ClickAttempt;
import com.medical.anschutz.cu.buttonpressingapp.model.statistics.ScreenStatistics;
import com.medical.anschutz.cu.buttonpressingapp.model.statistics.SessionStatistics;
import com.medical.anschutz.cu.buttonpressingapp.model.defaults.Defaults;
import com.medical.anschutz.cu.buttonpressingapp.services.UserIDDialogGenerator;

import java.util.List;

import static com.medical.anschutz.cu.buttonpressingapp.model.defaults.Defaults.FAILURE_BUTTON_CLICK_EVENT;
import static com.medical.anschutz.cu.buttonpressingapp.model.defaults.Defaults.SUCCESS_BUTTON_CLICK_EVENT;


@SuppressLint("ClickableViewAccessibility")
public class Session extends AppCompatActivity {

    private int currentScreenNum = 0;
    private SessionConfig config = null;
    private SessionStatistics stats = new SessionStatistics();
    private long startTime = 0;
    private long screenStartTime = 0;
    private String sessionID;
    private UserIDDialogGenerator userIDDialgGenerator = new UserIDDialogGenerator();

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
        userIDDialgGenerator.showGetUserIDDialog(this, "Enter an ID for this session", "Start", "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // start button listener
                String text = userIDDialgGenerator.inputValue.getText().toString();
                startSession(text);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // cancel button listener
                Intent intent = new Intent(Session.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
        );
    }

    private void startSession(String sessionID) {
        //iterate through screens one at a time, start at first screen
        this.sessionID = sessionID;
        this.stats.setSessionID(sessionID);
        this.startTime = System.currentTimeMillis();
        generateScreen(0);
    }

    private void generateScreen(int screenNum){

        ScreenConfig screenConfig = config.getScreenConfigs().get(screenNum);
        TableLayout buttonContainer = findViewById(R.id.buttonContainer);
        buttonContainer.removeAllViews();
        final ScreenStatistics screenStats = this.stats.addScreen(screenNum);
        //add button rows and butttons
        List<ScreenConfig.RowConfig> rowConfigs = screenConfig.getRowConfigs();
        for(ScreenConfig.RowConfig rowConfig : rowConfigs){
            LinearLayout buttonRow = new LinearLayout(this);
            //sets horix orientation
            buttonRow.setOrientation(LinearLayout.HORIZONTAL);
            buttonRow.setMinimumHeight(rowConfig.getButtonRowMinHeight());
            for(ButtonConfig buttonConfig : rowConfig.getButtonConfigs()){
                final ExtendedButton button = new ExtendedButton(this, buttonRow, buttonConfig);
                if (button.eventType.equals(SUCCESS_BUTTON_CLICK_EVENT)) {
                    screenStats.setSuccessXLocation(buttonConfig.getxPosition() + buttonConfig.getWidth() / 2);
                    screenStats.setSuccessYLocation(buttonConfig.getyPosition() + buttonConfig.getHeight() / 2);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(buttonConfig.marginLeft, buttonConfig.marginTop, buttonConfig.marginRight, buttonConfig.marginBottom);
                button.setLayoutParams(params);
                final ButtonPressTracker pressTracker = new ButtonPressTracker(screenStats);
                button.setOnTouchListener(new View.OnTouchListener() {
                    Rect rect = null;

                    ClickAttempt click = null;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                                pressTracker.trackPressDown(v, event, true);
                                return true; // if you want to handle the touch event
                            case MotionEvent.ACTION_UP:
                                // RELEASED
                                pressTracker.trackPressUp(v, event);
                                if (null != rect && !rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {

                                } else {
                                    v.performClick();
                                    if (button.eventType.equals(SUCCESS_BUTTON_CLICK_EVENT)) {
                                        successClick(v, screenStats);
                                        return false;
                                    } else if (button.eventType.equals(FAILURE_BUTTON_CLICK_EVENT)) {
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

        buttonContainer.setOnTouchListener(new ScreenClickListener(stats, screenStats, startTime));
    }

    private void successClick(View view, ScreenStatistics screenStats){
        screenStats.setTimeToComplete(System.currentTimeMillis() - this.screenStartTime);
        for(ClickAttempt attempt : screenStats.getClickAttempts()){
            Double distance = Math.sqrt((Math.pow((screenStats.getSuccessXLocation() - attempt.getClickEndLocationX()), 2) +
                    Math.pow((screenStats.getSuccessYLocation() - attempt.getClickEndLocationY()), 2)));
            attempt.setDistanceFromSuccessCenter(distance);
        }
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

    private void failureClick(View view, ScreenStatistics screenStats){
        screenStats.incrementFailues();
        if(config.getProgressionRule().equals(Defaults.PROGRESSION_RULE.PROGRESS_ON_BUTTON_PRESS)){
            screenStats.setFailureLimitReached(true);
            successClick(view, screenStats);
        }
    }
}
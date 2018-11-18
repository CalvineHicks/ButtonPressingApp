package com.medical.anschutz.cu.buttonpressingapp.model;

import android.view.MotionEvent;
import android.view.View;

import com.medical.anschutz.cu.buttonpressingapp.model.statistics.ClickAttempt;
import com.medical.anschutz.cu.buttonpressingapp.model.statistics.ScreenStatistics;

public class ButtonPressTracker {
    private ScreenStatistics screenStats;
    private long clickStartTime;
    private ClickAttempt click;

    public ButtonPressTracker(ScreenStatistics s) {
        screenStats = s;
    }

    public void trackPressDown (View v, MotionEvent event) {
        click = screenStats.addClickAttempt(event.getX(), event.getY());
        click.setPressure(event.getPressure());
        click.setFingerFootprint(event.getPointerCount() * event.getPressure());
        clickStartTime = System.currentTimeMillis();
    }


    public void trackPressUp (View v, MotionEvent event) {
        click = screenStats.getClickAttempts().get(screenStats.getClickAttempts().size()-1);
        click.setClickEndLocationX(event.getX());
        click.setClickEndLocationY(event.getY());
        click.setTimeToComplete(System.currentTimeMillis() - clickStartTime);
    }
}

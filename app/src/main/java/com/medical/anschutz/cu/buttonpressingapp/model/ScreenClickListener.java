package com.medical.anschutz.cu.buttonpressingapp.model;

import android.content.Intent;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.medical.anschutz.cu.buttonpressingapp.activities.SessionComplete;
import com.medical.anschutz.cu.buttonpressingapp.model.statistics.ClickAttempt;
import com.medical.anschutz.cu.buttonpressingapp.model.statistics.ScreenStatistics;
import com.medical.anschutz.cu.buttonpressingapp.model.statistics.SessionStatistics;

public class ScreenClickListener implements View.OnTouchListener {
    private boolean hitLeftCorner = false;
    private int[] leftCorner;
    private int[] rightCorner;
    private ScreenStatistics screenStats;
    private SessionStatistics stats;
    private long sessionStartTime;

    public ScreenClickListener(SessionStatistics stats, ScreenStatistics screenStatistics, long sessionStartTime){
        this.screenStats = screenStatistics;
        this.stats = stats;
        this.sessionStartTime = sessionStartTime;
    }

    View v;

    @Override
    public boolean onTouch(View v, MotionEvent event){
        this.v = v;
        Rect r = new Rect();
        this.v.getGlobalVisibleRect(r);
        this.leftCorner = new int[]{r.left+50, r.bottom-50};
        rightCorner = new int[]{r.right-50, r.bottom-50};
        ClickAttempt click = null;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("screen clicked");
                click = screenStats.addClickAttempt(event.getX(), event.getY());
                click.setPressure(event.getPressure());
                click.setFingerFootprint(event.getPointerCount() * event.getPressure());
                return true;
            case MotionEvent.ACTION_UP:
                //set the last clicks location on action up. no matter what action down adds to the end of the list
                screenStats.getClickAttempts().get(screenStats.getClickAttempts().size()-1).setClickEndLocationX(event.getX());
                screenStats.getClickAttempts().get(screenStats.getClickAttempts().size()-1).setClickEndLocationY(event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                if(event.getX() < leftCorner[0] && event.getY() < leftCorner[1]){
                    hitLeftCorner = true;
                }
                else if(hitLeftCorner && event.getX() > rightCorner[0] && event.getY() < rightCorner[1]){
                    screenStats.getClickAttempts().remove(screenStats.getClickAttempts().size()-1);
                    Intent myIntent = new Intent(this.v.getContext(), SessionComplete.class);
                    stats.setTimeToComplete(System.currentTimeMillis() - sessionStartTime);
                    myIntent.putExtra("SessionStatistics", stats);
                    this.v.getContext().startActivity(myIntent);
                }
                return true;
        }
        return false;
    }
}

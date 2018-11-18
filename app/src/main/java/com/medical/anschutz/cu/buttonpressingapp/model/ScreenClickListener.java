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

    //any click events that occur on buttons are handled there and not passed to the screen touch listener
    //this listener accounts for clicks on the screen, outside of the buttons view
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
                click = screenStats.addClickAttempt(event.getX(), event.getY());
                click.setPressure(event.getPressure());
                click.setFingerFootprint(event.getPointerCount() * event.getPressure());
                click.setClickStart(System.currentTimeMillis());
                return true;
            case MotionEvent.ACTION_UP:
                //set the last clicks location on action up. no matter what action down adds to the end of the list0
                //this is removed if the user does a gesture to exit
                if(screenStats.getClickAttempts().size() > 0) {
                    click = screenStats.getClickAttempts().get(screenStats.getClickAttempts().size() - 1);
                    click.setClickEndLocationX(event.getX());
                    click.setClickEndLocationY(event.getY());
                    click.setTimeToComplete(System.currentTimeMillis() - click.getClickStart());
                }
                //if we have an action up that isnt handled by a button click listener then increment the failure count
                screenStats.incrementFailues();
                hitLeftCorner = false;
                return true;
            case MotionEvent.ACTION_MOVE:
                if(event.getX() < leftCorner[0] && event.getY() < leftCorner[1]){
                    hitLeftCorner = true;
                }
                else if(hitLeftCorner && event.getX() > rightCorner[0] && event.getY() < rightCorner[1]){
                    if(screenStats.getClickAttempts().size() > 0) {
                        screenStats.getClickAttempts().remove(screenStats.getClickAttempts().size() - 1);
                    }
                    hitLeftCorner = false;
                    for(ClickAttempt attempt : screenStats.getClickAttempts()){
                        Double distance = Math.sqrt((Math.pow((screenStats.getSuccessXLocation() - attempt.getClickEndLocationX()), 2) +
                                Math.pow((screenStats.getSuccessYLocation() - attempt.getClickEndLocationY()), 2)));
                        attempt.setDistanceFromSuccessCenter(distance);
                    }
                    Intent myIntent = new Intent(this.v.getContext(), SessionComplete.class);
                    stats.setTimeToComplete(System.currentTimeMillis() - sessionStartTime);
                    myIntent.putExtra("SessionStatistics", stats);
                    this.v.getContext().startActivity(myIntent);
                }
                return false;
        }
        return false;
    }
}

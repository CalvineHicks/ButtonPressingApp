package com.medical.anschutz.cu.buttonpressingapp.model;

import android.graphics.Color;

import java.io.Serializable;

public class ButtonConfig implements Serializable{

    private Integer height = Defaults.DEFAULT_BUTTON_HEIGHT;
    private Integer width = Defaults.DEFAULT_BUTTON_WIDTH;
    private Integer xPosition = Defaults.DEFAULT_BUTTON_X_POSITION;
    private Integer yPosition = Defaults.DEFAULT_BUTTON_Y_POSITION;
    private Integer rotation = Defaults.DEFAULT_BUTTON_ROTATION;
    private String  buttonEvent = Defaults.DEFAULT_BUTTON_CLICK_EVENT;

    //Text and Styling
    private String text = Defaults.DEFAULT_BUTTON_TEXT;
    private int backgroundColorR = Defaults.DEFAULT_BUTTON_COLOR_R;
    private int backgroundColorG = Defaults.DEFAULT_BUTTON_COLOR_G;
    private int backgroundColorB = Defaults.DEFAULT_BUTTON_COLOR_B;


    //Clickable Area
    private int areaTop = Defaults.CLICKABLE_AREA_TOP;
    private int areaBottom = Defaults.CLICKABLE_AREA_BOTTOM;
    private int areaLeft = Defaults.CLICKABLE_AREA_LEFT;
    private int areaRight = Defaults.CLICKABLE_AREA_RIGHT;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getxPosition() {
        return xPosition;
    }

    public void setxPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getyPosition() {
        return yPosition;
    }

    public void setyPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getBackgroundColorR() {
        return backgroundColorR;
    }

    public void setBackgroundColorR(int backgroundColorR) {
        this.backgroundColorR = backgroundColorR;
    }

    public int getBackgroundColorG() {
        return backgroundColorG;
    }

    public void setBackgroundColorG(int backgroundColorG) {
        this.backgroundColorG = backgroundColorG;
    }

    public int getBackgroundColorB() {
        return backgroundColorB;
    }

    public void setBackgroundColorB(int backgroundColorB) {
        this.backgroundColorB = backgroundColorB;
    }

    public int getAreaTop() {
        return areaTop;
    }

    public void setAreaTop(int areaTop) {
        this.areaTop = areaTop;
    }

    public int getAreaBottom() {
        return areaBottom;
    }

    public void setAreaBottom(int areaBottom) {
        this.areaBottom = areaBottom;
    }

    public int getAreaLeft() {
        return areaLeft;
    }

    public void setAreaLeft(int areaLeft) {
        this.areaLeft = areaLeft;
    }

    public int getAreaRight() {
        return areaRight;
    }

    public void setAreaRight(int areaRight) {
        this.areaRight = areaRight;
    }

    //Button Events
    public void setButtonEvent(String eventType) { this.buttonEvent = eventType; }
    public String getButtonEvent(){return buttonEvent;}

}

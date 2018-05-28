package com.medical.anschutz.cu.buttonpressingapp.model;

import android.graphics.Color;

public class ButtonConfig {

    private Integer height = Defaults.DEFAULT_BUTTON_HEIGHT;
    private Integer width = Defaults.DEFAULT_BUTTON_WIDTH;
    private Integer xPosition = Defaults.DEFAULT_BUTTON_X_POSITION;
    private Integer yPosition = Defaults.DEFAULT_BUTTON_Y_POSITION;
    private Integer rotation = Defaults.DEFAULT_BUTTON_ROTATION;

    //Text and Styling
    private String text = Defaults.DEFAULT_BUTTON_TEXT;
    private int backgroundColor = Defaults.DEFAULT_BUTTON_COLOR;

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

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

}

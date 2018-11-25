package com.medical.anschutz.cu.buttonpressingapp.model;

import android.view.Gravity;

import com.medical.anschutz.cu.buttonpressingapp.model.defaults.Defaults;

import java.io.Serializable;

public class ButtonConfig implements Serializable{

    private Integer height = Defaults.DEFAULT_BUTTON_HEIGHT;
    private Integer width = Defaults.DEFAULT_BUTTON_WIDTH;
    private Integer xPosition = Defaults.DEFAULT_BUTTON_X_POSITION;
    private Integer yPosition = Defaults.DEFAULT_BUTTON_Y_POSITION;
    private Integer rotation = Defaults.DEFAULT_BUTTON_ROTATION;
    private String  buttonEvent = Defaults.DEFAULT_BUTTON_CLICK_EVENT;
    public Integer marginTop = Defaults.DEFAULT_BUTTON_MARGIN_TOP;
    public Integer marginBottom = Defaults.DEFAULT_BUTTON_MARGIN_BOTTOM;
    public Integer marginLeft = Defaults.DEFAULT_BUTTON_MARGIN_LEFT;
    public Integer marginRight = Defaults.DEFAULT_BUTTON_MARGIN_RIGHT;

    //Text and Styling
    private String text = Defaults.DEFAULT_BUTTON_TEXT;
    private int style = Defaults.DEFAULT_BUTTON_TEXT_STYLE;  //sets bold, italic etc
    private String gravity = Defaults.DEFAULT_BUTTON_TEXT_GRAVITY;
    private int backgroundColorR = Defaults.DEFAULT_BUTTON_COLOR_R;
    private int backgroundColorG = Defaults.DEFAULT_BUTTON_COLOR_G;
    private int backgroundColorB = Defaults.DEFAULT_BUTTON_COLOR_B;
    private String image = null;


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

    //Button Text and Text Styling
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int convertStringToGravity(String gravity) {
        int gravityInt = Gravity.NO_GRAVITY;
        String[] gravityParams = gravity.split("\\s+");
        switch (gravityParams[0]) {
            case "center" : gravityInt = Gravity.CENTER_VERTICAL;
                break;
            case "top" : gravityInt = Gravity.TOP;
                break;
            case "bottom" : gravityInt = Gravity.BOTTOM;
                break;
        }
        switch (gravityParams[1]) {
            case "center" : gravityInt = gravityInt | Gravity.CENTER_HORIZONTAL;
                break;
            case "left" : gravityInt = gravityInt | Gravity.LEFT;
                break;
            case "right" : gravityInt = gravityInt | Gravity.RIGHT;
                break;
        }
        return gravityInt;
    }

    public int getGravity() {
        return convertStringToGravity(gravity);
    }
    public void setGravity(String gravity) { this.gravity = gravity; }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

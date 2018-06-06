package com.medical.anschutz.cu.buttonpressingapp.model;

import android.graphics.Color;
import android.graphics.Typeface;
import com.medical.anschutz.cu.buttonpressingapp.services.dataTransformers;
import java.io.Serializable;

public class ButtonConfig implements Serializable{

    dataTransformers dataTransformers = new dataTransformers();

    private String text = Defaults.DEFAULT_BUTTON_TEXT;
    private String font = Defaults.DEFAULT_BUTTON_TEXT_FONT;
    private int style = Defaults.DEFAULT_BUTTON_TEXT_STYLE;
    private String gravity = Defaults.DEFAULT_BUTTON_TEXT_GRAVITY;
    private int backgroundColor = Defaults.DEFAULT_BUTTON_COLOR;
    private Integer height = Defaults.DEFAULT_BUTTON_HEIGHT;
    private Integer width = Defaults.DEFAULT_BUTTON_WIDTH;
    private Integer rotation = Defaults.DEFAULT_BUTTON_ROTATION;
    private Integer xPosition = Defaults.DEFAULT_BUTTON_X_POSITION;
    private Integer yPosition = Defaults.DEFAULT_BUTTON_Y_POSITION;

    //Text and Styling
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getFont() {
        return font;
    }
    public void setFont(String font) {
        this.font = font;
    }

    public int getStyle() {
        return style;
    } //bold, italic, normal
    public void setStyle(int style) {
        this.style = style;
    }

    public String getGravity() {
        return gravity;
    }
    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    //Button Dimensions and Orientation
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = dataTransformers.convertDpToPx(height);
    }

    public Integer getWidth() {
        return width;
    }
    public void setWidth(Integer width) {
        this.width = dataTransformers.convertDpToPx(width);
    }


    public Integer getRotation() {
        return rotation;
    }
    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    //Button Location
    public Integer getxPosition() {
        return xPosition;
    }
    public void setxPosition(Integer xPosition) {
        this.xPosition = dataTransformers.convertDpToPx(xPosition);
    }

    public Integer getyPosition() {
        return yPosition;
    }
    public void setyPosition(Integer yPosition) {
        this.yPosition = dataTransformers.convertDpToPx(yPosition);
    }

}

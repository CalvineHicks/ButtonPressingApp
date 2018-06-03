package com.medical.anschutz.cu.buttonpressingapp.model;

import android.graphics.Color;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Arrays;

public class Defaults {
    /* Screen */
    public static final int DEFAULT_SCREEN_BUTTON_COLUMNS = 4;
    public static final int DEFAULT_SCREEN_BUTTON_ROWS = 6;
    public static final int DEFAULT_SCREEN_BUTTON_ROW_MAX_HEIGHT = 200;
    public static final int DEFAULT_SCREEN_BUTTON_ROW_MIN_HEIGHT = 150;
    public static final int DEFAULT_SCREEN_BUTTON_ROW_MAX_WIDTH = 600;
    public static final int DEFAULT_SCREEN_SUCCESS_X_POSITION = 0;
    public static final int DEFAULT_SCREEN_SUCCESS_Y_POSITION = 0;

    /* Button */
    //size and position
    public static final int DEFAULT_BUTTON_HEIGHT = 50;
    public static final int DEFAULT_BUTTON_WIDTH = 50;
    public static final int DEFAULT_BUTTON_X_POSITION = 0;
    public static final int DEFAULT_BUTTON_Y_POSITION = 0;
    public static final int DEFAULT_BUTTON_ROTATION = 0;

    //text settings
    public static final String DEFAULT_BUTTON_TEXT = "TEXT";
    public static final int DEFAULT_BUTTON_COLOR = Color.GRAY;
    public static final String DEFAULT_BUTTON_TEXT_FONT = "roboto";
    public static final int DEFAULT_BUTTON_TEXT_STYLE = Typeface.NORMAL;
    public static final String DEFAULT_BUTTON_TEXT_GRAVITY = "center_horizontal | center_vertical";
    public static final ArrayList DEFAULT_BUTTON_TEXT_PADDING = new ArrayList<Integer>(Arrays.asList(0, 0,0,0));
    public static final ArrayList<Integer> DEFAULT_BUTTON_MARGINS = new ArrayList<Integer>(Arrays.asList(0, 0,0,0));
}

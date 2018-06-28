package com.medical.anschutz.cu.buttonpressingapp.model;

import android.graphics.Color;

public class Defaults {

    /* Session */
    public static enum PROGRESSION_RULE {PROGRESS_ON_SUCCESS_ONLY, PROGRESS_ON_BUTTON_PRESS, PROGRESS_ON_ANY_PRESS};
    public static final PROGRESSION_RULE DEFAULT_PROGRESSION_RULE = PROGRESSION_RULE.PROGRESS_ON_SUCCESS_ONLY;

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

    //button events
    public static final String DEFAULT_BUTTON_CLICK_EVENT = "success";
}

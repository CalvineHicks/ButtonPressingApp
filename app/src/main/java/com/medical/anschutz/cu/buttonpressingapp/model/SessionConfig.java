package com.medical.anschutz.cu.buttonpressingapp.model;

public class SessionConfig {
    private int buttonColumns = Defaults.DEFAULT_SESSION_BUTTON_COLUMNS;
    private int buttonRows = Defaults.DEFAULT_SESSION_BUTTON_ROWS;
    private int buttonRowMaxHeight = Defaults.DEFAULT_SESSION_BUTTON_ROW_MAX_HEIGHT;
    private int buttonRowMaxWidth = Defaults.DEFAULT_SESSION_BUTTON_ROW_MAX_WIDTH;

    private int buttonSuccessX = Defaults.DEFAULT_SESSION_SUCCESS_X_POSITION;
    private int buttonSuccessY = Defaults.DEFAULT_SESSION_SUCCESS_Y_POSITION;

    public int getButtonColumns() {
        return buttonColumns;
    }

    public void setButtonColumns(int buttonColumns) {
        this.buttonColumns = buttonColumns;
    }

    public int getButtonRows() {
        return buttonRows;
    }

    public void setButtonRows(int buttonRows) {
        this.buttonRows = buttonRows;
    }

    public int getButtonRowMaxHeight() {
        return buttonRowMaxHeight;
    }

    public void setButtonRowMaxHeight(int buttonRowMaxHeight) {
        this.buttonRowMaxHeight = buttonRowMaxHeight;
    }

    public int getButtonRowMaxWidth() {
        return buttonRowMaxWidth;
    }

    public void setButtonRowMaxWidth(int buttonRowMaxWidth) {
        this.buttonRowMaxWidth = buttonRowMaxWidth;
    }

    public int getButtonSuccessX() {
        return buttonSuccessX;
    }

    public void setButtonSuccessX(int buttonSuccessX) {
        this.buttonSuccessX = buttonSuccessX;
    }

    public int getButtonSuccessY() {
        return buttonSuccessY;
    }

    public void setButtonSuccessY(int buttonSuccessY) {
        this.buttonSuccessY = buttonSuccessY;
    }
}

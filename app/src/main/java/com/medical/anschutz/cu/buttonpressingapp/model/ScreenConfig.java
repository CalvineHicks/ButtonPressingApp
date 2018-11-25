package com.medical.anschutz.cu.buttonpressingapp.model;

import com.medical.anschutz.cu.buttonpressingapp.model.defaults.Defaults;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ScreenConfig implements Serializable{
    private int buttonColumns = Defaults.DEFAULT_SCREEN_BUTTON_COLUMNS;
    private int buttonRows = Defaults.DEFAULT_SCREEN_BUTTON_ROWS;

    private int buttonSuccessX = Defaults.DEFAULT_SCREEN_SUCCESS_X_POSITION;
    private int buttonSuccessY = Defaults.DEFAULT_SCREEN_SUCCESS_Y_POSITION;

    private List<RowConfig> rowConfigs = null;

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

    public List<RowConfig> getRowConfigs() {
        if(rowConfigs == null)
            this.rowConfigs = new LinkedList<RowConfig>();
        return rowConfigs;
    }

    public void setRowConfigs(List<RowConfig> rowConfigs) {
        this.rowConfigs = rowConfigs;
    }

    public class RowConfig implements Serializable {
        private List<ButtonConfig> buttonConfigs = null;
        private int buttonRowMaxHeight = Defaults.DEFAULT_SCREEN_BUTTON_ROW_MAX_HEIGHT;

        private int buttonRowMinHeight = Defaults.DEFAULT_SCREEN_BUTTON_ROW_MIN_HEIGHT;
        private int buttonRowMaxWidth = Defaults.DEFAULT_SCREEN_BUTTON_ROW_MAX_WIDTH;

        private boolean scrollEnabled = Defaults.DEFAULT_SCREEN_BUTTON_ROW_SCROLL_ENABLED;


        public int getButtonRowMaxHeight() {
            return buttonRowMaxHeight;
        }

        public void setButtonRowMaxHeight(int buttonRowMaxHeight) {
            this.buttonRowMaxHeight = buttonRowMaxHeight;
        }

        public int getButtonRowMinHeight() {
            return buttonRowMinHeight;
        }

        public void setButtonRowMinHeight(int buttonRowMinHeight) {
            this.buttonRowMinHeight = buttonRowMinHeight;
        }

        public int getButtonRowMaxWidth() {
            return buttonRowMaxWidth;
        }

        public void setButtonRowMaxWidth(int buttonRowMaxWidth) {
            this.buttonRowMaxWidth = buttonRowMaxWidth;
        }

        public List<ButtonConfig> getButtonConfigs() {
            if(buttonConfigs == null)
                this.buttonConfigs = new LinkedList<ButtonConfig>();
            return buttonConfigs;
        }

        public void setButtonConfigs(List<ButtonConfig> buttonConfigs) {
            this.buttonConfigs = buttonConfigs;
        }

        public boolean isScrollEnabled() {
            return scrollEnabled;
        }

        public void setScrollEnabled(boolean scrollEnabled) {
            this.scrollEnabled = scrollEnabled;
        }
    }
}

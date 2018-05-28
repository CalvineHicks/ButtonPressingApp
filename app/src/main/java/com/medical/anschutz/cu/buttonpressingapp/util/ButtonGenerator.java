package com.medical.anschutz.cu.buttonpressingapp.util;

import android.content.Context;
import android.widget.Button;

import com.medical.anschutz.cu.buttonpressingapp.model.ButtonConfig;

public class ButtonGenerator {
    public static Button generateButton(Context context, ButtonConfig config){
        Button b = new Button(context);
        //apply button attributes
        //bg color
        b.setBackgroundColor(config.getBackgroundColor());
        //height
        b.setHeight(config.getHeight());
        //width
        b.setWidth(config.getWidth());
        //text
        b.setText(config.getText());
        //button location
        b.setX(config.getxPosition());
        b.setY(config.getyPosition());
        b.setRotation(config.getRotation());

        return b;
    }
}

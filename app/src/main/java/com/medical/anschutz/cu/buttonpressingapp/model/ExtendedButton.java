package com.medical.anschutz.cu.buttonpressingapp.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;
import com.medical.anschutz.cu.buttonpressingapp.R;


import java.util.ArrayList;

public class ExtendedButton extends AppCompatButton {
    public String eventType = Defaults.DEFAULT_BUTTON_CLICK_EVENT;
    public ExtendedButton(Context context, View view, ButtonConfig config) {
        super(context);
        this.setBackgroundColor(Color.rgb(config.getBackgroundColorR(), config.getBackgroundColorG(), config.getBackgroundColorB()));
        //height
        this.setHeight(config.getHeight());
        //width
        this.setWidth(config.getWidth());
        //text
        this.setText(config.getText());
        this.setTypeface(null, config.getStyle());
        this.setGravity(config.getGravity());
        this.setIncludeFontPadding(false);
        //button location
        this.setX(config.getxPosition());
        this.setY(config.getyPosition());
        this.setRotation(config.getRotation());
        this.setClickableArea(view);
        this.eventType = config.getButtonEvent();
        //code for setting background image (overrides color/size)
        //Drawable d = getResources().getDrawable(R.drawable.circle2);
        //this.setBackgroundDrawable(d);
    }

    //This isnt working how we expect it too. Something is off.
    public void setClickableArea(View view){
        final Button b = this;
        view.post(new Runnable() {
            @Override
            public void run() {
                Rect delegateArea = new Rect();
                Button delegate = b;
                delegate.getHitRect(delegateArea);
                delegateArea.top -= 300;           //Choose yourself
                delegateArea.bottom += 300;
                delegateArea.left -= 300;
                delegateArea.right += 300;

                TouchDelegate expandedArea = new TouchDelegate(delegateArea, delegate);
                // give the delegate to an ancestor of the view we're
                // delegating the
                // area to
                if (View.class.isInstance(delegate.getParent())) {
                    ((View) delegate.getParent())
                            .setTouchDelegate(expandedArea);
                }
            }
        });
    }
}

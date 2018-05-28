package com.medical.anschutz.cu.buttonpressingapp.model;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;

public class ExtendedButton extends AppCompatButton {
    public ExtendedButton(Context context, View view, ButtonConfig config) {
        super(context);
        this.setBackgroundColor(config.getBackgroundColor());
        //height
        this.setHeight(config.getHeight());
        //width
        this.setWidth(config.getWidth());
        //text
        this.setText(config.getText());
        //button location
        this.setX(config.getxPosition());
        this.setY(config.getyPosition());
        this.setRotation(config.getRotation());
        this.setClickableArea(view);
    }

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

package com.medical.anschutz.cu.buttonpressingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;
import android.graphics.Rect;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout buttonContainer = findViewById(R.id.buttonContainer);

        //row to hold a button
        TableRow buttonRow = new TableRow(this);

        button = new Button(this);
//apply button attributes
//height
        button.setHeight(300);
//width
        button.setWidth(300);
//text
        button.setText("Test");
//text dropshadow
        button.setShadowLayer(50, 30, 30, Color.BLACK);
        button.setRotation(45);

        buttonRow.addView(button);
        View parent = buttonRow;
        parent.post(new Runnable() {
            @Override
            public void run() {
                Rect delegateArea = new Rect();
                Button delegate = button;
                delegate.getHitRect(delegateArea);
                delegateArea.top -= 600;           //Choose yourself
                delegateArea.bottom += 600;
                delegateArea.left -= 600;
                delegateArea.right += 600;

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
        buttonContainer.addView(buttonRow);
    }
}

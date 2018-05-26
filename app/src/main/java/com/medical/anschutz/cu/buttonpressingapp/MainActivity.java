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
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout buttonContainer = findViewById(R.id.buttonContainer);

        //row to hold a button
        TableRow buttonRow = new TableRow(this);
        //height of row
        buttonRow.setMinimumHeight(1000);

        button = new Button(this);
//apply button attributes
//height
        button.setHeight(300);
//width
        button.setWidth(300);
//text
        button.setText("Test");
//button location
        button.setX(40);
        button.setY(60);
//text dropshadow
        button.setShadowLayer(50, 30, 30, Color.BLACK);
        button.setRotation(45);
//button click event
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HomePage.class);
                startActivity(intent);
            }
        });

        buttonRow.addView(button);
        View parent = buttonRow;
        parent.post(new Runnable() {
            @Override
            public void run() {
                Rect delegateArea = new Rect();
                Button delegate = button;
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
        buttonContainer.addView(buttonRow);
    }
}

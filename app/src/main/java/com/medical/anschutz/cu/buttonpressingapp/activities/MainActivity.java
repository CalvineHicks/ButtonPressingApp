package com.medical.anschutz.cu.buttonpressingapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;
import android.graphics.Rect;
import android.graphics.Color;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.content.Intent;

import com.medical.anschutz.cu.buttonpressingapp.R;
import com.medical.anschutz.cu.buttonpressingapp.services.dataTransformers;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    private dataTransformers dt = new dataTransformers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout buttonContainer = findViewById(R.id.buttonContainer);

        //row to hold a button
        TableRow buttonRow = new TableRow(this);
        //height of row
        buttonRow.setMinimumHeight(500);
        buttonRow.setBackgroundColor(Color.GREEN);
        button = new Button(this);
//apply button attributes
//height
        button.setHeight(300);
//width
        button.setWidth(300);
//text
        button.setText("Home Page");
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
        //set margins for a column
        TableRow.LayoutParams lp = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        //set's margin for a set of columns
        lp.setMargins(0, 50, 0, 0);
        buttonRow.addView(button, lp);
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

        //messing around with arranging multiple buttons
        TableRow buttonRow2 = new TableRow(this);
        buttonRow2.setBackgroundColor(Color.RED);

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        //set's margin for a set of columns
        layoutParams.setMargins(100, 20, 0, 0);

        Button button2 = new Button(this);
        Button button3 = new Button(this);
        Button button4 = new Button(this);
        button2.setText("MORE");
        button2.setHeight(20);
        button2.setWidth(40);
        button3.setText("BUTTONS");
        button4.setText("HERE");
        buttonRow2.addView(button2, layoutParams);
        buttonRow2.addView(button3, layoutParams);
        buttonRow2.addView(button4, layoutParams);
        buttonContainer.addView(buttonRow2);
    }
}

package com.medical.anschutz.cu.buttonpressingapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.medical.anschutz.cu.buttonpressingapp.activities.HomePage;
import com.medical.anschutz.cu.buttonpressingapp.model.ButtonConfig;
import com.medical.anschutz.cu.buttonpressingapp.util.ButtonGenerator;

public class Session extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        TableLayout buttonContainer = findViewById(R.id.buttonContainer);

        TableRow buttonRow = new TableRow(this);
        //height of row
        buttonRow.setMinimumHeight(500);
        buttonRow.setBackgroundColor(Color.GREEN);
        ButtonConfig config = new ButtonConfig();
        Button button = ButtonGenerator.generateButton(this, config);
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

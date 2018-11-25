package com.medical.anschutz.cu.buttonpressingapp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.widget.AppCompatButton;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;

import com.medical.anschutz.cu.buttonpressingapp.BuildConfig;
import com.medical.anschutz.cu.buttonpressingapp.model.defaults.Defaults;
import com.medical.anschutz.cu.buttonpressingapp.model.defaults.GlobalDefaults;


import java.io.File;
import java.io.FileInputStream;

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
        //if the image is not found the button will fall back to using its config without the image file
        if(null!= config.getImage()){
            String uri = "@drawable/"+config.getImage();  // where myresource (without the extension) is the file

            int imageResource = getResources().getIdentifier(uri, null, BuildConfig.APPLICATION_ID);


            File sdCard = Environment.getExternalStorageDirectory();

            File directory = new File (sdCard.getAbsolutePath() + GlobalDefaults.IMAGE_DIR);

            File file = new File(directory, config.getImage()); //or any other format supported
            FileInputStream streamIn = null;
            try {
                 streamIn = new FileInputStream(file);
                Bitmap bitmap = BitmapFactory.decodeStream(streamIn); //This gets the image
                streamIn.close();
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                this.setBackgroundDrawable(d);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        //
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

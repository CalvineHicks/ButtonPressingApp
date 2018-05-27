package com.medical.anschutz.cu.buttonpressingapp.services;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.TypedValue;

//to do: cacheing values would speed this process
public class dataTransformers extends Service{

    //required for instantiating a service
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //convert dp to px for standard button location generation
    public int convertDpToPx(int dp){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
        return px;
    }

}

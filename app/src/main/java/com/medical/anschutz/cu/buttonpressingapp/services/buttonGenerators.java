package com.medical.anschutz.cu.buttonpressingapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class buttonGenerators extends Service {

    //required for instantiating a service
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

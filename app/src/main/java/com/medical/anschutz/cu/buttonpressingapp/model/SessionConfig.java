package com.medical.anschutz.cu.buttonpressingapp.model;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SessionConfig implements Serializable{

    private List<ScreenConfig> screenConfigs = null;

    public static SessionConfig fromJson(String s) {
        return new Gson().fromJson(s, SessionConfig.class);
    }

    public List<ScreenConfig> getScreenConfigs() {
        if(screenConfigs == null)
            this.screenConfigs = new ArrayList<ScreenConfig>();
        return screenConfigs;
    }

    public void setScreenConfigs(List<ScreenConfig> screenConfigs) {
        this.screenConfigs = screenConfigs;
    }
}

package com.medical.anschutz.cu.buttonpressingapp.model;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SessionConfig implements Serializable{

    private List<ScreenConfig> screenConfigs = null;
    private String progressionRule = Defaults.DEFAULT_PROGRESSION_RULE;

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

    public String getProgressionRule() {
        return progressionRule;
    }

    public void setProgressionRule(String progressionRule) {
        this.progressionRule = progressionRule;
    }
}

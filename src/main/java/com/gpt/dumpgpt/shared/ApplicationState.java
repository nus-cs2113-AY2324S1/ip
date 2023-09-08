package com.gpt.dumpgpt.shared;

import java.util.HashMap;

public class ApplicationState {
    private static ApplicationState appState = null;
    private final HashMap<String, Object> STATES = new HashMap<>();
    private static final String ENDED_STATE_KEY = "_INTERNAL_ENDED";

    private ApplicationState() {
        setApplicationEnded(false);
    }

    public static ApplicationState getAppState() {
        if (appState == null) {
            appState = new ApplicationState();
        }
        return appState;
    }

    public Object getStateObject(String key) {
        if (!STATES.containsKey(key)) {
            return null;
        }
        return STATES.get(key);
    }

    public void setStateObject(String key, Object state) {
        STATES.put(key, state);
    }

    public void setApplicationEnded(boolean isEnded) {
        STATES.put(ENDED_STATE_KEY, isEnded);
    }

    public boolean getApplicationEnded() {
        return (boolean) STATES.get(ENDED_STATE_KEY);
    }
}

package com.gpt.dumpgpt.shared;

import java.util.HashMap;

public class ApplicationState {
    private static ApplicationState appState = null;
    private final HashMap<String, Object> STATES = new HashMap<>();
    private boolean isEnded = false;

    private ApplicationState() {
    }

    /**
     * Returns singleton instance of ApplicationState
     * @return application state object
     */
    public static ApplicationState getAppState() {
        if (appState == null) {
            appState = new ApplicationState();
        }
        return appState;
    }

    /**
     * Returns the state object stored under key
     * @param key string referencing a stored state
     * @return the state object stored under key
     */
    public Object getStateObject(String key) {
        if (!STATES.containsKey(key)) {
            return null;
        }
        return STATES.get(key);
    }

    /**
     * Stores an object stored under provided key
     * @param key string referencing a stored state
     * @param state object to be stored under key
     */
    public void setStateObject(String key, Object state) {
        STATES.put(key, state);
    }

    /**
     * Set application ended property
     * @param isEnded new state of property
     */
    public void setApplicationEnded(boolean isEnded) {
        this.isEnded = isEnded;
    }

    /**
     * Gets isEnded state of application
     * @return boolean denoting if application is ended
     */
    public boolean getApplicationEnded() {
        return isEnded;
    }
}

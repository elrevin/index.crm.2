package me.elrevin.indexcrm.common;

import me.elrevin.indexcrm.ui.activity.BaseActivity;

public class CustomActivityManager {
    private BaseActivity currentActivity;

    public BaseActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public void onHomeNetHandler(boolean in) {
        if (currentActivity != null) {
            if (in) {
                currentActivity.onHomeNet();
            } else {
                currentActivity.onResumeHomeNet();
            }
        }
    }
}

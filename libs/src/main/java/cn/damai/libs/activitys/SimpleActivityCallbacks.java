package cn.damai.libs.activitys;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by wanggeng on 2017/5/9.
 */
public abstract class SimpleActivityCallbacks implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }
}

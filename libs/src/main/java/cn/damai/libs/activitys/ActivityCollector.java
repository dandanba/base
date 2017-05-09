package cn.damai.libs.activitys;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

    private final List<Activity> mContainer;

    public ActivityCollector() {
        mContainer = new ArrayList<>();


    }

    public void addActivity(Activity activity) {
        mContainer.add(activity);
    }

    public void removeActivity(Activity activity) {
        mContainer.remove(activity);
    }

    public void finishAll() {
        final int size = size(mContainer);
        Activity activity;
        for (int i = 0; i < size; i++) {
            activity = mContainer.get(i);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        mContainer.clear();
    }

    private <T> int size(List<T> list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

}  
package cn.damai.libs.bga;

import android.app.Application;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by wanggeng on 2017/4/13.
 */

public class BGAUtil {
    public static void init(Application application) {
        BGASwipeBackManager.getInstance().init(application);
        //更多的设置参考 https://github.com/bingoogolapple/BGASwipeBackLayout-Android
    }
}

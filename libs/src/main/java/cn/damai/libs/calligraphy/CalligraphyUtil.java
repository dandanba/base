package cn.damai.libs.calligraphy;

import android.content.Context;

import cn.damai.libs.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by wanggeng on 2017/4/7.
 */
public class CalligraphyUtil {
    public static void initDefault(String fontPath) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(fontPath)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static Context wrapContext(Context base) {
        return CalligraphyContextWrapper.wrap(base);
    }
}

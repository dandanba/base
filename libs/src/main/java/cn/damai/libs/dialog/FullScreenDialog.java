package cn.damai.libs.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import cn.damai.libs.R;

/**
 * Created by wanggeng on 2017/4/13.
 */

public class FullScreenDialog extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenTransparentDialog);
    }
}

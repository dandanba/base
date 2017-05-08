package cn.damai.libs.preference;

import android.content.Context;

import com.prashantsolanki.secureprefmanager.SecurePrefManager;
import com.prashantsolanki.secureprefmanager.SecurePrefManagerInit;

public class SecurePrefUtils {
    public static void initialize(Context context) {
        new SecurePrefManagerInit.Initializer(context).useEncryption(true).initialize();
    }

    public static void cleanAll(Context context) {
        getCleaner(context).confirm();
    }

    public static void remove(Context context, String name) {
        getDeleter(context, name).confirm();
    }

    public static void setValue(Context context, String name, String value) {
        getSetter(context, name).value(value).go();
    }

    public static void setValue(Context context, String name, int value) {
        getSetter(context, name).value(value).go();
    }

    public static void setValue(Context context, String name, boolean value) {
        getSetter(context, name).value(value).go();
    }

    public static void setValue(Context context, String name, long value) {
        getSetter(context, name).value(value).go();
    }

    public static void setValue(Context context, String name, float value) {
        getSetter(context, name).value(value).go();
    }

    public static String getValue(Context context, String name, String defaultValue) {
        return getGetter(context, name).defaultValue(defaultValue).go();
    }

    public static int getValue(Context context, String name, int defaultValue) {
        return getGetter(context, name).defaultValue(defaultValue).go();
    }

    public static boolean getValue(Context context, String name, boolean defaultValue) {
        return getGetter(context, name).defaultValue(defaultValue).go();
    }

    public static long getValue(Context context, String name, long defaultValue) {
        return getGetter(context, name).defaultValue(defaultValue).go();
    }

    public static float getValue(Context context, String name, float defaultValue) {
        return getGetter(context, name).defaultValue(defaultValue).go();
    }

    private static SecurePrefManager.Setter getSetter(Context context, String name) {
        return SecurePrefManager.with(context).set(name);
    }

    private static SecurePrefManager.Getter getGetter(Context context, String name) {
        return SecurePrefManager.with(context).get(name);
    }

    private static SecurePrefManager.Deleter getDeleter(Context context, String name) {
        return SecurePrefManager.with(context).remove(name);
    }

    private static SecurePrefManager.Deleter getCleaner(Context context) {
        return SecurePrefManager.with(context).clear();
    }
}
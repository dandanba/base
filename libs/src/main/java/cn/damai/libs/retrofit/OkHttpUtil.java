package cn.damai.libs.retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;

public class OkHttpUtil {
    public static OkHttpClient initClient(Context context, boolean debug, String appplicationId, String flavor, String versionName, String versionCode) {
        final Context fContext = context;
        final String fAppplicationId = appplicationId;
        final String fFlavor = flavor;
        final String fVersionName = versionName;
        final String fVersionCode = versionCode;

        // Define the interceptor, add authentication headers
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("PLATFORM", "android")
                        .addHeader("APPLICATION_ID", fAppplicationId)
                        .addHeader("FLAVOR", fFlavor)
                        .addHeader("VERSION_CODE", fVersionCode)
                        .addHeader("VERSION_NAME", fVersionName)
                        .build();
                return chain.proceed(newRequest);
            }
        };

        //设置缓存
        final File dir = new File(context.getExternalCacheDir(), fAppplicationId);
        final Cache cache = new Cache(dir, 1024 * 1024 * 50);

        final Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                if (isConnected(fContext)) {
                    int maxAge = 60; // read from cache for 1 minute
                    return response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    return response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };

        // logging
        final LoggingInterceptor loggingInterceptor = new LoggingInterceptor.Builder()
                .loggable(debug)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", versionName)
                .build();

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(cacheInterceptor)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .cache(cache)
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(null)
                .proxy(Proxy.NO_PROXY)
                .build();

        return client;
    }

    /**
     * 判断网络连接是否有效（此时可传输数据）。
     *
     * @return boolean 不管wifi，还是mobile net，只有当前在连接状态（可有效传输数据）才返回true,反之false。
     */
    private static boolean isConnected(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        return net != null && net.isConnected();
    }

    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
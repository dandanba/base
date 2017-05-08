package cn.damai.libs.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;

/**
 * Created by wanggeng on 2017/5/4.
 */

public class SampleFileDownloadListener extends FileDownloadListener {
    @Override
    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
    }

    @Override
    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
    }

    @Override
    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
    }

    @Override
    protected void blockComplete(BaseDownloadTask task) {
    }

    @Override
    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
    }

    @Override
    protected void completed(BaseDownloadTask task) {
    }

    @Override
    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
    }

    @Override
    protected void error(BaseDownloadTask task, Throwable e) {
    }

    @Override
    protected void warn(BaseDownloadTask task) {
    }
}

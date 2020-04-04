package com.lib.library.utils.android;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import java.util.List;

/**
 * 通知栏的封装类
 * Created by zhongjh on 2018/6/29.
 */
public class NotificationUtils {

    final static String CHANNEL_ID = "5";
    final static String CHANNEL_NAME = "channel5";

    /**
     * 创建通知栏
     *
     * @param context   上下文
     * @param smallIcon 小图标
     * @param title     标题
     * @param content   内容
     * @return 通知栏
     */
    public static NotificationCompat.Builder create(Context context, int smallIcon, CharSequence title, CharSequence content) {
        return new NotificationCompat.Builder(context, CHANNEL_ID) // channelid兼容android8.0
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(content);
    }

    /**
     * 创建通知栏
     *
     * @param context   上下文
     * @param largeIcon 大图标
     * @param smallIcon 小图标
     * @param title     标题
     * @param content   内容
     * @return 通知栏
     */
    public static NotificationCompat.Builder create(Context context, int largeIcon, int smallIcon, CharSequence title, CharSequence content) {
        NotificationCompat.Builder builder = create(context, smallIcon, title, content);
        // 大图标
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), largeIcon);
        builder.setLargeIcon(bitmap);
        return builder;
    }

    /**
     * 创建通知栏
     *
     * @param context       上下文
     * @param largeIcon     大图标
     * @param smallIcon     小图标
     * @param title         标题
     * @param content       内容
     * @param pendingIntent 处理即将发生的事
     * @return 通知栏
     */
    public static NotificationCompat.Builder create(Context context, int largeIcon, int smallIcon, CharSequence title, CharSequence content, PendingIntent pendingIntent) {
        return create(context, largeIcon, smallIcon, title, content).setAutoCancel(true).setContentIntent(pendingIntent);
    }

    /**
     * 根据8.0做的一些配置
     *
     * @param notificationManager 通知管理
     * @param enableLights        是否在桌面icon右上角展示小红点
     * @param lightColor          小红点颜色
     * @param showBadge           是否在久按桌面图标时显示此渠道的通知
     */
    public static void createNotificationChannel(NotificationManager notificationManager, boolean enableLights, int lightColor, boolean showBadge) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel
                    channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            channel.enableLights(enableLights);
            channel.setLightColor(lightColor);
            channel.setShowBadge(showBadge);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * 发送
     *
     * @param context      上下文
     * @param notifyId     通知id,以后取消也是根据这个来
     * @param notification builder.build()获取
     */
    public static void notify(Context context, int notifyId, Notification notification) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null)
            manager.notify(notifyId, notification);
    }

    /**
     * 根据id关闭通知
     *
     * @param context  上下文
     * @param notifyId 通知id
     */
    public static void cancel(Context context, int notifyId) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null)
            manager.cancel(notifyId);
    }

    /**
     * 取消所有通知
     *
     * @param context 上下文
     */
    public static void cancelAll(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null)
            manager.cancelAll();
    }

    /**
     * 多行文本样式
     *
     * @param content 内容，用 \n 换行
     * @return BigTextStyle
     */
    public static NotificationCompat.BigTextStyle bigText(CharSequence content) {
        return new NotificationCompat.BigTextStyle().bigText(content);
    }

    /**
     * 收件箱样式
     *
     * @param messages 列表文本
     * @return InboxStyle
     */
    public static NotificationCompat.InboxStyle makeInbox(List<String> messages) {
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        for (CharSequence msg : messages) {
            style.addLine(msg);
        }
        return style;
    }

    /**
     * 通过服务发送通知
     *
     * @param service      服务
     * @param notifyId     通知id
     * @param notification builder.build()获取
     */
    public static void startForeground(Service service, int notifyId, Notification notification) {
        service.startForeground(notifyId, notification);
    }

    /**
     * 通过服务停止通知
     *
     * @param service 服务
     */
    public static void stopForeground(Service service) {
        service.stopForeground(true);
    }

}

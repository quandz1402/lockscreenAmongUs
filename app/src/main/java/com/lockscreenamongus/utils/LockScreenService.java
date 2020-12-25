package com.lockscreenamongus.utils;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;

import com.lockscreenamongus.LockScreenActivity;
import com.lockscreenamongus.MainActivity;
import com.lockscreenamongus.R;


public class LockScreenService extends Service {
    private Context mContext = null;
    private NotificationManager mNM;
    private static final int NOTIFICATION_ID = 23;

    private BroadcastReceiver mLockScreenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != context && null != intent.getAction()) {
                if ( intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    startLockScreenActivity();
                }
            }
        }


    };

    private void stateReceiver(boolean isStartReceiver) {
        if (isStartReceiver) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            registerReceiver(mLockScreenReceiver, filter);
        } else {
            if (null != mLockScreenReceiver) {
                unregisterReceiver(mLockScreenReceiver);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        stateReceiver(true);
//        showNotification();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        stateReceiver(false);
        mNM.cancel(MainActivity.notificationId);
//        Intent broadcastIntent = new Intent();
//        broadcastIntent.setAction("restartservice");
//        broadcastIntent.setClass(this, Restarter.class);
//        this.sendBroadcast(broadcastIntent);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            stopForeground(true);
//        } else {
//            stopSelf();
//        }
    }

    private void startMyOwnForeground(){

//        String CHANNEL_ID = "lock_channel_01";
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        assert manager != null;
//// only use NotificationChannel when Api Level >= 26
//        if(Build.VERSION.SDK_INT >= 26) {
//            CharSequence name = "Among Us LockScreen";
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//            mChannel.enableLights(true);
//            mChannel.setLightColor(Color.RED);
//            mChannel.enableVibration(true);
//            mChannel.setShowBadge(false);
//            manager.createNotificationChannel(mChannel);
//
//        }
//
////Send push notification
//        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
//                .setContentTitle("Among Us LockScreen")
//                .setContentText("App is running in background.")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setPriority(NotificationManager.IMPORTANCE_MIN)
//                .setCategory(Notification.CATEGORY_SERVICE)
//                .build();
//
//
//        startForeground(2, notification);


        Intent notificationIntent =  new Intent(this, LockScreenService.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);



        Notification notification =
                new Notification.Builder(this)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText("App is running in background.")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .build();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            AppUtils.startMyOwnForeground(this, LockScreenService.class.getSimpleName(), NOTIFICATION_ID);
        }
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            startForeground(NOTIFICATION_ID, notification);
        }
    }

    private void startLockScreenActivity() {
        Intent startLockScreenActIntent = new Intent(mContext, LockScreenActivity.class);
        startLockScreenActIntent.setAction(Intent.ACTION_VIEW);
        startLockScreenActIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startLockScreenActIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(startLockScreenActIntent);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    private void showNotification() {
//        CharSequence text = "Running";
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                new Intent(this, MainActivity.class), 0);
//
//        Notification notification = new Notification.Builder(this)
//                .setSmallIcon(R.drawable.cerberus)
//                .setTicker(text)
//                .setWhen(System.currentTimeMillis())
//                .setContentTitle(getText(R.string.app_name))
//                .setContentText(text)
//                .setContentIntent(contentIntent)
//                .setOngoing(true)
//                .build();
//
////        startForeground(101, notification);
//        mNM.notify(MainActivity.notificationId, notification);
    }

}
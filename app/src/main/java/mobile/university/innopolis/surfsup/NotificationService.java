package mobile.university.innopolis.surfsup;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import java.util.ArrayList;

/**
 * Created by Kirill
 */
public class NotificationService extends Service {
    static ArrayList<String> floors;
    @Override
    public void onCreate() {
// TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
// TODO Auto-generated method stub
        floors = intent.getStringArrayListExtra("floors");
        Notification(floors);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
// TODO Auto-generated method stub
        return null;
    }

    private void Notification(ArrayList<String> floors) {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setContentTitle("Surf's Up")
                .setContentText(getText(R.string.message) + " "+ floors) //says on which floors bottles are empty
                .setAutoCancel(true)
                .setTicker(getText(R.string.fill))
                .setSmallIcon(R.mipmap.ic_launcher);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pending = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pending);

        Notification notification = builder.build();
        notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification);
    }
}
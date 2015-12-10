package mobile.university.innopolis.surfsup;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Kirill on 10.12.2015.
 */
public class NetworkConnectionReceiver extends BroadcastReceiver {
    public NetworkConnectionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(isConnected(context)) Toast.makeText(context, "Connection is available", Toast.LENGTH_LONG).show();
        else Toast.makeText(context, "Connection is lost", Toast.LENGTH_LONG).show();
    }

    public boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();

        if(!isConnected)
        {
            new AlertDialog.Builder(context)
                    .setTitle("No network connection")
                    .setMessage("Please connect to the network")
                    .setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        return isConnected;
    }
}
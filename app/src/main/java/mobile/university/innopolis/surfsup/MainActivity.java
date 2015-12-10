package mobile.university.innopolis.surfsup;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.DataSetObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* ArrayList<Bottle> bottles = new ArrayList<>();
        bottles.add(new Bottle(1,4));
        bottles.add(new Bottle(2,3));
        bottles.add(new Bottle(3, 2));
        bottles.add(new Bottle(4, 1));*/
        startService(new Intent(this, NotificationService.class));

        list = (ListView) findViewById(R.id.listView);
        NetworkConnectionReceiver receiver = new NetworkConnectionReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, filter);
        mContext = this;

        new AsyncTask<Void, Void, ArrayList<Bottle>>() {
            @Override
            protected ArrayList<Bottle> doInBackground(Void... params) {
                try {
                    ArrayList<Bottle> bottles = (ArrayList<Bottle>) (new ParseServerData()).getCoolerInfo();
                    return bottles;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(ArrayList<Bottle> bottles) {
                super.onPostExecute(bottles);
                Log.i("SIZELOC", "" + bottles.size());

                //Checking network connection
                ConnectivityManager connectivity = (ConnectivityManager) mContext
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivity.getActiveNetworkInfo();
                //if (activeNetworkInfo != null && activeNetworkInfo.isConnected())
                    //list.setAdapter(new BottleAdapter(bottles));
                    showAllMarkers(bottles);
                }
        }.execute();
    }

    public void showAllMarkers(ArrayList<Bottle> bottles) {
        list.setAdapter(new BottleAdapter(bottles));
    }

    private class BottleAdapter implements ListAdapter {
        private ArrayList<Bottle> bottles;
        private class ViewHolder {
            public TextView title;
            public ImageView image;

            public ViewHolder(TextView title, ImageView image) {
                this.title = title;
                this.image = image;
            }
        }

        public BottleAdapter(ArrayList<Bottle> bottle) {
            this.bottles = bottle;
        }


        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int i) {
            return false;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return bottles.size();
        }

        @Override
        public Object getItem(int i) {
            return bottles.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.bottle_row, parent, false);

                final TextView title = (TextView) convertView.findViewById(R.id.txtLoc);
                final ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
                ViewHolder holder = new ViewHolder(title, image);

                convertView.setTag(holder);
            }

            final ViewHolder holder = (ViewHolder) convertView.getTag();

            holder.title.setText(bottles.get(position).getID());
            holder.image.setImageResource(bottles.get(position).getLevel());

            return convertView;
        }


        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
}

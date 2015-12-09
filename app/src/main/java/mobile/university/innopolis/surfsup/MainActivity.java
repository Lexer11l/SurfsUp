package mobile.university.innopolis.surfsup;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Bottle> bottles = new ArrayList<>();
        bottles.add(new Bottle(1,4));
        bottles.add(new Bottle(2,3));
        bottles.add(new Bottle(3,2));
        bottles.add(new Bottle(4,1));
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(new BottleAdapter(bottles));

    }


    private class Bottle {

        private int ID;
        private int level;

        public int getID() {
            switch (ID){
                case 0: return R.string.campus_one_1st_floor;

                case 1: return R.string.campus_one_2nd_floor;

                case 2: return R.string.campus_one_3rd_floor;

                case 3: return R.string.campus_one_4th_floor;

                case 4: return R.string.campus_two_1st_floor;

                case 5: return R.string.campus_two_2nd_floor;

                case 6: return R.string.campus_two_3rd_floor;

                case 7: return R.string.campus_two_4th_floor;

                default: return 0;

            }
        }

        public int getLevel() {
            switch (level){
                case 0: return R.drawable.empty;

                case 1: return R.drawable.low;

                case 2: return R.drawable.half;

                case 3: return R.drawable.high;

                case 4: return R.drawable.full;

                default: return 0;
            }

        }

        public Bottle(int ID, int level) {
            this.ID = ID;
            this.level = level;
        }
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

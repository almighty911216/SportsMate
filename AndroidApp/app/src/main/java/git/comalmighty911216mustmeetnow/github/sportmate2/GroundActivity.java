package git.comalmighty911216mustmeetnow.github.sportmate2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GroundActivity extends AppCompatActivity {
    ArrayList<ground_item> ground_list = new ArrayList<>();
    GroundAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground);

        setStatusBarColor(this, Color.parseColor("#F2DE00"));

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //list에 임시 데이터를 추가합니다
        ground_list.add(new ground_item("qqwe","7~18", "www", "XX-XXX-XXXX"));
        ground_list.add(new ground_item("qqwe","7~18", "www", "XX-XXX-XXXX"));
        ground_list.add(new ground_item("qqwe","7~18", "www", "XX-XXX-XXXX"));
        ground_list.add(new ground_item("qqwe","7~18", "www", "XX-XXX-XXXX"));
        ground_list.add(new ground_item("qqwe","7~18", "www", "XX-XXX-XXXX"));
        ground_list.add(new ground_item("qqwe","7~18", "www", "XX-XXX-XXXX"));

        ListView listView = (ListView) findViewById(R.id.ground_list);
        adapter = new GroundAdapter(ground_list,R.layout.groundlist,this);
        listView.setAdapter(adapter);
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public class ground_item{
        String name;
        String address;
        String phone;
        String time;

        public ground_item(String name, String time, String address, String phone) {
            this.name = name;
            this.time = time;
            this.address = address;
            this.phone = phone;
        }
    }

    public class GroundAdapter extends BaseAdapter {
        ArrayList<ground_item> list;
        int res;
        Context context;

        LayoutInflater inflater;
        public GroundAdapter(ArrayList<ground_item> list, int res, Context context) {
            this.list = list;
            this.res = res;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(res, parent, false);
            }
            TextView g_name = (TextView) convertView.findViewById(R.id.gName_tv);
            g_name.setText(list.get(position).name);
            TextView g_time = (TextView) convertView.findViewById(R.id.gTime_tv);
            g_time.setText(list.get(position).time);
            TextView g_addr = (TextView) convertView.findViewById(R.id.gAddr_tv);
            g_addr.setText(list.get(position).address);
            TextView g_phone = (TextView) convertView.findViewById(R.id.gPhone_tv);
            g_phone.setText(list.get(position).phone);


            return convertView;

        }
    }
}

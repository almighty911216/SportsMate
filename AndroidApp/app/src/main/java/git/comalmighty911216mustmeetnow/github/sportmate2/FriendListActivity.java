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

public class FriendListActivity extends AppCompatActivity {
    ArrayList<friend_item> list = new ArrayList<>();
    FriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        setStatusBarColor(this, Color.parseColor("#F2DE00"));

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //list 임의 추가 부분
        list.add(new friend_item(0,"qqwe","www"));
        list.add(new friend_item(0,"qqwe","www"));
        list.add(new friend_item(0,"qqwe","www"));
        list.add(new friend_item(0,"qqwe","www"));
        list.add(new friend_item(0,"qqwe","www"));
        list.add(new friend_item(0,"qqwe","www"));

        ListView listView = (ListView) findViewById(R.id.friend_list);
        adapter = new FriendAdapter(list,R.layout.friendlist,this);
        listView.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    public class friend_item{
        int img;
        String name;
        String address;

        public friend_item(int img, String name, String address) {
            this.img = img;
            this.name = name;
            this.address = address;
        }
    }


    public class FriendAdapter extends BaseAdapter {
        ArrayList<friend_item> list;
        int res;
        Context context;

        LayoutInflater inflater;
        public FriendAdapter(ArrayList<friend_item> list, int res, Context context) {
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
            TextView f_name = (TextView) convertView.findViewById(R.id.fName_tv);
            f_name.setText(list.get(position).name);
            TextView f_addr = (TextView) convertView.findViewById(R.id.fAddr_tv);
            f_addr.setText(list.get(position).address);

            return convertView;

        }
    }
}

package git.comalmighty911216mustmeetnow.github.sportmate2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    ArrayList<room_item> room_list = new ArrayList<>();
    RoomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_make_room);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), MakeRoomActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setStatusBarColor(this, Color.parseColor("#F2DE00"));


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem infoItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                infoItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width

                //openItem???볦씠瑜??ㅼ젙 ?⑸땲??                //openItem.setWidth(dp2px(90));
                infoItem.setWidth(200);


                // set item title
                infoItem.setTitle("?곸꽭 ?뺣낫");
                // set item title fontsize
                infoItem.setTitleSize(10);
                // set item title font color
                infoItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(infoItem);

                // create "delete" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width

                //deleteItem???볦씠瑜?蹂寃쏀빀?덈떎
                //deleteItem.setWidth(dp2px(90));
                openItem.setWidth(200);
                openItem.setTitle("?닿린");
                // set item title fontsize
                openItem.setTitleSize(10);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);

                // set a icon
                //deleteItem.setIcon(R.drawable.ic_menu_friend);
                // add to menu
                menu.addMenuItem(openItem);
            }
        };

        // set creator

        SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.room_list);
        listView.setMenuCreator(creator);
        adapter = new RoomAdapter(room_list,R.layout.roomlist,this);
        listView.setAdapter(adapter);

        //list瑜?異붽??섏옄 ?꾩떆濡?        room_list.add(new room_item(0,"name","address", "date", "time", "num"));
        room_list.add(new room_item(0,"name","address", "date", "time", "num"));
        room_list.add(new room_item(0,"name","address", "date", "time", "num"));
        room_list.add(new room_item(0,"name","address", "date", "time", "num"));
        room_list.add(new room_item(0,"name","address", "date", "time", "num"));
        room_list.add(new room_item(0,"name","address", "date", "time", "num"));
        room_list.add(new room_item(0,"name","address", "date", "time", "num"));
        room_list.add(new room_item(0,"name","address", "date", "time", "num"));
        room_list.add(new room_item(0,"name","address", "date", "time", "num"));


        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // info
                        //dialog, ?곸꽭?뺣낫
                        break;
                    case 1:
                        // open
                        //梨꾪똿諛??낆옣
                        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                        startActivity(intent);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_friend) {
            intent = new Intent(getApplicationContext(), FriendListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_schedule) {
            intent = new Intent(getApplicationContext(), ScheduleActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_ground) {
            intent = new Intent(getApplicationContext(), GroundActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    public class room_item{
        int img;
        String name;
        String address;
        String date;
        String phone;
        String time;

        public room_item(int img, String name, String date, String time, String address, String phone) {
            this.img = img;
            this.name = name;
            this.date = date;
            this.time = time;
            this.address = address;
            this.phone = phone;
        }
    }

    public class RoomAdapter extends BaseAdapter {
        ArrayList<room_item> list;
        int res;
        Context context;

        LayoutInflater inflater;
        public RoomAdapter(ArrayList<room_item> list, int res, Context context) {
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
            TextView r_name = (TextView) convertView.findViewById(R.id.rName_tv);
            r_name.setText(list.get(position).name);
            TextView r_time = (TextView) convertView.findViewById(R.id.rTime_tv);
            r_time.setText(list.get(position).time);
            TextView r_addr = (TextView) convertView.findViewById(R.id.rAddr_tv);
            r_addr.setText(list.get(position).address);
            TextView r_date = (TextView) convertView.findViewById(R.id.rDate_tv);
            r_date.setText(list.get(position).phone);


            return convertView;

        }
    }
}

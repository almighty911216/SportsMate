package git.comalmighty911216mustmeetnow.github.sportmate2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.icu.util.Calendar;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {
    ArrayList<sch_item> schedule_list = new ArrayList<>();
    ScheduleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setStatusBarColor(this, Color.parseColor("#F2DE00"));

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //list 임시 데이터 저장
        schedule_list.add(new sch_item(0,"name","address", "date", "time", "num"));
        schedule_list.add(new sch_item(0,"name","address", "date", "time", "num"));
        schedule_list.add(new sch_item(0,"name","address", "date", "time", "num"));
        schedule_list.add(new sch_item(0,"name","address", "date", "time", "num"));
        schedule_list.add(new sch_item(0,"name","address", "date", "time", "num"));

        ListView listView = (ListView) findViewById(R.id.schedule_list);
        adapter = new ScheduleAdapter(schedule_list, R.layout.schedulelist,this);
        listView.setAdapter(adapter);

        //CampactCalendar
        final CompactCalendarView compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        compactCalendar.setFirstDayOfWeek(Calendar.MONDAY);


        /*
        이벤트 추가하는 방법
        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
        Event ev1 = new Event(Color.GREEN, 1533701251000L, "Some extra data that I want to store.");
        compactCalendar.addEvent(ev1);

        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
        Event ev2 = new Event(Color.GREEN, 1533704251000L);
        compactCalendar.addEvent(ev2);

        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.
        List<Event> events = compactCalendar.getEvents(1433701251000L); // can also take a Date object
         */
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

    public class sch_item{
        int img;
        String name;
        String address;
        String date;
        String time;
        String num;

        public sch_item(int img, String name, String address, String date, String time, String num) {
            this.img = img;
            this.name = name;
            this.address = address;
            this.date = date;
            this.time = time;
            this.num = num;
        }
    }


    public class ScheduleAdapter extends BaseAdapter {
        ArrayList<sch_item> list;
        int res;
        Context context;

        LayoutInflater inflater;
        public ScheduleAdapter(ArrayList<sch_item> list, int res, Context context) {
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
            ImageView s_sport = (ImageView)convertView.findViewById(R.id.sSport_img);

            TextView s_name = (TextView) convertView.findViewById(R.id.sName_tv);
            s_name.setText(list.get(position).name);
            TextView s_addr = (TextView) convertView.findViewById(R.id.sAddr_tv);
            s_addr.setText(list.get(position).address);
            TextView s_date = (TextView) convertView.findViewById(R.id.sDate_tv);
            s_date.setText(list.get(position).date);
            TextView s_time = (TextView) convertView.findViewById(R.id.sTime_tv);
            s_time.setText(list.get(position).time);
            TextView s_num = (TextView) convertView.findViewById(R.id.sNum_tv);
            s_num.setText(list.get(position).num);

            return convertView;
        }
    }
}

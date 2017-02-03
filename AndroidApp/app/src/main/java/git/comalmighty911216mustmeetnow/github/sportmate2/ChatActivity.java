package git.comalmighty911216mustmeetnow.github.sportmate2;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import git.comalmighty911216mustmeetnow.github.sportmate2.AsyncThread.AsyncCallback;
import git.comalmighty911216mustmeetnow.github.sportmate2.Chatroom.ChatAdapter;
import git.comalmighty911216mustmeetnow.github.sportmate2.Chatroom.ChatItem;
import git.comalmighty911216mustmeetnow.github.sportmate2.Network.Client;
import git.comalmighty911216mustmeetnow.github.sportmate2.Network.Message;

public class ChatActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //서버
    private Client client;
    private ArrayList<Message> messageQueue;

    private Button btnSend;
    private EditText editTextInputChatMsg;
    private ChatAdapter chatAdapter;

    private void initialize()
    {
        chatAdapter = new ChatAdapter(this.getApplicationContext(),R.layout.chat_item);
        final ListView listView = (ListView)findViewById(R.id.ListVew_chatroom);
        listView.setAdapter(chatAdapter);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        // When message is added, it makes listview to scroll last message
        chatAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatAdapter.getCount()-1);
            }
        });

        client = new Client(chatAdapter);

        editTextInputChatMsg = (EditText) findViewById(R.id.EditText_InputChatMsg);

        btnSend = (Button)findViewById(R.id.Btn_Send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMsg = editTextInputChatMsg.getText().toString();
                chatAdapter.add(new ChatItem(strMsg));
                client.sendMsg(strMsg);
                editTextInputChatMsg.setText("");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolbar_Chat_bar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initialize();

        client.connectServer();

        int size = messageQueue.size();
        for(int i = 0; i < size; i++) {
            chatAdapter.add(messageQueue.get(i));
        }
        messageQueue.clear();
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
        getMenuInflater().inflate(R.menu.chat, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private AsyncCallback<Void> callbackSendMsg = new AsyncCallback<Void>() {
        @Override
        public void onResult(Void result) {

        }

        @Override
        public void exceptionOccured(Exception e) {

        }

        @Override
        public void cancelled() {

        }
    };
}


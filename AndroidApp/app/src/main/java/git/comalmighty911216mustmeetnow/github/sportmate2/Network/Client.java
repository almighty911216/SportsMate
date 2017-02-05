package git.comalmighty911216mustmeetnow.github.sportmate2.Network;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import git.comalmighty911216mustmeetnow.github.sportmate2.Chatroom.ChatAdapter;
import git.comalmighty911216mustmeetnow.github.sportmate2.Object.User;

/**
 * Created by almig on 2017-02-03.
 */

public class Client {
    public static final String TAG_CLIENT = "Client";
    public static final String SERVERIP = "127.0.0.1";
    public static final int MAINSERVERPORT = 9123;
    public static final int SERVERPORT = 8888;
    public InetAddress inetAddress;
    private Thread thread;
    private BufferedReader inMsg;
    private PrintWriter outMsg;
    private Socket socket;
    private Handler mHandler;

    String r_msg;
    Context context;
    Gson gson = new Gson();
    Message m;
    private boolean status;
    private User user;
    private ChatAdapter chatAdapter;

    //메인서버소켓
    private Socket mainSocket;
    private BufferedReader mainInMsg;
    private PrintWriter mainOutMsg;


    public Client(ChatAdapter _chatAdapter) {
        mainSocket = null;

        user = new User();
        user.setName("test");
        socket = null;
        chatAdapter = _chatAdapter;

        mHandler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                chatAdapter.add(m.getMsg().toString());
            }
        };
    }

    public void connectMainServer() {
        mainSocket = new Socket("192.168.43.111", 9123);
    }

    // 젤리빈 이상에서는 메인스레드에서 소켓통신 안됨 -> 별도 스레드로 처리
    public void connectServer() {
        new Thread() {
            public void run() {
                try {
                    socket = new Socket("192.168.43.111", 9999);
                    Log.d("[Client]"," Server connected !!");

                    inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    outMsg = new PrintWriter(socket.getOutputStream(), true);

                    // 서버에 로그인 메시지 전달
                    m = new Message("123","","","login");
                    outMsg.println(gson.toJson(m));

                    thread = new Thread(new ReceiveMsg());
                    thread.setDaemon(true);
                    thread.start();
                }
                catch(Exception e) {
                    e.printStackTrace();
                    Log.d("[MultiChatClient]"," connectServer() Exception !!");
                }
            }
        }.start();
    }

    public void sendMsg(final String msg) {
        // 메시지 전송
        outMsg.println(gson.toJson(new Message("Mun", "", msg, "msg")));
    }

    // 메시지 수신 쓰레드
    class ReceiveMsg implements Runnable {
        @Override
        public void run() {
            String msg;
            status=true;
            //무한루프를 돌면서 메시지 수신을 기다림
            while(status) {
                try{
					/*들어온 메시지가 있으면 메시지를 읽어서 파싱함 */

                    msg = inMsg.readLine();
                    m = gson.fromJson(msg, Message.class);

                    mHandler.sendEmptyMessage(0);
                }
                catch(IOException e) {
                    e.printStackTrace();
                    status = false;
                }
            }
            Log.d("[MultiChatClient]"," Stopped");
        }
    }
}
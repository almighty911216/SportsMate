package git.comalmighty911216mustmeetnow.github.sportmate2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;

import git.comalmighty911216mustmeetnow.github.sportmate2.Object.Gym;
import git.comalmighty911216mustmeetnow.github.sportmate2.Object.User;

public class MainServer {
	// 서버 소켓 및 클라이언트 연결 소켓 
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private static int SERVERPORT = 9648;
	private ArrayList<Gym> arrGym = new ArrayList<Gym>();
	private ArrayList<User> arrUser = new ArrayList<User>();
	private boolean state = false;
	
	// 연결된 클라이언트 스레드를 관리하기 위한 ArrayList
	private ArrayList<ChatThread> chatlist = new ArrayList<ChatThread>();
	private TreeMap<Integer, Socket> roomsocklist = new TreeMap<Integer, Socket>();
	private int sockno = 1;
	
	public void makeGymDB() {
		//임시 디비
		JSONParser parser = new JSONParser();
		
		Object obj = null;
		
		try {
			obj = parser.parse(new FileReader("C:\\Users\\almig\\Desktop\\SportsMate\\JavaChatServer\\gymlist.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray memberArray = (JSONArray) jsonObject.get("DATA");
		
		for(int i = 0; i < memberArray.size() ; i++) {
			JSONObject tempObj = (JSONObject) memberArray.get(i);
			Gym gym = new Gym();
			gym.setManageOrgan((String) tempObj.get("MANAGE_ORGAN"));
			gym.setAddr((String) tempObj.get("ADDRESS"));
			gym.setCenterName((String) tempObj.get("CENTER_NAME"));
			gym.setTel((String) tempObj.get("TEL"));
			gym.setUrl((String) tempObj.get("HOMEPAGE"));
		 
			arrGym.add(gym);
		}	
	}
	
	public void makeUserDB() {
		//임시 디비
		JSONParser parser = new JSONParser();
		
		Object obj = null;
		
		try {
			obj = parser.parse(new FileReader("C:\\Users\\almig\\Desktop\\SportsMate\\JavaChatServer\\userlist.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray memberArray = (JSONArray) jsonObject.get("DATA");
		
		for(int i = 0; i < memberArray.size() ; i++) {
			JSONObject tempObj = (JSONObject) memberArray.get(i);
			User user = new User();
			user.setName((String) tempObj.get("NAME"));
			user.setAddr((String) tempObj.get("ADDRESS"));
			user.setGender((String) tempObj.get("SEX"));
			user.setAge(Integer.parseInt((String)tempObj.get("AGE")));
			user.setId((String) tempObj.get("ID"));
			user.setFavorspor1((String) tempObj.get("FAVORSPOR1"));
			user.setFavorspor2((String) tempObj.get("FAVORSPOR2"));
			user.setFavorspor3((String) tempObj.get("FAVORSPOR3"));
		 
			arrUser.add(user);
		}
	}
	
	// 멀티챗 메인 프로그램 부
	public void start() {
		try {
			// 서버 소켓 생성
			serverSocket = new ServerSocket(SERVERPORT);
			System.out.println("server start");
			
			// 무한 루프를 돌면서 클라이언트 연결을 기다림
			while(true) {
				socket = serverSocket.accept();			
				System.out.println(socket.getInetAddress() + " " + socket.getPort() + " 참여");
				// 연결된 클라이언트에 대해 쓰레드 클래스 생성
				ChatThread chat = new ChatThread();
				// 클라이언트 리스트 추가
				chatlist.add(chat);
				// 스레드 시작
				chat.start();
			}
		} 
		catch (Exception e) {
			//System.out.println(e);
			System.out.println("[MultiChatServer]start() Exception 발생!!");
		}   
	} 
	
	// 연결된 모든 클라이언트에 메시지 중계
	void msgSendAll(String msg) {
		for(ChatThread ct : chatlist) {
			ct.outMsg.println(msg);
		}
	}
	
	// 연결된 모든 클라이언트에 메시지 중계
	void msgSend(String Id, String msg) {
		for(ChatThread ct : chatlist) {
		//	if(chatlist.get(index))
			ct.outMsg.println(msg);
		}
	}

	// 각각의 클라이언트 관리를 위한 쓰레드 클래스
	class ChatThread extends Thread {
		// 수신 메시지 및 파싱 메시지 처리를 위한 변수 선언
		String msg;

		Message m = new Message();
		Gson gson = new Gson();
		// 입출력 스트림
		private BufferedReader inMsg = null;
		private PrintWriter outMsg = null;

		public void run() {
		
			boolean status = true;
			System.out.println("##ChatThread start...");
			try {
				// 입출력 스트림 생성
				inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outMsg = new PrintWriter(socket.getOutputStream(),true);
				
				// 상태정보가 true 이면 루프를 돌면서 사용자로 부터 수신된 메시지 처리
				while(status) {
					// 수신된 메시지를 msg 변수에 저장
					msg = inMsg.readLine();
					
					// JSON 메시지를 Message 객체로 매핑
					m = gson.fromJson(msg, Message.class);
												
					// 파싱된 문자열 배열의 두번째 요소 값에 따라 처리
					// 로그아웃 메시지 인 경우
					if(m.getType().equals("logout")) {
						chatlist.remove(this);
						msgSendAll(gson.toJson(new Message(m.getId(), "", (String)" 님이 로그아웃 하셨습니다.", "server")));
						// 해당 클라이언트 스레드 종료로 인해 status 를 false 로 설정
						status = false;
					}
					// 로그인할 경우
					else if(m.getType().equals("login")) {
						msgSendAll(gson.toJson(new Message(m.getId(), "", (String)" 님이 로그인 했습니다.", "server")));
					}
					// 방을 만들 경우
					else if(m.getType().equals("create")) {
						msgSendAll(gson.toJson(new Message(m.getId(), "", m.getMsg(), "create")));
					}
					// 방에 들어갈 경우
					else if(m.getType().equals("enter")) {
						msgSendAll(gson.toJson(new Message(m.getId(), "", (String)" 님이 입장 하셨습니다.", "server")));
					}
					// 방에 들어갈 경우
					else if(m.getType().equals("msg")) {
						msgSendAll(gson.toJson(new Message(m.getId(), "", m.getMsg(), "msg")));
					}
					// 그밖의 경우 즉 일반 메시지인 경우
					else {
						msgSendAll(msg);
					}
				}
				// 루프를 벗어나면 클라이언트 연결 종료 이므로 스레드 인터럽트
				this.interrupt();
				System.out.println("##"+this.getName()+"stop!!");
			} catch (IOException e) {
				chatlist.remove(this);
				//e.printStackTrace();
				System.out.println("[ChatThread]run() IOException 발생!!");
			}
		}
	}
}

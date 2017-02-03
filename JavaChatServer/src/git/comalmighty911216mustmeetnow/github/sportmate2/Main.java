package git.comalmighty911216mustmeetnow.github.sportmate2;

public class Main {
	public static void main(String[] args){
		MainServer mainServer = new MainServer();
		mainServer.start();
		
		MultiChatServer server = new MultiChatServer();
		server.start();
	}
}

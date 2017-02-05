package git.comalmighty911216mustmeetnow.github.sportmate2;

public class Main {
	public static void main(String[] args){
		MainServer mainServer = new MainServer();
		mainServer.makeGymDB();
		mainServer.makeUserDB();
		mainServer.start();
	}
}

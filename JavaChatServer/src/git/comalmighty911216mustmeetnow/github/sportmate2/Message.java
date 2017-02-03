package git.comalmighty911216mustmeetnow.github.sportmate2;

public class Message {
	private String id;
	private String passwd;
	private String msg;
	private String type;
	
	public Message() {}
	
	public Message(String id, String passwd, String msg, String type) {
		this.id = id;
		this.passwd = passwd;
		this.msg = msg;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPassword(String passwd) {
		this.passwd = passwd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

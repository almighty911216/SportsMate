package git.comalmighty911216mustmeetnow.github.sportmate2;

public class Message {
	private String id;
	private String name;
	private Object msg;
	private String type;
	
	public Message() {}
	
	public Message(String id, String name, Object msg, String type) {
		this.id = id;
		this.name = name;
		this.msg = msg;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

package git.comalmighty911216mustmeetnow.github.sportmate2.Object;

import java.util.ArrayList;

public class User {
    private String id;
    private String name;
    private String favorspor1;
    private String favorspor2;
    private String favorspor3;
    private int age;
    private String gender;
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

	public String getFavorspor1() {
		return favorspor1;
	}

	public void setFavorspor1(String favorspor1) {
		this.favorspor1 = favorspor1;
	}

	public String getFavorspor2() {
		return favorspor2;
	}

	public void setFavorspor2(String favorspor2) {
		this.favorspor2 = favorspor2;
	}

	public String getFavorspor3() {
		return favorspor3;
	}

	public void setFavorspor3(String favorspor3) {
		this.favorspor3 = favorspor3;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public ArrayList<User> getListFriend() {
		return listFriend;
	}

	public void setListFriend(ArrayList<User> listFriend) {
		this.listFriend = listFriend;
	}

	private String addr;
    
    private ArrayList<User> listFriend;
}


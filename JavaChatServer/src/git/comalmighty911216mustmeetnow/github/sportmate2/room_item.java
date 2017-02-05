package git.comalmighty911216mustmeetnow.github.sportmate2;

import java.io.Serializable;

@SuppressWarnings("serial")
public class room_item implements Serializable{

    String img;
    String name;
    String super_user;
    String address;
    String date;
    String time;
    String num;
    public room_item(String img, String name, String super_user, String address, String date, String time,String phone ) {
        this.img = img;
        this.name = name;
        this.super_user = super_user;
        this.address = address;
        this.date = date;
        this.time = time;
        this.num = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuper_user() {
        return super_user;
    }

    public void setSuper_user(String super_user) {
        this.super_user = super_user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
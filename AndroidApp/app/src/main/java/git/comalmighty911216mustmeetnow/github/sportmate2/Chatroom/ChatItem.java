package git.comalmighty911216mustmeetnow.github.sportmate2.Chatroom;

/**
 * Created by almig on 2017-02-03.
 */

public class ChatItem {
    private String msg;
    private boolean position;

    public boolean getPosition() {
        return position;
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

    public ChatItem(String _msg) {
        msg = _msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

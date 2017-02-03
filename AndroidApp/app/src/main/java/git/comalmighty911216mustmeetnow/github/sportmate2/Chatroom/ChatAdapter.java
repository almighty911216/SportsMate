package git.comalmighty911216mustmeetnow.github.sportmate2.Chatroom;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import git.comalmighty911216mustmeetnow.github.sportmate2.R;

/**
 * Created by almig on 2017-02-03.
 */

public class ChatAdapter extends ArrayAdapter {
    private List<ChatItem> msgs = new ArrayList();

    public ChatAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    //@Override
    public void add(Object object) {
        msgs.add((ChatItem) object);
        super.add(object);
    }



    @Override
    public int getCount() {
        return msgs.size();
    }

    @Override
    public ChatItem getItem(int index) {
        return msgs.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            // inflator를 생성하여, chatting_message.xml을 읽어서 View객체로 생성한다.
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.chat_item, parent, false);
        }

        // Array List에 들어 있는 채팅 문자열을 읽어
        ChatItem msg = msgs.get(position);

        // Inflater를 이용해서 생성한 View에, ChatMessage를 삽입한다.
        ImageView imageView = (ImageView)row.findViewById(R.id.ImageView_ChatProfile);
        imageView.setImageResource(R.drawable.people);

        TextView msgText = (TextView) row.findViewById(R.id.TextView_ChatMsg);
        msgText.setText(msg.getMsg());
        msgText.setTextColor(Color.parseColor("#000000"));

        return row;
    }
}

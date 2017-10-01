package com.quickpost.joshk.quickpost;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quickpost.joshk.quickpost.Models.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GIGABYTE-U on 9/30/2017.
 *
 * Adapter for binding the message objects to the recylcerview
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Message> messageList = new ArrayList<>();

    public MessageAdapter(Context context, Object response) {
        if (response instanceof Exception){

        } else if (response instanceof String){
            try {
                JSONArray jArray = new JSONArray(response.toString());
                for(int i = jArray.length() - 1; i >= 0; i--){
                    JSONObject jo = jArray.getJSONObject(i);
                    messageList.add(new Message(jo.get("msg").toString(), jo.get("user").toString(), jo.get("receivedTS").toString()));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageHolder(mLayoutInflater.inflate(R.layout.message_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        holder.bindMessage(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder {
        private TextView userName;
        private TextView dateTime;
        private TextView message;

        public MessageHolder(View v){
            super(v);
            userName = (TextView) v.findViewById(R.id.username);
            dateTime = (TextView) v.findViewById(R.id.date);
            message = (TextView) v.findViewById(R.id.message_body);
        }

        public void bindMessage(Message msg){
            userName.setText(msg.getUser());
            dateTime.setText(msg.getSentTimeStamp());
            message.setText(msg.getMsg());
        }
    }
}

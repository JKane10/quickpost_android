package com.quickpost.joshk.quickpost.Services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.quickpost.joshk.quickpost.Common.Constants;
import com.quickpost.joshk.quickpost.Models.Message;
import com.quickpost.joshk.quickpost.Events.MessageLoadedEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by GIGABYTE-U on 10/1/2017.
 *
 * Service for communicating messages between client and server.
 */

public class MessageService {

    private RequestQueue queue;
    private static MessageService instance;

    private MessageService(Context context){
        queue = Volley.newRequestQueue(context);
    }

    public static MessageService getInstance(Context context){
        if (instance == null){
            instance = new MessageService(context);
        }
        return instance;
    }

    public void postMessage(String input){

        Message msgObj = new Message(input,"test_user", Calendar.getInstance().getTime().toString());
        JSONObject requestBody = msgObj.toJson();

        Log.d("MessageService - POST", requestBody.toString());

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Constants.serverAddress + Constants.postMessageEndPoint, requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getMessages();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                EventBus.getDefault().post(new MessageLoadedEvent(error.toString()));
            }
        });
        queue.add(postRequest);
    }

    public void getMessages(){
        Log.d("MessageService - GET", "");
        StringRequest request = new StringRequest(Request.Method.GET, Constants.serverAddress + Constants.getMessagesEndPoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                EventBus.getDefault().post(new MessageLoadedEvent(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                EventBus.getDefault().post(new MessageLoadedEvent(error.toString()));
            }
        });
        queue.add(request);
    }
}

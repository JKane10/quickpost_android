package com.quickpost.joshk.quickpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.quickpost.joshk.quickpost.Events.MessageLoadedEvent;
import com.quickpost.joshk.quickpost.Services.MessageService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * The one and only Activity so far, just dumping everything here until I build the app out a bit more.
 * aka...over engineering will come later! Laziness comes now.
 *
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MessageService messageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //configure recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        messageService = MessageService.getInstance(this);

        EventBus.getDefault().register(this);

        loadMessages();
        bindButton();
    }

    private void loadMessages(){
        messageService.getMessages();
    }

    private void bindButton(){
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) findViewById(R.id.button);
                EditText input = (EditText) findViewById(R.id.message_input);
                if(btn.getText().equals(getString(R.string.btn_write))){
                    input.setVisibility(View.VISIBLE);
                    input.requestFocus();

                    btn.setText(getString(R.string.btn_post));
                } else {
                    input.clearFocus();
                    input.setVisibility(View.GONE);
                    btn.setText(getString(R.string.btn_write));

                    messageService.postMessage(input.getText().toString());
                    input.setText("");
                }
            }
        });
    }

    @Subscribe
    public void bindMessages(MessageLoadedEvent event){
        mAdapter = new MessageAdapter(getApplicationContext(), event.getMessage());
        mRecyclerView.setAdapter(mAdapter);
    }
}

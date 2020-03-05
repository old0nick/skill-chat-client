package com.example.cryptochat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static String myName = "";

    RecyclerView chatWindow;
    private MessageController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your name");
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myName = input.getText().toString();
                addTestMessages();
            }
        });
        builder.show();

        chatWindow = findViewById(R.id.chatWindow);

        controller = new MessageController();

        controller
                .setIncomingLayout(R.layout.incoming_message)
                .setOutgoingLayout(R.layout.outgoing_message)
                .setMessageTextId(R.id.messageText)
                .setMessageTimeId(R.id.messageTime)
                .setUserNameId(R.id.userName)
                .appendTo(chatWindow, this);

        final EditText chatInput = findViewById(R.id.chatInput);
        Button sendMessage = findViewById(R.id.sendMessage);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = chatInput.getText().toString();
                controller.addMessage(
                        new MessageController.Message(text, myName, true)
                );
                chatInput.setText("");
            }
        });


    }

    private void addTestMessages() {
        controller.addMessage(
                new MessageController.Message("Всем приветы в этом чате! Очень рад узнать наконец как разрабатываются приложения под Android=)))))", "Лунатик", false)
        );
        controller.addMessage(
                new MessageController.Message("И тебе привет", myName, true)
        );
    }
}
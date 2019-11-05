package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class level_1 extends AppCompatActivity {
    private EditText serverIp;

    private Button connectPhones;

    private String serverIpAddress = "";

    private boolean connected = false;

    private Handler handler = new Handler();
    int lat = new Random().nextInt(11);
    int log = new Random().nextInt(11);
    String log2   = String.valueOf(log) ;
    String lat2   = String.valueOf(lat) ;

    Context context ;

    int duration ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_1);
        FirebaseApp.initializeApp(this);
        lat = new Random().nextInt(11);
        log = new Random().nextInt(11);
        log2   = String.valueOf(log) ;
        lat2   = String.valueOf(lat) ;
        context = getApplicationContext();
        duration = Toast.LENGTH_SHORT;




        connectPhones = (Button) findViewById(R.id.button);
        connectPhones.setOnClickListener(connectListener);


    }


    private View.OnClickListener connectListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!connected) {
                //  serverIpAddress = "122.33.455";
                // if (!serverIpAddress.equals("")) {
                EditText text_box = (EditText) findViewById(R.id.edtInput);
                String text = text_box.getEditableText().toString();
                Toast.makeText(context, text + ", help is on the way", duration).show();
                Thread cThread = new Thread(new ClientThread());
                cThread.start();
                // }
            }
        }
    };
    public class ClientThread implements Runnable {

        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
                Log.d("ClientActivity", "C: Connecting...");
                Socket socket = new Socket("192.168.4.1",5000);
                connected = true;
                while (connected) {
                    try {
                        Log.d("ClientActivity", "C: Sending command.");
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                        // where you issue the commands
                        EditText text_box = (EditText) findViewById(R.id.edtInput);
                        String text = text_box.getEditableText().toString();

                        // where you issue the commands


                        out.println("Low -97.7402"+log2+" 30.2815"+lat2+" "+text);

                        // Log.d("ClientActivity", "C: Sent.");
                    } catch (Exception e) {
                        //Log.e("ClientActivity", "S: Error", e);
                    }
                }
                socket.close();
                Log.d("ClientActivity", "C: Closed.");
            } catch (Exception e) {
                Log.e("ClientActivity", "C: Error", e);
                connected = false;
            }
        }
    }
}

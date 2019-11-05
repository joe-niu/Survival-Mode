package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
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

public class level_3 extends AppCompatActivity {
    private EditText serverIp;

    private Button connectPhones;

    private String serverIpAddress = "";

    private boolean connected = false;

    private Handler handler = new Handler();
    double longitude;
    double latitude;
    int lat = new Random().nextInt(11);
    int log = new Random().nextInt(11);
    String log2   = String.valueOf(log) ;
    String lat2   = String.valueOf(lat) ;
    Context context ;
    int duration ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_3);
        FirebaseApp.initializeApp(this);

        connectPhones = (Button) findViewById(R.id.button);
        connectPhones.setOnClickListener(connectListener);
        lat = new Random().nextInt(11);
        log = new Random().nextInt(11);
        log2   = String.valueOf(log) ;
        lat2   = String.valueOf(lat) ;
        context = getApplicationContext();
        duration = Toast.LENGTH_SHORT;
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
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                                .getOutputStream())), true);
                        EditText text_box = (EditText) findViewById(R.id.edtInput);
                        String text = text_box.getEditableText().toString();
                        // where you issue the commands

                        out.println("High -97.7402" +log2 +"30.2815"+lat2+" "+text);
                        Log.d("ClientActivity", "C: Sent.");
                    } catch (Exception e) {
                        Log.e("ClientActivity", "S: Error", e);
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

    public void Send (View view){
        EditText text_box = (EditText) findViewById(R.id.edtInput);
        String text = text_box.getEditableText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("peeps").document("read-me").update("threat","Severe 30.281580 -97.740220");
        Toast.makeText(getApplicationContext(), "please keep app open",Toast.LENGTH_SHORT).show();

    }
}

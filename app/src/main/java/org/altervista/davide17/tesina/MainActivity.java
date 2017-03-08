package org.altervista.davide17.tesina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.altervista.davide17.tesina.logic.Client;
import org.altervista.davide17.tesina.logic.OnClientMessageRead;
import org.altervista.davide17.tesina.logic.Server;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //da qui COPIARE
    private String ip;//ip rasp
    private int port = 9898;//NON TOCCARE
    TextView listaDati;
    EditText editText;
    Client client;
    ArrayList<Double> tempi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDati = (TextView) findViewById(R.id.listaDati);
        editText = (EditText) findViewById(R.id.editText);
        tempi = new ArrayList<Double>();

        Button button = (Button)findViewById(R.id.collega);//id button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClient();
            }
        });

        Button button2 = (Button)findViewById(R.id.carica);//id button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),dati.class);
                startActivity(intent);

            }
        });

    }


    //Funzione temporale
    private void startServer(){
        Server server = new Server(port);
        server.start();
    }

    private void startClient(){
        ip = editText.getText().toString();
        System.out.println(ip);

        Thread startClientT = new Thread(new Runnable() {
            @Override
            public void run() {
                //Se serve far partire server sul telefono
                //startServer();

                client = new Client(ip, port);
                //Dentro onMessageRead fai quello che devi fare con i messagi che arrivano
                client.onClientMessageRead = new OnClientMessageRead() {
                    @Override
                    public void onMessageRead(final String message) {
                        //QUI dovrei ricevere tre messaggi separati (1, 2, 3) da ECLIPSE
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listaDati.setText(listaDati.getText().toString() + "\n" + message);
                                System.out.println("Ricevo: " + message);
                            }
                        });
                    }
                };
                //Per mandare un messaggio
                client.write("dati");
            }
        });
        startClientT.start();
    }
    //a qui
}
package cl.tgdtx1000.macrosolutions.gdtx_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class Visor extends AppCompatActivity {

    private Context thisContext = this;
    private TextView lblValores;
    private Button btnGuardar;

    final String ON = "1";
    final String OFF = "0";

    BluetoothSPP bluetooth;

    Button connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        lblValores = (TextView) findViewById(R.id.lblValores);

        bluetooth = new BluetoothSPP(this);

        connect = (Button) findViewById(R.id.connect);

        if (!bluetooth.isBluetoothAvailable()) {
            Toast.makeText(getApplicationContext(), "El Bluetooth no esta disponible", Toast.LENGTH_SHORT).show();
            finish();
        }

        bluetooth.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceConnected(String name, String address) {
                connect.setText("Conectado a " + name);
            }

            public void onDeviceDisconnected() {
                connect.setText("Conexión Perdida");
            }

            public void onDeviceConnectionFailed() {
                connect.setText("No se puede Conectar");
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bluetooth.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });

        bluetooth.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            public void onDataReceived(byte[] data, String message) {
                Log.d("Data value : " + data.toString() ,"Message : " + message);

                lblValores.setText(message);
            }
        });

    }

    public void onStart() {
        super.onStart();
        if (!bluetooth.isBluetoothEnabled()) {
            bluetooth.enable();
        } else {
            if (!bluetooth.isServiceAvailable()) {
                bluetooth.setupService();
                bluetooth.startService(BluetoothState.DEVICE_OTHER);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        bluetooth.stopService();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bluetooth.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bluetooth.setupService();
            } else {
                Toast.makeText(getApplicationContext()
                        , "El Bluetooth esta desactivado."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Particulas particulas = new Particulas(thisContext, "dbParticulas.db", null, 1);

                particulas.Insert(lblValores.getText().toString(), new Date().getDate() + "/" + (new Date().getMonth() + 1) + "/" + (new Date().getYear() + 1900) + " " + new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());

                ArrayAdapter<CParticulas> arrayAdapter = new ArrayAdapter<CParticulas>(thisContext,android.R.layout.simple_list_item_1, particulas.getAll());


            }

        });


    }

}

package com.example.tsc_printer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tscdll.TSCActivity;

public class MainActivity extends AppCompatActivity {
    TSCActivity TscDll = new TSCActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mPrint = findViewById(R.id.print_bt);
        mPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.TscDll.openport("00:19:0E:A4:DC:36");
                MainActivity.this.TscDll.sendcommand("SIZE 4, 0.2\r\n");
                MainActivity.this.TscDll.sendcommand("GAP 0 mm, 0 mm\r\n");
                MainActivity.this.TscDll.clearbuffer();
//                MainActivity.this.TscDll.sendcommand("SPEED 4\r\n");
//                MainActivity.this.TscDll.sendcommand("DENSITY 12\r\n");
//                MainActivity.this.TscDll.sendcommand("CODEPAGE UTF-8\r\n");
//                MainActivity.this.TscDll.sendcommand("SET TEAR ON\r\n");
//                MainActivity.this.TscDll.sendcommand("SET COUNTER @1 1\r\n");
//                MainActivity.this.TscDll.sendcommand("@1 = \"0001\"\r\n");
                MainActivity.this.TscDll.sendcommand("TEXT 0,0,\"3\",0,1,1,\"ABCDE\"\r\n");
//                MainActivity.this.TscDll.sendcommand("TEXT 100,400,\"ROMAN.TTF\",0,12,12,\"TEST FONT\"\r\n");
                MainActivity.this.TscDll.printlabel(1, 1);
                MainActivity.this.TscDll.sendcommand("SIZE 4, 0.2\r\n");
                MainActivity.this.TscDll.clearbuffer();
                MainActivity.this.TscDll.sendcommand("TEXT 0,0,\"3\",0,1,1,\"EFGH\"\r\n");
                MainActivity.this.TscDll.printlabel(1, 1);
                MainActivity.this.TscDll.closeport(5000);
            }
        });


    }



    public byte[] setup(int width, int height, int speed, int density, int sensor, int sensor_distance, int sensor_offset) {
        String message = "";
        String size = "SIZE " + width + " mm" + ", " + height + " mm";
        String speed_value = "SPEED " + speed;
        String density_value = "DENSITY " + density;
        String sensor_value = "";
        if (sensor == 0) {
            sensor_value = "GAP " + sensor_distance + " mm" + ", " + sensor_offset + " mm";
        } else if (sensor == 1) {
            sensor_value = "BLINE " + sensor_distance + " mm" + ", " + sensor_offset + " mm";
        }

        message = size + "\r\n" + speed_value + "\r\n" + density_value + "\r\n" + sensor_value + "\r\n";
        return message.getBytes();
    }
}
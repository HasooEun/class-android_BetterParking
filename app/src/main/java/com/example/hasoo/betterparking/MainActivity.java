package com.example.hasoo.betterparking;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView tvCarType[] = new TextView[4];
    TextView tvCarNum[] = new TextView[4];
    TextView tvInTime[] = new TextView[4];
    Button bInCar[] = new Button[4];
    Button bOutCar[] = new Button[4];

    ParkingInfo parkingInfo[] = new ParkingInfo[4];
    int parkNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0; i<4; i++){
            parkingInfo[i] = new ParkingInfo();
        }

        tvCarType[0] = (TextView)findViewById(R.id.textView);
        tvCarType[1] = (TextView)findViewById(R.id.textView4);
        tvCarType[2] = (TextView)findViewById(R.id.textView7);
        tvCarType[3] = (TextView)findViewById(R.id.textView10);

        tvCarNum[0] = (TextView)findViewById(R.id.textView2);
        tvCarNum[1] = (TextView)findViewById(R.id.textView5);
        tvCarNum[2] = (TextView)findViewById(R.id.textView8);
        tvCarNum[3] = (TextView)findViewById(R.id.textView11);

        tvInTime[0] = (TextView)findViewById(R.id.textView3);
        tvInTime[1] = (TextView)findViewById(R.id.textView6);
        tvInTime[2] = (TextView)findViewById(R.id.textView9);
        tvInTime[3] = (TextView)findViewById(R.id.textView12);

        bInCar[0] = (Button)findViewById(R.id.button);
        bInCar[1] = (Button)findViewById(R.id.button3);
        bInCar[2] = (Button)findViewById(R.id.button5);
        bInCar[3] = (Button)findViewById(R.id.button7);

        bOutCar[0] = (Button)findViewById(R.id.button2);
        bOutCar[1] = (Button)findViewById(R.id.button4);
        bOutCar[2] = (Button)findViewById(R.id.button6);
        bOutCar[3] = (Button)findViewById(R.id.button8);
    }

    public void onInCarClick(View v){

        if(v.getId() == R.id.button) parkNumber = 0;
        else if(v.getId() == R.id.button3) parkNumber = 1;
        else if(v.getId() == R.id.button5) parkNumber = 2;
        else if(v.getId() == R.id.button7) parkNumber = 3;

        final View dlgView = View.inflate(this, R.layout.incar_info, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("입차정보");
        dlg.setView(dlgView);
        dlg.setNegativeButton("취소", null);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText = (EditText)dlgView.findViewById(R.id.editText);
                EditText editText2 = (EditText)dlgView.findViewById(R.id.editText2);

                String sCarType = editText.getText().toString();
                String sCarNum = editText2.getText().toString();
                Date dTime = new Date();
                long lInTime = dTime.getTime();
                parkingInfo[parkNumber].setParkingTime(sCarType, sCarNum, lInTime);
                bInCar[parkNumber].setEnabled(false);
                bOutCar[parkNumber].setEnabled(true);
                printInformation();
            }
        });
        dlg.show();
    }

    public void onOutCarClick(View v){
        if(v.getId() == R.id.button2) parkNumber = 0;
        else if(v.getId() == R.id.button4) parkNumber = 1;
        else if(v.getId() == R.id.button6) parkNumber = 2;
        else if(v.getId() == R.id.button8) parkNumber = 3;
        Date dTime = new Date();
        long lOutTime = dTime.getTime();
        long lElapsedTime = lOutTime - parkingInfo[parkNumber].getParkingTime();
        int iElapsedMinute = (int)(lElapsedTime/(1000 * 60));
        int price = (iElapsedMinute % 30 + 1) * 1000;
        String sPrice = "주차시간 " + iElapsedMinute + "분의 주차요금은 "
                + price + "원 입니다.";
        Toast.makeText(getApplicationContext(), sPrice, Toast.LENGTH_SHORT).show();

        // cleaning
        parkingInfo[parkNumber] = new ParkingInfo();
        bInCar[parkNumber].setEnabled(true);
        bOutCar[parkNumber].setEnabled(false);
        printInformation();
    }

    private void printInformation(){

        String sCarType = "차종류: " + parkingInfo[parkNumber].getCarType();
        String sCarNum = "차번호: " + parkingInfo[parkNumber].getCarNum();
        long lTime = parkingInfo[parkNumber].getParkingTime();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        String sTime = "입차시간: " + (lTime==0?"":sdfDate.format(lTime));

        tvCarType[parkNumber].setText(sCarType);
        tvCarNum[parkNumber].setText(sCarNum);
        tvInTime[parkNumber].setText(sTime);
    }
}

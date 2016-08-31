package com.example.hz.memory;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText mOldTV;
    private TextView mNewTV;
    private Button mStartBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOldTV = (EditText) findViewById(R.id.old_tv);
        mNewTV = (TextView) findViewById(R.id.new_tv);
        mStartBtn = (Button) findViewById(R.id.start_format_bt);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Long oldValue = Long.parseLong(mOldTV.getText().toString());
                Long   oldValue = 1410335L;
//                Long   oldValue =1024*1024L;
                String str = getFormatedMemory(oldValue);
                mNewTV.setText(str);
            }
        });

    }

    public String getFormatedMemory(long memoryK) {
        long memoryM = memoryK / 1024;
        if (memoryM < 1024) {  //小于1024M即1G。用单位M。直接返回。
            return memoryM + "M";
        }

        float memoryG = memoryM / 1024f;
        if (memoryG == (int) memoryG) {
            return String.format("%.0fG", memoryG);
        } else {
            return String.format("%.3fG", memoryG);
        }


    }

    public String getFormatedMemory2(long memoryK) {
        long memoryM = memoryK / 1024;
        if (memoryM < 1024) {  //小于1024M即1G。用单位M。直接返回。
            return memoryM + "M";
        }

//能进下面表明：memoryM>1024G,设memoryM=1.345G=1.345*1024M=1410335K
        long memoryGTenTimes = memoryM * 10 / 1024;    //memoryGTenTimes=13.45表明G的10倍数.
        long memoryG = memoryGTenTimes / 10;           //memoryG=1G.
        if (memoryGTenTimes % 10 != 0) {               //memoryGTenTimes % 10 =13.45 % 10=3  ！= 0进入if
            return memoryG + "." + (memoryGTenTimes % 10) + "G";
                    //1.              3                     G
        } else { //不含小数，直接返回G。
            return memoryG + "G";
        }

    }
}

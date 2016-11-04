package lly.ad.messagetest;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Message> smList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smList = new ArrayList<Message>();
        for(int i = 0;i < 10;i++){
            Message sms = new Message("blabla"+1, SystemClock.currentThreadTimeMillis()+"","138"+i+i,"1");
            smList.add(sms);
        }
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetMessage();
            }
        });
    }

    protected void GetMessage(){
        File file = new File(getExternalCacheDir()+"sms.txt");
        StringBuffer sb = new StringBuffer();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            sb.append("<message>");
            for (Message sms : smList) {
                sb.append("<sms>");
                sb.append("<body>");
                sb.append(sms.getBody());
                sb.append("</body");
                sb.append("<date>");
                sb.append(sms.getDate());
                sb.append("</date");
                sb.append("<address>");
                sb.append(sms.getAddress());
                sb.append("</address");
                sb.append("<type>");
                sb.append(sms.getType());
                sb.append("</type");
                sb.append("</sms>");
            }
            sb.append("/message");
            fileOutputStream.write(sb.toString().getBytes());
            fileOutputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

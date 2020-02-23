package com.example.forline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoViewActivity extends AppCompatActivity {

    private EditText title_et;
    private EditText content_et;
    private TextView time_tv;
    private Button modify_btn;
    private ImageButton delete_btn;

    private Date today = new Date();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy. MM. dd");
    private SimpleDateFormat format2 = new SimpleDateFormat("yyyy. MM. dd. HH. mm. ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_view);

        title_et = (EditText)findViewById(R.id.edit_title_view);
        content_et = (EditText)findViewById(R.id.edit_content_view);
        time_tv = (TextView)findViewById(R.id.time_tv_view);
        modify_btn = (Button) findViewById(R.id.modify_btn);
        delete_btn = (ImageButton)findViewById(R.id.delete_memo_btn);

        final Intent intent = getIntent();
        title_et.setText(intent.getExtras().getString("title"));
        content_et.setText(intent.getExtras().getString("content"));
        time_tv.setText(intent.getExtras().getString("time"));


        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title_et.isEnabled()) {
                    title_et.setEnabled(false);
                    content_et.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"수정을 완료하였습니다.", Toast.LENGTH_LONG).show();
                    saveMemosToFile(new Memo(title_et.getText().toString(), content_et.getText().toString(), format.format(today),format2.format(today))
                    ,intent.getExtras().getString("detime") );
                }
                else{
                    title_et.setEnabled(true);
                    content_et.setEnabled(true);
                    Toast.makeText(getApplicationContext(),"수정이 가능합니다", Toast.LENGTH_LONG).show();
                    modify_btn.setText("수정완료하기");
                }
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dirPath = getFilesDir().getPath();
                dirPath = dirPath.replace("files", "memo_history");
                File file = new File(dirPath +"/" + intent.getExtras().getString("detime")  + ".txt");

                if(file.exists()){
                    file.delete();
                }
                finish();
            }
        });
    }

    private void saveMemosToFile(Memo memo, String detime)  {
        String dirPath = getFilesDir().getPath();
        dirPath = dirPath.replace("files", "memo_history");
        File file = new File(dirPath +"/" + detime + ".txt");

        if(file.exists()){
            file.delete();
        }

        file = new File(dirPath);

        // 일치하는 폴더가 없으면 생성
        if( !file.exists()){
            file.mkdirs();
            Toast.makeText(getApplicationContext(), "폴더 생성", Toast.LENGTH_SHORT).show();
        }
        Log.v(null, "fileNmae : " + dirPath);
        // txt 파일 생성

        String title = "<title>\n" + memo.getTitle() + "\n";
        String content = "<content>\n" + memo.getContent() + "\n";
        String time = "<time>\n" + memo.getCurrent_time() + "\n";
        detime = "<detime>\n" + memo.getDetime() + "\n";


        File saveFile = new File(dirPath  + "/" + memo.getDetime() + ".txt");
        try{
            FileOutputStream fos = new FileOutputStream(saveFile);
            fos.write(title.getBytes());
            fos.write(content.getBytes());
            fos.write(time.getBytes());
            fos.write(detime.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(),"저장 성공", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.getStackTrace();
        }
    }
    @Override
    public void onBackPressed(){
        finish();
    }
}

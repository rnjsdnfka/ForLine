package com.example.forline;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoEditActivity extends AppCompatActivity {

    private Button saveBtn;
    private EditText title_et;
    private EditText content_et;

    private Date today = new Date();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy. MM. dd");
    private SimpleDateFormat format2 = new SimpleDateFormat("yyyy. MM. dd. HH. mm. ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_edit);

        saveBtn = (Button) findViewById(R.id.save_btn);
        title_et = (EditText) findViewById(R.id.edit_title);
        content_et = (EditText) findViewById(R.id.edit_content);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveMemosToFile(new Memo(title_et.getText().toString(), content_et.getText().toString(), format.format(today),format2.format(today)));
                finish();
            }
        });

    }

    private void saveMemosToFile(Memo memo)  {
        String dirPath = getFilesDir().getPath();
        dirPath = dirPath.replace("files", "memo_history");
        File file = new File(dirPath);

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
        String detime = "<detime>\n" + memo.getDetime() + "\n";


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

package com.example.forline;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //파일 명은 시간과 내용
    private final String fileName = "items.list" ;

    //RecyclerView를 사용하기 위한 변수들을 선언
    private RecyclerView recyclerView;
    private MemoListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Memo> memos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.memo_recyclerview);

        //onCreate() 함수에서 RecyclerView와 MemoListAdapter를 초기화 한다.
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MemoListAdapter(getApplicationContext(), memos, R.layout.activity_main);
        memos.add(new Memo("우람","자야겠다;;"));
        // specify an adapter (see also next example)
//        mAdapter = new MemoListAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        saveMemosToFile();
    }

    private void saveMemosToFile()  {
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
        String testStr = "제발제발";
        File saveFile = new File(dirPath  + "/" + "first3.txt");
        try{
            FileOutputStream fos = new FileOutputStream(saveFile);
            fos.write(testStr.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(),"저장 성공", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.getStackTrace();
        }

        // 파일이 1개 이상이면 파일 이름 출력
        if( file.listFiles().length > 0)
            for(File f : file.listFiles()){
                String str = f.getName();
                Log.v(null, "fi2123leNmae : " + str);

                // 파일 내용 읽어 오기
                String loadPath = dirPath + "/" + str;
                try{
                    FileInputStream fis = new FileInputStream(loadPath);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));

                    String content = "", temp = "";
                    while( (temp = bufferedReader.readLine()) != null){
                        content += temp;
                    }
                    Log.v(null,"" + content);
                } catch (Exception e){
                    e.getStackTrace();
                }
            }


    }
}

package com.example.forline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MemoListActivity extends AppCompatActivity {


    //RecyclerView를 사용하기 위한 변수들을 선언
    private RecyclerView recyclerView;
    private MemoListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Memo> memos = new ArrayList<>();

    //메모 추가하기 버튼을 위한 변수 선언
    private ImageButton memoAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        recyclerView = (RecyclerView) findViewById(R.id.memo_recyclerview);

        //onCreate() 함수에서 RecyclerView와 MemoListAdapter를 초기화 한다.
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MemoListAdapter(getApplicationContext(), memos, R.layout.activity_memo_list);
        // specify an adapter (see also next example)
//        mAdapter = new MemoListAdapter(myDataset);

        recyclerView.setAdapter(mAdapter);

        // 메모가 많을 경우 상단으로 올리기
        recyclerView.scrollToPosition(0);
        mAdapter.notifyDataSetChanged();
        //loadMemosToFile();
        //메모 기능 추가
        memoAdd = (ImageButton) findViewById(R.id.add_memo_btn);
        memoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemoListActivity.this, MemoEditActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnItemClickListener(new MemoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Memo memo) {
                Intent intent = new Intent(MemoListActivity.this, MemoViewActivity.class);
                intent.putExtra("title",memo.getTitle());
                intent.putExtra("content",memo.getContent());
                intent.putExtra("time",memo.getCurrent_time());
                intent.putExtra("detime",memo.getDetime());

                startActivity(intent);
            }
        });
    }

    private void loadMemosToFile(){
        String dirPath = getFilesDir().getPath();
        dirPath = dirPath.replace("files", "memo_history");
        File file = new File(dirPath);
        if(!file.exists()){
            Log.d("돼잊","ㅇㅇ");
            mAdapter.notifyDataSetChanged();
            return;
        }
        // 파일이 1개 이상이면 파일 이름 출력
        if( file.listFiles().length > 0) {
            Log.d("ㅇㅇ","돼지");
            for (File f : file.listFiles()) {
                String str = f.getName();

                // 파일 내용 읽어 오기
                String loadPath = dirPath + "/" + str;
                try {
                    FileInputStream fis = new FileInputStream(loadPath);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));

                    String all  = "", temp = "";
                    boolean checkContent = true;
                    while ((temp = bufferedReader.readLine()) != null) {

                        if ( temp.equals("<content>")) {
                            checkContent = false;
                            all += temp;
                            continue;
                        }
                        else if (temp.equals("<time>"))
                            checkContent = true;

                        if(checkContent)
                            all += temp;
                        else{
                            all += temp + "\n";
                        }
                    }

                    String title = all.split("<title>")[1].split("<content>")[0];
                    String content = all.split("<content>")[1].split("<time>")[0];
                    content = content.substring(0, content.length() - 1);
                    String time = all.split("<time>")[1].split("<detime>")[0];
                    String detime = all.split("<detime>")[1];


                    memos.add(new Memo(title, content, time, detime));


                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }

        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onStart() {
        super.onStart();
        memos.clear();
        loadMemosToFile();
    }
}

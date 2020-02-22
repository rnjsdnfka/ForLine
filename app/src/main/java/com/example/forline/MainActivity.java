package com.example.forline;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MemoListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Memo> memos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

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
        fileSave();
    }

    public void fileSave(){
        String path = "/Test/";
        String fileName = "android.txt";

        File file = new File(path + fileName);
        String text = "저장될 내용";

        FileOutputStream fos = null;

        try{
            fos = new FileOutputStream(file);

            fos.write((text).getBytes());

            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

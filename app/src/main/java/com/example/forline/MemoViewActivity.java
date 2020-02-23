package com.example.forline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MemoViewActivity extends AppCompatActivity {

    private EditText title_et;
    private EditText content_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_view);

        title_et = (EditText)findViewById(R.id.edit_title_view);
        content_et = (EditText)findViewById(R.id.edit_content_view);

        Intent intent = getIntent();
        title_et.setText(intent.getExtras().getString("title"));
        content_et.setText(intent.getExtras().getString("content"));
    }
}

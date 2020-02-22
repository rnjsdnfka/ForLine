package com.example.forline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemoListAdapter extends RecyclerView.Adapter<MemoListAdapter.ViewHolder> {

    Context context;
    List<Memo> memos;
    int item_layout;

    public MemoListAdapter(Context context, List<Memo> memos, int item_layout) {
        this.context = context;
        this.memos = memos;
        this.item_layout = item_layout;
    }

    @NonNull
    @Override
    public MemoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoListAdapter.ViewHolder holder, int position) {
        final Memo memo = memos.get(position);

        holder.title.setText(memo.getTitle());
        holder.content.setText(memo.getContent());
    }

    @Override
    public int getItemCount() {
        return memos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        private TextView title;
        private TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
        }
    }
}

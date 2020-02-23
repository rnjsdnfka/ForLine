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

    public interface OnItemClickListener {
        void onItemClick(View v, int position, Memo memo);
    }

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    @NonNull
    @Override
    public MemoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoListAdapter.ViewHolder holder, final int position) {
        final Memo memo = memos.get(position);
        if(position == 0)
        {
            holder.initial_line.setVisibility(View.VISIBLE);
        }

        String title = memo.getTitle();
        String content = memo.getContent();

        if(memo.getTitle().length() > 10 ){
            title = title.substring(0,10) + "....";
        }

        content = content.split("\n")[0];

        if(content.length() > 13) {
            content = content.substring(0, 13) + "....";
        }
        holder.title.setText(title);
        holder.content.setText(content);
        holder.time.setText(memo.getCurrent_time());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) mListener.onItemClick(view, position, memo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return memos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        private TextView title;
        private TextView content;
        private TextView time;
        private View initial_line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
            time = itemView.findViewById(R.id.time_tv);
            initial_line = itemView.findViewById(R.id.initial_line);
        }
    }
}

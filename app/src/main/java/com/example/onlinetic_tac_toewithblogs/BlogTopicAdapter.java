package com.example.onlinetic_tac_toewithblogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BlogTopicAdapter extends RecyclerView.Adapter<BlogTopicAdapter.BlogTopicViewHolder>{

    private Context mContext;
    private List<BlogTopic> blogList;

    public BlogTopicAdapter(Context mContext, List<BlogTopic> blogList) {
        this.mContext = mContext;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public BlogTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_layout,null);
        BlogTopicViewHolder holder = new BlogTopicViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BlogTopicViewHolder holder, int position) {
        BlogTopic blogTopic = blogList.get(position);

        holder.blogTextView.setText(blogTopic.getTopic());
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    class BlogTopicViewHolder extends RecyclerView.ViewHolder{

        TextView blogTextView;

        public BlogTopicViewHolder(@NonNull View itemView) {
            super(itemView);

            blogTextView = itemView.findViewById(R.id.listLayoutTextView);
        }
    }

}

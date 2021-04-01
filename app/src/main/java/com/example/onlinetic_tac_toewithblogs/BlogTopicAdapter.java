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
    private OnBlogTopicListener mOnBlogTopicListener;

    public BlogTopicAdapter(Context mContext, List<BlogTopic> blogList,OnBlogTopicListener onBlogTopicListener) {
        this.mContext = mContext;
        this.blogList = blogList;
        this.mOnBlogTopicListener = onBlogTopicListener;
    }

    @NonNull
    @Override
    public BlogTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_layout,null);
        BlogTopicViewHolder holder = new BlogTopicViewHolder(view,mOnBlogTopicListener);
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

    class BlogTopicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView blogTextView;
        OnBlogTopicListener onBlogTopicListener;

        public BlogTopicViewHolder(@NonNull View itemView, OnBlogTopicListener onBlogTopicListener) {
            super(itemView);

            blogTextView = itemView.findViewById(R.id.listLayoutTextView);
            this.onBlogTopicListener = onBlogTopicListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBlogTopicListener.OnBlogTopicClick(getAdapterPosition());
        }
    }

    public interface OnBlogTopicListener{
        void OnBlogTopicClick(int position);
    }
}

package com.example.onlinetic_tac_toewithblogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder> {

    private Context mContext;
    private List<Blog> blogList;

    public BlogAdapter(Context mContext, List<Blog> blogList) {
        this.mContext = mContext;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public BlogAdapter.BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_layout,null);
        BlogAdapter.BlogViewHolder holder = new BlogAdapter.BlogViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BlogAdapter.BlogViewHolder holder, int position) {
        Blog blog = blogList.get(position);

        holder.blogTextView.setText(blog.getBlog());
        holder.blogTopicTextView.setText(blog.getBlogTopic());
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    class BlogViewHolder extends RecyclerView.ViewHolder {

        TextView blogTextView,blogTopicTextView;

        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);

            blogTextView = itemView.findViewById(R.id.blogTextView);
            blogTopicTextView = itemView.findViewById((R.id.blogTopicTextView));
        }
    }
}

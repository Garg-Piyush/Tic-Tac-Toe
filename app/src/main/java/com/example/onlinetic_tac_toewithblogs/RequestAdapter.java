package com.example.onlinetic_tac_toewithblogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder>{

    private Context mContext;
    private List<String> requestList;
    private OnRequestListener mOnRequestListener;

    public RequestAdapter(Context mContext, List<String> requestList, OnRequestListener onRequestListener) {
        this.mContext = mContext;
        this.requestList = requestList;
        this.mOnRequestListener = onRequestListener;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.request_list_item,null);
        RequestViewHolder holder = new RequestViewHolder(view,mOnRequestListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        String string = requestList.get(position);

        holder.requestTextView.setText(string);
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    class RequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView requestTextView;
        OnRequestListener onRequestListener;
        Button acceptButton, declineButton;

        public RequestViewHolder(@NonNull View itemView, OnRequestListener onRequestListener) {
            super(itemView);

            requestTextView = itemView.findViewById(R.id.requestedByTextView);
            acceptButton = itemView.findViewById(R.id.acceptButton);
            declineButton = itemView.findViewById(R.id.declineButton);
            this.onRequestListener = onRequestListener;

            acceptButton.setOnClickListener(this);
            declineButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.acceptButton:
                    onRequestListener.OnAcceptClick(getAdapterPosition());
                    break;

                case R.id.declineButton:
                    onRequestListener.OnDeclineClick(getAdapterPosition());
                    break;
            }
        }
    }

    public interface OnRequestListener{
        void OnAcceptClick(int position);
        void OnDeclineClick(int position);
    }
}

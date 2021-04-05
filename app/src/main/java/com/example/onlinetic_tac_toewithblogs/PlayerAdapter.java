package com.example.onlinetic_tac_toewithblogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>{

        private Context mContext;
        private List<String> playerList;
        private OnPlayerListener mOnPlayerListener;

        public PlayerAdapter(Context mContext, List<String> playerList, OnPlayerListener onPlayerListener) {
            this.mContext = mContext;
            this.playerList = playerList;
            this.mOnPlayerListener = onPlayerListener;
        }

        @NonNull
        @Override
        public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.list_layout,null);
            PlayerViewHolder holder = new PlayerViewHolder(view,mOnPlayerListener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
            String string = playerList.get(position);

            holder.playerTextView.setText(string);
        }

        @Override
        public int getItemCount() {
            return playerList.size();
        }

        class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView playerTextView;
            OnPlayerListener onPlayerListener;

            public PlayerViewHolder(@NonNull View itemView, OnPlayerListener onPlayerListener) {
                super(itemView);

                playerTextView = itemView.findViewById(R.id.listLayoutTextView);
                this.onPlayerListener = onPlayerListener;

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                onPlayerListener.OnPlayerClick(getAdapterPosition());
            }
        }

        public interface OnPlayerListener{
            void OnPlayerClick(int position);
        }
    }



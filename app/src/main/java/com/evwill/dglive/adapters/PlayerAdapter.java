package com.evwill.dglive.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evwill.dglive.R;
import com.evwill.dglive.listener.OnPlayerNameSelectListener;
import com.evwill.dglive.models.Player;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Player> mPlayers;
    public final OnPlayerNameSelectListener mListener;

    public PlayerAdapter(Context context, List<Player> players) {
        mPlayers = players;
        mListener = ((OnPlayerNameSelectListener)context);
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.existing_player_list_item, parent, false);
        PlayerViewHolder viewHolder = new PlayerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.bindPlayer(mPlayers.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {

        public TextView mNameLabel;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            mNameLabel = (TextView) itemView.findViewById(R.id.existing_player_name_label);

            mNameLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onPlayerNameSelect(mNameLabel.getText().toString());
                }
            });
        }

        public void bindPlayer(Player player) {
            mNameLabel.setText(player.getName());
        }


    }

}

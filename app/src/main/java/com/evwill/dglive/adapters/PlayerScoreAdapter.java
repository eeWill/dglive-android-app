package com.evwill.dglive.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.evwill.dglive.Player;
import com.evwill.dglive.R;

import org.w3c.dom.Text;

public class PlayerScoreAdapter extends BaseAdapter {

    private Context mContext;
    private Player[] mPlayers;

    public PlayerScoreAdapter(Context context, Player[] players) {
        mContext = context;
        mPlayers = players;
    }

    @Override
    public int getCount() {
        return mPlayers.length;
    }

    @Override
    public Object getItem(int i) {
        return mPlayers[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.player_score_row, null);
            holder.playerNameLabel = (TextView) view.findViewById(R.id.player_name_label);
            holder.playerScoreLabel = (TextView) view.findViewById(R.id.player_score_label);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        Player player = mPlayers[i];
        holder.playerNameLabel.setText(player.getName());
        holder.playerScoreLabel.setText(String.valueOf(3));

        return view;
    }

    private static class ViewHolder {
        TextView playerNameLabel;
        TextView playerScoreLabel;
        Button increaseScoreButton;
        Button decreaseScoreButton;


    }
}

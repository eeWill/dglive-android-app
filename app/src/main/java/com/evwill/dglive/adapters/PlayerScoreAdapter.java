package com.evwill.dglive.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.evwill.dglive.AdapterHandler;
import com.evwill.dglive.models.Player;
import com.evwill.dglive.R;
import com.evwill.dglive.models.Round;

public class PlayerScoreAdapter extends BaseAdapter {

    private AdapterHandler mListener;
    private Context mContext;
    private Round mRound;

    public PlayerScoreAdapter(Context context, Round round) {
        mListener = ((AdapterHandler)context);
        mContext = context;
        mRound = round;
    }

    @Override
    public int getCount() {
        return mRound.getPlayers().size();
    }

    @Override
    public Object getItem(int i) {
        return mRound.getPlayers().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.player_score_row, null);
            holder.playerNameLabel = (TextView) view.findViewById(R.id.player_name_label);
            holder.playerScoreLabel = (TextView) view.findViewById(R.id.player_score_label);
            holder.increaseScoreButton = (Button) view.findViewById(R.id.increase_score_button);
            holder.decreaseScoreButton = (Button) view.findViewById(R.id.decrease_score_button);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        Player player = mRound.getPlayers().get(i);
        holder.playerNameLabel.setText(player.getName());
        int holeScore = player.getScores().get(mRound.getCurrentHoleNumber() - 1).getScore();
        holder.playerScoreLabel.setText(String.valueOf(holeScore));
        holder.increaseScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.incrementPlayerScore(i);
                notifyDataSetChanged();
            }
        });

        holder.decreaseScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.decrementPlayerScore(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    private static class ViewHolder {
        TextView playerNameLabel;
        TextView playerScoreLabel;
        Button increaseScoreButton;
        Button decreaseScoreButton;
    }


}

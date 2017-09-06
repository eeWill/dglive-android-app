package com.evwill.dglive;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.evwill.dglive.adapters.PlayerAdapter;
import com.evwill.dglive.models.Player;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddExistingPlayerDialog extends DialogFragment {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_existing_player_dialog, null);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            PlayerAdapter adapter = new PlayerAdapter(getActivity(), bundle.<Player>getParcelableArrayList("Players"));
            mRecyclerView.setAdapter(adapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);
        }

        builder.setView(view);
        return builder.create();
    }

}

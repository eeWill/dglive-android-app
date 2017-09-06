package com.evwill.dglive;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.evwill.dglive.listener.OnPlayerNameSubmitListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPlayerDialog extends DialogFragment {

    @BindView(R.id.player_name_submit) Button submitPlayerButton;
    @BindView(R.id.player_name_input)
    EditText playerName;

    private OnPlayerNameSubmitListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (OnPlayerNameSubmitListener)context;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCompleteListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_player_dialog, null);
        builder.setView(view);
        ButterKnife.bind(this, view);


        submitPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPlayerNameSubmit(playerName.getText().toString());
                dismiss();
            }
        });

        return builder.create();
    }



}
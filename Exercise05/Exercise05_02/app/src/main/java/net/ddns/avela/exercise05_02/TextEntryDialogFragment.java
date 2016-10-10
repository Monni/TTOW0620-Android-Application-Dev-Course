package net.ddns.avela.exercise05_02;

/**
 * Created by Kotimonni on 10.10.2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class TextEntryDialogFragment extends DialogFragment {

    // send data to host with listeners
    public interface TextEntryDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String product, String count, String price);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    TextEntryDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
        // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (TextEntryDialogListener) activity;
        }
        catch (ClassCastException e) {
        // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement TextEntryDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// create a new
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
// Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
// Inflate and set the layout for the dialog
// Pass null as the parent view because its going in the dialog layout
        final
        View dialogView = inflater.inflate(R.layout.textentry_dialog,null);
        builder.setView(dialogView)
                .setTitle("Add a new product")
                // Add action buttons
                .setPositiveButton("Add",new DialogInterface.OnClickListener() {
                    @Override
                    public void
                    onClick(DialogInterface dialog,int id) {
                        EditText editText1 = (EditText) dialogView.findViewById(R.id.editText1);
                        EditText editText2 = (EditText) dialogView.findViewById(R.id.editText2);
                        EditText editText3 = (EditText) dialogView.findViewById(R.id.editText3);
                        String product = editText1.getText().toString();
                        String count = editText2.getText().toString();
                        String price = editText3.getText().toString();
// Send the positive button event back to the host activity
                        mListener.onDialogPositiveClick(TextEntryDialogFragment.this,product,count,price);
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) {
// Send the negative button event back to the host activity
                        mListener.onDialogNegativeClick(TextEntryDialogFragment.this
                        );
                    }
                });
        return
                builder.create();
    }
}






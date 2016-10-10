package net.ddns.avela.exercise05_02;

import android.app.DialogFragment;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextEntryDialogFragment.TextEntryDialogListener {
SQLiteDatabase groceryDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groceryDB = openOrCreateDatabase("Groceries",MODE_PRIVATE,null);
        groceryDB.execSQL("CREATE TABLE IF NOT EXISTS groceryList2 (product VARCHAR, count VARCHAR, price VARCHAR);");

    }

    protected void buttonClicked(View view) {
        TextEntryDialogFragment eDialog = new TextEntryDialogFragment();
        eDialog.show(getFragmentManager(),"Text Dialog");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String product, String count, String price) {
        groceryDB.execSQL("INSERT INTO groceryList (product, count, price) VALUES(product, count, price);");
        TextView tv = (TextView) findViewById(R.id.textView0);
        tv.setText(groceryDB.rawQuery("SELECT * FROM groceryList",null).toString());

    }
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT).show();
    }
}

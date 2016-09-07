package net.ddns.avela.exercise02_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AutoCompleteTextView
        AutoCompleteTextView actv = (AutoCompleteTextView)
                findViewById(R.id.edit_text0); // add stings to control
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]
                        {"Pasi", "Juha", "Kari", "Jouni", "Esa", "Hannu"});
        actv.setAdapter(aa);
    }

    public void loginButtonClicked(View view) {
        TextView atv = (AutoCompleteTextView) findViewById(R.id.edit_text0);
        String text0 = atv.getText().toString();
        TextView tv = (TextView) findViewById(R.id.edit_text1);
        String text1 = (String) tv.getText().toString();
        String f_text = (String) text0 + " " + (String) text1;
        // Toast message to screen
        Toast.makeText(getApplicationContext(), f_text, Toast.LENGTH_SHORT).show();
    }
}

package net.ddns.avela.exercise03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button0_clicked(View view) {
        Toast.makeText(this, "You enter a dangerous cave",Toast.LENGTH_SHORT).show();
        Toast customToast = Toast.makeText(this, "Filled with much danger", Toast.LENGTH_SHORT);
        customToast.setGravity(Gravity.BOTTOM,0,500);
        customToast.show();
        Toast customToast2 = Toast.makeText(this, "You must go derper", Toast.LENGTH_SHORT);
        customToast2.setGravity(Gravity.BOTTOM,0,1000);
        customToast2.show();

        Button button = (Button) findViewById(R.id.button1);
        button.setText("Go derper");
        button.setEnabled(true);

        Button button0 = (Button) findViewById(R.id.button0);
        button0.setText("You entered a cave");
        button0.setEnabled(false);
    }

    public void button1_clicked(View view) {
        Intent intent = new Intent(this,cave.class);
        startActivity(intent);
    }


}

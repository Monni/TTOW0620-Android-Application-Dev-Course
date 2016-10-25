package com.example.h4211.exercise07;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class kentta_detail extends AppCompatActivity {
    String kenttaImage;
    String kentta;
    String osoite;
    String puhelin;
    String sahkoposti;
    String kuvaus;
    Double lat;
    Double lng;
    String webbi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kentta_detail);

        Intent intent = getIntent();
        kenttaImage = intent.getStringExtra("kenttaimage");
        kentta = intent.getStringExtra("kenttatext");
        osoite = intent.getStringExtra("osoite");
        puhelin = intent.getStringExtra("puhelin");
        sahkoposti = intent.getStringExtra("sahkoposti");
        kuvaus = intent.getStringExtra("kuvaus");
        lat = Double.parseDouble(intent.getStringExtra("lat"));
        lng = Double.parseDouble(intent.getStringExtra("lng"));
        webbi = intent.getStringExtra("webbi");

        setTitle(kentta);

        ImageView kenttaImageView = (ImageView) findViewById(R.id.detail_kenttaImageView);
        Glide.with(this)
                .load("http://ptm.fi/jamk/android/golfcourses/" + kenttaImage)
                .override(300,200)
                .into(kenttaImageView);
        TextView osoiteTextView = (TextView) findViewById(R.id.detail_osoiteTextView);
        osoiteTextView.setText(osoite);
        TextView puhelinTextView = (TextView) findViewById(R.id.detail_puhelinTextView);
        puhelinTextView.setText(puhelin);
        TextView sahkopostiTextView = (TextView) findViewById(R.id.detail_sahkopostiTextView);
        sahkopostiTextView.setText(sahkoposti);
        TextView kuvausTextView = (TextView) findViewById(R.id.detail_kuvausTextView);
        kuvausTextView.setText(kuvaus);
        TextView webbiTextView = (TextView) findViewById(R.id.detail_webbiTextView);
        webbiTextView.setText(webbi);
    }

    public void detail_mapTextViewClicked(View view) {
        String uriBegin = "geo:"+lat+","+lng;
        String query = lat + "," + lng + "(" + kentta + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void detail_webbiClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webbi));
        startActivity(browserIntent);
    }

}

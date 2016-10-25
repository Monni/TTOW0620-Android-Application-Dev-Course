package com.example.h4211.exercise07;

import android.util.Log;

/**
 * Created by h4211 on 10/25/2016.
 */

public class Kentta {
    String tyyppi;
    String lat;
    String lng;
    String kentta;
    String osoite;
    String puhelin;
    String sahkoposti;
    String webbi;
    String kuva;
    String kuvaus;

    public Kentta(String tyyppi, String lat, String lng, String kentta, String osoite, String puhelin, String sahkoposti, String webbi, String kuva, String kuvaus) {
        this.tyyppi = tyyppi;
        this.lat = lat;
        this.lng = lng;
        this.kentta = kentta;
        this.osoite = osoite;
        this.puhelin = puhelin;
        this.sahkoposti = sahkoposti;
        this.webbi = webbi;
        this.kuva = kuva;
        this.kuvaus = kuvaus;
        Log.d("Kentta", "Created");
    }
}

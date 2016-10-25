package com.example.h4211.exercise07;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by h4211 on 10/25/2016.
 */

public class kenttaAdapter extends RecyclerView.Adapter<kenttaAdapter.ViewHolder> {
    // adapter data
    private List<Kentta> kenttaList;
    private Activity context;
    // adapter constructor, get data from activity
    public kenttaAdapter(Activity context, List<Kentta> kenttaList) {
        this.kenttaList = kenttaList;
        this.context = context;
        Log.d("kenttaAdapted", "started");
    }

    // return the size of kenttaList (invoked by the layout manager)
    @Override
    public int getItemCount(){
        if (kenttaList != null) {
            return kenttaList.size();
        }
        else return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kentta_card, parent, false);
        return new ViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    // get element from kenttaList at this position
    // replace the contents of the view with that element
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Kentta kentta = kenttaList.get(position);
        Glide.with(this.context)
                .load("http://ptm.fi/jamk/android/golfcourses/" + kentta.kuva)
                .override(250,170)
                .into(viewHolder.kenttaImageView);
        //viewHolder.kenttaImageView.setImageResource("http://ptm.fi/jamk/android/golfcourses/" + kentta.kuva);
        viewHolder.kenttaTextView.setText(kentta.kentta);
        viewHolder.osoiteTextView.setText(kentta.osoite);
        viewHolder.puhelinTextView.setText(kentta.puhelin);
        viewHolder.sahkopostiTextView.setText(kentta.sahkoposti);
    }

    // view holder class to specify card UI objects
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView kenttaImageView;
        public TextView kenttaTextView;
        public TextView osoiteTextView;
        public TextView puhelinTextView;
        public TextView sahkopostiTextView;

        public ViewHolder (View itemView) {
            super (itemView);
            // get layout IDs
            kenttaImageView = (ImageView) itemView.findViewById(R.id.kenttaImageView);
            kenttaTextView = (TextView) itemView.findViewById(R.id.kenttaTextView);
            osoiteTextView = (TextView) itemView.findViewById(R.id.osoiteTextView);
            puhelinTextView = (TextView) itemView.findViewById(R.id.puhelinTextView);
            sahkopostiTextView = (TextView) itemView.findViewById(R.id.sahkopostiTextView);
            // add click listener for a card
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, kentta_detail.class);
                    intent.putExtra("kenttaimage", kenttaList.get(position).kuva);
                    intent.putExtra("kenttatext", kenttaList.get(position).kentta);
                    intent.putExtra("osoite", kenttaList.get(position).osoite);
                    intent.putExtra("puhelin", kenttaList.get(position).puhelin);
                    intent.putExtra("sahkoposti", kenttaList.get(position).sahkoposti);
                    intent.putExtra("kuvaus", kenttaList.get(position).kuvaus);
                    intent.putExtra("lat", kenttaList.get(position).lat);
                    intent.putExtra("lng", kenttaList.get(position).lng);
                    intent.putExtra("webbi", kenttaList.get(position).webbi);

                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}

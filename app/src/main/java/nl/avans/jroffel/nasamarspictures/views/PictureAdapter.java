package nl.avans.jroffel.nasamarspictures.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import nl.avans.jroffel.nasamarspictures.R;
import nl.avans.jroffel.nasamarspictures.controllers.APIController;
import nl.avans.jroffel.nasamarspictures.models.AsyncResponse;
import nl.avans.jroffel.nasamarspictures.models.PhotoModel;

/**
 * Created by Jason on 3/13/2018.
 */

public class PictureAdapter extends ArrayAdapter<String> {

    private Activity context;
    private String[] ids;
    private String[] img_srcs;
    private String[] camera_full_names;

    public PictureAdapter(Activity context, String[] ids, String[] img_srcs, String[] camera_full_names) {
        super(context, R.layout.pictureslist, ids);
        this.context = context;

        this.ids = ids;
        this.img_srcs = img_srcs;
        this.camera_full_names = camera_full_names;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.pictureslist, null, true);

        final ImageView background = (ImageView) rowView.findViewById(R.id.backgroundImage);
        final TextView idField = (TextView) rowView.findViewById(R.id.idField);

        idField.setText(ids[position]);
        AsyncResponse callback = new AsyncResponse() {
            @Override
            public void processFinish(PhotoModel[] output) {

            }

            @Override
            public void processFinish(Bitmap output) {
                background.setImageBitmap(output);
            }
        };

        new APIController().getPicture(img_srcs[position], callback);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                int id = Integer.valueOf(ids[position].split(": ")[1]);
                System.out.println("Clicked on: " + id);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}

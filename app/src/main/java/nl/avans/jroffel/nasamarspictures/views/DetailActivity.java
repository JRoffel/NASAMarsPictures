package nl.avans.jroffel.nasamarspictures.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;

import nl.avans.jroffel.nasamarspictures.R;
import nl.avans.jroffel.nasamarspictures.controllers.DatabaseController;
import nl.avans.jroffel.nasamarspictures.models.AsyncResponse;
import nl.avans.jroffel.nasamarspictures.models.PhotoModel;

public class DetailActivity extends Activity {

    private DatabaseController DB = new DatabaseController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int id = (int) intent.getExtras().get("id");

        AsyncResponse delegate = new AsyncResponse() {
            @Override
            public void processFinish(PhotoModel[] output) {
                PhotoModel picture = output[0];

            }

            @Override
            public void processFinish(Bitmap output) {

            }
        };

        DB.getPicture(id, delegate);
    }

}

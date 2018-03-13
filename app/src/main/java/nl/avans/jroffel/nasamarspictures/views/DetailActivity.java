package nl.avans.jroffel.nasamarspictures.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import nl.avans.jroffel.nasamarspictures.R;
import nl.avans.jroffel.nasamarspictures.controllers.APIController;
import nl.avans.jroffel.nasamarspictures.controllers.DatabaseController;
import nl.avans.jroffel.nasamarspictures.models.AsyncResponse;
import nl.avans.jroffel.nasamarspictures.models.PhotoModel;

public class DetailActivity extends Activity {

    private DatabaseController DB = new DatabaseController(this);
    private APIController API = new APIController();

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
                TextView cameraField = (TextView) findViewById(R.id.cameraText);
                cameraField.setText(output[0].getCamera_full_name());
                API.getPicture(output[0].getImg_src(), this);
            }

            @Override
            public void processFinish(Bitmap output) {
                ImageView imgField = (ImageView) findViewById(R.id.detailImage);
                imgField.setImageBitmap(output);
            }
        };

        DB.getPicture(id, delegate);
    }

}

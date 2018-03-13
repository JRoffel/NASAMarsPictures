package nl.avans.jroffel.nasamarspictures;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.Bundle;
import android.widget.ListView;

import nl.avans.jroffel.nasamarspictures.controllers.APIController;
import nl.avans.jroffel.nasamarspictures.controllers.DatabaseController;
import nl.avans.jroffel.nasamarspictures.models.AsyncResponse;
import nl.avans.jroffel.nasamarspictures.models.PhotoModel;
import nl.avans.jroffel.nasamarspictures.views.PictureAdapter;

public class MainActivity extends Activity {

    private APIController API = new APIController();
    private DatabaseController DB = new DatabaseController(this);
    private MainActivity thisReference = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        API.getPictures(new AsyncResponse() {
            @Override
            public void processFinish(PhotoModel[] output) {
                String[] ids = new String[output.length];
                String[] img_srcs = new String[output.length];
                String[] camera_full_names = new String[output.length];

                for(int i = 0; i < output.length; i++) {
                    ids[i] = "Image ID: " + output[i].getId();
                    img_srcs[i] = output[i].getImg_src();
                    camera_full_names[i] = output[i].getCamera_full_name();
                }

                PictureAdapter adapter = new PictureAdapter(thisReference, ids, img_srcs, camera_full_names);
                ListView list = (ListView) findViewById(R.id.pictureList);
                list.setAdapter(adapter);

                DB.addPictures(output);
            }

            @Override
            public void processFinish(Bitmap output) {

            }
        });
    }
}

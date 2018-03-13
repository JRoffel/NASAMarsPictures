package nl.avans.jroffel.nasamarspictures.controllers.APIFunctions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

import nl.avans.jroffel.nasamarspictures.models.AsyncResponse;

/**
 * Created by Jason on 3/13/2018.
 */

public class GetPicture extends AsyncTask<String, Void, Bitmap> {

    private AsyncResponse delegate;

    public GetPicture(AsyncResponse delegate) {
        this.delegate = delegate;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return bmp;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap image) {
        delegate.processFinish(image);
    }
}

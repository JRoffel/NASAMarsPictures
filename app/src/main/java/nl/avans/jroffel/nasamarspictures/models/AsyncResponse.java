package nl.avans.jroffel.nasamarspictures.models;

import android.graphics.Bitmap;

/**
 * Created by Jason on 3/13/2018.
 */

public interface AsyncResponse {

    void processFinish(PhotoModel[] output);
    void processFinish(Bitmap output);
}

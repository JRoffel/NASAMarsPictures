package nl.avans.jroffel.nasamarspictures.controllers.DatabaseFunctions;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import nl.avans.jroffel.nasamarspictures.controllers.DatabaseController;
import nl.avans.jroffel.nasamarspictures.models.AsyncResponse;
import nl.avans.jroffel.nasamarspictures.models.PhotoModel;

import static nl.avans.jroffel.nasamarspictures.controllers.DatabaseController.KEY_CAMERA;
import static nl.avans.jroffel.nasamarspictures.controllers.DatabaseController.KEY_ID;
import static nl.avans.jroffel.nasamarspictures.controllers.DatabaseController.KEY_IMG;
import static nl.avans.jroffel.nasamarspictures.controllers.DatabaseController.TABLE_PICTURES;

/**
 * Created by Jason on 3/13/2018.
 */

public class GetPicture extends AsyncTask<Integer, Void, PhotoModel> {

    private AsyncResponse delegate;
    DatabaseController reference;

    public GetPicture(AsyncResponse delegate, DatabaseController reference) {
        this.delegate = delegate;
        this.reference = reference;
    }

    @Override
    protected PhotoModel doInBackground(Integer... integers) {
        SQLiteDatabase db = reference.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PICTURES, new String[] { KEY_ID,
                        KEY_IMG, KEY_CAMERA }, KEY_ID + "=?",
                new String[] { String.valueOf(integers[0]) }, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        PhotoModel picture = new PhotoModel(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return picture;
    }

    @Override
    protected void onPostExecute(PhotoModel photoModel) {
        PhotoModel[] pictureArray = new PhotoModel[] {
                photoModel
        };

        delegate.processFinish(pictureArray);
    }
}

package nl.avans.jroffel.nasamarspictures.controllers.DatabaseFunctions;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import nl.avans.jroffel.nasamarspictures.controllers.DatabaseController;
import nl.avans.jroffel.nasamarspictures.models.PhotoModel;

import static nl.avans.jroffel.nasamarspictures.controllers.DatabaseController.KEY_CAMERA;
import static nl.avans.jroffel.nasamarspictures.controllers.DatabaseController.KEY_ID;
import static nl.avans.jroffel.nasamarspictures.controllers.DatabaseController.KEY_IMG;
import static nl.avans.jroffel.nasamarspictures.controllers.DatabaseController.TABLE_PICTURES;

/**
 * Created by Jason on 3/13/2018.
 */

public class AddPictures extends AsyncTask<PhotoModel[], Void, Void> {

    private DatabaseController reference;

    public AddPictures(DatabaseController reference) {
        this.reference = reference;
    }

    @Override
    protected Void doInBackground(PhotoModel[]... photoModels) {
        PhotoModel[] pictures = photoModels[0];

        SQLiteDatabase db = reference.getWritableDatabase();

        for(int i = 0; i < pictures.length; i++) {
            ContentValues values = new ContentValues();
            values.put(KEY_ID, pictures[i].getId());
            values.put(KEY_IMG, pictures[i].getImg_src());
            values.put(KEY_CAMERA, pictures[i].getCamera_full_name());

            db.insert(TABLE_PICTURES, null, values);
        }

        db.close();

        return null;
    }
}

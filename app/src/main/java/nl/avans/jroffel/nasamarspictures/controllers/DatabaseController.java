package nl.avans.jroffel.nasamarspictures.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nl.avans.jroffel.nasamarspictures.controllers.DatabaseFunctions.AddPictures;
import nl.avans.jroffel.nasamarspictures.controllers.DatabaseFunctions.GetPicture;
import nl.avans.jroffel.nasamarspictures.models.AsyncResponse;
import nl.avans.jroffel.nasamarspictures.models.PhotoModel;

/**
 * Created by Jason on 3/13/2018.
 */

public class DatabaseController extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "NasaMarsPictures";

    public static final String TABLE_PICTURES = "pictures";

    public static final String KEY_ID = "id";
    public static final String KEY_IMG = "img_src";
    public static final String KEY_CAMERA = "camera_full_name";

    public DatabaseController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_PICTURES + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_IMG + "TEXT, "
                + KEY_CAMERA + " TEXT)";
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURES);
        sqLiteDatabase.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURES);

        onCreate(sqLiteDatabase);
    }

    public void addPictures(PhotoModel[] pictures) {
        AddPictures task = new AddPictures(this);
        task.execute(pictures);
    }

    public void getPicture(int id, AsyncResponse delegate) {
        GetPicture task = new GetPicture(delegate, this);
        task.execute(id);
    }
}

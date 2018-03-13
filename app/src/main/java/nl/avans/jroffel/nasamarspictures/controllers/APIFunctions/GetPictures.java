package nl.avans.jroffel.nasamarspictures.controllers.APIFunctions;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import nl.avans.jroffel.nasamarspictures.models.AsyncResponse;
import nl.avans.jroffel.nasamarspictures.models.PhotoModel;

/**
 * Created by Jason on 3/13/2018.
 */

public class GetPictures extends AsyncTask<Void, Void, JSONArray> {

    private String API_KEY;

    private AsyncResponse delegate = null;

    public GetPictures (AsyncResponse delegate, String API_KEY) {
        this.API_KEY = API_KEY;
        this.delegate = delegate;
    }
    @Override
    protected JSONArray doInBackground(Void... params) {
        JSONArray pictureData = new JSONArray();
        try {
            URL url = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=mast&api_key=" + API_KEY);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setDoOutput(false);
            urlConnection.setDoInput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String plainJson = readStream(in);
            JSONObject temp = new JSONObject(plainJson);
            pictureData = new JSONArray(temp.get("photos").toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pictureData;
    }

    @Override
    protected void onProgressUpdate(Void... values) {

    }

    @Override
    protected void onPostExecute(JSONArray pictureDataArray) {
        PhotoModel[] photoModels = new PhotoModel[pictureDataArray.length()];

        try {
            for(int i = 0; i < pictureDataArray.length(); i++) {
                JSONObject pictureData = (JSONObject) pictureDataArray.get(i);
                JSONObject camera = (JSONObject) pictureData.get("camera");
                photoModels[i] = (new PhotoModel(pictureData.getInt("id"), pictureData.getString("img_src"), camera.getString("full_name")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        delegate.processFinish(photoModels);
    }

    private String readStream(BufferedReader in) {
        String chunk;
        String result = "";

        try {
            while ((chunk = in.readLine()) != null) {
                result += chunk;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}

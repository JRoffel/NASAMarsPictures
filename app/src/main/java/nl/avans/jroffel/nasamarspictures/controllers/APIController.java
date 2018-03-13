package nl.avans.jroffel.nasamarspictures.controllers;

import nl.avans.jroffel.nasamarspictures.controllers.APIFunctions.GetPicture;
import nl.avans.jroffel.nasamarspictures.controllers.APIFunctions.GetPictures;
import nl.avans.jroffel.nasamarspictures.models.AsyncResponse;

/**
 * Created by Jason on 3/13/2018.
 */

public class APIController {

    private final String API_KEY = "Pb3ZYmptJsBtfpm4XGHJnZJJMKtPU8YRiT3MpGRT";

    public void getPictures(AsyncResponse delegate) {
        GetPictures task = new GetPictures(delegate, API_KEY);
        task.execute();
    }

    public void getPicture(String url, AsyncResponse delegate) {
        GetPicture task = new GetPicture(delegate);
        task.execute(url);
    }
}

package nl.avans.jroffel.nasamarspictures.models;

/**
 * Created by Jason on 3/13/2018.
 */

public class PhotoModel {

    private int id;
    private String img_src;
    private String camera_full_name;

    public PhotoModel(int id, String img_src, String camera_full_name) {
        this.id = id;
        this.img_src = img_src;
        this.camera_full_name = camera_full_name;
    }

    public int getId() {
        return id;
    }

    public String getImg_src() {
        return img_src;
    }

    public String getCamera_full_name() {
        return camera_full_name;
    }

    @Override
    public String toString() {
        return "{ " + getId() + " - " + getCamera_full_name() + " @ " + getImg_src() + " }";
    }
}

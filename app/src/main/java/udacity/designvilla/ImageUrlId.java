package udacity.designvilla;

import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;

public class ImageUrlId {

    @Exclude
    public String ImageUrlId;

    public <T extends ImageUrlId> T withId(@NonNull final String id) {
        this.ImageUrlId = id;
        return (T) this;
    }
}

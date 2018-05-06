package udacity.designvilla.listeners;

/**
 * Created by srdpatel on 2/25/2018.
 * <p>
 * Use to get image path of selected image from the gallery.
 * Use {@link android.support.v4.content.FileProvider} or {@link udacity.designvilla.Util.FileUtils} to get image path for image taken from camera.
 * <p>
 *
 * @since 1.0
 */
public interface OnSelectImageListener {
    void onSelectImage(String imagePath);
}

package udacity.designvilla.adapter_view;

import android.graphics.drawable.Drawable;

public class TemplateHolder {
    private Drawable mTemplateImage;

    public TemplateHolder(Drawable templateImage) {
        mTemplateImage = templateImage;
    }

    public Drawable getTemplateImage() {
        return mTemplateImage;
    }
}

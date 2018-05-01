package udacity.designvilla;

import android.graphics.drawable.Drawable;

public class GridItem {
    private Drawable mImageView;
    private int mTextView;

    GridItem(Drawable mImageView, int mTextView) {
        this.mImageView = mImageView;
        this.mTextView = mTextView;
    }


    public Drawable getmImageView() {
        return mImageView;
    }

    public int getmTextView() {
        return mTextView;
    }
}

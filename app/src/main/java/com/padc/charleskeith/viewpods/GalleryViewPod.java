package com.padc.charleskeith.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.padc.charleskeith.R;

import butterknife.BindView;

/**
 * Created by Phyo Thiha on 6/30/18.
 */
public class GalleryViewPod extends RelativeLayout {

    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_item_image_big)
    ImageView ivItemImage;

    public GalleryViewPod(Context context) {
        super(context);
    }

    public GalleryViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GalleryViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

}

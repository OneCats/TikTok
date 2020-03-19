package org.kakrot.tiktok.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.kakrot.tiktok.R;

public class ProfileLayout extends FrameLayout {

    public ProfileLayout(@NonNull Context context) {
        this(context, null);
    }

    public ProfileLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ProfileLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_profile, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}

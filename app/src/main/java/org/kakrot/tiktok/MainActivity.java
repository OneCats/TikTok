package org.kakrot.tiktok;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.tabs.TabLayout;

import org.kakrot.tiktok.adapter.TabAdapter;
import org.kakrot.tiktok.fragment.TabFragment;
import org.kakrot.tiktok.model.TabItemModel;
import org.kakrot.tiktok.widget.FullViewPager;
import org.kakrot.tiktok.widget.ScaleScrollView;
import org.kakrot.tiktok.widget.TitleLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kakrot
 */
public class MainActivity extends AppCompatActivity implements ScaleScrollView.OnScrollChangeListener {

    private TabLayout tab1, tab2;
    private TitleLayout titleLayout;
    private int colorPrimary;
    private ArgbEvaluator evaluator;
    private View statusView;

    private int getStatusBarHeight() {
        int height = 0;
        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            height = getResources().getDimensionPixelSize(resId);
        }
        return height;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
        initView();
    }

    private void initView() {
        //设置状态栏和导航栏
        statusView = findViewById(R.id.statusView);
        LinearLayoutCompat.LayoutParams lp = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        statusView.setLayoutParams(lp);
        statusView.setBackgroundColor(Color.TRANSPARENT);
        statusView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setNavigationBarColor(colorPrimary);

        AppCompatImageView banner = findViewById(R.id.banner);
        ScaleScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.setTargetView(banner);
        scrollView.setOnScrollChangeListener(this);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        titleLayout = findViewById(R.id.title_layout);
        FullViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabAdapter(this, getSupportFragmentManager(), getTabs()));
        tab1.setupWithViewPager(viewPager);
        tab2.setupWithViewPager(viewPager);
    }

    private List<TabItemModel> getTabs() {
        List<TabItemModel> tabs = new ArrayList<>();
        tabs.add(new TabItemModel("作品30", TabFragment.class.getName(), null));
        tabs.add(new TabItemModel("动态30", TabFragment.class.getName(), null));
        tabs.add(new TabItemModel("喜欢30", TabFragment.class.getName(), null));
        return tabs;
    }

    @Override
    public void onScrollChange(NestedScrollView v, int x, int y, int ox, int oy) {
        if (null != tab1 && null != tab2 && null != titleLayout && null != statusView) {
            int distance = tab1.getTop() - titleLayout.getHeight() - statusView.getHeight();
            float ratio = v.getScaleY() * 1f / distance;
            if (distance <= v.getScrollY()) {
                ratio = 1;
                if (tab2.getVisibility() != View.VISIBLE) {
                    tab2.setVisibility(View.VISIBLE);
                    statusView.setBackgroundColor(colorPrimary);
                }
            } else {
                if (tab2.getVisibility() == View.VISIBLE) {
                    tab2.setVisibility(View.INVISIBLE);
                    statusView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
            if (null == evaluator) {
                evaluator = new ArgbEvaluator();
            }
            titleLayout.setBackgroundColor((int) evaluator.evaluate(ratio, Color.TRANSPARENT, colorPrimary));
            titleLayout.setTitleColor((int) evaluator.evaluate(ratio, Color.TRANSPARENT, Color.WHITE));
        }
    }
}

package org.kakrot.tiktok.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.kakrot.tiktok.model.TabItemModel;

import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    private List<TabItemModel> models;
    private FragmentManager fm;
    private Context context;

    public TabAdapter(Context context, @NonNull FragmentManager fm, List<TabItemModel> models) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.models = models;
        this.fm = fm;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        TabItemModel model = models.get(position);
        Fragment fragment = fm.getFragmentFactory().instantiate(context.getClassLoader(), model.getClazz());
        fragment.setArguments(model.getArgs());
        return fragment;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return models.get(position).getTitle();
    }
}

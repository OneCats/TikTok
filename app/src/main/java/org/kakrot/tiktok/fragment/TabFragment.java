package org.kakrot.tiktok.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.kakrot.tiktok.DetailActivity;
import org.kakrot.tiktok.R;
import org.kakrot.tiktok.adapter.VideoAdapter;
import org.kakrot.tiktok.skeleton.Skeleton;
import org.kakrot.tiktok.skeleton.SkeletonScreen;

import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment implements VideoAdapter.OnItemClickListener {

    private SkeletonScreen mSkeletonScreen;
    private VideoAdapter mVideoAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideoAdapter = new VideoAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setNestedScrollingEnabled(false);
        mSkeletonScreen = Skeleton.bind(mRecyclerView)
                .adapter(mVideoAdapter)
                .load(R.layout.item_video_placeholder)
                .shimmer(true)
                .color(R.color.light_transparent)
                .angle(20)
                .frozen(false)
                .count(10)
                .show();
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSkeletonScreen.hide();
            }
        }, 2000);
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("" + i);
        }
        return list;
    }

    @Override
    public void onItemClick(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, view.getTransitionName());
        ActivityCompat.startActivity(getActivity(), new Intent(getContext(), DetailActivity.class), options.toBundle());
    }
}

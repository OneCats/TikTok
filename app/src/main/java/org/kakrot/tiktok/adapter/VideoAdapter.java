package org.kakrot.tiktok.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.kakrot.tiktok.DetailActivity;
import org.kakrot.tiktok.R;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    private OnItemClickListener mListener;
    private List<String> models = new ArrayList<>();

    public VideoAdapter(OnItemClickListener listener) {
        mListener = listener;
    }

    public void replaceAll(List<String> list) {
        this.models.clear();
        this.models.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_video, parent, false);
        view.setTransitionName(DetailActivity.TRANSITION_NAME);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    static class VideoHolder extends RecyclerView.ViewHolder {

        public VideoHolder(@NonNull final View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view);
    }
}

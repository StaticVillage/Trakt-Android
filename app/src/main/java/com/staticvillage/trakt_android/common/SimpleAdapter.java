package com.staticvillage.trakt_android.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.staticvillage.trakt_android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joelparrish on 11/6/16.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleItemViewHolder> {
    private List<String> items;
    private OnSimpleItemListener listener;

    public SimpleAdapter(List<String> items) {
        this.items = items;

        if (this.items == null) {
            this.items = new ArrayList<>();
        }
    }

    public void setListener(OnSimpleItemListener listener) {
        this.listener = listener;
    }

    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_layout, parent, false);
        return new SimpleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleItemViewHolder holder, int position) {
        holder.setText(items.get(position));
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

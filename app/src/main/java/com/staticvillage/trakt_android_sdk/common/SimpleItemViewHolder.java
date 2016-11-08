package com.staticvillage.trakt_android_sdk.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.staticvillage.trakt_android_sdk.R;

/**
 * Created by joelparrish on 11/6/16.
 */

public class SimpleItemViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public SimpleItemViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.simple_item_title);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}

package com.staticvillage.trakt_android_sdk.sync;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.staticvillage.trakt_android_sdk.R;
import com.staticvillage.trakt_android_sdk.common.OnSimpleItemListener;
import com.staticvillage.trakt_android_sdk.common.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joelparrish on 11/6/16.
 */

public class SyncFragment extends Fragment {
    public static SyncFragment newInstance() {
        return new SyncFragment();
    }

    private OnSimpleItemListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnSimpleItemListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_layout, container, false);

        List<String> sections = new ArrayList<>();
        sections.add("Last Activities");
        sections.add("Show Collection");
        sections.add("Movie Collection");

        SimpleAdapter adapter = new SimpleAdapter(sections);
        adapter.setListener(listener);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

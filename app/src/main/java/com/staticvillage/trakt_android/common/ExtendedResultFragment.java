package com.staticvillage.trakt_android.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staticvillage.trakt_android.R;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by joelparrish on 11/6/16.
 */

public abstract class ExtendedResultFragment extends Fragment {
    public TextView endpointUrl;
    public ViewGroup extendedContainer;
    public CheckBox extendedCheckBox;
    public TextInputLayout searchContainer;
    public EditText searchText;
    public Gson gson;
    private ProgressBar progressBar;
    private EditText resultText;
    private CompositeSubscription compositeSubscription;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.extended_result, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        endpointUrl = (TextView) view.findViewById(R.id.endpoint_url);
        extendedContainer = (ViewGroup) view.findViewById(R.id.extended_container);
        extendedCheckBox = (CheckBox) view.findViewById(R.id.checkbox_extended);
        searchContainer = (TextInputLayout) view.findViewById(R.id.search_container);
        searchText = (EditText) view.findViewById(R.id.edittext_query);
        resultText = (EditText) view.findViewById(R.id.result_text);
        resultText.setKeyListener(null);

        gson = new GsonBuilder().setPrettyPrinting().create();
        compositeSubscription = new CompositeSubscription();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.result_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.menu_request:
                submit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeSubscription.clear();
    }

    public void submit() {
        progressBar.setVisibility(View.VISIBLE);
        compositeSubscription.add(getResult()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    progressBar.setVisibility(View.GONE);
                    resultText.setText(result);
                }, error -> {
                    progressBar.setVisibility(View.GONE);
                    resultText.setText(error.getMessage());
                })
        );
    }

    public abstract Observable<String> getResult();
}

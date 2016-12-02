package com.staticvillage.trakt_android.common;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staticvillage.trakt_android.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by joelparrish on 11/6/16.
 */

public abstract class ExtendedCalendarResultFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    public TextView endpointUrl;
    public ViewGroup extendedContainer;
    public CheckBox extendedCheckBox;
    public TextInputLayout searchContainer;
    public EditText searchText;
    public EditText startDateText;
    public EditText daysText;
    public Gson gson;
    private ProgressBar progressBar;
    private EditText resultText;
    private DatePickerDialog datePicker;
    private CompositeSubscription compositeSubscription;
    private SimpleDateFormat dateFormat;
    private Date selectedDate;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.extended_calendar_result, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        endpointUrl = (TextView) view.findViewById(R.id.endpoint_url);
        extendedContainer = (ViewGroup) view.findViewById(R.id.extended_container);
        extendedCheckBox = (CheckBox) view.findViewById(R.id.checkbox_extended);
        searchContainer = (TextInputLayout) view.findViewById(R.id.search_container);
        searchText = (EditText) view.findViewById(R.id.edittext_query);
        startDateText = (EditText) view.findViewById(R.id.edit_start_date);
        daysText = (EditText) view.findViewById(R.id.edit_days);
        resultText = (EditText) view.findViewById(R.id.result_text);
        resultText.setKeyListener(null);

        gson = new GsonBuilder().setPrettyPrinting().create();
        compositeSubscription = new CompositeSubscription();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        Calendar calendar = Calendar.getInstance();
        datePicker = new DatePickerDialog(getContext(), this, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.setOnDismissListener(dialogInterface -> daysText.requestFocus());
        startDateText.setOnFocusChangeListener((v, b) -> {
            if (b) {
                datePicker.show();
            }
        });

        setUrl();

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

    public Date getStartDate() {
        return selectedDate;
    }

    public int getDays() {
        return Integer.parseInt(daysText.getText().toString());
    }

    public abstract void setUrl();

    public abstract Observable<String> getResult();

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        selectedDate = calendar.getTime();
        startDateText.setText(dateFormat.format(selectedDate));
    }
}

package com.staticvillage.trakt_android.auth;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.R;

import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static com.staticvillage.trakt_android.Constants.PREF_AUTH_TOKEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoutFragment extends Fragment {
    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    private CompositeSubscription compositeSubscription;
    private Button logoutButton;
    private OnAuthorizationListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnAuthorizationListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);
        logoutButton = (Button) view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(this::logout);

        compositeSubscription = new CompositeSubscription();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeSubscription.clear();
    }

    private void logout(View v) {
        compositeSubscription.add(App.getTraktService()
                .revokeToken()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                    editor.remove(PREF_AUTH_TOKEN);
                    editor.apply();

                    App.getTraktService().setAuthToken(null);
                    listener.onUnauthorized();
                }, error -> {
                    listener.onFailure();
                })
        );
    }
}

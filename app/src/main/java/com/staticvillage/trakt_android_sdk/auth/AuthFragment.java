package com.staticvillage.trakt_android_sdk.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.staticvillage.trakt_android_sdk.App;
import com.staticvillage.trakt_android_sdk.R;

import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static com.staticvillage.trakt_android_sdk.Constants.PREF_AUTH_TOKEN;

/**
 * Created by joelparrish on 10/24/16.
 */

public class AuthFragment extends Fragment {
    public static final String CODE = "code";

    private CompositeSubscription compositeSubscription;
    private OnAuthorizationListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnAuthorizationListener) context;
    }

    public static AuthFragment newInstance(String code) {
        Bundle bundle = new Bundle();
        bundle.putString(CODE, code);

        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        compositeSubscription = new CompositeSubscription();
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        compositeSubscription.add(App.getTraktService().getToken(getArguments().getString(CODE, ""))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(accessToken -> {
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                    editor.putString(PREF_AUTH_TOKEN, accessToken.getAccessToken());
                    editor.apply();

                    App.getTraktService().setAuthToken(accessToken.getAccessToken());
                    listener.onAuthorized();
                }, error -> {
                    listener.onFailure();
                })
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeSubscription.clear();
    }
}

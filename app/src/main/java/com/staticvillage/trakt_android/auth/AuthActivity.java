package com.staticvillage.trakt_android.auth;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.staticvillage.trakt_android.App;
import com.staticvillage.trakt_android.R;
import com.staticvillage.trakt_android.util.StringUtil;
import com.staticvillage.traktandroid.exception.AuthException;
import com.staticvillage.traktandroid.util.TraktUtil;

/**
 * Created by joelparrish on 11/4/16.
 */

public class AuthActivity extends AppCompatActivity implements OnAuthorizationListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String code = null;
        try {
            code = TraktUtil.getAuthCode(getIntent(), getString(R.string.redirect_uri));
        } catch (AuthException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (code != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, AuthFragment.newInstance(code))
                    .commit();
        } else if (StringUtil.isEmpty(App.getTraktService().getAuthToken())) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, LoginFragment.newInstance())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, LogoutFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onAuthorized() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, LogoutFragment.newInstance())
                .commit();
    }

    @Override
    public void onUnauthorized() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, LoginFragment.newInstance())
                .commit();
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, LoginFragment.newInstance())
                .commit();
    }
}

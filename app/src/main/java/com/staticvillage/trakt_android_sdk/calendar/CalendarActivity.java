package com.staticvillage.trakt_android_sdk.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.staticvillage.trakt_android_sdk.R;
import com.staticvillage.trakt_android_sdk.common.OnSimpleItemListener;

public class CalendarActivity extends AppCompatActivity implements OnSimpleItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, CalendarFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int position) {
        switch (position) {
            case 0:
                replaceFragment(MyShowsFragment.newInstance());
                break;
            case 1:
                replaceFragment(MyNewShowsFragment.newInstance());
                break;
            case 2:
                replaceFragment(MySeasonPremieresFragment.newInstance());
                break;
            case 3:
                replaceFragment(ShowsFragment.newInstance());
                break;
            case 4:
                replaceFragment(NewShowsFragment.newInstance());
                break;
            case 5:
                replaceFragment(SeasonPremieresFragment.newInstance());
                break;
            case 6:
                replaceFragment(MyMoviesFragment.newInstance());
                break;
            case 7:
                replaceFragment(MoviesFragment.newInstance());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}

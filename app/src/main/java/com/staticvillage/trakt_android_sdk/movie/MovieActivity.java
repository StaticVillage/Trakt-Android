package com.staticvillage.trakt_android_sdk.movie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.staticvillage.trakt_android_sdk.R;
import com.staticvillage.trakt_android_sdk.common.OnSimpleItemListener;

public class MovieActivity extends AppCompatActivity implements OnSimpleItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, MovieFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int position) {
        switch (position) {
            case 0:
                replaceFragment(TrendingFragment.newInstance());
                break;
            case 1:
                replaceFragment(PopularFragment.newInstance());
                break;
            case 2:
                replaceFragment(PlayedFragment.newInstance());
                break;
            case 3:
                replaceFragment(WatchedFragment.newInstance());
                break;
            case 4:
                replaceFragment(CollectedFragment.newInstance());
                break;
            case 5:
                replaceFragment(AnticipatedFragment.newInstance());
                break;
            case 6:
                replaceFragment(UpdatesFragment.newInstance());
                break;
            case 7:
                replaceFragment(SummaryFragment.newInstance());
                break;
            case 8:
                replaceFragment(AliasesFragment.newInstance());
                break;
            case 9:
                replaceFragment(ReleasesFragment.newInstance());
                break;
            case 10:
                replaceFragment(TranslationsFragment.newInstance());
                break;
            case 11:
                replaceFragment(RatingsFragment.newInstance());
                break;
            case 12:
                replaceFragment(RelatedFragment.newInstance());
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

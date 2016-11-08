package com.staticvillage.trakt_android_sdk.recommendation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.staticvillage.trakt_android_sdk.R;
import com.staticvillage.trakt_android_sdk.common.OnSimpleItemListener;
import com.staticvillage.trakt_android_sdk.movie.AliasesFragment;
import com.staticvillage.trakt_android_sdk.movie.AnticipatedFragment;
import com.staticvillage.trakt_android_sdk.movie.CollectedFragment;
import com.staticvillage.trakt_android_sdk.movie.PopularFragment;
import com.staticvillage.trakt_android_sdk.movie.RatingsFragment;
import com.staticvillage.trakt_android_sdk.movie.RelatedFragment;
import com.staticvillage.trakt_android_sdk.movie.ReleasesFragment;
import com.staticvillage.trakt_android_sdk.movie.SummaryFragment;
import com.staticvillage.trakt_android_sdk.movie.TranslationsFragment;
import com.staticvillage.trakt_android_sdk.movie.TrendingFragment;
import com.staticvillage.trakt_android_sdk.movie.UpdatesFragment;
import com.staticvillage.trakt_android_sdk.movie.WatchedFragment;

public class RecommendationActivity extends AppCompatActivity implements OnSimpleItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, RecommendationFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int position) {
        switch (position) {
            case 0:
                replaceFragment(MovieRecommendationFragment.newInstance());
                break;
            case 1:
                replaceFragment(ShowRecommendationFragment.newInstance());
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

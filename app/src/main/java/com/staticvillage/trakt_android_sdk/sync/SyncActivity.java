package com.staticvillage.trakt_android_sdk.sync;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.staticvillage.trakt_android_sdk.R;
import com.staticvillage.trakt_android_sdk.common.OnSimpleItemListener;
import com.staticvillage.trakt_android_sdk.show.AliasesFragment;
import com.staticvillage.trakt_android_sdk.show.AnticipatedFragment;
import com.staticvillage.trakt_android_sdk.show.CollectedFragment;
import com.staticvillage.trakt_android_sdk.show.LastEpisodeFragment;
import com.staticvillage.trakt_android_sdk.show.NextEpisodeFragment;
import com.staticvillage.trakt_android_sdk.show.PlayedFragment;
import com.staticvillage.trakt_android_sdk.show.PopularFragment;
import com.staticvillage.trakt_android_sdk.show.RatingsFragment;
import com.staticvillage.trakt_android_sdk.show.RelatedFragment;
import com.staticvillage.trakt_android_sdk.show.TranslationsFragment;
import com.staticvillage.trakt_android_sdk.show.TrendingFragment;
import com.staticvillage.trakt_android_sdk.show.UpdatesFragment;
import com.staticvillage.trakt_android_sdk.show.WatchedFragment;

public class SyncActivity extends AppCompatActivity implements OnSimpleItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, SyncFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int position) {
        switch (position) {
            case 0:
                replaceFragment(LastActivitiesFragment.newInstance());
                break;
            case 1:
                replaceFragment(ShowCollectionFragment.newInstance());
                break;
            case 2:
                replaceFragment(MovieCollectionFragment.newInstance());
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

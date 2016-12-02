package com.staticvillage.trakt_android.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.staticvillage.trakt_android.R;
import com.staticvillage.trakt_android.common.OnSimpleItemListener;

public class SearchActivity extends AppCompatActivity implements OnSimpleItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, SearchFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int position) {
        switch (position) {
            case 0:
                replaceFragment(MovieSearchFragment.newInstance());
                break;
            case 1:
                replaceFragment(ShowSearchFragment.newInstance());
                break;
            case 2:
                replaceFragment(EpisodeSearchFragment.newInstance());
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

package com.staticvillage.trakt_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.staticvillage.trakt_android.auth.AuthActivity;
import com.staticvillage.trakt_android.calendar.CalendarActivity;
import com.staticvillage.trakt_android.common.OnSimpleItemListener;
import com.staticvillage.trakt_android.episode.EpisodeActivity;
import com.staticvillage.trakt_android.genre.GenreActivity;
import com.staticvillage.trakt_android.movie.MovieActivity;
import com.staticvillage.trakt_android.recommendation.RecommendationActivity;
import com.staticvillage.trakt_android.search.SearchActivity;
import com.staticvillage.trakt_android.season.SeasonActivity;
import com.staticvillage.trakt_android.show.ShowActivity;
import com.staticvillage.trakt_android.sync.SyncActivity;

public class MainActivity extends AppCompatActivity implements OnSimpleItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, HomeFragment.newInstance())
                .commit();
    }

    @Override
    public void onItemSelected(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, AuthActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, CalendarActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, GenreActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, MovieActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, RecommendationActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, ShowActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, SeasonActivity.class));
                break;
            case 8:
                startActivity(new Intent(this, EpisodeActivity.class));
                break;
            case 9:
                startActivity(new Intent(this, SyncActivity.class));
                break;
        }
    }
}

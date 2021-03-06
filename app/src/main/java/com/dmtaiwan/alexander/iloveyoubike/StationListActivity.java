package com.dmtaiwan.alexander.iloveyoubike;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.dmtaiwan.alexander.iloveyoubike.Utilities.RecyclerAdapterStation;
import com.dmtaiwan.alexander.iloveyoubike.Utilities.Utilities;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * Created by Alexander on 7/28/2015.
 */
public class StationListActivity extends AppCompatActivity implements StationListFragment.Callback{
    private static final String LOG_TAG = StationListActivity.class.getSimpleName();
    private static final String DETAIL_FRAGMENT_TAG = "detail_frag_tag";
    private boolean mTabletLayout = false;

    @Optional
    @InjectView(R.id.toolbar_station)Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        if(findViewById(R.id.detail_container)!=null){
            mTabletLayout = true;
            if (savedInstanceState == null) {
                StationDetailFragment fragment = new StationDetailFragment();
                Bundle args = new Bundle();
                args.putInt(Utilities.EXTRA_STATION_ID, 1);
                fragment.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_container, fragment, DETAIL_FRAGMENT_TAG)
                        .commit();
            }
        }
    }

    @Override
    public void onItemSelected(int stationId, RecyclerAdapterStation.ViewHolder vh) {

        if (mTabletLayout) {
            Bundle args = new Bundle();
            args.putInt(Utilities.EXTRA_STATION_ID, stationId);
            StationDetailFragment fragment = new StationDetailFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment, DETAIL_FRAGMENT_TAG).commit();
        }else {

            Log.i(LOG_TAG, "Starting station detail activity");
            Intent detailIntent = new Intent(this, StationDetailActivity.class);
            detailIntent.putExtra(Utilities.EXTRA_STATION_ID, stationId);
            Pair<View, String> p1 = Pair.create((View) vh.stationStatus, getString(R.string.transition_status_iamge_view));
            Pair<View, String> p2 = Pair.create((View) vh.stationName, getString(R.string.transitoin_station_name_text));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                startActivity(detailIntent, options.toBundle());
            } else {
                startActivity(detailIntent);
            }
        }
    }

}

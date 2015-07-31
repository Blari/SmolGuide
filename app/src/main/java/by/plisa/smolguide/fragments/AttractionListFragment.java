package by.plisa.smolguide.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import by.plisa.smolguide.R;
import by.plisa.smolguide.activity.AttractionDetailActivity;
import by.plisa.smolguide.adapters.AttractionAdapter;
import by.plisa.smolguide.models.Attraction;
import by.plisa.smolguide.utils.AttractionApiInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AttractionListFragment extends ListFragment {

    private AttractionAdapter mAdapter;

    public static AttractionListFragment getInstans() {
        AttractionListFragment fragment = new AttractionListFragment();

        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListShown(false);
        getListView().setPadding(16, 16, 16, 16);
        getListView().setDivider(new ColorDrawable(Color.TRANSPARENT));
        getListView().setDividerHeight( 16 );
        getListView().setScrollBarStyle( View.SCROLLBARS_OUTSIDE_OVERLAY);
        getListView().setClipToPadding( true );
        mAdapter = new AttractionAdapter( getActivity(), 0);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint( getString(R.string.attraction_url))
                .build();

        AttractionApiInterface attractionApiInterface = restAdapter.create(AttractionApiInterface.class);
        attractionApiInterface.getStreams(new Callback<List<Attraction>>() {
            @Override
            public void success(List<Attraction> attractions, Response response) {
            if(attractions == null || attractions.isEmpty() || !isAdded() )
                return;

            for (Attraction attraction : attractions){
               mAdapter.add( attraction );
            }
                mAdapter.notifyDataSetChanged();
                setListAdapter( mAdapter );
                setListShown( true );
            }

            @Override
            public void failure(RetrofitError error) {
            Log.e("Smolguide", "Retrofit error" + error.getMessage());
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent( getActivity(), AttractionDetailActivity.class);
        intent.putExtra(AttractionDetailActivity.EXTRA_ATRACTION, mAdapter.getItem( position ));

        startActivity( intent );
    }
}

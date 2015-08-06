package by.plisa.smolguide.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import by.plisa.smolguide.R;
import by.plisa.smolguide.models.Pin;
import by.plisa.smolguide.utils.PinsApiInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

    public static MapFragment getInstance() {
        return new MapFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(54.0290, 28.0909), 13));

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint( getString( R.string.pins_url ) )
                .build();

        PinsApiInterface pinsApiInterface = adapter.create(PinsApiInterface.class);

        pinsApiInterface.getStreams(new Callback<List<Pin>>() {
            @Override
            public void success(List<Pin> pins, Response response) {
                if (!isAdded() || pins == null || pins.isEmpty())
                    return;

                for (Pin pin : pins) {
                    MarkerOptions options = new MarkerOptions()
                            .position(new LatLng(pin.getLatitude(), pin.getLongitude()));
                    options.title(pin.getName());
                    options.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    getMap().addMarker(options);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}

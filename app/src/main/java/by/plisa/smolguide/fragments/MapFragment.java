package by.plisa.smolguide.fragments;


        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.View;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.CameraPosition;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;

        import by.plisa.smolguide.R;
        import by.plisa.smolguide.models.Pin;
        import by.plisa.smolguide.utils.PinsApiInterface;

        import java.util.List;

        import retrofit.Callback;
        import retrofit.RestAdapter;
        import retrofit.RetrofitError;
        import retrofit.client.Response;


public class MapFragment extends SupportMapFragment {

    public static MapFragment getInstance() {
        MapFragment fragment = new MapFragment();

        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMap().setMyLocationEnabled(true);

        CameraPosition position = CameraPosition.builder()
                .target(new LatLng(54.0290, 28.0909))
                .zoom(16f )
                .bearing(0.0f)
                .tilt( 0.0f )
                .build();

        getMap().animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
        getMap().setMapType(GoogleMap.MAP_TYPE_HYBRID);
        getMap().setTrafficEnabled(true);

       // getMap().getUiSettings().setMapToolbarEnabled( true );
        getMap().getUiSettings().setZoomControlsEnabled(true);
        getMap().getUiSettings().setMyLocationButtonEnabled(true);
        getMap().getUiSettings().setCompassEnabled(true);

        final MarkerOptions options = new MarkerOptions().position( new LatLng(54.0298,28.0897));
        options.title("Смолевичи");
        options.snippet("Мой город!\nВторая строка");
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        getMap().addMarker(options);

        getMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                getMap().getUiSettings().setMapToolbarEnabled( true );
                marker.showInfoWindow();
                return true;
            }
        });

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint( getString( R.string.pins_url ) )
                .build();

        PinsApiInterface pinsApiInterface = adapter.create( PinsApiInterface.class );

        pinsApiInterface.getStreams( new Callback<List<Pin>>() {
            @Override
            public void success(List<Pin> pins, Response response) {
                if(!isAdded() || pins == null || pins.isEmpty() )
                    return;

                for( Pin pin : pins ) {
                    MarkerOptions options = new MarkerOptions().position( new LatLng( pin.getLatitude(), pin.getLongitude() ) );
                    options.title( pin.getName() );
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    getMap().addMarker( options );
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}

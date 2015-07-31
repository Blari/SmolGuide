package by.plisa.smolguide.utils;

import java.util.List;

import by.plisa.smolguide.models.Attraction;
import retrofit.Callback;
import retrofit.http.GET;


public interface AttractionApiInterface {
    @GET( "/attraction.json" )
    void getStreams( Callback<List<Attraction>> callback );
}

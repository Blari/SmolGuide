package by.plisa.smolguide.utils;

import by.plisa.smolguide.models.GalleryImage;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface GalleryApiInterface {

    @GET( "/Gallery.json" )
    void getStreams( Callback<List<GalleryImage>> callback );

}

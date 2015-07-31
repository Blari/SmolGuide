package by.plisa.smolguide.utils;

        import by.plisa.smolguide.models.Pin;

        import java.util.List;

        import retrofit.Callback;
        import retrofit.http.GET;

public interface PinsApiInterface {

    @GET( "/Pins.json" )
    void getStreams( Callback<List<Pin>> callback );
}

package by.plisa.smolguide.models;


import android.os.Parcel;
import android.os.Parcelable;


public class Attraction implements Parcelable {
    private String name;
    private String species;
    private String description;
    private String thumbnail;
    private String image;

    public Attraction() {

    }

    public Attraction( String name, String species, String description, String thumbnail, String image ) {
        this.name = name;
        this.species = species;
        this.description = description;
        this.thumbnail = thumbnail;
        this.image = image;
    }

    public Attraction( Parcel source ) {
        name = source.readString();
        species = source.readString();
        description = source.readString();
        thumbnail = source.readString();
        image = source.readString();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        applyDefaultValues();

        dest.writeString( name );
        dest.writeString( species );
        dest.writeString( description );
        dest.writeString( thumbnail );
        dest.writeString( image );
    }

    private void applyDefaultValues() {
        if( name == null )
            name = "";
        if( species == null )
            species = "";
        if( description == null )
            description = "";
        if( thumbnail == null )
            thumbnail = "";
        if( image == null )
            image = "";
    }

    public static Creator<Attraction> CREATOR = new Creator<Attraction>() {

        @Override
        public Attraction createFromParcel(Parcel source) {
            return new Attraction( source );
        }

        @Override
        public Attraction[] newArray(int size) {
            return new Attraction[size];
        }
    };
}

package by.plisa.smolguide.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import by.plisa.smolguide.R;
import by.plisa.smolguide.models.Attraction;


public class AttractionDetailActivity extends AppCompatActivity {
    public static final String EXTRA_ATRACTION = "extra_atraction";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atraction_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Attraction atraction = getIntent().getExtras().getParcelable( EXTRA_ATRACTION );

        TextView species = (TextView) findViewById( R.id.species );
        TextView description = (TextView) findViewById( R.id.description );
        ImageView image = (ImageView) findViewById( R.id.image );

        species.setText( atraction.getSpecies() );
        description.setText( atraction.getDescription() );

        Picasso.with(this).load( atraction.getImage() ).into( image );
    }
}

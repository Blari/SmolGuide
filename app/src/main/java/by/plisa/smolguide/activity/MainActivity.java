package by.plisa.smolguide.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.squareup.otto.Subscribe;

import by.plisa.smolguide.R;
import by.plisa.smolguide.events.DrawerSectionItemClickedEvent;
import by.plisa.smolguide.fragments.AttractionListFragment;
import by.plisa.smolguide.fragments.GalleryFragment;
import by.plisa.smolguide.fragments.MapFragment;
import by.plisa.smolguide.utils.EventBus;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private String mCurrentFragmentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.drawer_open);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.drawer_closed);
            }
        };
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        displayInitialFragment();
         }
        private void displayInitialFragment() {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, AttractionListFragment.getInstans() ).commit();
            mCurrentFragmentTitle = getString(R.string.dw_attraction);
        }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if( mActionBarDrawerToggle.onOptionsItemSelected( item ) )
            return true;

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getInstance().register( this );
    }

    @Override
    protected void onStop() {
        EventBus.getInstance().unregister(this);
        super.onStop();
    }
    @Subscribe
    public void onDrawerSectionItemClickEvent( DrawerSectionItemClickedEvent event ) {
        mDrawerLayout.closeDrawers();

        if( event == null || TextUtils.isEmpty( event.section ) || event.section.equalsIgnoreCase( mCurrentFragmentTitle ) ) {
            return;
        }

        if( event.section.equalsIgnoreCase( getString(R.string.dw_map) ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, MapFragment.getInstance() ).commit();

        } else if( event.section.equalsIgnoreCase( getString(R.string.dw_gallery) ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, GalleryFragment.getInstance() ).commit();

        } else if( event.section.equalsIgnoreCase( getString(R.string.dw_attraction) ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, AttractionListFragment.getInstans() ).commit();

        } /*else if( event.section.equalsIgnoreCase( getString(R.string.dw_news) ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, AttractionListFragment.getInstans() ).commit();

        }*/ else {
            return;
        }

        mCurrentFragmentTitle = event.section;
    }
}
//else if( event.section.equalsIgnoreCase( getString(R.string.dw_news, NewsFragment.getInstance() ).commit();
package by.plisa.smolguide.views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import by.plisa.smolguide.adapters.DrawerNavigationListAdapter;
import by.plisa.smolguide.R;
import by.plisa.smolguide.events.DrawerSectionItemClickedEvent;
import by.plisa.smolguide.utils.EventBus;


public class DrawerNavigationListView extends ListView implements AdapterView.OnItemClickListener {
    public DrawerNavigationListView(Context context) {
        this(context, null);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        DrawerNavigationListAdapter adapter = new DrawerNavigationListAdapter( getContext(), 0 );
        //adapter.add( getContext().getString(R.string.dw_news) );
        adapter.add(getContext().getString(R.string.dw_attraction));
        adapter.add( getContext().getString(R.string.dw_gallery) );
        adapter.add( getContext().getString(R.string.dw_map) );

        setAdapter( adapter );

        setOnItemClickListener( this );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getInstance().post( new DrawerSectionItemClickedEvent(
                (String) parent.getItemAtPosition( position ) ) );
    }
}

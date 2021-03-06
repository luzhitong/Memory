package com.ucas.memory;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    private ListView listView;
    List<String> data ;
    private TimelineAdapter timelineAdapter;

    private  BottomFloatListView mBottomFloatListView;


    private NavigationDrawerFragment mNavigationDrawerFragment;
 //   private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timelineAdapter = new TimelineAdapter(this, getData());

        mBottomFloatListView = (BottomFloatListView)findViewById(R.id.listView)  ;
        mBottomFloatListView.setDividerHeight(0);
        mBottomFloatListView.setAdapter(timelineAdapter);
        ViewGroup bottomView = (ViewGroup)findViewById(R.id.bottombar) ;
        mBottomFloatListView.setBottomBar(bottomView);
        mBottomFloatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, ShowMemoryInfoActivity.class);
                startActivity(i);

            }
        });

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        //mTitle = getTitle();



        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

       this.getSupportActionBar().setBackgroundDrawable(this.getResources().getDrawable(R.drawable.wb_bg_titlebar));

       // DataUtility.login(ConnServer.login(email, pwd));
        //DataUtility.add_memory(ConnServer.add_memory("title", "location", "2014-12-12 12:12:12", "content", "people", "123"));
       // DataUtility.get_momery_list(ConnServer.get_momery_list("", "2015-12-12 12:21:12", 0, 10));



    }





    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "title");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "title");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "title");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "title");
        list.add(map);
        return list;
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
      /*  switch (number) {
            case 1:
                mTitle = "Memory";
                break;
            case 2:
                mTitle = "Memory";
                break;
            case 3:
                mTitle = "Memory";
                break;
        }*/
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
       // actionBar.setDisplayShowTitleEnabled(true);
      //  actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);


        menu.add( "Search" ).setIcon( getResources().getDrawable( R.drawable.wb_item_lbs ) ).setActionView( R.layout.search )
                .setShowAsActionFlags( MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW );
        SearchView searchView = new SearchView( getSupportActionBar().getThemedContext() );
        searchView.setQueryHint( "Search for countries…" );
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener()
        {

            @Override
            public boolean onQueryTextSubmit( String arg0 )
            {
                // TODO Auto-generated method stub
                System.out.println(arg0+"    111111111111111111111a");
                return true ;
            }

            @Override
            public boolean onQueryTextChange( String arg0 )
            {
                // TODO Auto-generated method stub
                System.out.println(arg0+"    111111111111111111111");
                return true;
            }
        } );
     /*   if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.

            getMenuInflater().inflate(R.menu.main, menu);
           // restoreActionBar();




        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_add:
                Intent i=new Intent();
                i.setClass(MainActivity.this,AddMemoryActivity.class);
                startActivity(i);
                return true;
            case R.id.search:
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}

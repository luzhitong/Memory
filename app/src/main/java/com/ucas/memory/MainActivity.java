package com.ucas.memory;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    List<String> data ;
    private TimelineAdapter timelineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        listView = (ListView) this.findViewById(R.id.listview);
        listView.setDividerHeight(0);
        timelineAdapter = new TimelineAdapter(this, getData());
        listView.setAdapter(timelineAdapter);

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
}

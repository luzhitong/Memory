package com.ucas.memory;

import android.app.Activity;
import android.app.SearchManager;
import android.widget.ListView;
import android.widget.SearchView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by w on 2015/1/6.
 */
public class SearchActivity extends Activity {
    static final String[] DATA_COLLECTION = {
            "abc", "good", "baidu", "ni ku", "mitu", "11111111", "11111111",
            "222222222222", "sldf", "android", "apk"
    };

    private SearchView mSv;
    private SearchManager mSearMan;
    private SearchViewAdapter mAdapter;
    private ListView mSearchList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        init();
    }

    private void init() {
        mSearMan = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        mSv = (SearchView) findViewById(R.id.show_search_view);
        mSearchList = (ListView) findViewById(R.id.search_list_view);
        mSv.setSearchableInfo(mSearMan.getSearchableInfo(getComponentName()));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
            // the string used to query
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    private void doMySearch(String str) {
        ArrayList<String> data = new ArrayList<String>();
        for (int i = 0; i < DATA_COLLECTION.length; i++) {
            if (DATA_COLLECTION[i].contains(str)) {
                data.add(DATA_COLLECTION[i]);
            }
        }
        if (mAdapter == null) {
            mAdapter = new SearchViewAdapter(this, data);
            mSearchList.setAdapter(mAdapter);
        } else {
            mAdapter.mData = data;
            mAdapter.notifyDataSetChanged();
        }
    }

    private static class SearchViewAdapter extends BaseAdapter {

        public List<String> mData;
        LayoutInflater mInflater;

        public SearchViewAdapter(Context context, List<String> data) {
            if (context == null || data == null)
                throw new IllegalArgumentException("argument data or context is null .");
            mData = data;
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return mData.size();
        }

        public Object getItem(int pos) {
            return mData.get(pos);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.search_item, null);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mDataItem = (TextView) convertView.findViewById(R.id.searched_data_item);
            holder.mDataItem.setText((String) getItem(position));
            return convertView;
        }

    }

    private static class ViewHolder {
        public TextView mDataItem;
    }
}


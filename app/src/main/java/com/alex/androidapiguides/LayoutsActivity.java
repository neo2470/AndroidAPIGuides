package com.alex.androidapiguides;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by alex on 15-4-11.\
 * API Guides / User Interface / Layouts
 */
public class LayoutsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouts);
        changeTitle();

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LayoutsActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class GridAdapter extends BaseAdapter {

        public GridAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = null;
            if(null == convertView) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(imgs[position]);
            return imageView;
        }

        private Context mContext;
    }

    private final int[] imgs = {
            R.drawable.android_128,
            R.drawable.apple_128,
            R.drawable.badge_html5_128,
            R.drawable.chrome_128,
            R.drawable.dropbox_128,
            R.drawable.firefox_128,
            R.drawable.github_128,
            R.drawable.google_drive_128,
            R.drawable.google_play_128,
            R.drawable.google_plus_128,
            R.drawable.google_wallet_128,
            R.drawable.instagram_128,
            R.drawable.internet_explorer_128,
            R.drawable.kickstarter_128,
            R.drawable.picasa_128,
            R.drawable.pinterest_128,
            R.drawable.skype_128,
            R.drawable.square_linkedin_128,
            R.drawable.twitter_128,
            R.drawable.windows_128,
            R.drawable.youtube_128
    };
}

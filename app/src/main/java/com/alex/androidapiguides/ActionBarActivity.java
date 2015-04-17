package com.alex.androidapiguides;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.alex.fragment.TabOneFragment;
import com.alex.fragment.TabThreeFragment;
import com.alex.fragment.TabTwoFragment;

/**
 * Created by alex on 15-4-16.
 * API Guides / User Interface / Action Bar
 */
public class ActionBarActivity extends Activity implements MenuItem.OnActionExpandListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Add Navigation Tab
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        ActionBar.Tab tab = actionBar.newTab()
                .setText("One")
                .setTabListener(new TabListener<TabOneFragment>("One", TabOneFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("Two")
                .setTabListener(new TabListener<TabTwoFragment>("Two", TabTwoFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("Three")
                .setTabListener(new TabListener<TabThreeFragment>("Three", TabThreeFragment.class));
        actionBar.addTab(tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setOnActionExpandListener(this);

        MenuItem shareItem = menu.findItem(R.id.share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) shareItem.getActionProvider();
        shareActionProvider.setShareIntent(getDefaultIntent());

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent();
    }

    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        Toast.makeText(this, "onMenuItemActionExpand()", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        Toast.makeText(this, "onMenuItemActionCollapse()", Toast.LENGTH_SHORT).show();
        return true;
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
    }

    private class TabListener<T extends Fragment> implements ActionBar.TabListener {

        public TabListener(String tag, Class<T> clz) {
            mTag = tag;
            mClass = clz;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (null == mFragment) {
                mFragment = Fragment.instantiate(ActionBarActivity.this, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                ft.attach(mFragment);
            }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            if(null != mFragment) {
                ft.detach(mFragment);
            }
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        private Fragment mFragment;
        private final Class<T> mClass;
        private String mTag;
    }
}

package com.padc.charleskeith.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.charleskeith.R;
import com.padc.charleskeith.adapters.NewProductsAdapter;
import com.padc.charleskeith.data.models.NewProductsModel;
import com.padc.charleskeith.data.vos.NewProductVO;
import com.padc.charleskeith.delegates.NewProductDelegate;
import com.padc.charleskeith.events.ApiErrorEvent;
import com.padc.charleskeith.events.GetNewProductForceSuccessEvent;
import com.padc.charleskeith.events.GetNewProductSuccessEvent;
import com.padc.charleskeith.utils.AppConstants;
import com.padc.charleskeith.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, NewProductDelegate {
    @BindView(R.id.rv_new_items)
    RecyclerView rvNewItems;
    @BindView(R.id.iv_single_view)
    ImageView ivSingleView;
    @BindView(R.id.iv_dual_view)
    ImageView ivDualView;
    @BindView(R.id.v_single_highlighter)
    View vSingleViewHighlighter;
    @BindView(R.id.v_dual_highlighter)
    View vDualViewHighlighter;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_item_count)
    TextView tvItemCount;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    private NewProductsAdapter adapter;
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvItemCount.setText("20 Items");

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_opener);

        rvNewItems.setLayoutManager(new GridLayoutManager(this, 2));
        rvNewItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean isEndReached = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1
                        && !isEndReached) {
                    isEndReached = true;
                    NewProductsModel.getObjectReference().loadNewProducts();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleCount = recyclerView.getLayoutManager().getChildCount();
                int totalCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisibleItemCount = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if ((visibleCount + pastVisibleItemCount) < totalCount) {
                    isEndReached = false;
                    NewProductsModel.getObjectReference().loadNewProducts();
                }

            }
        });
        adapter = new NewProductsAdapter(this);
        rvNewItems.setAdapter(adapter);

        NewProductsModel.getObjectReference().loadNewProducts();
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> NewProductsModel.getObjectReference().forceRefreshNewProducts());

        ivDualView.setOnClickListener(v -> {
                    adapter.setLayoutManager(true);
                    rvNewItems.setLayoutManager(new GridLayoutManager(v.getContext(), 2));
                    vDualViewHighlighter.setVisibility(View.VISIBLE);
                    vSingleViewHighlighter.setVisibility(View.GONE);
                }
        );

        ivSingleView.setOnClickListener(v -> {
            adapter.setLayoutManager(false);
            rvNewItems.setLayoutManager(new GridLayoutManager(v.getContext(), 1));
            vSingleViewHighlighter.setVisibility(View.VISIBLE);
            vDualViewHighlighter.setVisibility(View.GONE);
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTapProduct(NewProductVO newProduct) {
        Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
        intent.putExtra(AppConstants.NEW_PRODUCT_ID, newProduct.getProductId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewProducts(GetNewProductSuccessEvent event) {
        Log.d("onSuccessGetNewProducts", "onSuccessGetNewProducts: " + event.getmNewProducts().size());
        swipeRefreshLayout.setRefreshing(false);
        adapter.appendNewProductList(event.getmNewProducts());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onerrorGetNewProducts(ApiErrorEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        if (!event.getErrorMessage().toLowerCase().equals("success")) {
            swipeRefreshLayout.setVisibility(View.GONE);
            vpEmpty.setVisibility(View.VISIBLE);
            vpEmpty.setEmptyData(R.drawable.ic_error_placeholder,"အသစ္ ထြက္ ပစၥည္း မ်ား ကို ေလာ ေလာ ဆယ္ တြင္ မျပ ႏိုင္ ေသး တာ စိတ္ မေကာင္း ပါ ဘူး");
            tvItemCount.setText("");
            Snackbar.make(rvNewItems, event.getErrorMessage(), Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessForceEvent(GetNewProductForceSuccessEvent event) {
        Log.d("onSuccessForceEvent", "onSuccessForceEvent: " + event.getmNewProducts().size());
        swipeRefreshLayout.setRefreshing(false);
        adapter.setNewProductList(event.getmNewProducts());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}

package com.mapabc.materialdrawerdemo.avtivity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mapabc.materialdrawerdemo.R;
import com.mapabc.materialdrawerdemo.adapter.HomeViewPagerAdapter;
import com.mapabc.materialdrawerdemo.base.BaseActivity;
import com.mapabc.materialdrawerdemo.fragment.HomeTabFragment1;
import com.mapabc.materialdrawerdemo.fragment.HomeTabFragment2;
import com.mapabc.materialdrawerdemo.fragment.HomeTabFragment3;
import com.mapabc.materialdrawerdemo.fragment.HomeTabFragment4;
import com.mapabc.materialdrawerdemo.viewmodule.CircleImageView;

public class HomeActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar toolbar;
    private CircleImageView currentUserAvater;
    private TextView currentUserName;

//    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeViewPagerAdapter adapter;

    private LinearLayout linearLayout_home;
    private LinearLayout linearLayout_groupbuy;
    private LinearLayout linearLayout_discover;
    private LinearLayout linearLayout_mine;

    private static int[] imgchecked = {R.drawable.home_checked, R.drawable.icon_nearby_check, R.drawable.discover_checked, R.drawable.mine_checked};
    private static int[] imgunchecked = {R.drawable.home_unchecked, R.drawable.icon_nearby_uncheck, R.drawable.discover_unchecked, R.drawable.mine_unchecked};
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContentView();
        initViews();
        initListeners();
        initData();
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_home);
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setNavigationBarTintEnabled(true);
//        tintManager.setStatusBarTintColor(getResources().getColor(R.color.titlebar));
    }

    @Override
    public void initViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        mNavigationView = (NavigationView) findViewById(R.id.id_navigationview);
//        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager_mainpage);
        linearLayout_home = (LinearLayout) findViewById(R.id.main_home);
        linearLayout_groupbuy = (LinearLayout) findViewById(R.id.main_groupbuy);
        linearLayout_discover = (LinearLayout) findViewById(R.id.main_discover);
        linearLayout_mine = (LinearLayout) findViewById(R.id.main_mine);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        /**
         * init DrawLayout+Navigation
         */
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mNavigationView.inflateHeaderView(R.layout.navigation_header);
        mNavigationView.inflateMenu(R.menu.menu_navigation);

        // 设置NavigationView中menu的item被选中后要执行的操作
        onNavgationViewMenuItemSelected(mNavigationView);

        /**
         * 当前用户信息
         */
        View navheaderView = mNavigationView.getHeaderView(0);  //获取头部布局

        currentUserAvater = (CircleImageView) navheaderView.findViewById(R.id.current_userAvater);
        currentUserName = (TextView) navheaderView.findViewById(R.id.current_userName);
    }

    @Override
    public void initListeners() {
        linearLayout_home.setOnClickListener(this);
        linearLayout_groupbuy.setOnClickListener(this);
        linearLayout_discover.setOnClickListener(this);
        linearLayout_mine.setOnClickListener(this);
        viewPager.setOnPageChangeListener(this);
        checkedNum(0);

    }

    boolean isfirst = true;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus){
            if (isfirst){
                viewPager.setCurrentItem(0);
//                fragmentList.get(0).setUserVisibleHint(true);
                isfirst = false;
            }
        }
    }

    @Override
    public void initData() {
        currentUserAvater.setImageResource(R.drawable.default_avater);//默认的头像
        currentUserName.setText("我是用户名");
    }


    /**
     * 设置ViewPager+Fragment
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new HomeTabFragment1().newInstance("Page1"), "地图1");
        adapter.addFragment(new HomeTabFragment2().newInstance("Page2"), "地图2");
        adapter.addFragment(new HomeTabFragment3().newInstance("Page3"), "地图3");
        adapter.addFragment(new HomeTabFragment4().newInstance("Page4"), "地图4");
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
//        tabLayout.setupWithViewPager(viewPager);
    }



    /**
     * 设置NavigationView中menu的item被选中后要执行的操作
     *
     * @param mNav
     */
    private void onNavgationViewMenuItemSelected(NavigationView mNav) {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    // do your business here eg:
                    case R.id.unusual_jams:
//                        msgString = (String) menuItem.getTitle();
                        Toast.makeText(HomeActivity.this, "异常拥堵", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.regular_jam:
                        Toast.makeText(HomeActivity.this, "常规拥堵", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.traffic_incidents:
                        Toast.makeText(HomeActivity.this, "交通事件", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.event_publishing:
                        Toast.makeText(HomeActivity.this, "事件发布", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.study_analysis:
                        Toast.makeText(HomeActivity.this, "研判分析", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.traffic_reports:
                        Toast.makeText(HomeActivity.this, "交通路况", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.transport_layer:
                        Toast.makeText(HomeActivity.this, "交通图层", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.traffic_report:
                        Toast.makeText(HomeActivity.this, "交通报告", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

                // Menu item点击后选中，并关闭Drawerlayout
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_home:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.main_groupbuy:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.main_discover:
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.main_mine:
                viewPager.setCurrentItem(3, false);
                break;
            default:break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                checkedNum(0);
                break;
            case 1:
                checkedNum(1);
                break;
            case 2:
                checkedNum(2);
                break;
            case 3:
                checkedNum(3);
                break;
            default:break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 点击下方button时，切换图片和字体的颜色
     * @param checked
     */
    public void checkedNum(int checked){
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            LinearLayout linear = (LinearLayout) radioGroup.getChildAt(i);
            ((ImageView)linear.getChildAt(0)).setImageResource(imgunchecked[i]);
            ((TextView)linear.getChildAt(1)).setTextColor(getResources().getColor(R.color.maintab_text));
            if (checked == i) {
                ((ImageView)linear.getChildAt(0)).setImageResource(imgchecked[i]);
                ((TextView)linear.getChildAt(1)).setTextColor(getResources().getColor(R.color.titlebar));
            }
        }
    }

}

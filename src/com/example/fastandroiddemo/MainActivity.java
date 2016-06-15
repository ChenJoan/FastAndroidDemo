package com.example.fastandroiddemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    //viewpger构成主题页面
    public ViewPager viewPager;
    private List<Fragment> fragmentList = null;
    private MyPagerAdapter myPagerAdapter;
    private FragmentManager fragmentManager;

    private LinearLayout linearLayout_home;
    private LinearLayout linearLayout_groupbuy;
    private LinearLayout linearLayout_discover;
    private LinearLayout linearLayout_mine;

    private static int[] imgchecked = {R.drawable.home_checked, R.drawable.icon_nearby_check, R.drawable.discover_checked, R.drawable.mine_checked};
    private static int[] imgunchecked = {R.drawable.home_unchecked, R.drawable.icon_nearby_uncheck, R.drawable.discover_unchecked, R.drawable.mine_unchecked};

    private RadioGroup radioGroup;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

  
    @Override
    public void initView() {
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager_mainpage);

        linearLayout_home = (LinearLayout) findViewById(R.id.main_home);
        linearLayout_groupbuy = (LinearLayout) findViewById(R.id.main_groupbuy);
        linearLayout_discover = (LinearLayout) findViewById(R.id.main_discover);
        linearLayout_mine = (LinearLayout) findViewById(R.id.main_mine);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        fragmentList = new ArrayList<Fragment>();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(null).commit();
        myPagerAdapter = new MyPagerAdapter(fragmentManager, fragmentList);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void initData() {
        fragmentList.add(new CheckItemFragHome());
        fragmentList.add(new CheckItemFragGroupBy());
        fragmentList.add(new CheckItemFragDiscover());
        fragmentList.add(new CheckItemFragMine());
        myPagerAdapter.notifyDataSetChanged();
        checkedNum(0);
    }

    boolean isfirst = true;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus){
            if (isfirst){
                viewPager.setCurrentItem(0);
                fragmentList.get(0).setUserVisibleHint(true);
                isfirst = false;
            }
        }
    }

    @Override
    public void setListener() {
        linearLayout_home.setOnClickListener(this);
        linearLayout_groupbuy.setOnClickListener(this);
        linearLayout_discover.setOnClickListener(this);
        linearLayout_mine.setOnClickListener(this);
        viewPager.setOnPageChangeListener(this);
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
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
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
    public void onPageScrollStateChanged(int i) {

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

    private long firstTime = 0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000){
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
//                    return  true;
                }else {
//                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
    
    
    
    public static class CheckItemFragMine extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.mine,
					container, false);

			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
		}
	}
    
    public static class CheckItemFragHome extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.home,
					container, false);

			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
		}
	}
    
    public static class CheckItemFragGroupBy extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.groupby,
					container, false);

			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
		}
	}
    
    public static class CheckItemFragDiscover extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.discover,
					container, false);

			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
		}
	}
    
}

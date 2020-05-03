package com.example.cmusic.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cmusic.R;
import com.example.cmusic.view.Home.HomeFragment;
import com.example.cmusic.view.mycenter.MyCenterFragment;
import com.example.cmusic.view.mysonglist.MySongListFragment;
import com.example.cmusic.view.play.PlayFragment;
import com.example.cmusic.view.playlist.PlayListActivity;
import com.example.cmusic.view.playlist.PlayListFragment;

import java.util.ArrayList;
import java.util.List;

import bean.Album;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,
MyCenterFragment.OnFragmentInteractionListener,MySongListFragment.OnFragmentInteractionListener{

    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;

    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;

    private static String baseUrl = "http://47.99.165.194";
    public static final String GET_RECOMMEND_ALBUM = baseUrl + "/top/playlist";
    public static final String GET_PLAYLIST_DETAIL = baseUrl + "/playlist/detail";
    public static final String GET_MY_SONG_LIST = baseUrl + "/user/playlist";
    public static final String GET_USER_DETAIL = baseUrl + "/user/detail";

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(position);
            if (radioButton != null)radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(mPageChangeListener);
    }

    private void initView() {
        mViewPager = findViewById(R.id.main_activity_viewpager);
        mTabRadioGroup = findViewById(R.id.main_activity_radio_group);

        mFragments = new ArrayList<>(3);
        mFragments.add(HomeFragment.newInstance("",""));
        mFragments.add(MySongListFragment.newInstance("",""));
        mFragments.add(MyCenterFragment.newInstance("",""));

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onFragmentInteraction(Uri uri, Album album) {
        Intent intent = new Intent(MainActivity.this, PlayListActivity.class);
        intent.putExtra("id",album.getId());
        intent.putExtra("name",album.getName());
        intent.putExtra("nickname",album.getCreator().getNickname());
        startActivity(intent);
    }
}

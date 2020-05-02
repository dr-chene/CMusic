package com.example.cmusic.view.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dr-chene on @date 2020/5/2
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.mList == null?null:this.mList.get(position);
    }

    @Override
    public int getCount() {
        return this.mList == null ? 0 : this.mList.size();
    }
}

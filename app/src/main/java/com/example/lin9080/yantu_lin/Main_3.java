package com.example.lin9080.yantu_lin;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Main_3 extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> TitleList = new ArrayList<>();  //页卡标题集合
    private ArrayList<Fragment> ViewList = new ArrayList<>();   //页卡视图集合
    private Fragment main_3_fun_1,main_3_fun_2,main_3_fun_3;  //页卡视图
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_3_fragment, container, false);
        tabLayout = (TabLayout) (view.findViewById(R.id.Tablayout));
        viewPager = (ViewPager) (view.findViewById(R.id.Viewpager));
        main_3_fun_1=new main_3_fun_1_Fragment();
        main_3_fun_2=new main_3_fun_2_Fragment();
        main_3_fun_3=new main_3_fun_3_Fragment();
        ViewList.add(main_3_fun_1);
        ViewList.add(main_3_fun_2);
        ViewList.add(main_3_fun_3);
        TitleList.add("院校");
        TitleList.add("资讯");
        TitleList.add("资料");
        //设置tab模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(TitleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(TitleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(TitleList.get(2)));
        //设置adapter
        viewPager.setAdapter(new FragmentPagerAdapter(this.getChildFragmentManager(),1){

            //获取每个页卡
            @Override
            public Fragment getItem(int position){
                return ViewList.get(position);
            }

            //获取页卡数
            @Override
            public int getCount(){
                return  TitleList.size();
            }

            //获取页卡标题
            @Override
            public CharSequence getPageTitle(int position){
                return TitleList.get(position);
            }
        });

        //tab与viewpager绑定
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }


}

package lab.jlhgxu520.equipment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;


import java.util.ArrayList;
import java.util.List;

import lab.jlhgxu520.equipment.adapters.MainPagerAdapter;
import lab.jlhgxu520.equipment.adapters.MainTabAdapter;
import lab.jlhgxu520.equipment.fragments.HomeFragment;
import lab.jlhgxu520.equipment.fragments.MineFragment;
import lab.jlhgxu520.equipment.fragments.CourseFragment;
import lab.jlhgxu520.equipment.fragments.StudyFragment;
import lab.jlhgxu520.equipment.po.TabBean;
import lab.jlhgxu520.equipment.tools.UserCache;
import lab.jlhgxu520.equipment.views.CustomViewPager;

public class MainActivity extends FragmentActivity {
    private Fragment[] fragments = {new HomeFragment(), new StudyFragment(), new CourseFragment(), new MineFragment()};;
    private int[] iconDown = { R.drawable.home_down, R.drawable.study_down, R.drawable.plan_down, R.drawable.mine_down };
    private int[] iconUp = { R.drawable.home_up, R.drawable.study_up, R.drawable.plan_up, R.drawable.mine_up };
    private String[] title = {"主页", "学习", "课堂", "个人中心"};

    private GridView gridView;
    private CustomViewPager viewPager;
    private List<TabBean> tabBeans;
    private MainTabAdapter gridViewAdapter;
    private MainPagerAdapter viewPagerAdapter;

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListener();
    }

    private void addListener() {
        viewPager.setAdapter(viewPagerAdapter);
        gridView.setAdapter(gridViewAdapter);
    }

    public void ChangePager(int item){
        if (item >= tabBeans.size()) throw new IndexOutOfBoundsException("item >= tabBeans.size()");
        for (int i=0;i<tabBeans.size();i++){
            tabBeans.get(i).setImage(iconUp[i]);
            tabBeans.get(i).setTextColor(false);
        }
        tabBeans.get(item).setTextColor(true);
        tabBeans.get(item).setImage(iconDown[item]);
        viewPager.setCurrentItem(item);
        gridViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserCache.clear();
    }

    private void init() {
        preferences = getSharedPreferences("equipment", Context.MODE_PRIVATE);

        UserCache.UUID = preferences.getString("uuid","");
        UserCache.power = preferences.getInt("power",1);
        UserCache.school = preferences.getString("school","");
        UserCache.name = preferences.getString("field1","");
        UserCache.number = preferences.getString("field2","");

        gridView = findViewById(R.id.gridView);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setScanScroll(false);
        viewPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),fragments);
        tabBeans = new ArrayList<>();
        for (int i = 0;i < iconUp.length;i++){
            TabBean bean = new TabBean();
            bean.setImage(iconUp[i]);
            bean.setTitle(title[i]);
            bean.setTextColor(false);
            tabBeans.add(bean);
        }
        tabBeans.get(0).setTextColor(true);
        tabBeans.get(0).setImage(iconDown[0]);
        gridViewAdapter = new MainTabAdapter(tabBeans,this);

    }

}

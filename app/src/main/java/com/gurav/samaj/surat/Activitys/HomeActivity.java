package com.gurav.samaj.surat.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gurav.samaj.surat.Fragment.GalleryFragement;
import com.gurav.samaj.surat.Fragment.HomeFragment;
import com.gurav.samaj.surat.Fragment.SGSFragement;
import com.gurav.samaj.surat.Model.MenuModel;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.DataProccessor;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ScaleAnimation animBlink;
    FrameLayout continer;
    LinearLayout ly_com;
    LinearLayout ly_home;
    LinearLayout ly_sahki;
    LinearLayout ly_setting;
    LinearLayout ly_sgs;
    private int[] tabIcons = {R.drawable.ic_plant, R.drawable.ic_friendship, R.drawable.ic_pray, R.drawable.ic_handshake, R.drawable.ic_women};
    private TabLayout tabLayout;
    TextView tvHomeTitle,login_btn;
    public static  TextView name_txt;
    ImageView ivopenclose;
    RecyclerView menuList;

    private ViewPager viewPager;
    MyListAdapter menuAdapter;
    List<MenuModel> modelList = new ArrayList<>();
    DrawerLayout drawerLayout;

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public Fragment getItem(int position) {
            return (Fragment) this.mFragmentList.get(position);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return (CharSequence) this.mFragmentTitleList.get(position);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_home);
        getSupportActionBar().hide();
        initView();


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                    tvHomeTitle.setText("batmI Aa`I kayRk/m");
                if (position == 1)
                    tvHomeTitle.setText("surt gurv smaj m.D;");
                if (position == 2)
                    tvHomeTitle.setText("fo3o gelerI");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setMenu();
    }

    private void setMenu() {


        modelList.add(new MenuModel("vrI*# gurv", R.drawable.ic_suppter));
        modelList.add(new MenuModel("ivkas 3/S3", R.drawable.ic_plant));
        modelList.add(new MenuModel("yuva im5 m.D;", R.drawable.ic_friendship));
        modelList.add(new MenuModel("s.t kaixba", R.drawable.ic_pray));
        modelList.add(new MenuModel("sqI mihla m.D;", R.drawable.ic_women));

        menuAdapter = new MyListAdapter();
        menuList.setLayoutManager(new LinearLayoutManager(HomeActivity.this, RecyclerView.VERTICAL, false));
        menuList.setAdapter(menuAdapter);
    }

    private void setFrament(Fragment frament) {
    }

    private void initView() {

        drawerLayout = findViewById(R.id.drawerLayout);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(this.viewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.tabLayout.setupWithViewPager(this.viewPager);
        menuList = findViewById(R.id.rc_menu_list);
        tvHomeTitle = findViewById(R.id.tv_home_title);
        name_txt=findViewById(R.id.nameTxt);
        login_btn=findViewById(R.id.tv_login);
        ivopenclose = findViewById(R.id.iv_menu_opener);

        setupTabIcons();


        ivopenclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });



        try {
            if(!DataProccessor.getStr("uname").equals("DNF")){

            name_txt.setText("nmSte "+DataProccessor.getStr("uname"));
            }
        }catch (Exception e){}

    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

        tabOne.setText("batmI Aa`I kayRk/m");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_calendar, 0, 0);
        this.tabLayout.getTabAt(0).setCustomView((View) tabOne);

        TextView tab2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab2.setText("surt gurv smaj m.D;");
        tab2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_handshake, 0, 0);
        this.tabLayout.getTabAt(1).setCustomView((View) tab2);

        TextView tab3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab3.setText("fo3o gelerI");
        tab3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_picture, 0, 0);
        this.tabLayout.getTabAt(2).setCustomView((View) tab3);

       /* tabOne.setText("vrI*# gurv");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_suppter, 0, 0);
        this.tabLayout.getTabAt(0).setCustomView((View) tabOne);

        TextView tab2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab2.setText("ivkas 3/S3");
        tab2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_plant, 0, 0);
        this.tabLayout.getTabAt(1).setCustomView((View) tab2);

        TextView tab3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab3.setText("yuva im5 m.D;");
        tab3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_friendship, 0, 0);
        this.tabLayout.getTabAt(3).setCustomView((View) tab3);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("s.t kaixba");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_pray, 0, 0);
        this.tabLayout.getTabAt(4).setCustomView((View) tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("surt gurv smaj m.D;");
        tabFour.setTextColor(getResources().getColor(R.color.sub_head_color));
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_handshake, 0, 0);
        this.tabLayout.getTabAt(2).setCustomView((View) tabFour);

        TextView tabFive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFive.setText("sqI mihla m.D;");
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_women, 0, 0);
        this.tabLayout.getTabAt(5).setCustomView((View) tabFive);

        TextView tabsix = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabsix.setText("fo3o gelerI");
        tabsix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_picture, 0, 0);
        this.tabLayout.getTabAt(6).setCustomView((View) tabsix);*/
    }

    private void setupViewPager(ViewPager viewPager2) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new HomeFragment(), "");
        adapter.addFrag(new SGSFragement(), "");
        adapter.addFrag(new GalleryFragement(), "");
//        adapter.addFrag(new YuvaMitraActivity(), "");
//        adapter.addFrag(new SantKashibActivity(), "");
//        adapter.addFrag(new SahiActivity(), "");
//        adapter.addFrag(new GalleryFragement(), "");
        viewPager2.setAdapter(adapter);
    }


    public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

        // RecyclerView recyclerView;
        public MyListAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.item_menu_list, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.textView.setText(modelList.get(position).mTitle);

            holder.mTextView.setImageResource(modelList.get(position).mIcon);


            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    if (position == 0) {
                        startActivity(new Intent(HomeActivity.this, VaristGuravActivity.class));
                    }
                    if (position == 1) {
                        startActivity(new Intent(HomeActivity.this, VikashTrustActivity.class));
                    }
                    if (position == 2) {
                        startActivity(new Intent(HomeActivity.this, YuvaMitraActivity.class));
                    }

                    if(position==3)
                    {
                        startActivity(new Intent(HomeActivity.this, SantKashibActivity.class));
                    }
                    if(position==4)
                    {
                        startActivity(new Intent(HomeActivity.this, SahiActivity.class));
                    }
                }
            });
        }


        @Override
        public int getItemCount() {
            return modelList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView mTextView;
            public TextView textView;
            RelativeLayout relativeLayout;

            public ViewHolder(View v) {
                super(v);
                mTextView = (ImageView) v.findViewById(R.id.iv_menu);
                textView = v.findViewById(R.id.iv_menu_title);
                relativeLayout = v.findViewById(R.id.rl_menu_root);
            }
        }
    }

    @Override
    public void onBackPressed() {


        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }
}

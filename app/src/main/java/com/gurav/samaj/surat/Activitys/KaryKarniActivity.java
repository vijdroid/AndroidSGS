package com.gurav.samaj.surat.Activitys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gurav.samaj.surat.Fragment.etardengiFragement;
import com.gurav.samaj.surat.Fragment.suratFemaledengiFragement;
import com.gurav.samaj.surat.Fragment.suratMaledengiFragement;
import com.gurav.samaj.surat.Model.Body;
import com.gurav.samaj.surat.Adapters.KaryakarniAdapter;
import com.gurav.samaj.surat.R;

import java.util.ArrayList;
import java.util.List;

public class KaryKarniActivity extends AppCompatActivity {
    KaryakarniAdapter adp_varishta;
    List<Body> bodyList = new ArrayList();
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
        setContentView((int) R.layout.activity_kary_karni);
        getSupportActionBar().hide();
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(this.viewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.tabLayout.setupWithViewPager(this.viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("pu=8 de`gIdata surt");
        this.tabLayout.getTabAt(0).setCustomView((View) tabOne);
        TextView tab2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab2.setText("mihla de`gIdata surt");
        this.tabLayout.getTabAt(1).setCustomView((View) tab2);
        TextView tab3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab3.setText("[tr de`gIdata");
        this.tabLayout.getTabAt(2).setCustomView((View) tab3);
    }

    private void setupViewPager(ViewPager viewPager2) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new suratMaledengiFragement(), "");
        adapter.addFrag(new suratFemaledengiFragement(), "");
        adapter.addFrag(new etardengiFragement(), "");
        viewPager2.setAdapter(adapter);
    }
}

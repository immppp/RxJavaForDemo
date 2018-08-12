package imp.qaq.com.rxjavafordemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import imp.qaq.com.rxjavafordemo.R;

/**
 * Created by qaq on 2018/8/7.
 */

public class First_fragment extends Base_Fragment {

    private ViewPager vp_first;
    private List<Fragment> list;

    {
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            Sencond_Fragment fragment = new Sencond_Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("imp","text"+i);
            fragment.setArguments(bundle);
            list.add(fragment);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_fragment;
    }

    @Override
    public void initView() {
        vp_first = findView(R.id.vp_first);
        vp_first.setAdapter(new Myadpater(getChildFragmentManager(),list));

    }

    class Myadpater extends FragmentPagerAdapter {

        List<Fragment> mlist;

        public Myadpater(FragmentManager fm, List<Fragment> mlist) {
            super(fm);
            this.mlist = mlist;
        }

        @Override
        public Fragment getItem(int position) {
            return mlist.get(position);
        }

        @Override
        public int getCount() {
            return mlist.size();
        }
    }

}

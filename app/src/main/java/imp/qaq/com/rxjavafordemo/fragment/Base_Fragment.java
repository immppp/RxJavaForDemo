package imp.qaq.com.rxjavafordemo.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qaq on 2018/8/7.
 */

public abstract class Base_Fragment extends Fragment {

    protected View mRootView;

    protected abstract
    @LayoutRes
    int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        initView();
        return mRootView;
    }

    public abstract void initView();
    protected <T extends View> T findView(@IdRes int id) {
        return (T) mRootView.findViewById(id);
    }
}

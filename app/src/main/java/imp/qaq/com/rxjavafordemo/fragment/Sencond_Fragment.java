package imp.qaq.com.rxjavafordemo.fragment;

import android.widget.TextView;

import imp.qaq.com.rxjavafordemo.R;

/**
 * Created by qaq on 2018/8/7.
 */

public class Sencond_Fragment extends Base_Fragment {
    @Override
    protected int getLayoutId() {
        return R.layout.sencond_fragment;
    }

    @Override
    public void initView() {
        TextView textView = findView(R.id.text_s);
        String s = getArguments().getString("imp");
        textView.setText(s);
    }
}

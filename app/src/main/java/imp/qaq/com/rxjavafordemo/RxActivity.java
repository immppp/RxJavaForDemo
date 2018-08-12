package imp.qaq.com.rxjavafordemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

import imp.qaq.com.rxjavafordemo.api.City;
import imp.qaq.com.rxjavafordemo.api.CityApi;
import imp.qaq.com.rxjavafordemo.api.CityModel;
import imp.qaq.com.rxjavafordemo.date.CityCreate;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qaq on 2018/8/11.
 * 这里提供一下城市Id的json地址：https://raw.githubusercontent.com/li-yu/FakeWeather/master/api/heweather_city_list.json
 * 地址是从别人那获取的，感谢下提供地址的人
 * 代码不清楚的可以加qq询问：：982388296
 */

public class RxActivity extends AppCompatActivity {

    public static OkHttpClient okHttpClient = new OkHttpClient();
    public static Converter.Factory Gsonconver = GsonConverterFactory.create();
    public static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();
    //输入的城市名称
    private EditText editText;
    //显示的城市Id
    private TextView text_show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxactivity);
        editText = findViewById(R.id.text_click);
        text_show = findViewById(R.id.text_show);

        findViewById(R.id.click3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                List<CityCreate> list = DataSupport.where("county like ?",s).find(CityCreate.class);
                if (list.size() > 0){
                    for (CityCreate create : list){
                        text_show.setText(create.gettag_id()+"  /"+create.getCounty()+"  /"+create.getCity()+"  /"
                        +create.getProvince());
                    }
                }else {
                    Snackbar.make(v,"请输入正确的城市名称",Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        findViewById(R.id.click2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(Gsonconver).client(okHttpClient)
                        .addCallAdapterFactory(rxJavaCallAdapterFactory).
                                baseUrl("https://raw.githubusercontent.com/li-yu/FakeWeather/master/api/")
                        .build();
                CityApi api = retrofit.create(CityApi.class);
                api.getCity().subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io()).subscribe(new Consumer<City>() {
                    @Override
                    public void accept(City city) throws Exception {

                        List<CityModel> citys = city.citys;

                        for (CityModel model : citys){
                            CityCreate create = new CityCreate();
                            create.settag_id(model.id);
                            create.setCity(model.leaderZh);
                            create.setCounty(model.cityZh);
                            create.setProvince(model.provinceZh);
                            create.save();
                            Log.e("accept",model.id+model.leaderZh+model.cityZh+model.provinceZh);
                        }

                        Log.e("accept:","  "+citys.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("throwable", throwable.getMessage());
                    }
                });
            }
    });

}
}
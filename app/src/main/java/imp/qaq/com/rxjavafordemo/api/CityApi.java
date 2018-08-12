package imp.qaq.com.rxjavafordemo.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by qaq on 2018/8/12.
 */

public interface CityApi {
    @GET("heweather_city_list.json")
    Observable<City> getCity();
}

package imp.qaq.com.rxjavafordemo.date;

import org.litepal.crud.DataSupport;

/**
 * Created by qaq on 2018/8/12.
 * 这里截取了返回的json的城市id，省，市，县地址
 * 其中id不要做更改，这是litepal自带的，在这里我走了些弯路
 */

public class CityCreate extends DataSupport {
    private int id;
    private String tag_id;
    private String city;
    private String county;
    private String province;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gettag_id() {
        return tag_id;
    }

    public void settag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}

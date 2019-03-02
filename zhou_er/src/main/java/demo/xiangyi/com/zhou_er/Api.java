package demo.xiangyi.com.zhou_er;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {
    @GET("data/%E7%A6%8F%E5%88%A9/10/1")
    Call <ResponseBody> getResponseData();
}

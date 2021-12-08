package algonquin.cst2335.sing1477;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPointInterface {

    @GET("reports")
    Call<BeanResponse> getCovidInfo(@Query("after") String Date);

}

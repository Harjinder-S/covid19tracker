package algonquin.cst2335.sing1477;

public class BaseUrlClass {

    /** BaseUrl of this application
     * */


    public static final String BASE_URL = "https://api.covid19tracker.ca/";


    private BaseUrlClass() {
    }

    public static EndPointInterface getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(EndPointInterface.class);

    }

}

package app.driveaz.driveazevendor;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkAPI {

    /* Registration Vendor */

    @POST("vendor/registration")
    Call<Response> registervendor(@Query("name") String name, @Query("email") String email,
                                  @Query("contact") String contact, @Query("password") String password,
                                  @Query("gstn") String gstn, @Query("trade_license") String trade_license,
                                  @Query("pan") String pan, @Query("aadhar") String aadhar,
                                  @Query("addressl1") String addressl1, @Query("addressl2") String addressl2,
                                  @Query("pincode") String pincode, @Query("city") String city,
                                  @Query("state") String state, @Query("country") String country,
                                  @Query("landmark") String landmark, @Query("tags") String tags);

    /* Otp verifiy Vendor */

    @POST("vendor/verify/otp")
    Call<Response> verifyvendorotp(@Query("contact") String contact, @Query("otp") String otp);

    /* Login Vendor */

    @POST("vendor/login")
    Call<Response> loginvendor(@Query("contact") String contact, @Query("password") String password);

    /* Reset password request */


}

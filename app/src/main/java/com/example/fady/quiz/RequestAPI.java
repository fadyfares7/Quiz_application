package com.example.fady.quiz;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by fady on 2/11/2015.
 */
public interface RequestAPI {
    @POST("/sessions/authorize.json")
    public void post(@Body Login_ReqData login_reqData, Callback<Error> response);
    @POST("/student_mcqs.json")
    public void post_quiz(@Body Post_quiz quiz, Callback<Grade> response);
    @POST("/sign_in.json")
    public void sign_in(@Body Login_ReqData login_reqData, Callback<User_Data> response);
   @DELETE("/sign_out.json")
   public void delete(@Query("auth_token")String token,Callback<Delete>del);
   @GET("/quizzes/{id}.json")
    public void get(@Path("id") String id,@Query("auth_token")String token,Callback<Question_Take_quiz>get);
    @POST("/quizzes.json")
    public void post_instructor(@Body quiz_post quizPost,Callback<quiz_get> response);
    @GET("/quizzes/student_statistics.json")
    public void get_statistics(@Query("auth_token")String token,@Query("seat_number")String seat,Callback<graph_get>get);

}

package JavaProject.network;


import JavaProject.network.DTO.AlarmDTO;
import JavaProject.network.DTO.FollowerShipDTO;
import JavaProject.network.DTO.SensorValue;
import JavaProject.network.VO.FollowshipVO;
import JavaProject.network.VO.LoginVO;
import JavaProject.network.VO.Sensor;
import JavaProject.network.VO.SignUpVO;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RetrofitAPI {

@POST("login")Call<String> post_login_request(@Body LoginVO loginVO);
@POST ("sign_up") Call<String> post_signUp_request(@Body SignUpVO signUpVO);

@GET("sensors/{ID}") Call<Sensor[]> get_all_sensor(@Path("ID") String id);

@GET ("sensors_value/{ID}/resent_one") Call<SensorValue[]>get_sensor_value_resent_one(@Path("ID")String id);
@GET ("sensors_value/{ID}") Call<SensorValue[][]>get_sensor_values_period(@Path("ID") String ID, @Query("from") String from, @Query("to") String to);
@GET ("sensors_value/{ID}/all") Call<SensorValue[][]>get_sensor_value_all(@Path("ID") String ID);

@POST ("followership") Call<String> post_follower(@Body FollowshipVO followshipVO);
@GET ("followership/{ID}/follow") Call<List<FollowerShipDTO>> get_follow(@Path("ID") String myID);
@GET ("followership/{ID}/follower") Call<List<FollowerShipDTO>> get_follower(@Path("ID") String myID);
@PATCH ("followership/{index}") Call<String> patch_follower(@Path("index") Long followerShipIndex,@Query("enable") boolean enable);
@DELETE ("followership/{ID}") Call<String> delete_follower(@Path("ID") Long followerShipIndex);

@POST ("alarm/{ID}") Call<String> post_alarm(@Path("ID") String myID ,@Body AlarmDTO alarmDTO);
@GET ("alarm/{ID}") Call<List<AlarmDTO>> get_alarm(@Path("ID") String myID);
@PATCH ("alarm") Call<String> patch_alarm(@Body AlarmDTO alarmDTO);
@DELETE ("alarm/{index}") Call<String> delete_alarm(@Path("index") Long alarmIndex);

@POST("pushToken/{ID}") Call<String> postPushToken(@Body String pushToken, @Path("ID") String user_id);
@GET("User_list/{partOfID}") Call<List<String>> get_list_of_user_by(@Path("partOfID") String findingUser);
@GET("user/{index}") Call<String> get_userID_of_by_index(@Path("index")long index);
}
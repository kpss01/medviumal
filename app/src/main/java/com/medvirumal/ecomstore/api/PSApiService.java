package com.medvirumal.ecomstore.api;

import com.google.gson.JsonElement;
import com.medvirumal.ecomstore.Madhu.BannerModel;
import com.medvirumal.ecomstore.Madhu.LabModel;
import com.medvirumal.ecomstore.viewobject.AboutUs;
import com.medvirumal.ecomstore.viewobject.ApiStatus;
import com.medvirumal.ecomstore.viewobject.Blog;
import com.medvirumal.ecomstore.viewobject.Category;
import com.medvirumal.ecomstore.viewobject.City;
import com.medvirumal.ecomstore.viewobject.Comment;
import com.medvirumal.ecomstore.viewobject.CommentDetail;
import com.medvirumal.ecomstore.viewobject.Country;
import com.medvirumal.ecomstore.viewobject.CouponDiscount;
import com.medvirumal.ecomstore.viewobject.Image;
import com.medvirumal.ecomstore.viewobject.Noti;
import com.medvirumal.ecomstore.viewobject.PSAppInfo;
import com.medvirumal.ecomstore.viewobject.Product;
import com.medvirumal.ecomstore.viewobject.ProductCollectionHeader;
import com.medvirumal.ecomstore.viewobject.Rating;
import com.medvirumal.ecomstore.viewobject.ShippingCost;
import com.medvirumal.ecomstore.viewobject.ShippingMethod;
import com.medvirumal.ecomstore.viewobject.ShippingCostContainer;
import com.medvirumal.ecomstore.viewobject.Shop;
import com.medvirumal.ecomstore.viewobject.SubCategory;
import com.medvirumal.ecomstore.viewobject.TransactionDetail;
import com.medvirumal.ecomstore.viewobject.TransactionHeaderUpload;
import com.medvirumal.ecomstore.viewobject.TransactionObject;
import com.medvirumal.ecomstore.viewobject.User;

import java.util.List;

import androidx.lifecycle.LiveData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * REST API access points
 */
public interface PSApiService {


    //region Products

    //region Get Product Collection

    @GET("rest/collections/get/api_key/{API_KEY}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<ProductCollectionHeader>>> getProductCollectionHeader(@Path("API_KEY") String apiKey, @Path("limit") String limit, @Path("offset") String offset);

    //endregion

    //region Get product detail related product list

    @GET("rest/products/related_product_trending/api_key/{API_KEY}/id/{id}/cat_id/{cat_id}")
    LiveData<ApiResponse<List<Product>>> getProductDetailRelatedList(@Path("API_KEY") String apiKey, @Path("id") String id, @Path("cat_id") String catId);

    //endregion

    //region Get favourite product list

    @GET("rest/products/get_favourite/api_key/{API_KEY}/login_user_id/{login_user_id}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<Product>>> getFavouriteList(@Path("API_KEY") String apiKey, @Path("login_user_id") String userId, @Path("limit") String limit, @Path("offset") String offset);

    //endregion

    //region Get Product by Category Id

    @GET("rest/products/get/api_key/{API_KEY}/login_user_id/{login_user_id}/limit/{limit}/offset/{offset}/cat_id/{cat_id}")
    LiveData<ApiResponse<List<Product>>> getProductListByCatId(@Path("API_KEY") String apiKey, @Path("login_user_id") String loginUserId, @Path("limit") String limit, @Path("offset") String offset, @Path("cat_id") String catId);

    //end region

    //endregion

    //region Get Collection Product List

    @GET("rest/products/all_collection_products/api_key/{API_KEY}/limit/{limit}/offset/{offset}/id/{id}")
    LiveData<ApiResponse<List<Product>>> getCollectionProducts(@Path("API_KEY") String apiKey, @Path("limit") String limit, @Path("offset") String offset, @Path("id") String id);

    //endregion

    //region Post Favourite Product
    @FormUrlEncoded
    @POST("rest/favourites/press/api_key/{API_KEY}")
    Call<Product> setPostFavourite(
            @Path("API_KEY") String api_key,
            @Field("product_id") String productId,
            @Field("user_id") String userId);

    //endregion

    //region Search Product

    @FormUrlEncoded
    @POST("rest/products/search/api_key/{API_KEY}/limit/{limit}/offset/{offset}/login_user_id/{login_user_id}")
    LiveData<ApiResponse<List<Product>>> searchProduct(
            @Path("API_KEY") String api_key,
            @Path("login_user_id") String login_user_id,
            @Path("limit") String limit,
            @Path("offset") String offset,
            @Field("searchterm") String searchterm,
            @Field("cat_id") String catId,
            @Field("sub_cat_id") String subCatId,
            @Field("is_featured") String isFeatured,
            @Field("is_discount") String isDiscount,
            @Field("is_available") String isAvailable,
            @Field("max_price") String max_price,
            @Field("min_price") String min_price,
            @Field("rating_value") String rating_value,
            @Field("order_by") String order_by,
            @Field("order_type") String order_type);

    //endregion

    //region Get Product Detail

    @GET("rest/products/get/api_key/{API_KEY}/id/{id}/login_user_id/{login_user_id}")
    LiveData<ApiResponse<Product>> getProductDetail(@Path("API_KEY") String apiKey, @Path("id") String Id, @Path("login_user_id") String login_user_id);

    //endregion

    //endregion


    //region Get Image List

    @GET("rest/images/get/api_key/{API_KEY}/img_parent_id/{img_parent_id}/img_type/{img_type}")
    LiveData<ApiResponse<List<Image>>> getImageList(@Path("API_KEY") String apiKey, @Path("img_parent_id") String img_parent_id, @Path("img_type") String imageType);


    //endregion


    //region Comments

    //region Get commentlist

    @GET("rest/commentheaders/get/api_key/{API_KEY}/product_id/{product_id}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<com.medvirumal.ecomstore.viewobject.Comment>>> getCommentList(@Path("API_KEY") String apiKey, @Path("product_id") String productId, @Path("limit") String limit, @Path("offset") String offset);

    //endregion

    //region Get comment detail list

    @GET("rest/commentdetails/get/api_key/{API_KEY}/header_id/{header_id}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<com.medvirumal.ecomstore.viewobject.CommentDetail>>> getCommentDetailList(@Path("API_KEY") String apiKey, @Path("header_id") String headerId, @Path("limit") String limit, @Path("offset") String offset);

    //endregion

    //region Get comment detail count

    @GET("rest/commentheaders/get/api_key/{API_KEY}/id/{id}")
    Call<Comment> getRawCommentDetailCount(@Path("API_KEY") String apiKey, @Path("id") String id);

    //endregion

    //region Post comment header

    @FormUrlEncoded
    @POST("rest/commentheaders/press/api_key/{API_KEY}")
    Call<List<Comment>> rawCommentHeaderPost(
            @Path("API_KEY") String apiKey,
            @Field("product_id") String productId,
            @Field("user_id") String userId,
            @Field("header_comment") String headerComment);

    //endregion

    //region Post comment detail

    @FormUrlEncoded
    @POST("rest/commentdetails/press/api_key/{API_KEY}")
    Call<List<CommentDetail>> rawCommentDetailPost(
            @Path("API_KEY") String apiKey,
            @Field("header_id") String headerId,
            @Field("user_id") String userId,
            @Field("detail_comment") String detailComment);

    //endregion

    //endregion


    //region Notification

    //region Submit Notification Token
    @FormUrlEncoded
    @POST("rest/notis/register/api_key/{API_KEY}")
    Call<ApiStatus> rawRegisterNotiToken(@Path("API_KEY") String apiKey, @Field("platform_name") String platform, @Field("device_id") String deviceId);


    @FormUrlEncoded
    @POST("rest/notis/unregister/api_key/{API_KEY}")
    Call<ApiStatus> rawUnregisterNotiToken(@Path("API_KEY") String apiKey, @Field("platform_name") String platform, @Field("device_id") String deviceId);

    //endregion

    //region Get Notification List

    @FormUrlEncoded
    @POST("rest/notis/all_notis/api_key/{API_KEY}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<Noti>>> getNotificationList(@Path("API_KEY") String apiKey,
                                                          @Path("limit") String limit,
                                                          @Path("offset") String offset,
                                                          @Field("user_id") String userId,
                                                          @Field("device_token") String deviceToken);

    //endregion

    //region Get Notification detail

    @GET("rest/notis/get/api_key/{API_KEY}/id/{id}")
    LiveData<ApiResponse<Noti>> getNotificationDetail(@Path("API_KEY") String apiKey, @Path("id") String id);

    //endregion

    //region Is Read Notificaiton
    @FormUrlEncoded
    @POST("rest/notis/is_read/api_key/{API_KEY}")
    Call<Noti> isReadNoti(
            @Path("API_KEY") String apiKey,
            @Field("noti_id") String noti_id,
            @Field("user_id") String userId,
            @Field("device_token") String device_token);

    //endregion

    //endregion


    //region Transactions

    //region Get transaction detail

    @GET("rest/transactionheaders/get/api_key/{API_KEY}/user_id/{user_id}/id/{id}")
    LiveData<ApiResponse<TransactionObject>> getTransactionDetail(@Path("API_KEY") String apiKey, @Path("user_id") String user_id, @Path("id") String id);

    //endregion

    //region Get transaction list

    @GET("rest/transactionheaders/get/api_key/{API_KEY}/user_id/{user_id}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<TransactionObject>>> getTransList(@Path("API_KEY") String apiKey, @Path("user_id") String userId, @Path("limit") String limit, @Path("offset") String offset);

    //endregion

    //region Get transaction order list

    @GET("rest/transactiondetails/get/api_key/{API_KEY}/transactions_header_id/{transactions_header_id}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<TransactionDetail>>> getTransactionOrderList(@Path("API_KEY") String apiKey, @Path("transactions_header_id") String transactionsHeadersId, @Path("limit") String limit, @Path("offset") String offset);

    //endregion

    //region Upload Transaction

    @Headers("Content-Type: application/json")
    @POST("rest/transactionheaders/submit/api_key/{API_KEY}")
    Call<TransactionObject> uploadTransactionHeader(@Body TransactionHeaderUpload items, @Path("API_KEY") String API_KEY);

    //endregion

    //endregion


    //region Category

    //region Get category list

    @FormUrlEncoded
    @POST("rest/categories/search/api_key/{API_KEY}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<Category>>> getSearchCategory(@Path("API_KEY") String apiKey, @Path("limit") String limit,
                                                            @Path("offset") String offset, @Field("order_by") String order_by);

    //endregion

    //endregion


    //region User Related

    //region GET User
    @GET("rest/users/get/api_key/{API_KEY}/user_id/{user_id}")
    LiveData<ApiResponse<List<User>>> getUser(@Path("API_KEY") String apiKey, @Path("user_id") String userId);
    //endregion

    //region POST Upload Image
    @Multipart
    @POST("rest/images/upload/api_key/{API_KEY}")
    LiveData<ApiResponse<User>> doUploadImage(@Path("API_KEY") String apiKey, @Part("user_id") RequestBody userId, @Part("file") RequestBody name, @Part MultipartBody.Part file, @Part("platform_name") RequestBody platformName);
    //endregion

    //region POST User for Login
    @FormUrlEncoded
    @POST("rest/users/login/api_key/{API_KEY}")
    LiveData<ApiResponse<User>> postUserLogin(@Path("API_KEY") String apiKey, @Field("user_email") String userEmail, @Field("user_password") String userPassword, @Field("referral_code") String referral_code);
    //endregion

    //region POST User for Register

    @FormUrlEncoded
    @POST("rest/users/register/api_key/{API_KEY}")
    Call<User> postFBUser(@Path("API_KEY") String apiKey, @Field("facebook_id") String facebookId, @Field("user_name") String userName, @Field("user_email") String userEmail, @Field("profile_photo_url") String profilePhotoUrl);

    @FormUrlEncoded
    @POST("rest/users/add/api_key/{API_KEY}")
    Call<User> postUser(@Path("API_KEY") String apiKey, @Field("user_id") String userId, @Field("user_name") String userName, @Field("user_email") String userEmail, @Field("user_password") String userPassword, @Field("user_phone") String userPhone, @Field("device_token") String deviceToken,@Field("referral_by") String referral_by);
    //endregion

    //region POST Forgot Password
    @FormUrlEncoded
    @POST("rest/users/reset/api_key/{API_KEY}")
    LiveData<ApiResponse<ApiStatus>> postForgotPassword(@Path("API_KEY") String apiKey, @Field("user_email") String userEmail);
    //endregion

    //region PUT User for User Update
    @FormUrlEncoded
    @POST("rest/users/profile_update/api_key/{API_KEY}")
    LiveData<ApiResponse<User>> putUser(@Path("API_KEY") String apiKey,
                                             @Field("user_id") String loginUserId,
                                             @Field("user_name") String userName,
                                             @Field("user_email") String userEmail,
                                             @Field("user_phone") String userPhone,
                                             @Field("user_about_me") String userAboutMe,
                                             @Field("billing_first_name") String billingFirstName,
                                             @Field("billing_last_name") String billingLastName,
                                             @Field("billing_company") String billingCompany,
                                             @Field("billing_address_1") String billingAddress1,
                                             @Field("billing_address_2") String billingAddress2,
                                             @Field("billing_country") String billingCountry,
                                             @Field("billing_state") String billingState,
                                             @Field("billing_city") String billingCity,
                                             @Field("billing_postal_code") String billingPostalCode,
                                             @Field("billing_email") String billingEmail,
                                             @Field("billing_phone") String billingPhone,
                                             @Field("shipping_first_name") String shippingFirstName,
                                             @Field("shipping_last_name") String shippingLastName,
                                             @Field("shipping_company") String shippingCompany,
                                             @Field("shipping_address_1") String shippingAddress1,
                                             @Field("shipping_address_2") String shippingAddress2,
                                             @Field("shipping_country") String shippingCountry,
                                             @Field("shipping_state") String shippingState,
                                             @Field("shipping_city") String shippingCity,
                                             @Field("shipping_postal_code") String shippingPostalCode,
                                             @Field("shipping_email") String shippingEmail,
                                             @Field("shipping_phone") String shippingPhone,
                                             @Field("country_id") String countryId,
                                             @Field("city_id") String cityId
    );

    //endregion

    //region PUT for Password Update
    @FormUrlEncoded
    @POST("rest/users/password_update/api_key/{API_KEY}")
    LiveData<ApiResponse<ApiStatus>> postPasswordUpdate(@Path("API_KEY") String apiKey, @Field("user_id") String loginUserId, @Field("user_password") String password);
    //endregion

    //endregion


    //region About Us

    @GET("rest/abouts/get/api_key/{API_KEY}")
    LiveData<ApiResponse<List<AboutUs>>> getAboutUs(@Path("API_KEY") String apiKey);

    //endregion


    //region Contact Us

    @FormUrlEncoded
    @POST("rest/contacts/add/api_key/{API_KEY}")
    Call<ApiStatus> rawPostContact(@Path("API_KEY") String apiKey, @Field("name") String contactName, @Field("email") String contactEmail, @Field("message") String contactMessage, @Field("phone") String contactPhone);

    //endregion


    //region GET SubCategory List

    @GET("rest/subcategories/get/api_key/{API_KEY}")
    LiveData<ApiResponse<List<SubCategory>>> getAllSubCategoryList(@Path("API_KEY") String apiKey);


    @GET("rest/subcategories/get/api_key/{API_KEY}/login_user_id/{login_user_id}/cat_id/{cat_id}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<SubCategory>>> getSubCategoryListWithCatId(@Path("API_KEY") String apiKey, @Path("login_user_id") String loginUserId, @Path("cat_id") String catId, @Path("limit") String limit, @Path("offset") String offset);

    //endregion

    //country list
    @FormUrlEncoded
    @POST("rest/shipping_zones/get_shipping_country/api_key/{API_KEY}/shop_id/{shop_id}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<Country>>> getCountryListWithShopId(@Path("API_KEY") String apiKey, @Field("shop_id") String shopId, @Path("limit") String limit, @Path("offset") String offset);

    //endregion

    //country list
    @Headers("Content-Type: application/json")
    @POST("rest/shipping_zones/get_shipping_cost/api_key/{API_KEY}")
    Call<ShippingCost> postShippingByCountryAndCity(@Path("API_KEY") String apiKey, @Body ShippingCostContainer productUpload);

    //endregion

    //city list
    @FormUrlEncoded
    @POST("rest/shipping_zones/get_shipping_city/api_key/{API_KEY}/shop_id/{shop_id}/country_id/{country_id}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<City>>> getCityListWithCountryId(@Path("API_KEY") String apiKey, @Field("shop_id") String shopId, @Field("country_id") String country_id, @Path("limit") String limit, @Path("offset") String offset);

    //endregion

    //region Delete item list by date

    @FormUrlEncoded
    @POST("rest/appinfo/get_delete_history/api_key/{API_KEY}")
    Call<PSAppInfo> getDeletedHistory(
            @Path("API_KEY") String apiKey,
            @Field("start_date") String start_date,
            @Field("end_date") String end_date);

    //endregion

    //region Get Shop by Id

    @GET("rest/shops/get_shop_info/api_key/{API_KEY}")
    LiveData<ApiResponse<Shop>> getShopById(@Path("API_KEY") String api_key);

    //endregion

    //region Get All Rating List

    @FormUrlEncoded
    @POST("rest/users/getReferralCode/api_key/{API_KEY}")
    Call<JsonElement> getReferralCode(@Path("API_KEY") String api_key, @Field("user_id") String user_id);





    @GET("rest/rates/get/api_key/{API_KEY}/product_id/{product_id}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<Rating>>> getAllRatingList(@Path("API_KEY") String apiKey, @Path("product_id") String productId, @Path("limit") String limit, @Path("offset") String offset);

    //endregion

    //region Post Rating

    @FormUrlEncoded
    @POST("rest/rates/add_rating/api_key/{API_KEY}")
    Call<Rating> postRating(
            @Path("API_KEY") String api_key,
            @Field("title") String title,
            @Field("description") String description,
            @Field("rating") String rating,
            @Field("user_id") String userId,
            @Field("product_id") String productId);

    //endregion

    //endregion


    //region Touch Count

    @FormUrlEncoded
    @POST("rest/touches/add_touch/api_key/{API_KEY}")
    Call<ApiStatus> setrawPostTouchCount(
            @Path("API_KEY") String apiKey,
            @Field("type_id") String typeId,
            @Field("type_name") String typeName,
            @Field("user_id") String userId);

    //endregion

    //region News|Blog

    @GET("rest/feeds/get/api_key/{API_KEY}/limit/{limit}/offset/{offset}")
    LiveData<ApiResponse<List<Blog>>> getAllNewsFeed(@Path("API_KEY") String api_key, @Path("limit") String limit, @Path("offset") String offset);

    @GET("rest/feeds/get/api_key/{API_KEY}/id/{id}")
    LiveData<ApiResponse<Blog>> getNewsById(@Path("API_KEY") String api_key, @Path("id") String id);

    //endregion


    //region Shipping Methods

    @GET("rest/shippings/get/api_key/{API_KEY}")
    LiveData<ApiResponse<List<ShippingMethod>>> getShipping(@Path("API_KEY") String api_key);

    //endregion


    //region Paypal

    @GET("rest/paypal/get_token/api_key/{API_KEY}")
    Call<ApiStatus> getPaypalToken(@Path("API_KEY") String apiKey);

    //endregion


    //region Check Coupon Discount
    @FormUrlEncoded
    @POST("rest/coupons/check/api_key/{API_KEY}/")
    Call<CouponDiscount> checkCouponDiscount(@Path("API_KEY") String apiKey,
                                             @Field("coupon_code") String couponCode,@Field("user_id") String user_id);

    //User Verify Email
    @FormUrlEncoded
    @POST("rest/users/verify/api_key/{API_KEY}")
    Call<User> verifyEmail(
            @Path("API_KEY") String API_KEY,
            @Field("user_id") String userId,
            @Field("code") String code);


    //Resend Verify Code again
    @FormUrlEncoded
    @POST("rest/users/request_code/api_key/{API_KEY}")
    Call<ApiStatus> resentCodeAgain(
            @Path("API_KEY") String API_KEY,
            @Field("user_email") String user_email
    );

    @FormUrlEncoded
    @POST("rest/users/google_register/api_key/{API_KEY}")
    Call<User> postGoogleLogin(
            @Path("API_KEY") String API_KEY,
            @Field("google_id") String googleId,
            @Field("user_name") String userName,
            @Field("user_email") String userEmail,
            @Field("profile_photo_url") String profilePhotoUrl,
            @Field("device_token") String deviceToken
    );

    @FormUrlEncoded
    @POST("rest/users/phone_register/api_key/{API_KEY}")
    Call<User> postPhoneLogin(
            @Path("API_KEY") String API_KEY,
            @Field("phone_id") String phoneId,
            @Field("user_name") String userName,
            @Field("user_phone") String userPhone,
            @Field("device_token") String deviceToken,
            @Field("referral_by") String referral_code
    );

    @GET("rest/abouts/getLabData/api_key/teampsisthebest")
    Call<LabModel> getLabTest();

    @GET("rest/abouts/getdocterData/api_key/teampsisthebest")
    Call<LabModel> getDrList();


    @FormUrlEncoded
    @POST("rest/abouts/askDocter/api_key/teampsisthebest")
    Call<LabModel> getSkDr(@Field("docter_id") String docter_id,
                           @Field("name") String name , @Field("mobile") String mobile,
                           @Field("email") String email , @Field("address") String Address,
                           @Field("speciality") String speciality, @Field("descripition") String descripition);



    @FormUrlEncoded
    @POST("rest/abouts/Appointment/api_key/teampsisthebest")
    Call<LabModel> getAppoinwmnt(@Field("docter_id") String docter_id,
                           @Field("name") String name , @Field("mobile") String mobile,
                           @Field("time") String time , @Field("date") String date,
                           @Field("email") String email , @Field("Address") String Address,
                           @Field("details") String speciality);


    @Multipart
    @POST("rest/abouts/medicineOrder/api_key/teampsisthebest")
    Call<LabModel> getUploAdPres(@Part MultipartBody.Part file,
                                 @Part("image") RequestBody photo,
                                 @Part("pescription") RequestBody pescription,
                                 @Part("product_ids") RequestBody product_ids,
                                 @Part("days") RequestBody days);
//SLIDER


    @GET("api/slider")
    Call<BannerModel> getBannerList();


}
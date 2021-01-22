package com.yemen.oshopping.Chat.`interface`

import com.yemen.oshopping.Chat.constant.Constants.Companion.CONTENT_TYPE
import com.yemen.oshopping.Chat.constant.Constants.Companion.SERVER_KEY
import com.yemen.oshopping.Chat.model.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {

    @Headers("Authorization: key=$SERVER_KEY","Content-type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}
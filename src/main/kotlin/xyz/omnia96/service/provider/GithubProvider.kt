package xyz.omnia96.service.provider


import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import okhttp3.*
import org.springframework.stereotype.Component
import xyz.omnia96.service.dto.AccessTokenDto
import xyz.omnia96.service.dto.GithubUser
import java.io.IOException
import java.lang.Character.getType
import java.lang.Exception

@Component
class GithubProvider {
    fun getAccessToken(accessTokenDto: AccessTokenDto): String? {
        var mediaType:MediaType = MediaType.get("application/json; charset=utf-8")
        var okHttpClient:OkHttpClient = OkHttpClient()
        var requestBody:RequestBody = RequestBody.create(mediaType,Gson().toJson(accessTokenDto))
//        print(Gson().toJson(accessTokenDto))
        var request:Request = Request.Builder().url("https://github.com/login/oauth/access_token").post(requestBody).build()
        try {
            var response:Response = okHttpClient.newCall(request).execute()
            var string:String = response.body()!!.string()
            println(string)
            var tokenString: String =string.split("&")[0]
            var token:String = tokenString.split("=")[1]
            println(token)
            return  token
        }catch (e: IOException){ }
        return null
    }

    fun getUser(accessToken: String?):GithubUser?{
        var okHttpClient:OkHttpClient = OkHttpClient()
        var request:Request = Request.Builder().url("https://api.github.com/user?access_token=" + accessToken).build()
        try {
            var response:Response = okHttpClient.newCall(request).execute()
            var string:String = response.body()!!.string()
            println(string)
            var githubUser:GithubUser? = Gson().fromJson(string)



            return githubUser

        } catch (e:IOException){}
        return  null
    }
}
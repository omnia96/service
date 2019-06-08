package xyz.omnia96.service.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import xyz.omnia96.service.dto.AccessTokenDto
import xyz.omnia96.service.dto.GithubUser
import xyz.omnia96.service.mapper.UserMapper
import xyz.omnia96.service.model.User
import xyz.omnia96.service.provider.GithubProvider
import java.util.*
import javax.annotation.Resource
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class AuthorizeController {

    @Autowired
    var githubProvider:GithubProvider = GithubProvider()

    @Value("\${github.client.id}")
    val clientId:String? = null
    @Value("\${github.client.secret}")
     val clientSecret:String? = null
    @Value("\${github.redirect.url}")
    val redirectUrl:String? = null


    @Resource
    lateinit var userMapper:UserMapper

    @GetMapping("/callback")
    fun callback(@RequestParam(name = "code") code:String,@RequestParam(name = "state") state:String,httpServletResponse: HttpServletResponse):String{
        var accessTokenDto:AccessTokenDto = AccessTokenDto(clientId,clientSecret,code,redirectUrl,state)
        var accessToken: String? =  githubProvider.getAccessToken(accessTokenDto)
        var githubUser: GithubUser? = githubProvider.getUser(accessToken)
        if (githubUser != null){

            var name:String = githubUser.name.toString()
            var accountId:String = githubUser.id
            var token:String = UUID.randomUUID().toString()
            var gmtCreate:Long = System.currentTimeMillis()
            var gmtModified:Long = gmtCreate

            var user:User = User(name,accountId,token,gmtCreate,gmtModified)
            var userIs:User = userMapper.findUserByAccountId(user)
            println(userIs)
            if(userIs != null){
                httpServletResponse.addCookie(Cookie("token",userIs.token))
            }else{
                userMapper.insert(user)
            }
            return "redirect:/"
        }else{
            return "redirect:/"
        }
        return "index"
    }
}
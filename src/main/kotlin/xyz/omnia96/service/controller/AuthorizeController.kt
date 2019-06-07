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
import javax.servlet.http.HttpServletRequest

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
    fun callback(@RequestParam(name = "code") code:String,@RequestParam(name = "state") state:String,httpServletRequest:HttpServletRequest):String{
        var accessTokenDto:AccessTokenDto = AccessTokenDto(clientId,clientSecret,code,redirectUrl,state)
        var accessToken: String? =  githubProvider.getAccessToken(accessTokenDto)
        var githubUser: GithubUser? = githubProvider.getUser(accessToken)
        if (githubUser != null){

            var name = githubUser.name
            var accountId = githubUser.id
            var token = UUID.randomUUID().toString()
            var gmtCreate = System.currentTimeMillis()
            var gmtModified = gmtCreate

            var user:User = User(name,accountId, token,gmtCreate,gmtModified)
            userMapper.insert(user)

            httpServletRequest.getSession().setAttribute("user",githubUser)
            return "redirect:/"
        }else{
            return "redirect:/"
        }
        return "index"
    }
}
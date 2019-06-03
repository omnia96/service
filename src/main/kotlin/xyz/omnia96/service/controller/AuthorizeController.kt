package xyz.omnia96.service.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.context.support.ContextExposingHttpServletRequest
import xyz.omnia96.service.dto.AccessTokenDto
import xyz.omnia96.service.dto.GithubUser
import xyz.omnia96.service.provider.GithubProvider
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

    @GetMapping("/callback")
    fun callback(@RequestParam(name = "code") code:String,@RequestParam(name = "state") state:String,httpServletRequest:HttpServletRequest):String{
        var accessTokenDto:AccessTokenDto = AccessTokenDto(clientId,clientSecret,code,redirectUrl,state)
        var accessToken: String? =  githubProvider.getAccessToken(accessTokenDto)
        var user: GithubUser? = githubProvider.getUser(accessToken)
        if (user != null){
            httpServletRequest.getSession().setAttribute("user",user)
            return "redirect:/"
        }else{
            return "redirect:/"
        }
        return "index"
    }
}
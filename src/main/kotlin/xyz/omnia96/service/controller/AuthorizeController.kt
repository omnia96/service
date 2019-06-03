package xyz.omnia96.service.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import xyz.omnia96.service.dto.AccessTokenDto
import xyz.omnia96.service.dto.GithubUser
import xyz.omnia96.service.provider.GithubProvider

@Controller
class AuthorizeController {

    @Autowired
    var githubProvider:GithubProvider = GithubProvider()

    @GetMapping("/callback")
    fun callback(@RequestParam(name = "code") code:String,@RequestParam(name = "state") state:String):String{
        var accessTokenDto:AccessTokenDto = AccessTokenDto("da83c9e8c7ecc111b498","3a4dac562f03c4d0f900c3194bc607d1f7adad2f",code,"http://localhost:9696/callback",state)
        var accessToken: String? =  githubProvider.getAccessToken(accessTokenDto)
        var user: GithubUser? = githubProvider.getUser(accessToken)
        println(user!!.id)
        println(user!!.name)
        println(user!!.bio)
        return "index"
    }
}
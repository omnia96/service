package xyz.omnia96.service.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import xyz.omnia96.service.mapper.UserMapper
import xyz.omnia96.service.model.User
import javax.annotation.Resource
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest


@Resource
class IndexController{
    @Autowired
    lateinit var userMapper:UserMapper

    @GetMapping("/")
    fun hello(httpServletRequest: HttpServletRequest):String{
        var cookies = httpServletRequest.cookies
        for(cookie:Cookie in cookies){
            if (cookie.name.equals("token")){
                var token = cookie.name
                var user:User = userMapper.findByToken(token)
                if (user != null){
                    httpServletRequest.getSession().setAttribute("user",user)
                }
                break
            }
        }
        return "index"
    }
}
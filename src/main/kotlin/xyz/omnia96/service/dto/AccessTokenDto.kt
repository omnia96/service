package xyz.omnia96.service.dto

data class AccessTokenDto(var client_id:String?,var client_secret:String?,var code:String?,var redirect_uri:String?,var state:String?){}
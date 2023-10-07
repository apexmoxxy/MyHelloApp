package com.example.myhelloapp

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("jwt")
    var jwt: String = ""
}
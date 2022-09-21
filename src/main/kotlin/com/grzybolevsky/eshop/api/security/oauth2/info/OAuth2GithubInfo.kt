package com.grzybolevsky.eshop.api.security.oauth2.info

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

fun getGithubUserEmail(request: OAuth2UserRequest?): String {
    val responseBody = HttpClient.newHttpClient().send(
        HttpRequest.newBuilder()
            .uri(URI("https://api.github.com/user/emails"))
            .version(HttpClient.Version.HTTP_2)
            .header(
                "Authorization",
                "token ${request?.accessToken?.tokenValue}"
            )
            .GET()
            .build(),
        BodyHandlers.ofString()
    ).body()
    return Json.parseToJsonElement(responseBody).jsonArray[0].jsonObject["email"]!!.jsonPrimitive.content
}

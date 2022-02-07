package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
    private val webClientApi: WebClientApi
) {

    @ResponseBody
    @GetMapping("/demo")
    suspend fun demo(): String? {
        return webClientApi.executeCall()
    }
}

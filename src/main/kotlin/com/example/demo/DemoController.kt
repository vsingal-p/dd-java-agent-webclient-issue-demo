package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import kotlin.math.log

@RestController
class DemoController(
    private val webClientApi: WebClientApi
) {

    @ResponseBody
    @GetMapping("/demo")
    suspend fun demo(): String? {
        logger.info("Log1")
        logger.info("Log2")
        val x = webClientApi.executeCall()
        logger.debug(x)
        logger.info("Log3")
        logger.info("Log4")
        return webClientApi.executeCall()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DemoController::class.java)
    }
}

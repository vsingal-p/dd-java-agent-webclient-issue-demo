package com.example.demo

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class WebClientApi(
    webClientBuilder: WebClient.Builder
) {
    private val printlnFilter =
        ExchangeFilterFunction { request, next ->
            next.exchange(request).doOnSuccess { LOG.info("webclient call complete") }
        }

    private val webClient = webClientBuilder.filter(printlnFilter).build()

    suspend fun executeCall(): String? {
        return webClient
            .get()
            .uri("https://httpbin.org/get")
            .retrieve()
            .bodyToMono<String>()
            .awaitSingleOrNull()
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(WebClientApi::class.java)
    }
}

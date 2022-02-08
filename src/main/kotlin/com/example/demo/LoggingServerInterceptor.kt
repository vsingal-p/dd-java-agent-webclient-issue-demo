package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class LoggingServerInterceptor : WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        logger.info("Server call starts")
        return chain.filter(exchange).doOnSuccess { logger.info("Server call complete") }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(LoggingServerInterceptor::class.java)
    }
}

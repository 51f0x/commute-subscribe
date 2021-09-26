package me.matt.commutesubscribe

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "application.twitter")
data class TwitterConfigurationProperties(
    var OAuthConsumerKey: String="",
    var OAuthConsumerSecret:String="",
    var OAuthAccessToken: String="",
    var OAuthAccessTokenSecret:String=""
)
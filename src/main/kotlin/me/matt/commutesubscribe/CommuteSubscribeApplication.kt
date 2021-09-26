package me.matt.commutesubscribe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import twitter4j.*
import twitter4j.conf.ConfigurationBuilder


@SpringBootApplication
class CommuteSubscribeApplication {
    @Bean
    fun statusListener(): StatusListener {
        return myStreamListener()
    }
    @Bean
    fun twitterStream(statusListener: StatusListener?): TwitterStream? {
        val cb: ConfigurationBuilder = ConfigurationBuilder()
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey("kb8kYypQqWr3OHJw0xf84MGCV")
            .setOAuthConsumerSecret("SqmGdBkJpetRvW4STXXgU3HxHbpNA9ElnlStpplrTTMsXHyDaB")
            .setOAuthAccessToken("2623678366-AUZMvvY2ruyM2CfMm8sWsum7QgXyYi965XcJBV9")
            .setOAuthAccessTokenSecret("wwgN9fvu2vMhwey8eBDFDHhxRo28yt76hzrkUO63B69M7")

        val twitterStream: TwitterStream = TwitterStreamFactory(cb.build()).instance
        twitterStream.addListener(statusListener)

        //var railinfoId: Long = 407415054
        //twitterStream.filter(new FilterQuery(railinfoId));
        twitterStream.sample()
        return twitterStream
    }
}

fun main(args: Array<String>) {
    runApplication<CommuteSubscribeApplication>(*args){
        setBannerMode(org.springframework.boot.Banner.Mode.OFF)
    }
}

class myStreamListener: StatusListener{
    override fun onStatus(status: twitter4j.Status){
        if (!status.isRetweet()) {
            System.out.println(status.toString());
        }
    }
    override fun onDeletionNotice(statusDeletionNotice: twitter4j.StatusDeletionNotice?){}
    override fun onException(ex: java.lang.Exception?){ }
    override fun onScrubGeo(userId: kotlin.Long, upToStatusId: kotlin.Long){ }
    override fun onStallWarning(warning: twitter4j.StallWarning?){ }
    override fun onTrackLimitationNotice(numberOfLimitedStatuses: kotlin.Int){ }
}

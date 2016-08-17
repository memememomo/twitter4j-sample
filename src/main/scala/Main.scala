import com.typesafe.config.ConfigFactory
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

object Main {
  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.load()

    val cb = new ConfigurationBuilder
    cb.setOAuthConsumerKey(conf.getString("consumerKey"))
      .setOAuthConsumerSecret(conf.getString("consumerSecret"))
      .setOAuthAccessToken(conf.getString("accessToken"))
      .setOAuthAccessTokenSecret(conf.getString("accessTokenSecret"))

    val twitterFactory = new TwitterFactory(cb.build)
    val client = twitterFactory.getInstance

    // Something...
  }
}

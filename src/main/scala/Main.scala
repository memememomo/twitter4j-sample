import com.typesafe.config.ConfigFactory
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder


case class Config(mode: String = "", screenName: String = "")

object Main {
  def main(args: Array[String]): Unit = {
    val parser = new scopt.OptionParser[Config]("twitter4j") {
      cmd("followers").action( (_, c) => c.copy(mode = "followers") )
        .text("followers is a command.")
        .children(
          opt[String]('s', "screenName").action( (x, c) =>
            c.copy(screenName = x)
          ).text("target screen name.")
        )
    }

    parser.parse(args, Config()) match {
      case Some(config) =>
        dispatch(config)
      case None =>
    }
  }

  def buildClient = {
    val conf = ConfigFactory.load()

    val cb = new ConfigurationBuilder
    cb.setOAuthConsumerKey(conf.getString("consumerKey"))
      .setOAuthConsumerSecret(conf.getString("consumerSecret"))
      .setOAuthAccessToken(conf.getString("accessToken"))
      .setOAuthAccessTokenSecret(conf.getString("accessTokenSecret"))

    new TwitterFactory(cb.build).getInstance()
  }

  def dispatch(config: Config) = {
    config.mode match {
      case "followers" =>
        val client = buildClient
        println(client.getFollowersIDs(config.screenName, -1))
    }
  }
}

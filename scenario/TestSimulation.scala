import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TestSimulation extends Simulation {

  // アクセス情報、UAとかアクセスURLとか
  val httpConf = http
    .baseURL("http://localhost:8021/")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  // ユーザごとのシナリオ
  // "/"にアクセスして1秒待機
  val users = scenario("hogeSimulation")
    .exec(http("http://localhost:8021/").get("/")).pause(1)

  // ユーザをどう増やすか
  // rampUsersPerSec(1) to (10) during (30 seconds),で30秒間で1-10までユーザ増やして
  // constantUsersPerSec(10) during(30 seconds)で10ユーザを30秒キープ
  setUp(
    users.inject(
      rampUsersPerSec(1) to (10) during (30 seconds),
      constantUsersPerSec(10) during(30 seconds)
    ).protocols(httpConf)
  )
}

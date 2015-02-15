package com.example

import com.intellij.notification.{Notification, NotificationType, Notifications}
import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.components.ServiceManager
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

import scala.collection.JavaConversions._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TwitterIdeaPlugin extends AnAction {

  def buildClient(config: TwitterIdeaConfig) = {
    val cb = new ConfigurationBuilder()
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(config.consumerKey)
      .setOAuthConsumerSecret(config.consumerSecret)
      .setOAuthAccessToken(config.accessToken)
      .setOAuthAccessTokenSecret(config.accessTokenSecret)
    new TwitterFactory(cb.build()).getInstance()
  }

  def actionPerformed(e: AnActionEvent) = {
    val config: TwitterIdeaConfig = ServiceManager.getService(classOf[TwitterIdeaConfig])

    if (!config.isConfigure) {
      Notifications.Bus.notify(new Notification("twitter for idea", "Config Error", "Please Config Twitter Key and Secret", NotificationType.INFORMATION))
    } else {
      val twitter = buildClient(config)
      val notifications = twitter.getHomeTimeline.toList.take(10).map(s => {
        new Notification("twitter for idea", s.getUser.getName, s.getText, NotificationType.INFORMATION)
      })

      notifications.foreach(Notifications.Bus.notify)

      Future {
        Thread.sleep(8000)
        notifications.map(_.expire)
      }
    }
  }
}
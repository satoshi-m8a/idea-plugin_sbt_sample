package com.example;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

@State(
        name = "TwitterIdeaConfig",
        storages = {
                @Storage(
                        id = "other",
                        file = "$APP_CONFIG$/twitteridea.xml")
        })
class TwitterIdeaConfig implements PersistentStateComponent<TwitterIdeaConfig> {
    public String consumerKey = "";
    public String consumerSecret = "";
    public String accessToken = "";
    public String accessTokenSecret = "";

    public boolean isConfigure() {
        return !(consumerKey.isEmpty() || consumerSecret.isEmpty() || accessToken.isEmpty() || accessTokenSecret.isEmpty());
    }

    public TwitterIdeaConfig getState() {
        return this;
    }

    public void loadState(TwitterIdeaConfig state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}

package com.example;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;

import javax.swing.*;

public class TwitterIdeaConfigurable implements Configurable {
    private JPanel myPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    final TwitterIdeaConfig config = ServiceManager.getService(TwitterIdeaConfig.class);

    public String getDisplayName() {
        return "Twitter For IDEA";
    }

    public boolean isModified() {
        return !(config.consumerKey.equals(textField1.getText()) &&
                config.consumerSecret.equals(textField2.getText()) &&
                config.accessToken.equals(textField3.getText()) &&
                config.accessTokenSecret.equals(textField4.getText()));
    }

    private void setFromConfig() {
        textField1.setText(config.consumerKey);
        textField2.setText(config.consumerSecret);
        textField3.setText(config.accessToken);
        textField4.setText(config.accessTokenSecret);
    }

    public JComponent createComponent() {
        setFromConfig();
        return myPanel;

    }

    public void apply() {
        if (!isModified()) {
            return;
        }

        config.consumerKey = textField1.getText();
        config.consumerSecret = textField2.getText();
        config.accessToken = textField3.getText();
        config.accessTokenSecret = textField4.getText();
    }

    public void disposeUIResources() {
        myPanel.removeAll();
        myPanel.getParent().remove(myPanel);
    }

    public String getHelpTopic() {
        return "preferences.topic";
    }

    public void reset() {
        setFromConfig();
    }


}
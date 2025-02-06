package com.example.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@ConfigurationProperties("urls.back")
public class BackProperties {
    private boolean enabled;
    private String uri;

  public BackProperties(boolean enabled, String uri) {
    this.enabled = enabled;
    this.uri = uri;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }
}

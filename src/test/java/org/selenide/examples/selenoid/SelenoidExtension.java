package org.selenide.examples.selenoid;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

class SelenoidExtension implements BeforeAllCallback  {
  @Override
  public void beforeAll(ExtensionContext context) {
    Configuration.remote = "http://localhost:4444/wd/hub";
    Configuration.reportsFolder = "target/surefire-reports";
    Configuration.downloadsFolder = "target/downloads";

    Map<String, Boolean> options = new HashMap<>();
    options.put("enableVNC", true);
    options.put("enableVideo", true);
    options.put("enableLog", true);
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("chrome");
    capabilities.setVersion("86.0");
    capabilities.setCapability("selenoid:options", options);
    Configuration.browserCapabilities = capabilities;
  }
}

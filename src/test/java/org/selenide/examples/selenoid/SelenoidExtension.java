package org.selenide.examples.selenoid;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.remote.DesiredCapabilities;

class SelenoidExtension implements BeforeAllCallback  {
  @Override
  public void beforeAll(ExtensionContext context) {
    Configuration.remote = "http://localhost:4444/wd/hub";
    Configuration.reportsFolder = "target/surefire-reports";
    Configuration.downloadsFolder = "target/downloads";

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("chrome");
    capabilities.setVersion("86.0");
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("enableVideo", true);
    capabilities.setCapability("enableLog", true);
    Configuration.browserCapabilities = capabilities;
  }
}

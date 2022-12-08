package org.selenide.examples.selenoid;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.chrome.ChromeOptions;

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

    ChromeOptions capabilities = new ChromeOptions();
    capabilities.setBrowserVersion("100.0");
    Configuration.browserCapabilities = capabilities;
    Configuration.browserCapabilities.setCapability("selenoid:options", options);
  }
}

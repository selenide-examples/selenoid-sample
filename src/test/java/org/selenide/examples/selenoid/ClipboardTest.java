package org.selenide.examples.selenoid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.selenide.selenoid.SelenoidClipboard;

import static com.codeborne.selenide.ClipboardConditions.content;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.clipboard;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SelenoidExtension.class)
public class ClipboardTest {
  @Test
  void canGetClipboardContent() {
    open("https://github.com/selenide/selenide");
    $(".file-navigation get-repo details.details-overlay").shouldBe(visible).click();
    sleep(500);
    $(".octicon-clippy").shouldBe(visible).click();
    assertThat(clipboard()).isInstanceOf(SelenoidClipboard.class);
    clipboard().shouldHave(content("https://github.com/selenide/selenide.git"));
  }

  @Test
  void canSetClipboardContent() {
    open("https://github.com/selenide/selenide");
    assertThat(clipboard()).isInstanceOf(SelenoidClipboard.class);
    clipboard().setText("что такое осень");
    sleep(500);
    clipboard().shouldHave(content("что такое осень"));
  }
}

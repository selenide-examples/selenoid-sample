package org.selenide.examples.selenoid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.selenide.selenoid.SelenoidClipboard;

import static com.codeborne.selenide.ClipboardConditions.content;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.clipboard;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SelenoidExtension.class)
public class ClipboardTest {
  @BeforeEach
  public void prepare() {
    open("https://www.w3schools.com/howto/howto_js_copy_clipboard.asp");
    $(".snigel-cmp-framework").shouldBe(visible);
    executeJavaScript("const popup = document.getElementById('snigel-cmp-framework'); popup.parentNode.removeChild(popup);");
  }

  @Test
  void canGetClipboardContent() {
    $("#myInput")
        .shouldHave(attribute("value", "Hello World"))
        .val("Dexter Morgan Freeman");
    $("button").click();
    clipboard().shouldHave(content("Dexter Morgan Freeman"));
    assertEquals("Dexter Morgan Freeman", clipboard().getText());
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

package org.selenide.examples.selenoid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.clipboard;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SelenoidExtension.class)
public class ClipboardTest {
  @Test
  void canGetClipboardContent() {
    open("https://github.com/selenide/selenide");
    $(".file-navigation get-repo details.details-overlay").click();
    $(".octicon-clippy").click();
    assertThat(clipboard().getText()).isEqualTo("https://github.com/selenide/selenide.git");
  }

  @Test
  void canSetClipboardContent() {
    open("https://github.com/selenide/selenide");
    clipboard().setText("что такое осень");
    assertThat(clipboard().getText()).isEqualTo("что такое осень");
  }
}

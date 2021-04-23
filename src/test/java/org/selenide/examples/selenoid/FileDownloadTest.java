package org.selenide.examples.selenoid;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.files.FileFilters.withExtension;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SelenoidExtension.class)
public class FileDownloadTest {
  @BeforeEach
  void setUp() {
    Configuration.fileDownload = FileDownloadMode.FOLDER;
  }

  @Test
  void download() throws IOException {
    open("https://the-internet.herokuapp.com/download");

    File file = $(byText("some-file.txt")).download(withExtension("txt"));

    assertThat(file.getName()).isEqualTo("some-file.txt");
    assertThat(readFileToString(file, UTF_8)).startsWith("{\\rtf");
  }
}

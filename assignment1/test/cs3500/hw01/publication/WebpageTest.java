package cs3500.hw01.publication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the webpage class.
 */
public class WebpageTest {

  Publication myWeb = new Webpage("Something From Google", "www.google.com",
      "January 1st, 2018");

  /**
   * Tests the APA citation.
   */
  @Test
  public void testCiteApa() {
    String apaString = "Something From Google. Retrieved January 1st, 2018, from www.google.com.";
    assertEquals(apaString,
        myWeb.citeApa());
  }

  /**
   * Tests the MLA citation.
   */
  @Test
  public void testCiteMla() {
    String mlaString = "\"Something From Google.\" Web. January 1st, 2018 <www.google.com>.";
    assertEquals(mlaString,
        myWeb.citeMla());
  }
}

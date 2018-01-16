package cs3500.hw01.publication;

/**
 * Represents bibliographic information for a webpage.
 */
public class Webpage implements Publication {

  private final String title;
  private final String url;
  private final String retrieved;

  /**
   * Constructs a {@code Webpage} object.
   *
   * @param title the title of the webpage
   * @param url the webpage's url
   * @param retrieved the date the webpage was retrieved
   */
  public Webpage(String title, String url, String retrieved) {
    this.title = title;
    this.url = url;
    this.retrieved = retrieved;

  }

  /**
   * Returns the APA citation of the webpage.
   *
   * @return String representing the APA Citation
   */
  @Override
  public String citeApa() {
    return this.title + ". Retrieved " + this.retrieved + ", from " + this.url + ".";
  }

  /**
   * Returns the MLA citation of the webpage.
   *
   * @return String representing the MLA Citation
   */
  @Override
  public String citeMla() {

    return "\"" + this.title + ".\" Web. " + this.retrieved + " <" + this.url + ">.";
  }

}

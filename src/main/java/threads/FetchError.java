package threads;

/**
 *
 * @author Abed
 */
public class FetchError {

  private String error;
  private String url;

  /**
   *
   * @param error
   * @param url
   */
  public FetchError(String error, String url) {
    this.error = error;
    this.url = url;
  }

  /**
   *
   * @return
   */
  public String getError() {
    return error;
  }

  /**
   *
   * @param error
   */
  public void setError(String error) {
    this.error = error;
  }

  /**
   *
   * @return
   */
  public String getUrl() {
    return url;
  }

  /**
   *
   * @param url
   */
  public void setUrl(String url) {
    this.url = url;
  }
}

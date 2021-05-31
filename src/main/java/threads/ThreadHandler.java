/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abed
 */
public class ThreadHandler {

  private static List<String> urls = new ArrayList();

  public ThreadHandler() {}

  public static List<String> getUrls() {
    return urls;
  }

  public static void setUrls(List<String> urls) {
    ThreadHandler.urls = urls;
  }

  public static void addUrl(String url) {
    urls.add(url);
  }
}

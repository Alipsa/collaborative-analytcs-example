package se.alipsa.ringlist;

import java.net.URL;

public class ScriptLoader {

  public static URL getScript() {
    return ScriptLoader.class.getResource("/Ringlist.R");
  }
}
package oogasalad.view.login;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class UserSession {

  private static Preferences prefs = Preferences.userNodeForPackage(UserSession.class);

  public static void saveUserLogin(int userId, String username) {
    prefs.putInt("userId", userId);
    prefs.put("username", username);
  }

  public static int getUserId() {
    return prefs.getInt("userId", -1);
  }

  public static String getUsername() {
    return prefs.get("username", "Guest");
  }

  public static void clearAllPreferences() {
    try {
      prefs.clear();
    } catch (BackingStoreException e) {
      e.printStackTrace();
    }
  }

}

package oogasalad.database.realtime;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.InputStream;

/**
 * This class initializes the Firebase app with the service account key.
 */
public class Firebase {

  public static void initializeFirebase() {
    try {
      InputStream serviceAccount = Firebase.class.getClassLoader()
          .getResourceAsStream("database/firebase_service_account_key.json");
      FirebaseOptions options =
          new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
              .setDatabaseUrl("https://ageless-lamp-416320-default-rtdb.firebaseio.com/").build();
      FirebaseApp.initializeApp(options);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

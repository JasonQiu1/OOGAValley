package oogasalad.database.realtime;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.function.Consumer;

public class GameService {

  private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("game");

  private void playerExists(String playerName, Consumer<Boolean> callback) {
    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot gameSnapshot : dataSnapshot.getChildren()) {
          if (gameSnapshot.child("players").child(playerName).exists()) {
            callback.accept(true);
            return;
          }
        }
        callback.accept(false);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        callback.accept(false);
      }
    });
  }

  public void startNewGame(String playerName, int initialScore, Consumer<Boolean> callback) {
    playerExists(playerName, exists -> {
      if (!exists) {
        String gameId = dbRef.push().getKey();
        GameData gameData = new GameData(playerName, initialScore);
        dbRef.child(gameId).setValue(gameData, (databaseError, databaseReference) -> {
          if (databaseError != null) {
            callback.accept(false);
          } else {
            callback.accept(true);
          }
        });
      } else {
        callback.accept(false);
      }
    });
  }

  public void joinGameByPlayerName(String targetPlayerName, String joiningPlayerName,
      int initialScore, Consumer<Boolean> callback) {
    playerExists(joiningPlayerName, exists -> {
      if (!exists) {
        attemptToJoinGame(targetPlayerName, joiningPlayerName, initialScore, callback);
      } else {
        callback.accept(false);
      }
    });
  }

  private void attemptToJoinGame(String targetPlayerName, String joiningPlayerName,
      int initialScore, Consumer<Boolean> callback) {
    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        if (!updateGameIfPlayerFound(dataSnapshot, targetPlayerName, joiningPlayerName, initialScore, callback)) {
          callback.accept(false);
        }
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        callback.accept(false);
      }
    });
  }

  private boolean updateGameIfPlayerFound(DataSnapshot dataSnapshot, String targetPlayerName,
      String joiningPlayerName, int initialScore, Consumer<Boolean> callback) {
    for (DataSnapshot gameSnapshot : dataSnapshot.getChildren()) {
      GameData gameData = gameSnapshot.getValue(GameData.class);
      if (shouldUpdateGame(gameData, targetPlayerName)) {
        updateGame(gameSnapshot.getKey(), gameData, joiningPlayerName, initialScore, callback);
        return true;
      }
    }
    return false;
  }

  private boolean shouldUpdateGame(GameData gameData, String targetPlayerName) {
    return gameData != null && gameData.getPlayers() != null && gameData.getPlayers().containsKey(targetPlayerName);
  }

  private void updateGame(String gameKey, GameData gameData, String joiningPlayerName, int initialScore, Consumer<Boolean> callback) {
    gameData.getPlayers().put(joiningPlayerName, new Player(joiningPlayerName, initialScore));
    dbRef.child(gameKey).setValue(gameData, (databaseError, databaseReference) -> {
      if (databaseError != null) {
        callback.accept(false);
      } else {
        callback.accept(true);
      }
    });
  }
}

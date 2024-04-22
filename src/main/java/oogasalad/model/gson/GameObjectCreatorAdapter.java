package oogasalad.model.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import oogasalad.model.gameObjectFactories.GameObjectCreator;

public class GameObjectCreatorAdapter extends TypeAdapter<GameObjectCreator> {

  private Gson gson = new Gson();

  @Override
  public void write(JsonWriter out, GameObjectCreator value) throws IOException {
    out.beginObject();
    out.name("type").value(value.getClass().getName());
    out.name("data").value(gson.toJson(value));
    out.endObject();
  }

  @Override
  public GameObjectCreator read(JsonReader in) throws IOException {
    in.beginObject();
    GameObjectCreator gameObjectCreator = null;
    while (in.hasNext()) {
      String name = in.nextName();
      if ("type".equals(name)) {
        String className = in.nextString();
        try {
          Class<?> clazz = Class.forName(className);
          String data = in.nextName();
          gameObjectCreator = (GameObjectCreator) gson.fromJson(in.nextString(), clazz);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      } else {
        in.skipValue();
      }
    }
    in.endObject();
    return gameObjectCreator;
  }
}

package oogasalad.model.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class InterfaceAdapterFactory implements TypeAdapterFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    if (!type.getRawType().isInterface()) {
      return null; // Not an interface type, ignore
    } else if (type.getRawType().getSimpleName().equals("ReadOnlyGameTime") || type.getRawType()
        .getSimpleName().equals("GameObjectCreate")) {
      return (TypeAdapter<T>) new InterfaceTypeAdapter<>(gson, type);
    } else {
      return null;
    }
  }

  private static class InterfaceTypeAdapter<T> extends TypeAdapter<T> {

    private final Gson gson;
    private final TypeToken<T> typeToken;

    InterfaceTypeAdapter(Gson gson, TypeToken<T> typeToken) {
      this.gson = gson;
      this.typeToken = typeToken;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
      System.out.println(value);
      out.beginObject();
      out.name("type").value(value.getClass().getName());
      out.name("data").value(gson.toJson(value));
      out.endObject();
    }

    @Override
    public T read(JsonReader in) throws IOException {
      in.beginObject();
      T value = null;
      while (in.hasNext()) {
        String name = in.nextName();
        if ("type".equals(name)) {
          String className = in.nextString();
          try {
            Class<?> clazz = Class.forName(className);
            String data = in.nextName();
            value = (T) gson.fromJson(in.nextString(), clazz);
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        } else {
          in.skipValue();
        }
      }
      in.endObject();
      return value;
    }
  }
}
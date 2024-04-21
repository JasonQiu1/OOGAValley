package oogasalad.database;

public class Thought {

  private int id;
  private String username;
  private String text;
  private String time;

  public Thought(int id, String username, String text, String time) {
    this.id = id;
    this.username = username;
    this.text = text;
    this.time = time;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getText() {
    return text;
  }

  public String getTime() {
    return time;
  }
}
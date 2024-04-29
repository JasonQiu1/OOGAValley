package oogasalad.view.login;

@FunctionalInterface
public interface EventHandlerSetup {

  void setup(Object component, String eventName) throws Exception;
}

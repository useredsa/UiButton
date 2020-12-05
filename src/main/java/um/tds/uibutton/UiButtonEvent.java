package um.tds.uibutton;

import java.util.EventObject;

public class UiButtonEvent extends EventObject {
  protected boolean oldTurnedOn, turnedOn;

  public UiButtonEvent(Object source, boolean oldTurnedOn, boolean turnedOn) {
    super(source);
    this.oldTurnedOn = oldTurnedOn;
    this.turnedOn = turnedOn;
  }

  public boolean isOldTurnedOn() {
    return oldTurnedOn;
  }

  public boolean isTurnedOn() {
    return turnedOn;
  }
}

package um.tds.uibutton;

import java.util.EventListener;

public interface UiButtonListener extends EventListener {
  public void notifyButtonEvent(UiButtonEvent event);
}

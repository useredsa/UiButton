package um.tds.uibutton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Vector;

public class UiButton extends Canvas implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Color borderBaseColor = new Color(160, 160, 160);
  private Color borderFrontColor = new Color(200, 200, 200);

  private String name; // button identifier
  private Color lightColor;
  private boolean turnedOn = false; // linked property

  private Vector<UiButtonListener> listeners = new Vector<>();
  private boolean pressed = false;

  public UiButton() {
    // First set initial and minimum sizes
    setSize(30, 30);
    setMinimumSize(new Dimension(30, 30));
    repaint();

    // Register mouse events
    this.addMouseListener(
        new java.awt.event.MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            buttonPressed(e);
          }

          public void mouseReleased(MouseEvent e) {
            buttonReleased(e);
          }
        });
  }

  public void paint(Graphics g) {
    int width = getSize().width;
    int height = getSize().height;

    // Block aspect ratio
    if (width != height) {
      int newSize = Math.min(width, height);
      width = newSize;
      height = newSize;
      setSize(width, height);
      invalidate();
    }

    // Calculate Dimensions
    int thickness = 3; // thickness del botón
    int buttonSize = width - thickness;
    // int alturaBoton=height-x;
    int buttonBorder = buttonSize / 5;
    // int lightThickness=2*buttonBorder-2;
    int lightThickness = buttonSize - 2 * buttonBorder;
    int displacement = 0; // desplazamiento;
    if (!pressed) {
      displacement = 0;
    } else {
      displacement = thickness;
    }

    // Draw furthest cirlce
    // Draw interior (of thickness thickness);
    g.setColor(borderBaseColor);
    g.fillOval(thickness, thickness, buttonSize, buttonSize);
    // Draw border
    g.setColor(Color.BLACK);
    g.drawOval(thickness, thickness, buttonSize - 1, buttonSize - 1);
    // Draw closest cirlce
    g.setColor(borderFrontColor);
    g.fillOval(displacement, displacement, buttonSize, buttonSize);
    // Draw lighted circle
    g.setColor(turnedOn ? lightColor : getBackground());
    g.fillOval(
        displacement + buttonBorder, displacement + buttonBorder, lightThickness, lightThickness);
    // Draw border
    g.setColor(Color.BLACK);
    g.drawOval(displacement, displacement, buttonSize - 1, buttonSize - 1);
    g.drawOval(
        displacement + buttonBorder,
        displacement + buttonBorder,
        lightThickness - 1,
        lightThickness - 1);

    // Restore color
    g.setColor(getForeground());
  }

  public Color getBorderBaseColor() {
    return borderBaseColor;
  }

  public void setBorderBaseColor(Color borderBaseColor) {
    this.borderBaseColor = borderBaseColor;
    repaint();
  }

  public Color getBorderFrontColor() {
    return borderFrontColor;
  }

  public void setBorderFrontColor(Color borderFrontColor) {
    this.borderFrontColor = borderFrontColor;
    repaint();
  }

  public Color getColor() {
    return lightColor;
  }

  public void setColor(Color color) {
    this.lightColor = color;
    repaint();
  }

  public boolean isTurnedOn() {
    return turnedOn;
  }

  public void setTurnedOn(boolean value) {
    boolean oldvalue = turnedOn;
    turnedOn = value;
    if (oldvalue != value) {
      UiButtonEvent event = new UiButtonEvent(this, oldvalue, value);
      notifyListeners(event);
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void buttonPressed(MouseEvent e) {
    pressed = true;
    repaint();
  }

  public void buttonReleased(MouseEvent e) {
    if (pressed) {
      pressed = false;
      setTurnedOn(!turnedOn);
      repaint();
    }
  }

  public synchronized void addUiButtonListener(UiButtonListener listener) {
    listeners.add(listener);
  }

  public synchronized void removeUiButtonListener(UiButtonListener listener) {
    listeners.removeElement(listener);
  }

  private void notifyListeners(UiButtonEvent event) {
    Vector<UiButtonListener> copy;
    synchronized (this) {
      copy = (Vector<UiButtonListener>) listeners.clone();
    }
    copy.forEach(l -> l.notifyButtonEvent(event));
  }

  public Dimension getPreferredSize() {
    return new Dimension(30, 30);
  }

  @Deprecated
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }
}
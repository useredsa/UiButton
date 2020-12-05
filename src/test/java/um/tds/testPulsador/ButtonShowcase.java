package um.tds.uibutton.test;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import um.tds.uibutton.UiButton;
import um.tds.uibutton.UiButtonEvent;
import um.tds.uibutton.UiButtonListener;

public class ButtonShowcase {
  private JFrame frame;
  private JTextArea textArea;
  private UiButton[] buttons;
  private String[] colors = {"yellow", "red", "blue"};

  /** Launch the application. */
  public static void main(String[] args) {
    EventQueue.invokeLater(
        new Runnable() {
          public void run() {
            try {
              ButtonShowcase window = new ButtonShowcase();
              window.frame.setVisible(true);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
  }

  public ButtonShowcase() {
    initialize();
  }

  /** Initialize the contents of the frame. */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 450, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    buttons = new UiButton[3];

    UiButton yellowbutton = new UiButton();
    buttons[0] = yellowbutton;
    yellowbutton.setBounds(26, 10, 67, 70);
    yellowbutton.addUiButtonListener(
        new UiButtonListener() {
          public void notifyButtonEvent(UiButtonEvent e) {
            buttonChange(0);
          }
        });
    frame.getContentPane().setLayout(null);
    yellowbutton.setColor(Color.YELLOW);
    frame.getContentPane().add(yellowbutton);

    UiButton redbutton = new UiButton();
    buttons[1] = redbutton;
    redbutton.setColor(Color.RED);
    redbutton.addUiButtonListener(
        new UiButtonListener() {
          public void notifyButtonEvent(UiButtonEvent e) {
            buttonChange(1);
          }
        });
    redbutton.setBounds(49, 115, 30, 30);
    frame.getContentPane().add(redbutton);

    UiButton bluebutton = new UiButton();
    buttons[2] = bluebutton;
    bluebutton.setColor(Color.BLUE);
    bluebutton.addUiButtonListener(
        new UiButtonListener() {
          public void notifyButtonEvent(UiButtonEvent e) {
            buttonChange(2);
          }
        });
    bluebutton.setBounds(49, 188, 30, 30);
    frame.getContentPane().add(bluebutton);

    textArea = new JTextArea();
    textArea.setBounds(153, 43, 260, 194);
    frame.getContentPane().add(textArea);
  }

  private void buttonChange(int n) {
    String value = buttons[n].isTurnedOn() ? "turned on" : "turned off";
    textArea.append("Button " + colors[n] + " " + value);
    textArea.append(System.getProperty("line.separator"));
  }
}

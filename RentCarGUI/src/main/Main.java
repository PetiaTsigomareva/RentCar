package main;

import static javax.swing.SwingUtilities.invokeLater;

import javax.swing.WindowConstants;

import frame.MainFrame;

public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {

    invokeLater(new Runnable() {

      @Override
      public void run() {
        MainFrame mf = new MainFrame();
        mf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mf.setVisible(true);
      }

    });
  }
}
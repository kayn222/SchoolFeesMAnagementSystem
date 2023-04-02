/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author USER
 */
public class NumericFilter extends DocumentFilter{
    
      public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
    if (text.matches("\\d+")) {
      super.insertString(fb, offset, text, attr);
    }
  }

  public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
    if (text.matches("\\d+")) {
      super.replace(fb, offset, length, text, attrs);
    }
  }
}




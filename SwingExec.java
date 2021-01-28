/**
 * 
 */
package co.jp.any.swing.excecute;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import co.jp.any.swing.component.JButtonTest;



/**
 * @author tozawa_h01
 *
 */
public class SwingExec {

	/**
	 * 
	 */
	public SwingExec() {
	}
	
    public class bottanOkActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
        	System.out.println(JButtonTest.getFrameHeight());
        }
    }
    
    public class bottanNgActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
        	
        	
        	System.out.println(JButtonTest.getFrameWidth());
        }
    }

    public class bottanEndActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
        	CloseHandler.windowClosing();
        	
        	
        }
    }

    
    public class MyWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
        	CloseHandler.windowClosing();
        }
    }

    private static class CloseHandler {
         static void windowClosing() {
        	System.out.println("èIóπ");
        	System.exit(0);
        }
    	
    }

}

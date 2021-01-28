/**
 * 
 */
package co.jp.any.swing.component;

import java.awt.FlowLayout;

import javax.swing.JFrame;

/**
 * @author tozawa_h01
 *
 */
public class JFrameTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public JFrameTest() {
		getContentPane().setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("JFrameTest");
		setSize(640, 480);
		//pack();
		setVisible(true);
	}
}

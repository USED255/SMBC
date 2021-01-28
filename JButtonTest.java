/**
 * 
 */
package co.jp.any.swing.component;

import javax.swing.JButton;
import javax.swing.JFrame;

import co.jp.any.swing.excecute.SwingExec;

/**
 * @author tozawa_h01
 *
 */
public class JButtonTest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_BUTTON_XPOSI_SIZE = 150;
	
	private static final int DEFAULT_BUTTON_YPOSI_SIZE = 30;
	
	private static final int DEFAULT_SPACE_SIZE = 10;
	
	private static final int ACTIVE_BAR_SIZE = 33;
	

	private static Object connectButton[] = {new JButton("高さ"),new JButton("幅"),new JButton("閉じる")};
	
	private static JFrame frame = new JFrame("JButtonTest");

	private static final SwingExec swingExecute = new SwingExec();
	
	public JButtonTest() {
		components();
	}

	private void components() {
		// 表示開始位置
		int x_Position = 10;
		int y_Position = 10;
		
		int index = 0;
		// ボタン表示位置
		for(Object bttons : connectButton){
			if (bttons instanceof JButton) {
				JButton btton = (JButton) bttons;
				btton.setBounds(x_Position,y_Position,DEFAULT_BUTTON_XPOSI_SIZE, DEFAULT_BUTTON_YPOSI_SIZE);
				x_Position = x_Position + DEFAULT_BUTTON_XPOSI_SIZE + DEFAULT_SPACE_SIZE; 

				// リスナーのセット
				setActionListeners(index, btton);
				index++;
			}
		}

		// フレームにボタンを追加
		for(Object bttons : connectButton){
			if (bttons instanceof JButton) {
				JButton btton = (JButton) bttons;
				frame.getContentPane().add(btton);
			}
		}
        
        frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(swingExecute.new MyWindowAdapter());
		frame.setTitle("JButtonTest");
		
		
		int frameXSize = x_Position + DEFAULT_SPACE_SIZE;
		int frameYSize = y_Position + DEFAULT_BUTTON_YPOSI_SIZE + DEFAULT_SPACE_SIZE + ACTIVE_BAR_SIZE;
		
		frame.setSize( frameXSize, frameYSize);
		frame.setVisible(true);
		
	}

	/**
	 * @param index
	 * @param btton
	 */
	private void setActionListeners(int index, JButton btton) {
		// リスナー
		switch(index){
			case 0:
				btton.addActionListener(swingExecute.new bottanOkActionHandler());
				break;
				
			case 1:
				btton.addActionListener(swingExecute.new bottanNgActionHandler());
				break;
				
			case 2:
				btton.addActionListener(swingExecute.new bottanEndActionHandler());
				break;
				
			default:
				System.err.println(index);
				break;
		}
	}

	public static int getFrameWidth(){
		return frame.getWidth();
	}
	
	public static int getFrameHeight(){
		return frame.getHeight();
	}
}

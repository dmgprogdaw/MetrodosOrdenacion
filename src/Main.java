import java.awt.Color;

import javax.swing.JFrame;

public class Main extends JFrame{
	
	public Main() {
		setTitle("KURISUTINA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(new Lienzo());
		pack();
	}
	
	public static void main(String[] args) {
		new Main().setVisible(true);
	}

}
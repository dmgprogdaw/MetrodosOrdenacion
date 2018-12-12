import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;

public class Lienzo extends JPanel {
	
	private static Random r = new Random();
	private int [] vector = new int[900];
	
	public Lienzo() {
		setPreferredSize(new Dimension(900, 600));
		setBackground(new Color(55, 55, 55));
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_I) {
					llenarVector();
					repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_O){
					ordenarVector();
					repaint();
				}
			}
		});
		
		llenarVector();
	}
	
	private void llenarVector() {
		for (int i=0; i<vector.length; i++) {
			vector[i] = r.nextInt(600);
		}
	}
	
	private void ordenarVector() {
		Arrays.sort(vector);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(33, 235, 31));
		for (int i=0; i<vector.length; i++) {
			g.drawLine(i, 599, i, 599 - vector[i]);
		}
	}
}
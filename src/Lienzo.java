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
	private int[] vector = new int[900];
	private Thread t = null;
	private String metodo;

	public Lienzo() {
		setPreferredSize(new Dimension(900, 600));
		setBackground(new Color(55, 55, 55));
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (t == null && e.getKeyCode() == KeyEvent.VK_0) {
					llenarVector();
					repaint();
				} else {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_1:
						metodo = "Insercion Directa";
						t = new Thread(new InsercionDirecta());
						t.start();
						break;
					case KeyEvent.VK_2:
						metodo = "Seleccion Directa";
						t = new Thread(new SeleccionDirecta());
						t.start();
						break;
					case KeyEvent.VK_3:
						metodo = "Intercambio Directo";
						t = new Thread(new IntercambioDirecto());
						t.start();
						break;
					}
				}
			}
		});

		llenarVector();
	}

	private void llenarVector() {
		for (int i = 0; i < vector.length; i++) {
			vector[i] = r.nextInt(600);
		}
	}

	private class InsercionDirecta implements Runnable {
		@Override
		public void run() {
			for (int i = 1; i < vector.length; i++) {
				int j = 0;
				while (j < i && vector[j] < vector[i]) {
					j++;
				}
				if (j < i) {
					int aux = vector[i];
					for (int k = i - 1; k >= j; k--) {
						vector[k + 1] = vector[k];
					}
					vector[j] = aux;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
			t = null;
		}
	}

	private class SeleccionDirecta implements Runnable {
		@Override
		public void run() {
			int menor;
			for (int i = 0; i <= vector.length - 2; i++) {
				menor = i;
				for (int j = i + 1; j <= vector.length - 1; j++) {
					if (vector[menor] > vector[j]) {
						menor = j;
					}
				}
				if (menor != i) {
					int aux = vector[menor];

					vector[menor] = vector[i];

					vector[i] = aux;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
			t = null;
		}
	}

	private class IntercambioDirecto implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= vector.length - 2; i++) {
				for (int j = vector.length - 1; j > i; j--) {
					if (vector[j] < vector[j - 1]) {
						int aux = vector[j];

						vector[j] = vector[j - 1];

						vector[j - 1] = aux;
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
				}
				t  = null;
			}
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(33, 235, 31));
		for (int i = 0; i < vector.length; i++) {
			g.drawLine(i, 599, i, 599 - vector[i]);
		}
		if (t != null) {
			g.setColor(new Color(25, 164, 32));
			g.drawString(metodo, 100, 100);
		}
	}
}
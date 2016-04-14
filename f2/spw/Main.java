//MY
package f2.spw;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Space War");
		frame.setSize(400,650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		SpaceShip v = new SpaceShip(180, 550, 20, 20);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v);
		frame.getContentPane().add(gp, BorderLayout.CENTER);

		frame.setVisible(true);

		engine.start();

		}
	}
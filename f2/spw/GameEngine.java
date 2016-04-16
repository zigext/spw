package f2.spw;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
	private SpaceShip v;

	private Timer timer;

	private double difficulty = 0.1;
	private long score = 0;

	public GameEngine(GamePanel gp, SpaceShip v){
		this.gp = gp;
		this.v =v;

		gp.sprites.add(v); //add spaceship

		timer = new Timer(50, new ActionListener(){
			//@overide
			public void actionPerformed(ActionEvent arg0){
				process();
			}
		});
		timer.setRepeats(true);
	}

	public void start(){
		timer.start();
	}

	private void process(){
		gp.updateGameUI(this); //show panel
		
	}

	public long getScore(){
		return score;
}

void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT: //move left
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT: //move right
			v.move(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
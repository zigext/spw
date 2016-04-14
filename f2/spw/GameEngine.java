package f2.spw;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

public class GameEngine implements GameReporter{
	GamePanel gp;
	private SpaceShip v;
	private Timer timer;
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
}
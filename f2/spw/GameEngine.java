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
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

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

	private void generateEnemy(){ //generate enemy
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}

	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		} //generate enemy depends on difficulty

		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed(); //movement of enemy
			
			if(!e.isAlive()){//enemy that dies will disapeare
				e_iter.remove();
				gp.sprites.remove(e); 
				score += 100; //increase score
			} 
		} 

		gp.updateGameUI(this); //show panel

		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		} //player dies when hits an enemy 
	}

	public void die(){ // player can die
		timer.stop();
	}

	public long getScore(){
		return score;
}

    void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT: //move left
			v.moveLeftRight(-1);
			break;
		case KeyEvent.VK_RIGHT: //move right
			v.moveLeftRight(1);
			break;
		case KeyEvent.VK_UP:
			v.moveUpDown(-1);
			break;
		case KeyEvent.VK_DOWN:
			v.moveUpDown(1);
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
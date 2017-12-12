//package edu.cpp.cs.cs141.projectTwo;

import java.util.Random;

import edu.cpp.cs.cs141.projectTwo.ActiveAgent;
import edu.cpp.cs.cs141.projectTwo.ActiveAgent.Character_Type;
import edu.cpp.cs.cs141.projectTwo.Gun.GUN_TYPE;
import edu.cpp.cs.cs141.projectTwo.Gun;
import edu.cpp.cs.cs141.projectTwo.UserInterface;
import edu.cpp.cs.cs141.projectTwo.ItemDrops;
public class GameEngine{
	
	private ActiveAgent player;
	
	private ActiveAgent villain;
	
	public void intializeTurn() {
		Gun gun=null;
		villain = new ActiveAgent(Character_Type.Villain, gun);

	}
	public void setPlayerGun(GUN_TYPE gunType){
		player = new ActiveAgent(Character_Type.Player, new Gun(gunType));
	}

	public boolean tryEscape(){
		Random rn= new Random();
		int chance= rn.nextInt(100);
		if(chance<=10){
		 return true;
		}
		else {
			while(!fight());
			return false;
		}
	}
	
	public int getEnemyHP() {
		return villain.getHp();
	}

	
	public int getPlayerHP() {
		return player.getHp();
	}
	
	public ActiveAgent getPlayer(){
		return player;
	}
	
	public ActiveAgent getVillain(){
		return villain;
	}
	
	public boolean fight() {
		/*
		 * Both player and villain shoot at each other at the exact same time.
		 * Sometimes the player get's shot and sometimes the villain gets shot depending on the accuracy of the gun.
		 */
		player.shoot(villain);
		villain.shoot(player);
		/*
		 * If statement checks if villain died and if player is still alive.
		 * if the statement is true it creates a new ItemDrops instance. 
		 */
		if(villain.getHp() <=0 && player.getHp() > player.getClass().0)
			new ItemDrops(player, player.getGun());
		/*
		 *
		 */
		return (player.getHp() <= 0|| villain.getHp() <= 0);
	}

	public boolean  gameEnds(){
		boolean finishGame= false;
		if(player.getHp()<=0){
		finishGame= true;
		}
		return finishGame;
		
	}
}

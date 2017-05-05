package edu.cpp.cs.cs141.projectTwo;

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
	
	public boolean fight() {
		// TODO Auto-generated method stub
		player.shoot(villain);
		villain.shoot(player);
		//Checks if someone dies
		if(villain.getHp() <=0 && player.getHp() > 0)
			new ItemDrops(player, player.getGun());
			
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

package edu.cpp.cs.cs141.projectTwo;

import java.util.Random;

import edu.cpp.cs.cs141.projectTwo.Gun;
import edu.cpp.cs.cs141.projectTwo.ActiveAgent.Character_Type;
import edu.cpp.cs.cs141.projectTwo.Gun.GUN_TYPE;

public class ActiveAgent {
	public static enum Character_Type {
		Villain, Player
	}

	private int hp;
	private Gun activeGun;
	
	public ActiveAgent(Character_Type gameCharacter, Gun gun) {
		hp = initialHp(gameCharacter);
		this.activeGun = getCharacterGun(gameCharacter, gun);
	}

	public int initialHp(Character_Type gameCharacter) {
		switch (gameCharacter) {
		case Villain:
			hp = 5;
			break;
		case Player:
			hp = 20;
			break;
		}
		return hp;
	}
	
	public boolean shoot(ActiveAgent target){
		GUN_TYPE gunType = GUN_TYPE.PISTOL;
		Random rn= new Random();
		int chance= rn.nextInt(100); 
		if(chance<=activeGun.getAccuracy() && activeGun.gunAmmo()>0){
			int newHealth= target.getHp()-activeGun.getDamage();
			target.setHp(newHealth);	
			activeGun.setAmmo(activeGun.getAmmo()-1);
			return true;
		}
		else{
			return false;
		}
	}
	public void setHp(int newHp) {
		hp=newHp;
	}
	public Gun getCharacterGun(Character_Type type, Gun gun) {
		switch (type) {
		case Player:
			//gun.getGunType();
			break;
		case Villain:
			gun=villainGun();
			break;
		default:
			System.out.println("Error choosing gun.");
		}
		return gun;
	}

	public int getHp() {
		return hp;
	}
	public Character_Type getPlayerCharacter(){
		return Character_Type.Player ;
	}
	public Character_Type getEnemyCharacter(){
		return Character_Type.Villain;
	}
	public GUN_TYPE playerGunType(Gun gun) {
		return gun.getGunType();
	}

	public void resetHp(int hp1) {
		this.hp = hp1;
	}

public Gun villainGun(){
	//randomly choosing gun
	Random rn= new Random();
	int chance= rn.nextInt(100);
	if(chance<= 50){
		Gun villainGun1 = new Gun(GUN_TYPE.PISTOL);
		return villainGun1;
	}
	else if (chance<=85){
		Gun villainGun1 = new Gun(GUN_TYPE.RIFLE);
		return villainGun1;
	}
	else {
		Gun villainGun1 = new Gun(GUN_TYPE.SHOTGUN);
		return villainGun1;	
	}
}
}

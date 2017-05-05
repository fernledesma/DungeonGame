package edu.cpp.cs.cs141.projectTwo;

import java.util.Random;

import edu.cpp.cs.cs141.projectTwo.Gun.GUN_TYPE;

public class itemDrops {
public itemDrops(ActiveAgent Player, Gun gun){
	Random rn= new Random();
	int chance= rn.nextInt(100);
	if(chance<=30){
		getIncreaseHealth(Player);
	}
	else{
		getMaxAmmo(gun);
	}
}
public void getIncreaseHealth(ActiveAgent agent){
	agent.setHp(agent.getHp()+5);
}
public void getMaxAmmo(Gun gun){
	gun.resetAmmo();
}
}

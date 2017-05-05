package edu.cpp.cs.cs141.projectTwo;

import java.util.Random;

import edu.cpp.cs.cs141.projectTwo.Gun.GUN_TYPE;




public class Gun {

public static enum GUN_TYPE {PISTOL, SHOTGUN, RIFLE};


private final int damage;

private final int accuracy;

private GUN_TYPE gunType;

private int ammo;


public Gun(GUN_TYPE Gun){
	this.gunType= Gun;
	this.damage= getDamage();
	this.accuracy=gunAccuracy();
	this.ammo= gunAmmo();
}



public int getDamage() {
	int damage= 0;
	switch(gunType){
	case PISTOL:
		damage= 1;
		break;
	case RIFLE:
		damage=2;
		break;
	case SHOTGUN:
		damage= 5;
	}
	return damage;
}

public int getAccuracy() {
	return accuracy;
}
public GUN_TYPE getGunType(){
	return gunType;
}
public int getAmmo() {
	return ammo;
}

public int gunAmmo(){
	int ammo= 0;
	switch(gunType){
	case PISTOL:
		ammo= 15;
		break;
	case RIFLE:
		ammo=10;
		break;
	case SHOTGUN:
		ammo= 5;
	}
	return ammo;
}
public int gunAccuracy() {
	int accuracy = 0;
	
	switch(gunType) {
	case PISTOL:
		accuracy = 75;
		break;
	case RIFLE:
		accuracy = 65;
		break;
	case SHOTGUN:
		accuracy = 40;
		break;
	}
	
	return accuracy;
}
public int resetAmmo(){
	ammo= getAmmo();
	return ammo;
}
public void setAmmo(int newAmmo){
	ammo=newAmmo;
}
}


/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment 2
 *
 * <Escape the dungeon game>
 *
 *	Jose Fernando Ledesma 
 */
package edu.cpp.cs.cs141.projectTwo;

import java.util.Random;

import edu.cpp.cs.cs141.projectTwo.Gun.GUN_TYPE;

public class ItemDrops {
	
	public ItemDrops(ActiveAgent Player, Gun gun){
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

package edu.cpp.cs.cs141.projectTwo;

import java.util.Random;
import java.util.Scanner;

import edu.cpp.cs.cs141.projectTwo.GameEngine;
import edu.cpp.cs.cs141.projectTwo.ActiveAgent.Character_Type;
import edu.cpp.cs.cs141.projectTwo.Gun.GUN_TYPE;

public class UserInterface {
	private int steps= 0;
	private int maxSteps=10;
	private Scanner userIn;
	private GameEngine GE;
	
	public UserInterface() {
		userIn= new Scanner(System.in);
		GE= new GameEngine();
	}
	
	
	public int getSteps() {
		return steps;
	}
	public void increment(){
		steps++;
	}
			 
	public int decreaseStep(){
			 steps--;
			 return steps;
		
	}
	/**
	 * 
	 */
	
	
	public void startGame() {
		
		int option = 0;
		
		while((option = getUserMenuOption()) != 2) {
			switch(option) {
			case 1:
				steps = 0;
				GE.setPlayerGun(gunMenu());
				//Terminates once game loop is false
				while(!runGameLoop());
				break;
			default:
				System.out.println("Invalid option!");
				break;
			}
		}
	}

	private boolean runGameLoop() {
		GE.intializeTurn();
		
		gameEncounterVillain();
		
		if(steps >= 10){
			System.out.println("YOU WIN!!!");
			return true;
		}
		
		if(GE.getPlayerHP() <= 0){
			System.out.println("You DIED.");
			return true;
		}

		return false;
	}

	private boolean gameEncounterVillain() {
		Random rn= new Random();
		int chance= rn.nextInt(100);
		if(chance <= 15){
			String enemyGun = getEnemyGunName();
			System.out.println("An enemy with a " + enemyGun  + " spawned!");
			System.out.println("You can either:");
			System.out.println("\t1. Fight");
			System.out.println("\t2. Try to escape");
			int choice = userIn.nextInt();
			if(choice ==1){
				//run fight until someone dies
				while(!GE.fight());
			}else if(choice==2){
				if (GE.tryEscape()) {
					decreaseStep();
					return true;
				}
			}
			
			//boolean fightOver = GE.fight();
		
			System.out.println("Your current health is " + GE.getPlayerHP());
			System.out.println("The villain's health is " + GE.getEnemyHP());
		
			//returns false
			//return fightOver;
			return false;
		}else{
			increment();
			System.out.println("You are "+ (10 - getSteps())+ " steps away!");
			//runGameLoop();
			return true;
		}
	}

	private String getEnemyGunName() {
		/*String gunName = "";
		GUN_TYPE gunType = GE.getVillainGun() ;
		
		switch(gunType) {
		case PISTOL:
			gunName = "Pistol";
			break;
		case RIFLE:
			gunName = "Rifle";
			break;
		case SHOTGUN:
			gunName = "Shotgun";
			break;
		}
		
		return gunName;*/
		return "Fake";
	}

	public GUN_TYPE getUserGunType() {
		GUN_TYPE guntype = gunMenu();
		return guntype;
	}

	private GUN_TYPE gunMenu() {
		//GUN_TYPE gunType = GUN_TYPE.PISTOL;
		GUN_TYPE gunType = null;
		System.out.println("Chooset your type of weapon:");
		System.out.println("\t1. Rifle");
		System.out.println("\t2. Pistol");
		System.out.println("\t3. Shotgun");
		int choice = userIn.nextInt();
		userIn.nextLine();
		
		switch(choice) {
		case 1:
			gunType= GUN_TYPE.RIFLE;
			break;
		case 2:
			gunType = GUN_TYPE.PISTOL;
			break;
		case 3:
			gunType = GUN_TYPE.SHOTGUN;
			break;
		default:
			System.out.println("Invalid selection!"
					+ " Pistol selected by default");
		}
		
		return gunType;
	}

	private int getUserMenuOption() {
		int option;
		System.out.println("Welcome to the Dungeon of Terror. You are ten steps away from the exit. Escape if you dare!");
		System.out.println("\nSelect from the following options:");
		System.out.println("\t1. New Game");
		System.out.println("\t2. Quit");
		option = userIn.nextInt();
		userIn.nextLine();
		return option;
	}

	
	
		
		

}

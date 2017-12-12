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
import java.util.Scanner;

import edu.cpp.cs.cs141.projectTwo.GameEngine;
import edu.cpp.cs.cs141.projectTwo.ActiveAgent.Character_Type;
import edu.cpp.cs.cs141.projectTwo.Gun.GUN_TYPE;

public class UserInterface {
	private int steps= 0;
	private int maxSteps=10;
	private Scanner userIn;
	private GameEngine GE;
	//Method creates objects from the Scanner and GameEngine class. 
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
		//Begins the game and prints the main menu in which the user makes the choice of continuing.
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
		//Creates the villain
		GE.intializeTurn();
		//Calculates if the player encounters a villain every time they take a step.
		gameEncounterVillain();
		
		/*
		 * Calculates if the player wins by reaching the end of the tunnel or dies before reaching the end.
		 */
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
		/*
		 * Within the if statement, it is assumed that the player has a 15 percent chance of running into an enemy.
		 */
		if(chance <= 15){
			/*
			 * Communicates to the user that they ran into a villain and presents the user with a choice. 
			 */
			String enemyGun = getEnemyGunName();
			System.out.println("A villain with a " + enemyGun  + " spawned!");
			System.out.println("You can either:");
			System.out.println("\t1. Fight");
			System.out.println("\t2. Try to escape");
			//The user's choice is stored into the variable choice, through an integer, and will be used to calculate a scenario for the user.  
			int choice = userIn.nextInt();
			if(choice ==1){
				/*
				 * It will continue to fight(run) until either person dies.
				 * If villain dies, the player continues on with the game.
				 * If player dies, the game will end. 
				 */
				while(!GE.fight());
			}else if(choice==2){
				/*
				 * The if statement calls the tryEscape method and if it is true, the player escaped. 
				 * Within the method, if the player can not escape he is forced to fight the villain and it returns false, ignoring the if statement. 
				 */
				if (GE.tryEscape()) {
					/*
					 *Decrease step since player managed to escape villain;
					 */
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
		String gunName = "";
		GUN_TYPE gunType= GE.getVillain().playerGunType(GE.getVillain().getGun());
		
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
		
		return gunName;
	}

	public GUN_TYPE getUserGunType() {
		
		GUN_TYPE guntype = gunMenu();
		return guntype;
	}
	
	private GUN_TYPE gunMenu() {
		//Initializes the gunType variable so it can later be assigned to user's choice. 
		GUN_TYPE gunType = null;
		
		/*
		 * Prints a selection of weapon choices for the user.
		 */
		System.out.println("Chooset your type of weapon:");
		System.out.println("\t1. Rifle");
		System.out.println("\t2. Pistol");
		System.out.println("\t3. Shotgun");
		/*
		 * Assigns user choice, through an integer, to the variable choice
		 */
		int choice = userIn.nextInt();
		userIn.nextLine();
		
		/*
		 * The variable choice is used to bridge the choice of the user with the actual gun type defined in the gun class. 
		 */
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
		/*
		 * Introduces the player to the game and displays the main menu of the game.
		 */
		System.out.println("Welcome to the Dungeon of Terror. You are ten steps away from the exit. Escape if you dare!");
		System.out.println("\nSelect from the following options:");
		System.out.println("\t1. New Game");
		System.out.println("\t2. Quit");
		
		/*
		 * Assigns user's choice, through an integer, to the variable option. 
		 * Option is returned so it can be used in the StartGame method.
		 */
		option = userIn.nextInt();
		userIn.nextLine();
		return option;
	}

	
	
		
		

}

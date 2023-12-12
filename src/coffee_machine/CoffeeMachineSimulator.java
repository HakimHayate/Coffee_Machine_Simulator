package coffee_machine;

import java.util.Scanner;

public class CoffeeMachineSimulator {
	
	public MachineSimulator(int water, int milk, int coffee, int cups, int cash) {
		this.water = water;
		this.milk = milk;
		this.coffee = coffee;
		this.cups = cups;
		this.cash = cash;
		
		displayMenu();  // Showing the menu when we start the machine.
		setState(MachineState.MENU); // Set the machine at menu at the start.
	}			
	
	
	// We check the state of the machine to decide what to do next.
	public void excuting(String input) {
		switch (this.state) {
		
			case MENU -> menu(input);
				
			case BUY -> buy(input);
				
			case FILLING_WATER -> fillingWater(input);

			case FILLING_MILK -> fillingMilk(input);
			
			case FILLING_COFFEE -> fillingCoffee(input);
			
			case FILLING_CUPS -> fillingCups(input);
			
			default -> System.out.printf("State %d unknow.\n\n", this.state);			
		}
	}
	
	
	// Possible choices in the menu.
	public void menu(String input) {
		switch (input) {
		
			case "buy" -> pickCoffee();
			
			case "fill" -> fill();
			
			case "take" -> take();
			
			case "remaining" -> getResources();
			
			case "exit" -> shutDown();
			
			default -> invalidInput(input);  // In case the input is none of the possible choices.
				 
		}
	}
	
	
	private void pickCoffee() {
		setState(MachineState.BUY);
		System.out.print("What do you want to buy? "
				+ "1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n");
	}
	
	
	// Machine asks how much resources to add.
	private void fill() {
		System.out.print("Write how many ml of water you want to add:\n");
		setState(MachineState.FILLING_WATER);
		
	}
	
	
	// Let the user know that the input is invalid
	private void invalidInput(String input) {
		System.out.printf("%s is not a valid input.\n", input);
		back();
	}
	
	
	// Adding water machine.
	private void fillingWater(String water) {
		int waterToAdd = Integer.parseInt(water);
		this.water += waterToAdd;
		System.out.print("Write how many ml of milk you want to add:\n");
		setState(MachineState.FILLING_MILK);
	}
	
	
	// Adding milk to machine.
	private void fillingMilk(String milk) {
		int milkToAdd = Integer.parseInt(milk);
		this.milk += milkToAdd;
		System.out.print("Write how many grams of coffee beans you want to add:\n");
		setState(MachineState.FILLING_COFFEE);
	}
	
	
	// Adding coffee to machine.
	private void fillingCoffee(String coffee) {
		int coffeeToAdd = Integer.parseInt(coffee);
		this.cups += coffeeToAdd;
		System.out.print("Write how many disposable cups you want to add:\n");
		setState(MachineState.FILLING_CUPS);
	}
	
	
	// Adding cups then it goes back to display menu.
	private void fillingCups(String cups) {
		int cupsToAdd = Integer.parseInt(cups);
		this.cups += cupsToAdd;
		back();
	}
	
	// Turning off the machine.
	private void shutDown() {
		setState(MachineState.OFF);
	}
	
	
	// Give all the money collected.
	private void take() {
		System.out.printf("I gave you %d.\n", this.cash);
		this.cash = 0;
		back();
		
	}
	
	
	// Showing types of coffee that the machine offers.
	public void buy(String input) {
		
		switch (input) {
		
			case "1" -> makeCoffee(250, 0, 16, 4);  // Espresso.
			
			case "2" -> makeCoffee(350, 75, 20, 7);  // Latte.
			
			case "3" -> makeCoffee(200, 100, 12, 6);  // Cappuccino.
			
			case "back" -> System.out.print("");  // Nothing happens...
			
			default -> {
				System.out.println("Invalid input."); // In case the input is none of the possible choices.
			}
		}
		
		back();
	}
	
	
	// Not necessary...
	private void displayMenu() {
		System.out.print("Write action (buy, fill, take, remaining, exit):\n");
	}
	
	
	// Method that simulate making coffee.
	public void makeCoffee(int water, int milk, int coffee, int cost) {
		
		if (isEnoughResources(water, milk, coffee)) {  
			
			System.out.print("I have enough resources, making you a coffee!\n");
			
			this.water -= water;
			this.milk -= milk;
			this.coffee -= coffee;
			this.cups -= 1;
			this.cash += cost;
		}
		
	}
	
	
	// Go back to menu.
	public void back() {
		System.out.print("\n");
		setState(MachineState.MENU);
		displayMenu();
	}
	
	
	// Set the state of the machine.
	private void setState(MachineState state) {
		this.state = state;
	}
	
	
	// Print the resources available inside the machine.
	public void getResources() {
		System.out.println("The coffee machine has: ");
		System.out.printf("%d ml of water\n"
				          +"%d ml of milk\n"
				          +"%d g of coffee beans\n"
				          +"%d disposable cups\n"
				          +"$%d of money\n", this.water, this.milk, this.coffee,
				          					this.cups, this.cash);
		
		
		back();
	}
		
		
	// It returns true if the machine has enough resources
	// to make the coffee chosen, otherwise it returns
	// false and which resource to fill.
	private boolean isEnoughResources(int water, int milk, int coffee ) {
		
		boolean isEnough = true;
		
		if (this.water < water) {
			isEnough = false;
			System.out.print("Sorry, not enough water!\n");
		}
		
		else if (this.milk < milk) {
			isEnough = false;
			System.out.print("Sorry, not enough milk!\n\n");
		}
		
		else if (this.coffee < coffee) {
			isEnough = false;
			System.out.print("Sorry, not enough coffee!\n\n");
		}
		
		else if (this.cups-1 < 0) {
			isEnough = false;
			System.out.print("Sorry, not enough cups!\n\n");
		}
		
		return isEnough;
	}
	

	// Return state of the machine.
	public MachineState getState() {
		return this.state;
	}
	
	
	private MachineState state;
	private int water;
	private int milk;
	private int coffee;
	private int cups;
	private int cash;
}

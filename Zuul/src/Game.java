import java.util.ArrayList;

/*
 * This is an adventure game based off of the "World of Zuul" application by Michael Kolling and David J. Barnes
 * Author: Omar Nassar
 * Date: October 15, 2018
 */

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery. That's all. It should really be extended to make it more
 * interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game {
	private Parser parser;
	private Room currentRoom;
	Room outsideFront, mainOffice, attendance, principal, d8, mainHall, oneHall, cafeteria, math, teacherOffice, gHall,
		 outsideBack, stall, bathroom, onetwenty; //room list
	ArrayList<Item> inventory = new ArrayList<Item>();
	//items list
	final Item Juul = new Item("Juul");
	final Item Apple = new Item("Half-Eaten_Apple");
	final Item Eraser = new Item("Eraser");
	final Item Pencil = new Item("Pencil");
	final Item lateSlip = new Item("Late_Slip");
	final Item test = new Item("Graded_Test");
	final Item backpack = new Item("Backpack");
	final Item calculator = new Item("Calculator");
	
	boolean finished = false; //ending the game

	/**
	 * Create the game and initialize its internal map.
	 */
	public Game() {
		createRooms();
		parser = new Parser();
	}

	public static void main(String[] args) {
		Game mygame = new Game();
		mygame.play();
	}

	/**
	 * Create all the rooms and link their exits together.
	 */
	private void createRooms() {
		//main directions
		final String west = "west";
		final String east = "east";
		final String north = "north";
		final String south = "south";

		// create the rooms
		outsideFront = new Room("outside in front of the School");
		mainOffice = new Room("in the main office");
		attendance = new Room("in the attendance office");
		principal = new Room("in the principal's office");
		d8 = new Room("in room D-8. The teacher asks if you need help finding your classroom");
		mainHall = new Room("in Main hall");
		oneHall = new Room("in 1 Hall");
		cafeteria = new Room("in the cafeteria");
		math = new Room("in your Math classroom");
		teacherOffice = new Room("in your math teacher's office");
		gHall = new Room("in G Hall");
		outsideBack = new Room("outside behind the school");
		bathroom = new Room("in the G Hall bathroom");
		stall = new Room("in the nearest stall");
		onetwenty = new Room("in Room 1-20. You are amazed at the rows upon rows of computers in the room");

		// Initialize room exits

		// outside front exits and items
		outsideFront.setExit(west, mainOffice);
		outsideFront.setExit("around", outsideBack);

		// main office exits
		mainOffice.setExit(east, outsideFront);
		mainOffice.setExit(west, mainHall);
		mainOffice.setExit(north, attendance);
		mainOffice.setExit(south, principal);

		// attendance office exits
		attendance.setExit(south, mainOffice);

		// principal's office exits
		principal.setExit(north, mainOffice);

		// main hall exits
		mainHall.setExit(east, mainOffice);
		mainHall.setExit(south, oneHall);
		mainHall.setExit(north, d8);

		// Room D-8 exits
		d8.setExit(south, mainHall);

		// one hall exits
		oneHall.setExit(north, mainHall);
		oneHall.setExit(south, cafeteria);
		oneHall.setExit(east, onetwenty);
		oneHall.setExit(west, gHall);

		// room 1-20 exits
		onetwenty.setExit(west, oneHall);

		// cafeteria exits
		cafeteria.setExit(north, oneHall);

		// outside back exits
		outsideBack.setExit("around", outsideFront);
		outsideBack.setExit(south, gHall);

		// G-hall exits
		gHall.setExit(north, outsideBack);
		gHall.setExit(east, oneHall);
		gHall.setExit(south, math);
		gHall.setExit(west, bathroom);

		// math room exits
		math.setExit(north, gHall);
		math.setExit(east, teacherOffice);

		// teacher's office exits
		teacherOffice.setExit(west, math);

		// bathroom exits
		bathroom.setExit(east, gHall);
		bathroom.setExit(south, stall);

		// stall exits
		stall.setExit(north, bathroom);

		// Set room items
		outsideFront.setItem(backpack);
		attendance.setItem(lateSlip);
		mainHall.setItem(Pencil);
		onetwenty.setItem(calculator);
		cafeteria.setItem(Apple);
		gHall.setItem(Eraser);
		teacherOffice.setItem(test);
		stall.setItem(Juul);

		currentRoom = outsideFront; // start game outside

	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
			if (inventory.contains(Juul)) {//losing condition 1
				System.out.println("Don't do drugs. You lose.");
				finished = true;
			}
			if (inventory.contains(Apple)) {//losing condition 2
				System.out.println("Don't pick up something someone else ate. It has germs. You lose.");
				finished = true;
			}
			if (inventory.contains(backpack) && inventory.contains(lateSlip) && inventory.contains(Pencil)
				&& inventory.contains(calculator) && inventory.contains(Eraser) && inventory.contains(test)
				&& currentRoom == math) {//winning condition
				System.out.println("Good job! You win! \nAgainst all odds, you found everything you lost and got your test back for credit!");
				System.out.println("Thanks for playing! Goodbye!");
				finished = true;
			}
		}

	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("You are late to school and are rushing to get "
		+ "to school when someone runs up from behind you and takes your things. "
		+ "\nYou try to catch him but he is too fast. "
		+ "He drops your backpack but keeps running with a few of your things. "
		+ "\nHe runs inside the school and hides somewhere. "
		+ "What you need to find along with your backpack is your pencil, eraser, "
		+ "\nlate slip, calculator, and your graded test that your parents "
		+ "signed and you were supposed to return today.");
		System.out.println();
		System.out.println("Type 'help' if you need help.");
		System.out.println();
		System.out.println(currentRoom.getLongDescription());
	}

	/**
	 * Given a command, process (that is: execute) the command. If this command ends
	 * the game, true is returned, otherwise false is returned.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")) {
			printHelp();
		}
		else if (commandWord.equals("go")) {
			wantToQuit = goRoom(command);
		} else if (commandWord.equals("quit")) {
			System.out.println("Thanks for playing! Goodbye.");
			wantToQuit = quit(command);
		} else if (commandWord.equals("inventory")) {
			printInventory();
		} else if (commandWord.equals("get")) {
			getItem(command);
		} else if (commandWord.equals("drop")) {
			dropItem(command);
		}
		return wantToQuit;
	}

	private void dropItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to drop...
			System.out.println("Drop what?");
			return;
		}

		String item = command.getSecondWord();

		
		Item newItem = null;
		int index = 0;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getDescription().equals(item)) {
				newItem = inventory.get(i);
				index = i;
			}
		}

		if (newItem == null)
			System.out.println("That item is not in your inventory!");
		else {
			inventory.remove(index);
			currentRoom.setItem(newItem);
			System.out.println("Dropped: " + item);
		}
	}

	private void getItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to pick up...
			System.out.println("Get what?");
			return;
		}

		String item = command.getSecondWord();

		
		Item newItem = currentRoom.getItem(item);

		if (newItem == null)
			System.out.println("That item is not here!");
		else {
			inventory.add(newItem);
			currentRoom.removeItem(item);
			System.out.println("Picked up: " + item);
		}
	}

	private void printInventory() {
		StringBuilder output = new StringBuilder("");
		for (int i = 0; i < inventory.size(); i++) {
			output.append(inventory.get(i).getDescription() + ", "); // += inventory.get(i).getDescription() + ", ";
		}
		output.delete(output.length() - 2, output.length());
		System.out.println("You are carrying:");
		System.out.println(output);
	}

	// implementations of user commands:

	/**
	 * Print out some help information. Here we print some stupid, cryptic message
	 * and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("You are looking for your things that were stolen from you.");
		System.out.println();
		System.out.println("Your command words are:");
		parser.showCommands();
	}

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */
	private boolean goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return false;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null)
			System.out.println("There is no door!");
		else {
			currentRoom = nextRoom;
			System.out.println(currentRoom.getLongDescription());
		}
		return false;
	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we really
	 * quit the game. Return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		if (command.hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else
			return true; // signal that we want to quit
	}
}


import java.util.ArrayList;
import java.util.Random;

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
 * @version 2006.03.30
 */
public class Game 
{
    
    private Parser parser;
    private Room currentRoom;
    private ArrayList items;
    private ArrayList weights;
    private int totalWeight;
    private final int MAX_WEIGHT = 10;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
	{
        createRooms();
        parser = new Parser();
        items = new ArrayList();
        weights = new ArrayList();
        totalWeight = 0;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() 
	{
        Room outside, theatre, pub, lab, office, up, down;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        up = new Room("creepy upstairs");
        down = new Room("spooky downstairs");

        // initialise room exits
        outside.setExit("north", lab);
      //  outside.item.addItem("Gun", 10);
        outside.setExit("down", down);
        outside.setExit("up", up);
        outside.setExit("west", pub);
        outside.setExit("east", office);

        down.setExit("north", outside);
       // down.item.addItem("book", 1);
        up.setExit("south", outside);
        // up.item.addItem("knife", 5);

        lab.setExit("east", office);
        lab.setExit("south", outside);
        lab.setExit("north", theatre);
        // lab.item.addItem("bomb", 10);

        office.setExit("south", lab);
        // office.item.addItem("key", 0);

        pub.setExit("east", theatre);
        pub.setExit("south", outside);
        // pub.item.addItem("beer", 2);

        theatre.setExit("south", outside);
        currentRoom = outside;  // start game outside
        
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() 
	{
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) 
		{
            Command command = parser.getCommand();
            finished = processCommand(command);

            
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() 
	{
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());        
    }


    /* to keep with the idea of nostalgia the Ai will be basic, to fit the basic text based feel
    *
    * although, making it easy enough to expand upon, should they need
    *
    *
    */
    private void BasicEnemyAi()     
    {
        
      //  Random rand = new Random();



     //   int rand_int1 = rand.nextInt(1000); 
      //  int rand_int2 = rand.nextInt(1000);

      //  System.out.println("Random Integers: "+rand_int1); 
      //  System.out.println("Random Integers: "+rand_int2);
    
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
	{
        boolean wantToQuit = false;

        if (command.isUnknown()) 
		{
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch(commandWord) 
        {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            case "take":
                take(command);
                break;
            case "drop":
                drop(command);
                break;
            case "give":
                give(command);
                break;
            case "look":
                look(command);
                break;
            case "test":
                test(command);
                break;
            default:
                // Since we check if the command is valid
                // this shouldnt be reachable but we could do 
                // something by default if we so wanted...
                break;
        }
        return wantToQuit;
    }

// implementations of user commands:
    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp() 
	{ 
        System.out.println("Your command words are:");
        parser.showCommands();
        
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command) 
	{
        if (!command.hasSecondWord()) 
		{
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.  Room nextRoom = currentRoom.getExit(direction);

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) 
		{
            System.out.println("There is no door!");
        } 
		else 
		{
            currentRoom = nextRoom; 
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private void look(Command command)
    {
        System.out.println("Looking around, whats that in the shadows?");
    }

    private void test(Command command)
    {
        System.out.println("simple test");
    }


    /**
     * Try to take an item from the current room, otherwise print an error
     * message.
     */
    private void take(Command command) 
	{
        if (!command.hasSecondWord()) 
		{
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }

        String item = command.getSecondWord();
        int w = currentRoom.containsItem(item);
        if (w == 0) 
		{
            // The item is not in the room
            System.out.println("No " + item + " in the room");
            return;
        }
        if (totalWeight + w <= MAX_WEIGHT) 
		{
            // The player is carrying too much
            System.out.println(item + " is too heavy");
            return;
        }
        // OK we can pick it up
        currentRoom.removeItem(item);
        items.add(item);
        weights.add(w);
        totalWeight += w;
    }

    /**
     * Try to drop an item, otherwise print an error message.
     */
    private void drop(Command command) 
	{
        if (!command.hasSecondWord()) 
		{
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }

        String item = command.getSecondWord();
        int i = items.indexOf(item);
        if (i == -1) 
		{
            System.out.println("You don't have the " + item);
            return;
        }
        items.remove(i);
        int w = (Integer) weights.remove(i);
        currentRoom.addItem(item, w);
        totalWeight -= w;
    }

    /**
     * Try to drop an item, otherwise print an error message.
     */
    private void give(Command command) 
	{
        if (!command.hasSecondWord()) 
		{
            // if there is no second word, we don't know what to give...
            System.out.println("Give what?");
            return;
        }
        if (!command.hasThirdWord()) 
		{
            // if there is no third word, we don't know to whom to give it...
            System.out.println("Give it to who?");
            return;
        }

        String item = command.getSecondWord();
        String whom = command.getThirdWord();

        if (!currentRoom.getcharacter().equals(whom)) 
		{
            // cannot give it if the chacter is not here
            System.out.println(whom + " is not in the room");
            return;
        }
        int i = items.indexOf(item);
        if (i == -1) 
		{
            System.out.println("You don't have the " + item);
            return;
        }
        items.remove(i);
        int w = (Integer) weights.remove(i);
        totalWeight -= w;
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
	{
        if (command.hasSecondWord()) 
		{
            System.out.println("Quit what?");
            return false;
        } 
		else 
		{
            return true;  // signal that we want to quit
        }
    }
}
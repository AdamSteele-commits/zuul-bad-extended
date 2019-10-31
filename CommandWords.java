import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class CommandWords
{
    private HashMap<String, CommandWord> commands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // a constant array that holds all valid command words 
    // if you can be bothered to change a bunch of stuff
    // then this could (and maybe should...) become an enum
    // private static final String[] VALID_COMMANDS = 
	// {
    //     "go", "quit", "help", "look", "take", "drop", "give", "test"
    // };
        commands = new HashMap<>();
        for(CommandWord command : CommandWord.values()) {
            if (command != CommandWord.UNKNOWN){
                commands.put(command.toString(), command);
            }
        }
    }

    public CommandWord getCommand(String commandWord){
        CommandWord command = commands.get(commandWord);
        if(command != null) {
            return command;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return commands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll()
    {
        for(String command : commands.keySet()) {
            System.out.print(command + " ");
        }
        System.out.println();
    }
}

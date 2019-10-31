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
    // a constant array that holds all valid command words 
    // if you can be bothered to change a bunch of stuff
    // then this could (and maybe should...) become an enum
    private static final String[] VALID_COMMANDS = 
	{
        "go", "quit", "help", "look", "take", "drop", "give", "test"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        /*
        validCommands = new HashMap<>();
     validCommands.put("go", CommandWord.GO);
      validCommands.put("help", CommandWord.HELP);
      validCommands.put("quit", CommandWord.QUIT);
*/
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < VALID_COMMANDS.length; i++)
		{
            if(VALID_COMMANDS[i].equals(aString))
			{
                return true;
			}
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    public String[] getValidCommands() 
    {
		return VALID_COMMANDS;
    }
/**
 * Print all valid commands to System.out.
 */
public void showAll()
{
 for(String command : VALID_COMMANDS) {
 System.out.print(command + " ");
 }
 System.out.println();
}
}

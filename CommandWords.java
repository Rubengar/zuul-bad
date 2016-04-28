import java.util.*;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private HashMap<String,Option> validCommands;
    private Option[] comando;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();        
        comando = Option.values();
        String[] comandos = {"al", "salir", "ayuda", "look", "eat","back","take","drop","items"};
        for (int i=0;i<comandos.length;i++)
        {
            validCommands.put(comandos[i],comando[i]);
        }        
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        // if we get here, the string was not found in the commands
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        String info = "los comandos que puesdes usar son:";
        for(int i = 0; i < comando.length; i++)
        {
            info += "|"+comando[i];
        }
        System.out.println(info.toLowerCase());
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord)
    {
        Option comando = Option.UNKNOWN;
        if (isCommand(commandWord))
        {
            comando = validCommands.get(commandWord);
        }
        return comando;
    }
}

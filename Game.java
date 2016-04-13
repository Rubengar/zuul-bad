/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room celda, pasillo, otraCelda, comedor, gimnasio, patio, entrada, salida;

        // create the rooms
        celda = new Room("en tu celda");
        otraCelda = new Room("en otra celda, vaya escapista que esta hecho");
        comedor = new Room("en el comedor,no te pares a tomar algo que te conozco");
        pasillo = new Room("en el pasillo, muy bien!!! vamos avanzando");
        gimnasio = new Room("en el gimnasio ponte a hacer ejercicio que lo necesitas");
        patio = new Room("en el patio, donde todo el mundo te puede ver y nadie sospechar�");
        entrada = new Room("en la entrada, no te lo crees ni tu");
        salida = new Room("la salida!!!, lo has consegido , eres libre, corre y no mires atras");

        // initialise room exits
        celda.setExits("norte",pasillo);
        otraCelda.setExits("sur",pasillo);
        otraCelda.setExits("sureste",comedor);
        pasillo.setExits("norte",otraCelda);
        pasillo.setExits("sur",celda);
        pasillo.setExits("este",comedor);
        comedor.setExits("norte",patio);
        comedor.setExits("este",gimnasio);
        comedor.setExits("noroeste",otraCelda);
        comedor.setExits("oeste",pasillo);
        gimnasio.setExits("oeste",comedor);
        gimnasio.setExits("noroeste",patio);
        patio.setExits("este",entrada);
        patio.setExits("sureste",gimnasio);
        patio.setExits("sur",comedor);
        entrada.setExits("este",salida);
        entrada.setExits("oeste",patio);
        salida.setExits("oeste",entrada);

        currentRoom = celda;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Gracias por jugar, adios.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenidos a la carcel");
        System.out.println("Este es un juego que consiste en escapar,podras consegirlo?");
        System.out.println("Escribe (ayuda) si necesitas ayuda.");
        System.out.println();
        printLocalInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("No se que quieres decir..");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ayuda")) {
            printHelp();
        }
        else if (commandWord.equals("al")) {
            goRoom(command);
        }
        else if (commandWord.equals("salir")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Estas en una carcel y tienes que escapar");
        System.out.println();
        System.out.println("los comandos que puesdes usar son:");
        System.out.println("   al(norte,sur,este,oeste), salir, ayuda");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("ir a donde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if(nextRoom == null) {
            System.out.println("Si puedes atravesar la pared.., adelante");
        }
        else {
            currentRoom = nextRoom;
            printLocalInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printLocalInfo()
    {
        System.out.println(currentRoom.getExitString());
    }
}

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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
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
        patio = new Room("en el patio, donde todo el mundo te puede ver y nadie sospechará");
        entrada = new Room("en la entrada, no te lo crees ni tu");
        salida = new Room("la salida!!!, lo has consegido , eres libre, corre y no mires atras");

        // initialise room exits
        celda.setExit("norte",pasillo);
        otraCelda.setExit("sur",pasillo);
        otraCelda.setExit("sureste",comedor);
        pasillo.setExit("norte",otraCelda);
        pasillo.setExit("sur",celda);
        pasillo.setExit("este",comedor);
        comedor.setExit("norte",patio);
        comedor.setExit("este",gimnasio);
        comedor.setExit("noroeste",otraCelda);
        comedor.setExit("oeste",pasillo);
        gimnasio.setExit("oeste",comedor);
        gimnasio.setExit("noroeste",patio);
        patio.setExit("este",entrada);
        patio.setExit("sureste",gimnasio);
        patio.setExit("sur",comedor);
        entrada.setExit("este",salida);
        entrada.setExit("oeste",patio);
        salida.setExit("oeste",entrada);
        celda.addItem("Mesa",12F);
        celda.addItem("Cama",12F);
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
        else if (commandWord.equals("look")) 
        {
            System.out.println(currentRoom.getLongDescription());
        }
        else if (commandWord.equals("eat"))
        {
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else if(commandWord.equals("back"))
        {
            int limite = 0;
            if (previousRoom != null && limite != 2)
            {
                currentRoom = previousRoom;
                System.out.println(currentRoom.getLongDescription());
                limite++;
            }else
            {
                System.out.println("Error no puedes volver atras");
            }

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
        parser.showCommands();
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
            previousRoom = currentRoom;
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
        System.out.println(currentRoom.getLongDescription());
    }
}

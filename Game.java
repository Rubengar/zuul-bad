import java.util.*;
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
    private Player jugador;
    private Policia policia;
    private Craft creador;
    private ArrayList<Room> habitacionesPolicia;
    private Room inicio;
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
        creador = new Craft();

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
        celda.addItem(new Item ("Palo",5F,true));
        celda.addItem(new Item("Madera",5F,true));
        celda.addItem(new Item("Metal",5F,true));

        habitacionesPolicia = new ArrayList<>();

        habitacionesPolicia.add(otraCelda);
        habitacionesPolicia.add(comedor);
        habitacionesPolicia.add(pasillo);
        habitacionesPolicia.add(gimnasio);
        habitacionesPolicia.add(patio);

        inicio = celda;

        policia = new Policia(habitacionesPolicia);
        jugador = new Player(inicio);
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
        System.out.println("Escribe ("+Option.AYUDA.getComando()+") si necesitas ayuda.");
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

        Option commandWord = command.getCommandWord();
        switch (commandWord) {
            case AYUDA:
            printHelp();
            break;
            case AL:
            goRoom(command);
            break;
            case SALIR:
            wantToQuit = quit(command);
            break;
            case LOOK:
            System.out.println(jugador.getPlayerRoom().getLongDescription());
            break;
            case EAT:
            System.out.println("You have eaten now and you are not hungry any more");
            break;
            case BACK:;
            returnRoom();
            break;
            case TAKE:
            takeItem(command);
            break;
            case DROP:
            dropItem(command);
            break;
            case ITEMS:
            jugador.infoItems();
            break;
            case CRAFT:
            crearObjeto(command);
            break;
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
        Room nextRoom = jugador.getPlayerRoom().getExit(direction);

        if(nextRoom == null) {
            System.out.println("Si puedes atravesar la pared.., adelante");
        }
        else {
            jugador.addRoom(jugador.getPlayerRoom());
            jugador.movePlayer(nextRoom);
            policia.moverse();
            if (jugador.getPlayerRoom().getDescription()== policia.getRoomPolicia().getDescription())
            {
                if (jugador.haveItem("cuchillo"))
                {
                    System.out.println("- A donde te crees que vas!!!!,anda pa tu celda");
                    System.out.println("+ Yo? , a salir de aqui cueste lo que cueste");
                    System.out.println("- Suelta ese cuchillo ahora mismo!!!");
                    System.out.println("-nooooo........!!");
                    System.out.println("+ he dicho cueste lo que cueste..");
                    policia = null;
                    jugador.dropItem(jugador.getItem("cuchillo"));
                    System.out.println("enhorabuena te has librado del policia ahora tienes camino libre");

                }else
                {
                    System.out.println("- A donde te crees que vas!!!!,anda pa tu celda");
                    jugador.setRoom(inicio);
                    jugador.eliminarObjetos();

                }
            }
            printLocalInfo();
        }
    }

    private void crearObjeto(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            creador.puedeCrear(jugador);
            return;
        }
        creador.crearObjeto(command.getSecondWord(),jugador);
    }

    /**
     * Metodo que añade el item al jugador y lo elimina de la habitacion
     */
    private void takeItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Coger que?");
            return;
        }
        Item item = jugador.getPlayerRoom().getItems(command.getSecondWord());
        if (item == null)
        {
            System.out.println("No existe ese objeto");
            return;
        }
        else if (!item.disponible())
        {
            System.out.println("No puedes coger ese objeto");
            return;
        }
        else
        {
            jugador.getPlayerRoom().removeItem(jugador.takeItem(item));
            System.out.println("Cogiste " + command.getSecondWord()) ;
        }
    }

    /** 
     * Metodo que añade el item al la habitacion  y lo elimina de jugador
     */
    private void dropItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Dejar que?");
            return;
        }
        Item item = jugador.getItem(command.getSecondWord());
        if (item ==null)
        {
            System.out.println("No existe ese objeto");
            return;
        }else
        {
            jugador.getPlayerRoom().addItem(jugador.dropItem(item));
            System.out.println("Dejaste "+ command.getSecondWord());
        }
    }

    /**
     * Metodo que muestra por pantalla la informacion de la habitacion anterior.
     */
    private void returnRoom()
    {
        if (jugador.puedeVolver())
        {
            System.out.println("Error no puedes volver atras");
        }else
        {
            Room room = jugador.removeRoom();
            jugador.movePlayer(room);
            System.out.println(jugador.getPlayerRoom().getLongDescription());
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
        System.out.println(jugador.getPlayerRoom().getLongDescription());
    }
}

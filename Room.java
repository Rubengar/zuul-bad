import java.util.*;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    public String description;
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;
    public Room southEastExit;
    public Room northWestExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room norte, Room este, Room sur, Room oeste,Room sureste,Room noroeste) 
    {
        if(norte != null)
            northExit = norte;
        if(este != null)
            eastExit = este;
        if(sur != null)
            southExit = sur;
        if(oeste != null)
            westExit = oeste;
        if(sureste != null)
            southEastExit = sureste;
        if(noroeste != null)
            northWestExit = noroeste;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direccion)
    {
        Room habitacion = null;
        if(direccion.equals("norte")) {
            habitacion = northExit;
        }
        if(direccion.equals("este")) {
            habitacion = eastExit;
        }
        if(direccion.equals("sur")) {
            habitacion = southExit;
        }
        if(direccion.equals("oeste")) {
            habitacion = westExit;
        }
        if(direccion.equals("sureste")) {
            habitacion = southEastExit;
        }
        if(direccion.equals("noroeste")) {
            habitacion = northWestExit;
        }
        return habitacion;  
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String descripcion = "Estas " + getDescription();
        descripcion += " Salidas: ";
        if(getExit("norte") != null) {
            descripcion +="norte ";
        }
        if(getExit("este") != null) {
           descripcion +="este ";
        }
        if(getExit("sur") != null) {
            descripcion +="sur ";
        }
        if(getExit("oeste") != null) {
            descripcion +="oeste ";
        }
        if(getExit("sureste") != null) {
            descripcion +="sureste ";
        }
        if(getExit("noroeste") != null) {
            descripcion +="noroeste ";
        }
        return descripcion;
    }
}

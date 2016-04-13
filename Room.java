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
    private String description;
    private HashMap<String,Room> salidas;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<>();
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
            salidas.put("norte",norte);
        if(este != null)
            salidas.put("este",este);
        if(sur != null)
            salidas.put("sur",sur);
        if(oeste != null)
            salidas.put("oeste",oeste);
        if(sureste != null)
            salidas.put("sureste",sureste);
        if(noroeste != null)
            salidas.put("noroeste",noroeste);
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
            habitacion = salidas.get("norte");
        }
        if(direccion.equals("este")) {
            habitacion = salidas.get("este");
        }
        if(direccion.equals("sur")) {
            habitacion = salidas.get("sur");
        }
        if(direccion.equals("oeste")) {
            habitacion = salidas.get("oeste");
        }
        if(direccion.equals("sureste")) {
            habitacion = salidas.get("sureste");
        }
        if(direccion.equals("noroeste")) {
            habitacion = salidas.get("noroeste");
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

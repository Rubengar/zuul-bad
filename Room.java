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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String,Room> salidas;
    private ArrayList<Item> objetos;
    private Item item;

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
        objetos = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor) 
    {
        salidas.put(direction,neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * Devuelve la habitacion segun la direccion.
     */
    public Room getExit(String direccion)
    {
        return salidas.get(direccion);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String descripcion = " Salidas: ";
        Iterator it = salidas.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            descripcion += (e.getKey() + " ");
        }
        return descripcion;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String descripcion = ("Estas " + getDescription()+"\n");    
        descripcion += getExitString()+"\n";        
        if (!objetos.isEmpty())
        {
            descripcion+= ("objetos disponibles"+"\n");
            for (Item item : objetos)
            {
                descripcion += (item.getDescripcion()+ " Peso: "+item.getPeso()+"Kg"+"\n");
            }       
        }
        else
        {
            descripcion += ("No hay objetos en esta habitacion");
        }
        return descripcion;
    }

    /**
     * Permite añadir un objeto a la habitación
     */
    public void addItem(String descripcion,float peso)
    {
        objetos.add(item = new Item(descripcion,peso));

    }    
    /**
     * Añade  un objeto pasado por parametro
     */
    public void addItems(Item item)
    {
         objetos.add(item);
    }
    /**
     * Elimina un objeto de la habitacion
     */
    public void removeItem(Item item)
    {
        objetos.remove(item);
    }
    /**
     * Metodo que busca un objeto por la descripcion y lo devuelve
     */
    public Item getItems(String descripcion)
    {
        for (Item item : objetos)
        {
            if (item.getDescripcion().equals(descripcion))
            {
                return item;
            }
        }
        return null;
    }
}

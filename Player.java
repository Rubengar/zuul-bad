import java.util.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> objetos;

    private  static final float PESO_MAXIMO = 20f;;

    private float pesoTotal;

    private Room habitacion;
    
    public Stack <Room> lista;

    /**
     * Constructor for objects of class Player
     */
    public Player(Room habitacion)
    {
        this.habitacion = habitacion;
        pesoTotal = 0F;
        lista = new Stack<>();
        objetos = new ArrayList<>();
    }

    /**
     * Metodo que añade un objeto al jugador y lo devuelve
     */
    public Item takeItem(Item item)
    {
        if(pesoTotal < PESO_MAXIMO - item.getPeso())
        {
            objetos.add(item);
            pesoTotal += item.getPeso();
            return item;
        }else
        {
            System.out.println("No puedes coger mas objetos");
        }
        return null;
    }

    /**
     * Metodo que elimina un objeto del jugador y lo devuelve
     */
    public Item dropItem(Item item)
    {        
        if (objetos.isEmpty())
        {
            System.out.println("Ya no tiene objetos");
        }else
        {
            pesoTotal -= item.getPeso();
            objetos.remove(item);
            return item;
        } 
        return null;
    }

    /**
     * Metodo que devuelve la habitacion en la que se encuentra el jugador
     */
    public Room getPlayerRoom()
    {
        return habitacion;
    }

    /**
     * Metodo que cambia al jugador de habitacion
     */
    public void movePlayer(Room room)
    {
        habitacion = room;
    }
    /**
     * Devuelve el item segun la descripcion
     */
    public Item getItem(String descripcion)
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
    /**
     * Muestra la descripcion de los objetos que tiene el jugador
     */
    public void infoItems()
    {
        if(objetos.isEmpty()) {
            System.out.println("No tiene ningun objeto");
        }else{
            for (Item item : objetos)
            {
                System.out.print(item);
            }
        }
    }
}

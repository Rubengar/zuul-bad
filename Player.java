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
    
    private Stack <Room> habitaciones;

    /**
     * Constructor for objects of class Player
     */
    public Player(Room habitacion)
    {
        this.habitacion = habitacion;
        pesoTotal = 0F;
        habitaciones = new Stack<>();
        objetos = new ArrayList<>();
    }

    /**
     * Metodo que a�ade un objeto al jugador y lo devuelve
     */
    public Item takeItem(Item item)
    {
        Item objeto = null;
        if(pesoTotal < PESO_MAXIMO - item.getPeso())
        {
            objetos.add(item);
            pesoTotal += item.getPeso();
            objeto = item;
        }else
        {
            System.out.println("No puedes coger mas objetos");
        }
        return objeto;
    }

    /**
     * Metodo que elimina un objeto del jugador y lo devuelve
     */
    public Item dropItem(Item item)
    {        
        Item objeto = null;
        if (objetos.isEmpty())
        {
            System.out.println("Ya no tiene objetos");
        }else
        {
            pesoTotal -= item.getPeso();
            objetos.remove(item);
            objeto = item;
        } 
        return objeto;
    }

    /**
     * Metodo que devuelve la habitacion en la que se encuentra el jugador
     */
    public Room getPlayerRoom()
    {
        return habitacion;
    }
    public void setRoom(Room room)
    {
        habitacion = room;
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
        Item objeto = null;
        for (Item item : objetos)
        {
            if (item.getDescripcion().equals(descripcion))
            {
                objeto = item;
            }
        }
        return objeto;
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
    /**
     * A�ade una habitacion al Stack
     */
    public void addRoom(Room room)
    {
        habitaciones.push(room);
    }
    /**
     * Elimina la habitacion del Stack y la devuelve
     */
    public Room removeRoom()
    {
        return habitaciones.pop();
    }
    /**
     * Devuelve true si esta vacio el Stack
     */
    public boolean puedeVolver()
    {
     return habitaciones.isEmpty();
    }
    /**
     * Metodo que elimina los objetos del arraylist
     */
    public void eliminarObjetos()
    {
        ArrayList<Item> vacio = new ArrayList<>();
        objetos = vacio;
    }
    /**
     * Comprueba si el jugador tiene un objeto
     */
    public boolean haveItem(String descripcion)
    {
        boolean existe = false;
        for (Item item : objetos)
        {
            if (item.getDescripcion().equals(descripcion))
            {
                existe = true;
            }
        }
        return existe;
    }
}

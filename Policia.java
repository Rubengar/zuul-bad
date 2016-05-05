import java.util.*;
/**
 * Write a description of class Policia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Policia
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private ArrayList<Room> habitaciones;
    /**
     * Constructor for objects of class Policia
     */
    public Policia()
    {
        Room celda, pasillo, otraCelda, comedor, gimnasio, patio, entrada, salida;

        // create the rooms
        otraCelda = new Room("en otra celda, vaya escapista que esta hecho");
        comedor = new Room("en el comedor,no te pares a tomar algo que te conozco");
        pasillo = new Room("en el pasillo, muy bien!!! vamos avanzando");
        gimnasio = new Room("en el gimnasio ponte a hacer ejercicio que lo necesitas");
        patio = new Room("en el patio, donde todo el mundo te puede ver y nadie sospechará");


        // initialise room exits
        otraCelda.setExit("sur",pasillo);
        otraCelda.setExit("sureste",comedor);
        pasillo.setExit("norte",otraCelda);
        pasillo.setExit("este",comedor);
        comedor.setExit("norte",patio);
        comedor.setExit("este",gimnasio);
        comedor.setExit("noroeste",otraCelda);
        comedor.setExit("oeste",pasillo);
        gimnasio.setExit("oeste",comedor);
        gimnasio.setExit("noroeste",patio);
        patio.setExit("sureste",gimnasio);
        patio.setExit("sur",comedor);
        //guarda las habitaciones en las que puede estar el policia
        habitaciones.add(otraCelda);
        habitaciones.add(comedor);
        habitaciones.add(pasillo);
        habitaciones.add(gimnasio);
        habitaciones.add(patio);
        Collections.shuffle(habitaciones);
    }

}

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
    public Policia(ArrayList<Room> habitaciones1)
    {
        habitaciones = new ArrayList<>();
        this.habitaciones = habitaciones1;
        //Desordena las habitaciones
        Collections.shuffle(habitaciones);
        //Seleciona la situacion actual del policia
        currentRoom = habitaciones.get(0);
    }

    /**
     * Devuelve la posición actual del policía
     */
    public Room getRoomPolicia()
    {
        return currentRoom;        
    }

    /**
     * 
     */
    public void moverse()
    {
        ArrayList<Room> posiblesSalidas = new ArrayList<>();
        
        ArrayList<Room> todasLasSalidas = new ArrayList<>();
        
        todasLasSalidas.add(currentRoom.getExit("norte"));
        todasLasSalidas.add(currentRoom.getExit("sur"));
        todasLasSalidas.add(currentRoom.getExit("este"));
        todasLasSalidas.add(currentRoom.getExit("oeste"));
        todasLasSalidas.add(currentRoom.getExit("noroeste"));
        todasLasSalidas.add(currentRoom.getExit("sureste"));

        for (Room habitacion: todasLasSalidas)
        {
            if(habitacion !=null)
            {
                posiblesSalidas.add(habitacion);
            }
        }
        
        Collections.shuffle(posiblesSalidas);
        currentRoom = posiblesSalidas.get(0);

    }

}

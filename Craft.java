
/**
 * Write a description of class Craft here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Craft
{
    // instance variables - replace the example below with your own
    private Item objeto1;
    
    private Item objeto2;

    /**
     * Constructor for objects of class Craft
     */
    public Craft()
    {
        // initialise instance variables
        objeto1 = new Item ("martillo",10f,true);
        objeto2 = new Item ("cuchillo",10f,true);
    }

    /**
     * Metodo que añade el nuevo objeto al jugador y elimana los utilizados para su creacion.
     */
    public void crearObjeto(String descripcion,Player jugador)
    {
        if (objeto1.getDescripcion().equals(descripcion))
        {
            if(jugador.haveItem("Palo") && jugador.haveItem("Madera"))
            {
                jugador.dropItem(jugador.getItem("Palo"));
                jugador.dropItem(jugador.getItem("Madera"));
                jugador.takeItem(objeto1);
            }else
            {
                System.out.println("Te faltan objetos para la creación");
            }
        }
        else if(objeto2.getDescripcion().equals(descripcion))
        {
            if(jugador.haveItem("Palo") && jugador.haveItem("Trozo de metal"))
            {
                jugador.dropItem(jugador.getItem("Palo"));
                jugador.dropItem(jugador.getItem("Trozo de metal"));
                jugador.takeItem(objeto2);
            }else
            {
                System.out.println("Te faltan objetos para la creación");
            }
        }
        else
        {
            System.out.println("No eres Dios, no puedes crear lo que quieras!!!");
        }
    }
    /**
     * Metodo que le da informacion al jugador de los objetos que puede crear
     */
    public void puedeCrear(Player jugador)
    {
        if (jugador.haveItem("Palo") && jugador.haveItem("Madera"))
        {
            System.out.println("Si lo desea puedes crear un martillo");
        }
        else if (jugador.haveItem("Palo")&& jugador.haveItem("Trozo de metal"))
        {
            System.out.println("Puedes crear un cuchillo");
        }
        else
        {
            System.out.println("No puedes crear nada con los objetos que tienes");
        }
    }
}

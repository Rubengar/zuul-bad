
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private float peso;

    private String descripcion;
    
    private boolean disponible;

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion , float peso,boolean disponible)
    {
        this.descripcion = descripcion;
        this.disponible = disponible;
        this.peso = peso;
    }

    /**
     * Metodo que te devuelve la descripcion del objeto
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Devuelve el peso del objeto
     */
    public float getPeso()
    {
        return peso;
    }
    /**
     * Devuelve la informacion del objeto
     */
    public String toString()
    {
        return descripcion + "Peso: " + peso+ "kg\n";
    }
    /**
     * Devuelve un valor booleano
     */
    public boolean disponible()
    {
        return disponible;
    }
}

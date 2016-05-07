
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    AL("al"),
    SALIR("salir"),
    AYUDA("ayuda"),
    LOOK("look"), 
    EAT("eat"), 
    BACK("back"), 
    TAKE("take"),
    DROP("drop"), 
    ITEMS("items"),
    CRAFT("crear"),
    UNKNOWN(null);
    private String comando;
    Option(String comando)
    {
        this.comando = comando;
    }
    /**
     * metodo que te devuelve el comando
     */
    public String getComando()
    {
        return comando;
    }
}

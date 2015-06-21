
/**
 * Write a description of class Node here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lista
{
    // instance variables - replace the example below with your own
    private Object x;
    private Lista proximo;

    /**
     * Constructor for objects of class Node
     */
    public Lista()
    {

    }
    
    public Lista(String args)
    {
        this.x = args;
    }

    public void setX(Object _x){
        this.x = _x;
    }
    
    public Object getX(){
        return this.x;
    }
    
    public void setProximo(Lista _proximo){
        this.proximo = _proximo;
    }
    
    public Lista getProximo(){
        return this.proximo;
    }
    
}

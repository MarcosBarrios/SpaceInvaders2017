
/**
 * Contiene coordenadas x e y
 * 
 * @author M. B
 * @version 1.0
 */
public class Coordenadas{
    private int x, y;

    /**
     * Constructor for objects of class Coordenadas
     */
    public Coordenadas(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
}

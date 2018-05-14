import java.awt.*;
import javax.swing.*;

/**
 * Clase encargada de la parte gráfica de los proyectiles, utilizada
 * por el renderizador.
 * 
 * @author M. B.
 * @version 1.0
 */
public class GUIProyectil{
    
    private Color c;
    private int posx, posy, tamañox, tamañoy;
    
    /**
     * Constructor for objects of class GUIProyectil
     */
    public GUIProyectil(int posx, int posy, int tamañox, int tamañoyr){
        c = new Color(43, 234, 212);
    }

    public Color color(){
        return c;
    }
    
    public int getX(){
        return posx;
    }
    
    public int getY(){
        return posy;
    }
    
    public int getLargo(){
        return tamañox;
    }

    public int getAlto(){
        return tamañoy;
    }
    
}

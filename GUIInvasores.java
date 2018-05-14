import java.awt.*;
import javax.swing.*;

/**
 * Esta clase contiene básicamente el renderizado para los invasores del juego
 * 
 * @author M. B.
 * @version 1.0
 */
public class GUIInvasores{
    
    private Color c;
    
    private int posx, posy, tamañox, tamañoy;
    
    /**
     * Constructor for objects of class GUIProyectil
     */
    public GUIInvasores(int posx, int posy, int tamañox, int tamañoyr){
        c = new Color(147, 244, 36);
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


import javax.swing.Timer;
import java.awt.event.*;
/**
 * Clase encargada de proveer los sistemas de tiempo para el juego (bucle principal y para invasores)
 * 
 * @author M. B.
 * @version 1.0
 */
public class Contadores{
    private Timer tmrPrincipal, tmrInvasores, tmrEntradas;
    
    public Contadores(int tPrincipal, ActionListener aPrincipal, int tInvasores, ActionListener aInvasores){
        tmrPrincipal = new Timer(tPrincipal, aPrincipal);
        tmrInvasores = new Timer(tInvasores, aInvasores);
    }
    
    /**
     * Inicia el contador principal del juego
     */
    public void iniciarPrincipal(){
        tmrPrincipal.start();
    }
    
    /**
     * Inicia el contador encargado del movimiento de los invasores
     */
    public void iniciarInvasores(){
        tmrInvasores.start();
    }
    
    /**
     * Para el contador principal
     */
    public void pararPrincipal(){
        tmrPrincipal.stop();
    }
    
    /**
     * Para el contador encargado del movimiento de los invasores
     */
    public void pararInvasores(){
        tmrInvasores.stop();
    }
    
    /**
     * Cambia el retardo del contador principal
     */
    public void cambiarTmpPrincipal(int nuevoTiempo){
        tmrPrincipal.setDelay(nuevoTiempo);
    }
    
    /**
     * Cambia el retardo del contador de los invasores
     */
    public void cambiarTmpInvasores(int nuevoTiempo){
        tmrInvasores.setDelay(nuevoTiempo);
    }
    
    /**
     * Devuelve le retardo del bucle principal
     * @return retardo contador principal
     */
    public int tiempoPrincipal(){
        return tmrPrincipal.getDelay();
    }
    
    /**
     * Devuelve le retardo del bucle de los invasores
     * @return retardo contador movimiento invasores
     */
    public int tiempoInvasores(){
        return tmrInvasores.getDelay();
    }
    
}

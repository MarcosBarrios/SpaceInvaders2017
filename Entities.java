import java.util.ArrayList;
import java.util.Iterator;

/**
 * Esta clase contiene todos los elementos del juego.
 * 
 * @author M. B. 
 * @version 1.0
 */
public class Entities{
    private EntityNave jugador;
    
    private ArrayList<Entity> elementos;
    
    private int nInvasores, espaciado, largoRenderizador, altoRenderizador;
    private int nInvasoresX, nInvasoresY;
    
    /**
     * Constructor for objects of class Entities
     */
    public Entities(int nInvasores, int largoRenderizador, int altoRenderizador){
        this.nInvasores = nInvasores;
        this.largoRenderizador = largoRenderizador;
        this.altoRenderizador = altoRenderizador;
        espaciado = 25;
        
        elementos = new ArrayList<Entity>();
        
        crearElementos();
    }
    
    public void crearElementos(){
        jugador = new EntityNave((largoRenderizador/2 - 25), altoRenderizador-25, 25, 25, this);
        añadirElemento(jugador);
        
        nInvasoresX = largoRenderizador / (25+espaciado);
        nInvasoresY = altoRenderizador / (25+espaciado);
        //System.out.println("nInvasoresX: " + nInvasoresX + " nInvasoresY: " + nInvasoresY);
        
        int nInvasoresRenderizados = 0;
        for(int y = 0; y < nInvasoresY; y++){
            for(int x = 0; x < nInvasoresX; x++){
                if(nInvasoresRenderizados < nInvasores){
                    añadirElemento(new EntityInvasor((25+espaciado)*x+6, (25+espaciado)*y+6, 25, 25, this));   
                }
                nInvasoresRenderizados++;
            }
            
        }

    }
    
    public void reiniciar(){
        /*for(int i = 0; i < tamaño(); i++){
            Entity temp = elemento(i);
            if(temp instanceof EntityViva){
                EntityViva ev = (EntityViva) elemento(i);
                if(!ev.activo()){
                   ev.cambiarEstado(true);
                }
            }
        }*/
        
        for(int i = 0; i < elementos.size(); i++){
            elementos.remove(i);
        }
        
        crearElementos();
    }
    
    public void añadirElemento(Entity e){
        elementos.add(e);
    }
    
    public void eliminarElemento(int id){
        elementos.remove(id);
    }
    
    public Entity elemento(int id){
        return elementos.get(id);
    }
    
    public int tamaño(){
        return elementos.size();
    }
    
    public EntityNave jugador(){
        return this.jugador;
    }
}

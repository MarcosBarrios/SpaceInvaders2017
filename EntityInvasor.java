
/**
 * Clase para los invasores del juego.
 * 
 * @author M. B.
 * @version 1.0
 */
public class EntityInvasor extends EntityViva{
    
    private boolean movimientoVertical = false;
    
    public EntityInvasor(int x, int y, int ancho, int alto, Entities e){
        super(x,y, ancho, alto, e);
    }
    
    /**
     * Implemente la manera en la que se mueve el elemento
     */
    public void moverse(){
        if(!movimientoVertical){
            setX(getX()+(velocidadX()*sentido()));
        }else{
            setY(getY()+1);
        }
    }
    
    /**
     * Comportamiento a la hora de disparar. Similar al del jugador.
     */
    public void disparar(){
        if(activo() && puedeDisparar()){
            Entities elementos = entities();
            EntityProyectil p = new EntityProyectil((getX()+(getAncho()/2)-EntityViva.LARGO_PROYECTIL/2),
                getY()+getAlto()+5, EntityViva.LARGO_PROYECTIL, EntityViva.ALTO_PROYECTIL, true, this);
            p.ponerDisparador(this);
            elementos.añadirElemento(p);
        }
    }
    
    public void setMovimientoVertical(boolean mv){
        this.movimientoVertical = mv;
    }
    
    public boolean movimientoVertical(){
        return this.movimientoVertical;
    }
}

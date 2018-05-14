
/**
 * Esta clase proporciona la lógica detrás de un proyectil.
 * 
 * @author M. B.
 * @version 1.0
 */
public class EntityProyectil extends Entity{
    
    //Velocidad con la que se mueve el proyectil
    private int velocidad;
    
    //Booleana encargada de la trayectoria del movimiento vertical
    private boolean movimientoDescendiente;
    
    //Devuelve el elemento con el que no va a colisionar el proyectil
    private EntityViva inmunidad;
    
    //Devuelve el elemento que ha disparado el proyectil
    private EntityViva disparador;
    
    public EntityProyectil(int x, int y, int ancho, int alto, boolean movimientoVertical, EntityViva inmunidad){
        super(x,y, ancho, alto);
        this.inmunidad = inmunidad;
        this.movimientoDescendiente = movimientoVertical;
        this.velocidad = 1;
    }
    
    //Constructor alternativo
    public EntityProyectil(int x, int y, int ancho, int alto, boolean movimientoVertical, EntityViva inmunidad,
        int velocidad){
        super(x,y, ancho, alto);
        this.inmunidad = inmunidad;
        this.movimientoDescendiente = movimientoVertical;
        this.velocidad = velocidad;
    }
    
    /**
     * Establece el disparador del proyectil
     * @param d disparador
     */
    public void ponerDisparador(EntityViva d){
        this.disparador = d;
    }
    
    /**
     * Devuelve el elemento que ha disparado el proyectil
     */
    public EntityViva disparador(){
        return disparador;
    }
    
    /**
     * Indica si el proyectil se mueve hacia arriba o hacia abajo
     */
    public boolean movimientoDescendiente(){
        return movimientoDescendiente;
    }
    
    /**
     * Cambia la trayectoria vertical del proyectil
     * @param descendiente hacia arriba o hacia abajo false/true
     */
    public void cambiarMovimiento(boolean descendiente){
        movimientoDescendiente = descendiente;
    }
    
    /**
     * Devuelve la velocidad a la que se mueve el proyectil.
     * Nota: en la implementación actual el movimiento se verá forzado si la velocidad
     * es mayor que 1
     */
    public int getVelocidad(){
        return velocidad;
    }
    
    /**
     * Permite cambiar la velocidad a la que se mueve el proyectil
     */
    public void setVelocidad(int nuevaVelocidad){
        this.velocidad = velocidad;
    }
    
    /**
     * Devuelve los elementos a los que no afecta el proyectil
     */
    public EntityViva inmunidad(){
        return inmunidad;
    }
    
    /**
     * Establece qué elementos no colisionarán con el proyectil
     * @param in Elemento hijo de EntityViva por necesidad de un método disparar()
     */
    public void setInmunidad(EntityViva in){
        this.inmunidad = in;
    }
    
    /**
     * Implementa el movimiento del proyectil
     */
    public void moverse(){
        if(activo()){
            if(movimientoDescendiente){
                setY(getY()+(1*velocidad));
            }else{
                setY(getY()-(1*velocidad));
            }
        }
    }
    
}

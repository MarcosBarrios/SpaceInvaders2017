
/**
 * Clase para la nave del jugador, controlada en tiempo real mediante las teclas
 *  O (movimiento izquierdo) y P (movimiento derecho)
 * 
 * @author M. B.
 * @version 1.0
 */
public class EntityNave extends EntityViva{
    
    public EntityNave(int x, int y, int ancho, int alto, Entities e){
        super(x,y, ancho, alto, e);
        cambiarSentido(EntityViva.PARADO);
        setVelocidadX(7);
    }
    
    /**
     * Método llamado para el movimiento del elemento
     */
    public void moverse(){
        //Si está activo se mueve según su sentido y su velocidad
        if(activo()){
            setX(getX()+(velocidadX()*sentido()));
        }
    }
    
    /**
     * Implementa el comportamiento de disparo del jugador
     */
    public void disparar(){
        //únicamente si puede disparar y está activo crea un proyectil encima de él y establece
        //el disparador del proyectil como él mismo además de la trayectoria vertical.
        //Añade el proyectil a la lista de elementos en Entities
        if(activo() && puedeDisparar()){
            Entities elementos = entities();
            EntityProyectil p = new EntityProyectil((getX()+(getAncho()/2)-EntityViva.LARGO_PROYECTIL/2),
                getY()-EntityViva.ALTO_PROYECTIL-5, EntityViva.LARGO_PROYECTIL, EntityViva.ALTO_PROYECTIL, false, this);
            p.ponerDisparador(this);
            elementos.añadirElemento(p);
        }
    }

}

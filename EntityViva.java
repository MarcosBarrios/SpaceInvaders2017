
/**
 * Clase abstracta para todos los elementos del videojuego que se mueve
 * 
 * @author M. B.
 * @version 1.0
 */
public abstract class EntityViva extends Entity{
    //Constantes para el sentido del movimiento de los elementos
    public static final int SENTIDO_DERECHO = 1;
    public static final int PARADO = 0;
    public static final int SENTIDO_IZQUIERDO = -1;
    public static final int LARGO_PROYECTIL = 6;
    public static final int ALTO_PROYECTIL = 12;
    
    //Es necesaria una variable que acceda a todos los elementos para implementar la lógica
    //de los proyectiles
    private Entities e;
    
    //Sentido inicial
    private int sentido = 1;
    
    private boolean puedeDisparar;

    /**
     * Constructor for objects of class EntityMovil
     */
    public EntityViva(int x, int y, int ancho, int alto, Entities e){
        super(x,y, ancho, alto);
        this.e = e;
        puedeDisparar = true;
    }
    
    /**
     * Indica si el elemento puede disparar o no
     */
    public boolean puedeDisparar(){
        return puedeDisparar;
    }
    
    /**
     * Establece el poder disparar o no
     * @param poderdisparar true puede disparar false no puede disparar
     */
    public void poderDisparar(boolean poderDisparar){
        puedeDisparar = poderDisparar;
    }
    
    /**
     * Establece el sentido en el que se moverá. Uso complementario con las constantes
     * establecidas en esta misma clase.
     */
    public int sentido(){
        return this.sentido;
    }
    
    /**
     * Permite cambiar el sentido en el que se moverá el elemento
     * @param nuevoSentido sentido (1, -1 o 0 según constantes)
     */
    public void cambiarSentido(int nuevoSentido){
        this.sentido = nuevoSentido;
    }
    
    /**
     * Devuelve la clase encargada de gestionar los elementos del juego
     */
    public Entities entities(){
        return e;
    }
    
    /**
     * Implementa el movimiento del elemento
     */
    public abstract void moverse();
    
    /**
     * Implementa el comportamiento a la hora de disparar
     */
    public abstract void disparar();

}

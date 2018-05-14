
/**
 * Esta clase es la base para todos los elementos del juego
 * 
 * @author M. B.
 * @version 1.0
 */
public abstract class Entity{
    //Clase con las posiciones x e y
    private Coordenadas coordenadas;
    
    //ancho y alto de cada elemento
    private int ancho, alto;
    //velocidad empleada por algunos elementos
    private int velocidadX, velocidadY;
    
    //indica si el elemento está activo o no
    private boolean activo;
    
    /**
     * Constructor
     */
    public Entity(int x, int y, int ancho, int alto){
        coordenadas = new Coordenadas(x, y);
        this.ancho = ancho;
        this.alto = alto;
        velocidadX = 1;
        velocidadY = 1;
        activo = true;
    }
    
    /**
     * Devuelve si el elemento está activo o no
     * @return activo estado del elemento
     */
    public boolean activo(){
        return activo;
    }
    
    /**
     * Cambia el estado del elemento
     * @param nuevo estado
     */
    public void cambiarEstado(boolean estaActivo){
        activo = estaActivo;
    }
    
    /**
     * Devuelve la coordenada X
     * 
     */
    public int getX(){
        return coordenadas.getX();
    }
    
    /**
     * Devuelve la coordenada Y
     * 
     */
    public int getY(){
        return coordenadas.getY();
    }
    
    /**
     * Devuelve la velocidad horizontal
     */
    public int velocidadX(){
        return this.velocidadX;
    }
    
    /**
     * Devuelve la velocidad vertical
     */
    public int velocidadY(){
        return this.velocidadY;
    }
    
    /**
     * Cambia la velocidad horizontal
     * @param nueva velocidad horizontal
     */
    public void setVelocidadX(int nuevaVX){
        this.velocidadX = nuevaVX;
    }
    
    /**
     * Cambia la velocidad vertical
     * @param nueva velocidad vertical
     */
    public void setVelocidadY(int nuevaVY){
        this.velocidadY = nuevaVY;
    }
    
    /**
     * Devuelve el ancho del elemento
     */
    public int getAncho(){
        return this.ancho;
    }
    
    /**
     * Devuelve el alto del elemento
     */
    public int getAlto(){
        return this.alto;
    }
    
    /**
     * Permite cambiar la posición horizontal del elemento
     * @param nueva posición horizontal
     */
    public void setX(int nuevoX){
        coordenadas.setX(nuevoX);
    }
    
    /**
     * Permite cambiar la posición vertical del elemento
     * @param nueva posición vertical
     */
    public void setY(int nuevoY){
        coordenadas.setY(nuevoY);
    }
    
    /**
     * Devuelve el ancho del elemento
     * @param tamaño horizontal del elemento
     */
    public void setAncho(int nuevoAncho){
        this.ancho = nuevoAncho;
    }
    
    /**
     * Devuelve el alto del elemento
     * @param tamaño vertical del elemento
     */
    public void setAlto(int nuevoAlto){
        this.alto = nuevoAlto;
    }
    
    /**
     * Método abstracto principal de las clases Entity. Proporcionan implementación
     * para el movimiento de cada elemento en el juego.
     */
    public abstract void moverse();
}

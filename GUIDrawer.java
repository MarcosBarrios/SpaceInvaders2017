import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Este es el componente que renderiza el juego. Utiliza la clase Graphics de 
 * java para dibujar en pantalla los elementos del juego. No realiza ningún tipo de
 * transformación, únicamente dibuja según la posición que cada elemento tenga
 * dentro de su clase lógica
 * 
 * @author Marcos B.
 * @version 1.0
 */
public class GUIDrawer extends JPanel{
    
    //Es necesario obtener la clase con todos los elementos del juego
    private Entities e;
    //Variable para el uso de graphics globalmente, permite llamadas externas
    private Graphics graphics;
    
    //Estado del renderizador
    private boolean activo;
    
    /**
     * Constructor for objects of class GUIDrawer
     */
    
    public GUIDrawer(int ancho, int alto, boolean activo, Entities e){
        this.activo = activo;
        this.e = e;
        
        setFocusable(true);//Activamos el focus en él para que las teclas se activen
        setFocusTraversalKeysEnabled(false); //Evitamos que se pueda presionar tab y otros para cambiar el foco
        setBackground(Color.DARK_GRAY); //Color de fondo del renderizador
        setPreferredSize(new Dimension(ancho, alto)); //Fijamos el tamaño
        
        //Implementamos un borde transparente puramente estético. Esto hará que se vea mejor cuando se implemente
        //la parte gráfica del juego alrededor de otros componentes
        setBorder(new CompoundBorder(new EmptyBorder(5,10,5,10), new EmptyBorder(3, 3, 3, 3)));
    }
    
    /**
     * Método encargado de dibujar el componente en pantalla
     * @param graphics de la clase padre componentes
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g); //llamamos al método padre a cada llamada para que se dibuje bien el panel
        this.graphics = g; //iniciamos nuestra variable global para su uso en otros métodos
        
        //Si el panel está activo dibuja los elementos del juego en pantalla
        if(activo){
            for(int i = 0; i < e.tamaño(); i++){
                Entity temp = e.elemento(i);
                if(temp.activo()){
                    
                    //En este caso todos los elementos son rectangulos de diferentes tamaños
                    //Cada clase GUI tiene su propio color
                    if(temp instanceof EntityInvasor){
                        GUIInvasores guii = new GUIInvasores(temp.getX(), temp.getY(), temp.getAncho(),
                            temp.getAlto());
                        g.setColor(guii.color());
                        g.fillRect(temp.getX(), temp.getY(), temp.getAncho(), temp.getAlto());
                        //g.drawString("(" + (temp.getX()+temp.getAncho())+ ","+ (temp.getY()+temp.getAlto()) + ")", 
                            //temp.getX()-10, temp.getY()+15);
                    }else if(temp instanceof EntityNave){
                        GUINave guin = new GUINave(temp.getX(), temp.getY(), temp.getAncho(),
                            temp.getAlto());
                        g.setColor(guin.color());
                        g.fillRect(temp.getX(), temp.getY(), temp.getAncho(), temp.getAlto());
                    }else if(temp instanceof EntityProyectil){
                        GUIProyectil guip = new GUIProyectil(temp.getX(), temp.getY(), temp.getAncho(),
                            temp.getAlto());
                        g.setColor(guip.color());
                        g.drawRect(temp.getX(), temp.getY(), temp.getAncho(), temp.getAlto());
                    }
                }
            }
        }else{ //Si no está activo se deja de renderizar y se imprime un mensaje indicando que el juego terminó
            g.setColor(Color.RED);
            g.drawString("Juego terminado", (getSize().width/2)-80, getSize().height/2);
        }
    }
    
    /**
     * Método encargado de actualizar gráficamente el panel
     */
    public void actualizar(){ //llamado junto con actualizar() de Juego.class
        repaint();
    }
    
    /**
     * Encargado de controlar el estado del panel.
     */
    public void cambiarEstado(boolean activo){
        this.activo = activo;
    }
    
    /**
     * Estado del panel
     * @return estado del panel, true = renderizando / false = terminado
     */
    public boolean getEstado(){
        return activo;
    }

}

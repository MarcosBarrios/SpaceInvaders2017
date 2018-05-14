import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * En esta clase se encuentra todo lo necesario para iniciar el programa.
 * Implementación de la interfaz gráfica básica (ventana) y el método main
 * de inicio del programa.
 * 
 * @author M. B.
 * @version 1.0
 */
public class SpaceInvaders extends JFrame{
    
    /**
     * Método principal del programa. Inicia directamente la interfaz gráfica del juego.
     */
    public static void main (String args[]){
        SpaceInvaders guij = new SpaceInvaders();
    }
    
    //Variable contenedora del juego y toda su lógica / parte gráfica incluida para
    //facilitar su uso.
    private Juego j;
    
    //Contenedor de los componentes de la ventana.
    private Container c;
    
    public SpaceInvaders(){
        //Creamos el juego. 30 es el número de invasores totales,
        //550 es el largo del renderizador (cercano al tamaño de la ventana)
        //y 320 es el alto.
        j = new Juego(30, 550, 320);
        
        //Se construye la interfaz que contendrá el renderizador del juego sobre un panel centrado.
        construirInterfaz();
    }
    
    /**
     * Construye la interfaz gráfica.
     */
    public void construirInterfaz(){
        //Inicia la configuración de la ventana.
        crearVentana();
        
        //Obtiene el contenedor respectivo de la ventana
        c = this.getContentPane();
        //Establece la plantilla, en este caso el renderizador estará en el medio 
        //así que BorderLayout funciona bien
        c.setLayout(new BorderLayout());
        //Establecemos el color de fondo de la ventana principal
        c.setBackground(Color.DARK_GRAY);
        //Incrustamos el renderizador en el centro de la ventana.
        añadirRenderizador(c);
    }   
    
    /**
     * Añade el renderizador a la ventana
     */
    public void añadirRenderizador(Container c){
        JPanel dcontainer = new JPanel();
        dcontainer.setBackground(Color.LIGHT_GRAY);
        dcontainer.add(j.renderizador());
        c.add(dcontainer, BorderLayout.CENTER);
    }
    
    /**
     * Configura la ventana
     */
    public void crearVentana(){
        setTitle("Space Invaders"); //Fijamos el título de la ventana
        setSize(new Dimension(560, 360)); //Establecemos el tamaño fijo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Nos aseguramos 
        //que se cierre el programa al presionar cerrar
        
        //Centra la ventana al abrir el programa.
        setLocationRelativeTo(null);
        setResizable(false); //Evita que se pueda cambiar su tamaño.
        setVisible(true); //La vuelve visible.
    }
    
    
}
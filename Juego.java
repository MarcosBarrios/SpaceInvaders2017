import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase base para el juego. Reune todos los componentes necesarios para su funcionamiento.
 * 
 * @author M.B 
 * @version 1.0
 */
public class Juego{
    
    //Renderizador del juego, componente principal
    private GUIDrawer guid;
    
    //Clase contenedora de todos los elementos del juego y su gestión
    private Entities elementos;
    
    //Encargada de todos los tiempos de actualización además del bucle principal
    private Contadores contadores;
    
    //Contador extra para los proyectiles de los invasores
    private Timer tPysInvasores;
    
    //Tamaño de la interfaz de juego
    private int largoRenderizador, altoRenderizador;

    //Clase principal del jugador
    private EntityNave jugador;
    
    //Indica si el juego sigue activo o no se ha perdido / ganado
    private boolean activo;
    
    /**
     * Constructor
     */
    public Juego(int nInvasores, int largoRenderizador, int altoRenderizador){
        //Iniciamos los elementos del juego antes que nada
        elementos = new Entities(nInvasores, largoRenderizador, altoRenderizador);
        //La clase del jugador está en Entities así que la obtenemos
        jugador = elementos.jugador();
        //Iniciamos el juego en activo
        activo = true;
        
        //Almacenamos el tamaño del renderizador en variables
        this.largoRenderizador = largoRenderizador;
        this.altoRenderizador = altoRenderizador;
        
        //Iniciamos el renderizador principal del juego, la booleana indica el estado inicial (activo o no)
        guid = new GUIDrawer(largoRenderizador, altoRenderizador, true, elementos);
        //Se le añade un key listener para la interacción del jugador
        guid.addKeyListener(new Interaccion());
        
        //Iniciamos la clase contenedora de los contadores. Indica los milisegundos que tardan
        //entre acción (el primero es el bucle principal, el segundo es el que se encarga del
        //movimiento de los invasores. 16 ms para lograr que el juego se actualize suavemente.
        contadores = new Contadores(16, new AccionPrincipal(), 70, new AccionInvasores());
        //Iniciamos el contador de los invasores
        contadores.iniciarInvasores();
        //Iniciamos el contador del bucle principal
        contadores.iniciarPrincipal();
        
        //Llamamos al método que inicia el contador para los proyectiles de los invasores
        proyectilesInvasores();
    }
    
    /**
     * Devuelve el estado del juego
     * @return activo
     */
    public boolean activo(){
        return activo;
    }
    
    /**
     * Cambia el estado del juego
     * @param activo - Si el juego está activo o no
     */
    
    public void cambiarEstado(boolean activo){
        this.activo = activo;
    }
    
    /**
     * Método principal del juego, es llamado por el contador principal cada 16 ms
     */
    public void calcular(){
        
        if(activo()){
            calcularEstado();
            logicaJugador();
            logicaProyectiles();
            guid.actualizar();
        }else{
            tPysInvasores.stop();
            contadores.pararInvasores();
            contadores.pararPrincipal();
            guid.cambiarEstado(false);
            guid.actualizar();
        }
    }
    
    /**
     * Contiene la lógica del movimiento del jugador y controla que no se salga
     * de la pantalla de juego. Llamado junto a calcular()
     */
    public void logicaJugador(){
        jugador.moverse();
        if((jugador.getX()+jugador.getAncho()) > largoRenderizador-1){
            jugador.setX(largoRenderizador-jugador.getAncho()-1);
        }else if(jugador.getX()<0){
            jugador.setX(0);
        }
    }
    
    /**
     * Lógica de las colisiones entre elementos del juego. Actualiza el movimiento de
     * los proyectiles y las acciones tras una colisión. Llamado junto a calcular()
     */
    public void logicaProyectiles(){
        ArrayList<EntityProyectil> proyectiles = Util.proyectilesActivos(elementos);
        for(EntityProyectil aux : proyectiles){
            if(aux.activo()){
                aux.moverse();
                EntityViva eColision = Util.colisionProyectil(aux, elementos);
                if(eColision!=null){
                    eColision.cambiarEstado(false);
                    aux.cambiarEstado(false);
                    EntityViva temp = aux.disparador();
                    temp.poderDisparar(true);
                }
            }
            
            if((aux.getY()+aux.getAlto())<0){
                EntityViva temp = aux.disparador();
                temp.poderDisparar(true);
                aux.cambiarEstado(false);
            }else if(aux.getY()>altoRenderizador){
                EntityViva temp = aux.disparador();
                temp.poderDisparar(true);
                aux.cambiarEstado(false);
            }
            
        }
    }
    
    /**
     * Inicia un contador que permite que un invasor dispare aleatoriamente cada
     * 4 segundos.
     */
    public void proyectilesInvasores(){
        ArrayList<EntityInvasor> invasores = Util.invasores(elementos);
        Random rnd = new Random();

        tPysInvasores = new Timer(4000, new ActionListener(){
           public void actionPerformed(ActionEvent e){
               int aux = rnd.nextInt(invasores.size());
               EntityInvasor temp = invasores.get(aux);
               
               temp.disparar();
           }
        });
        tPysInvasores.start();
    }
    
    /**
     * Comprueba si el juego ha de terminar o no, ya sea tras ganar o perder. 
     * Llamado en calcular()
     */
    public void calcularEstado(){
        if(juegoGanado() || juegoPerdido()){ 
            activo = false;
        }else{
            activo = true;
        }
    }
    
    /**
     * Devuelve verdadero cuando los invasores han llegado hasta la zona del jugador
     * Llamado indirectamente por calcular()
     * @return true si ha perdido false si ha no ha perdido todavía
     */
    public boolean juegoPerdido(){
        EntityInvasor invasorMaxY = Util.invasorMaxPosY(elementos);
        
        if(invasorMaxY.getY()+invasorMaxY.getAlto() >= altoRenderizador-40){
            System.out.println("Juego perdido!");
            return true;
        }else if(!jugador.activo()){
            System.out.println("Juego perdido!");
            return true;
        }
        return false;
    }
    
    /**
     * Devuelve verdadero cuando el jugador ha acabado con todos los invasores
     * Llamado indirectamente por calcular()
     * @return true si ha ganado false si no ha ganado todavía
     */
    public boolean juegoGanado(){
        int ninvasores = 0;
        for(int i = 0; i < elementos.tamaño(); i++){
            Entity temp = elementos.elemento(i);
            if(temp instanceof EntityViva){
                EntityViva ev = (EntityViva) elementos.elemento(i);
                if(ev.activo()){
                   ninvasores++; 
                }
            }
        }
        
        if(ninvasores == 0){
            System.out.println("Juego ganado!");
            return true;
        }
        return false;
    }
    
    /**
     * Método sin implementanción. Reinicia el juego.
     */
    public void reiniciar(){
        elementos.reiniciar();
        jugador.cambiarEstado(true);
        activo = true;
        contadores.iniciarInvasores();
        contadores.iniciarPrincipal();
        guid.cambiarEstado(true);
    }
    
    /**
     * Devuelve el renderizador del juego, método importante para el buen funcionamiento de este.
     * @return guid GUIDrawer renderizador gráficos del juego
     */
    public GUIDrawer renderizador(){
        return guid;
    }
    
    //Clase encargada de la interacción usuario - juego. Básicamente cuando el jugador
    //presiona la tecla O la nave va hacia la izquierda, cuando presiona P la nave va
    //hacia la derecha y cuando presiona espacio dispara.
    //La implementación usada se basa en una booleana que se activa cuando presiona alguna de
    //las teclas de movimiento. De esta manera se evitan los retrasos dependientes del sistema
    //operativo
    class Interaccion implements KeyListener{
        
        //private Timer tRetardo;
        //private int retardo = 0;
        
        /*public Interaccion(){
            tRetardo = new Timer(2000, new AccionRetardo());
        }*/
        
        public void keyPressed(KeyEvent k){
            int cod = k.getKeyCode();
            if(cod==KeyEvent.VK_SPACE){
                if(jugador.puedeDisparar()){
                    jugador.disparar();
                    jugador.poderDisparar(false);
                }
                //jugador.poderDisparar(false);
                //tRetardo.start();
            }else if(cod==KeyEvent.VK_O){
                jugador.cambiarSentido(EntityViva.SENTIDO_IZQUIERDO);
            }else if(cod==KeyEvent.VK_P){
                jugador.cambiarSentido(EntityViva.SENTIDO_DERECHO);
            }
            
            /*if(!activo()){
               if(cod==KeyEvent.VK_R){
                   System.out.println("r");
                   reiniciar();
               }
            }*/
        }
        
        public void keyTyped(KeyEvent k){}

        public void keyReleased(KeyEvent k){
            int cod = k.getKeyCode();
            
            if(cod==KeyEvent.VK_SPACE){
                //Dejar de disparar
            }else if(cod==KeyEvent.VK_O){
                jugador.cambiarSentido(EntityViva.PARADO);
            }else if(cod==KeyEvent.VK_P){
                jugador.cambiarSentido(EntityViva.PARADO);
            }
            
        }
        
        /*class AccionRetardo implements ActionListener{
            public void actionPerformed(ActionEvent ev){
                jugador.poderDisparar(true);
                tRetardo.stop();
            }
        }*/
    }
    
    //ActionListener del contador del bucle de juego. Se limita a actualizarse cuando
    //pasan 16 ms
    class AccionPrincipal implements ActionListener{
        public void actionPerformed(ActionEvent e){
            calcular();
        }
    }
    
    //ActionListener del contador del movimiento de los invasores.
    class AccionInvasores implements ActionListener{
        
        //Esta clase implementa el movimiento zig zag de los invasores utilizando
        //las constantes de las clases Entity además de enteros para retardo.
        //Cada vez que los invasores llegan al límite izquierdo o derecho bajan
        //5 veces para luego cambiar su sentido.
        private int sentido = EntityViva.SENTIDO_DERECHO;   
        private boolean mv = false;
        private int aux = 0;
        
        public void actionPerformed(ActionEvent e){
            if(guid.getEstado()){
                moverInvasores();
            }
        }
        
        public void moverInvasores(){
            
            //Llamada al método de búsqueda de la clase Util que obtiene el invasor
            //que está más a la derecha en el límite.
            EntityInvasor maxAnchor = Util.invasorMaxPosX(elementos);
            
            //En este caso el método devuelve el invasor que está más a la izquierda.
            EntityInvasor minAnchor = Util.invasorMinPosX(elementos, largoRenderizador);
            
            //Activa/desactiva el movimiento vertical cuando se llega a alguno de los límites
            if((maxAnchor.getX() + maxAnchor.getAncho()) >= largoRenderizador-1){
                mv = true;
            }else if(minAnchor.getX() < 1){
                mv = true;
            }
            
            //Se aumenta la variable de retardo
            if(mv){
                aux++;
            }
            
            //Se reinicia la variable cuando los invasores se mueven 5 veces
            if(aux>5){
                aux=0;
                sentido = -sentido;
                mv = false;
            }
            
            //Movimiento y cambio de sentido de los invasores.
            EntityInvasor ei = null;
            for(int i = 0; i < elementos.tamaño(); i++){
                Entity temp = elementos.elemento(i);
    
                if(temp instanceof EntityInvasor){
                    ei = (EntityInvasor) temp;
                    ei.setMovimientoVertical(mv);
                    ei.cambiarSentido(sentido);
                    ei.moverse();
                }
            }

        }
    }
    
    
}

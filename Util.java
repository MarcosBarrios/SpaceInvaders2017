import java.util.ArrayList;
/**
 * Una de las clases más importantes. Encargada de búsquedas esenciales y 
 * de las colisiones.
 * 
 * @author M. B.
 * @version 1.0
 */
public class Util{
    private Util(){} //No queremos que se pueda crear una instancia de esta clase
    
    /**
     * Devuelve el invasor que esté más a la derecha
     * 
     * @return invasor más a la derecha
     * @param Entities elementos del juego
     */
    public static EntityInvasor invasorMaxPosX(Entities e){
        EntityInvasor rtn = null;
        int posx = 0;
        for(int i = 0; i < e.tamaño(); i++){
            Entity temp = e.elemento(i);
            
            if(temp instanceof EntityInvasor){
                if(posx<temp.getX()){
                    posx = temp.getX();
                    rtn = (EntityInvasor) temp;
                }
            }
        }
        if(rtn == null)
            System.err.println("{Util.class} invasorMaxPosX(...) devuelve null;" + 
                "invasor mas a la derecha no encontrado. ");
        return rtn;
    }
    
    /**
     * Devuelve el invasor que esté más a la izquierda
     * 
     * @return invasor más a la izquierda
     * @param Entities elementos del juego
     */
    public static EntityInvasor invasorMinPosX(Entities e, int minGrid){
        EntityInvasor rtn = null;
        int posx = minGrid;
        for(int i = 0; i < e.tamaño(); i++){
            Entity temp = e.elemento(i);
            
            if(temp instanceof EntityInvasor){
                if(posx>temp.getX()){
                    posx = temp.getX();
                    rtn = (EntityInvasor) temp;
                }
            }
        }
        if(rtn == null)
            System.err.println("{Util.class} invasorMinPosX(...) devuelve null;" + 
                "invasor mas a la izquierda no encontrado. ");
        return rtn;
    }
    
    /**
     * Devuelve el invasor más cercano al jugador
     * @param Entities elementos del juego
     */
    public static EntityInvasor invasorMaxPosY(Entities e){
        EntityInvasor rtn = null;
        int posy = 0;
        for(int i = 0; i < e.tamaño(); i++){
            Entity temp = e.elemento(i);
            
            if(temp instanceof EntityInvasor){
                if(posy < temp.getY()){
                    posy = temp.getY();
                    rtn = (EntityInvasor) temp;
                }
            }
        }
        if(rtn == null){
            System.err.println("{Util.class} invasorMaxPosY(...) devuelve null;" + 
                "invasor más cercano a la nave no encontrado. ");
        }
        return rtn;
    }
    
    /**
     * Devuelve los proyectiles que están activos en el juego
     * @param Entities elementos del juego
     */
    public static ArrayList<EntityProyectil> proyectilesActivos(Entities e){
        ArrayList<EntityProyectil> rtn = new ArrayList<EntityProyectil>();
        for(int i = 0; i < e.tamaño(); i++){
            Entity temp = e.elemento(i);
            if(temp instanceof EntityProyectil){
                EntityProyectil aux = (EntityProyectil) temp;
                if(aux.activo()){
                    rtn.add(aux);
                }   
            }
        }
        return rtn;
    }
    
    /**
     * Comprueba la posición de los proyectiles con respecto a los entities y devuelve el invasor
     * con el que ha colisionado el proyectil.
     * @param p proyectil a comprobar, Entities elementos del juego
     * @return EntityViva que ha colisionado con el proyectil proporcionado
     */
    public static EntityViva colisionProyectil(EntityProyectil p, Entities e){
        
        if(p.activo()){
            for(int i = 0; i < e.tamaño(); i++){
                if(e.elemento(i) instanceof EntityViva){
                    EntityViva temp = (EntityViva) e.elemento(i);
                    
                    if(temp.activo()){
                        //Comprueba que la posición mínima-máxima X e Y del proyectil coincide con un entity
                        //cualquiera. Luego emplea las clases como máscara para evitar colisionar con todo.
                        if( (p.getX()+p.getAncho()) >= temp.getX() && p.getX() <= (temp.getX()+temp.getAncho())){
                            if((p.getY() <= (temp.getY()+temp.getAlto())&& (p.getY()+p.getAlto()) >= temp.getY())){
                                if(!temp.getClass().equals(p.inmunidad().getClass())){
                                    return temp;
                                }
                                /*System.out.println("p.getX()+p.getAncho(): " + (p.getX()+p.getAncho()));
                                System.out.println("p.getX(): " + p.getX());
                                System.out.println("p.getY()+p.getAlto(): " + (p.getY()+p.getAlto()));
                                System.out.println("p.getY(): " + p.getY());
                                System.out.println("temp.getY(): " + temp.getY());
                                System.out.println("temp.getY()+temp.getAlto(): " + (temp.getY()+temp.getAlto()));
                                System.out.println("temp.getX(): " + temp.getX());
                                System.out.println("temp.getX()+temp.getAncho(): " + (temp.getX()+temp.getAncho()));
                                System.out.println("colisión");*/
                            }
                        }
                    }
                }
            }  
        }
        
        return null;
    }
    
    /**
     * Devuelve un ArrayList con todos los invasores activos del juego
     * @param Entities elementos del juego
     * @return ArrayList con los invasores del juego
     */
    public static ArrayList<EntityInvasor> invasores(Entities e){
        ArrayList<EntityInvasor> rtn = new ArrayList<EntityInvasor>();
        
        for(int i = 0; i < e.tamaño(); i++){
            if(e.elemento(i) instanceof EntityInvasor){
                EntityInvasor in = (EntityInvasor) e.elemento(i);
                rtn.add(in);
            }
        }    
        
        return rtn;
    }
}

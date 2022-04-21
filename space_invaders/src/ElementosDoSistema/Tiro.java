package ElementosDoSistema;

/**
 * Esta classe implementa o tiro de um canhão e de um alien. O uso (se será um tiro
 * de canhão ou alien) será controlado pela classe que possui o tiro.
 * @author Bruno Alvarenga Colturato 11200251
 */
public class Tiro extends Sprite{
    
    /**
     * Atributo que controla se o tiro está subindo na tela ou não.
     */
    private boolean goUp;
    /**
     * Atributo que controla se o tiro está descebndo na tela ou não.
     */
    private boolean goDown;
    
    /**
     * Construtor de uma objeto do tipo Tiro.
     * @param sw Largura da tela do jogo.
     * @param sh Altura da tela do jogo.
     * @param vX Velocidade inicial do tiro em x.
     * @param vY Velocidade incial do tiro em y.
     */
    public Tiro(double sw, double sh, double vX, double vY){
        /**
         * Chamando construtor da classe mãe.
         */
        super(sw, sh, vX, vY);
        
        /**
         * Inicialmente o tiro está parado.
         */
        goUp = false;
        goDown = false;
    }
    
    /**
     * @return Retorna se o tiro está subindo na tela ou não.
     */
    public boolean getGoUp(){
        return goUp;
    }
    
    /**
     * Método que permite estabelecer se o tiro está subindo na tela ou não.
     * @param b True ou false.
     */
    public void setGoUp(boolean b){
        goUp = b;
    }
    
    /**
     * @return Retorna se o tiro está descendo na tela ou não.
     */
    public boolean getGoDown(){
        return goDown;
    }
    
    /**
     * Método que permite estabelecer se o tiro está descendo na tela ou não.
     * @param b True ou false.
     */
    public void setGoDown(boolean b){
        goDown = b;
    }
    
    /**
     * Um tiro não possui vida.
     */
    @Override
    public boolean getVivo(){
        return true;
    }
    
}

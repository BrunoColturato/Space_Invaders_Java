package ElementosDoSistema;

/**
 * Esta classe define um alien e seus atributos no jogo. Esta classe é herdeira 
 * da classe Sprite.
 * @author Bruno Alvarenga Colturato 11200251
 */
public class Alien extends Sprite{
    
    /**
     * Objeto do tipo Tipo responsável por controlar o tiro do alien.
     */
    private Tiro tiro;
    /**
     * Atributo que indica se um alien está vivo ou não.
     */
    private boolean vivo;
    
    /**
     * Construtor de um objeto do tipo alien.
     * @param sw Largura da tela de jogo.
     * @param sh Altura da tela de jogo.
     * @param vX Velocidade do alien no eixo x.
     * @param vY Velocidade do alien no exio y.
     * @param vXtiro Velocidade do tiro do alien no eixo x.
     * @param vYtiro Velocidade do tiro do alien no eixo y.
     */
    public Alien(double sw, double sh, double vX, double vY, double vXtiro, double vYtiro){
        /**
         * Chamando o contrutor da classe mãe.
         */
        super(sw, sh, vX, vY);
        
        /**
         * Inicialmento o alien está vivo.
         */
        vivo = true;

        /**
         * Chama o método que inicia o tiro do alien.
         */
        this.iniciarTiro(sw, sh, vXtiro, vYtiro);
    }
    
    /**
     * @return Retorna se o alien está vivo ou não.
     */
    public boolean getVivo(){
        return vivo;
    }
    
    /**
     * Método que permite definir se o alien está vivo ou não.
     * @param b True ou false.
     */
    public void setVivo(boolean b){
        vivo = b;
    }
    
    /**
     * Método que cria o objeto do tipo Tiro dentro da classe Alien.
     * @param sw
     * @param sh 
     */
    private void iniciarTiro(double sw, double sh, double vX, double vY){
        tiro = new Tiro(sw, sh, vX, vY);
        tiro.setImagem("Imagens/tiroAlien.png");
    }
    
    /**
     * @return Retorna se o tiro do alien está ativo ou não.
     */
    public boolean getAtirando(){
        return tiro.getGoDown();
    }
    
    /**
     * @return Retorna o objeto Tiro do alien. 
     */
    public Tiro getTiro(){
        return tiro;
    }
}

package ElementosDoSistema;

/**
 * Esta classe define as barreiras do jogo.
 * A classe herda características da classe Sprite.
 * @author Bruno Alvarenga Colturato 11200251
 */
public class Barreira extends Sprite{
    
    /**
     * Atributo que define a vida de uma barreira.
     */
    private int vida;
    
    /**
     * Construtor de um objeto do tipo barreira.
     */
    public Barreira() {
        /**
         * Chamando construtor da classe mãe.
         */
        super();
        
        /**
         * Definimos a imagem incial de uma barreira.
         */
        this.setImagem("Imagens/barreira.png");
        
        /**
         * Uma barreira tem inicialmente 100 de vida.
         */
        vida = 100;
    }
    
    /**
     * Método que permite definir a quantidade de vida da barreira.
     * @param v Valor a ser atribuido a vida da barreira.
     */
    public void setVida(int v){
        vida = v;
    }
    
    /**
     * Método que permite aumentar ou decrementar a vida da barreira. Pode ser uma
     * valor positivo ou negativo.
     * @param v Valor a ser adicionado a vida da barreira.
     */
    public void addVida(int v){
        vida += v;
    }
    
    /**
     * @return Retorna se a barreira está viva ou não. 
     */
    public boolean getVivo(){
        if(vida <= 0)
            return false;
        return true;
    }
    
    /**
     * @return Retorna o quanto de vida a barreira ainda possui.
     */
    public int getVida(){
        return vida;
    }
}

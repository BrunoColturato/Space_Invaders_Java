package ElementosDoSistema;

/**
 * Classe que determina as características do canhão do jogo.
 * @author Bruno Alvarenga Colturato 11200251
 */
public class Canhao extends Sprite{
    
    /**
     * Objeto do tipo Tiro do canhão.
     */
    private Tiro tiro;
    /**
     * Atributo que determina a quantidade de vida do canhão.
     */
    private int vida;
    /**
     * Atributo que determina a quantidade de pontos conquistados pela canhão 
     * durante a partida.
     */
    private int pontos;
    /**
     * Atributo que controla se o canhão está indo para a esquerda ou não.
     */
    private boolean goLeft;
    /**
     * Atributo que controla se o canhão está indo para a direita ou não.
     */
    private boolean goRight;
    
    /**
     * Contrutor de um objeto do tipo canhão.
     * @param sw Largura da tela do jogo.
     * @param sh Altura da tela do jogo.
     */
    public Canhao(double sw, double sh){
        /**
         * Chamando o construtor da classe mãe para definirmos a posição inicial do
         * canhão (no canto inferiror esquerdo da tela) e a velocidade da movimentação
         * do canhão (que no caso foi definida como 10 pixels por comando de movimentação).
         */
        super(sw, sh, 0.1*sw, sh - 30, 10, 0);
        
        /**
         * Inicialmento o canhão não está indo nem para a direita nem para a esquerda.
         */
        goLeft = false;
        goRight = false;
        
        /**
         * O canhão possui 3 vidas e inicialmente não tem nenhum ponto.
         */
        vida = 3;
        pontos = 0;

        /**
         * Definindo a imagem do canhão.
         */
        this.setImagem("Imagens/canhao.png");
        
        /**
         * Iniciando o objeto do tipo Tiro do canhão.
         */
        this.iniciarTiro(sw, sh);
    }
    
    /**
     * @return Rotorna o objeto Tiro do canhão. 
     */
    public Tiro getTiro(){
        return tiro;
    }
    
    /**
     * Método que cria o objeto do tipo Tiro dentro da classe Canhao.
     * @param sw Largura da tela do jogo.
     * @param sh Altura da tela do jogo.
     */
    private void iniciarTiro(double sw, double sh){
        tiro = new Tiro(sw, sh, 0, 9);
        tiro.setImagem("Imagens/tiroCanhao.png");
    }
    
    /**
     * @return Retorna o número de vidas do canhão.
     */
    public int getVida(){
        return vida;
    }
    
    /**
     * @return Retorna se o canhão está vivo ou não. 
     */
    @Override
    public boolean getVivo(){
        if(vida > 0)
            return true;
        else return false;
    }
    
    /**
     * @return Retorna o número de pontos conquistados pelo canhão.
     */
    public int getPontos(){
        return pontos;
    }
    
    /**
     * @return Retorna se o canhão está indo para a esquerda ou não.
     */
    public boolean getGoLeft(){
        return goLeft;
    }
    
    /**
     * @return Retorna se o canhão está indo para a direita ou não.
     */
    public boolean getGoRight(){
        return goRight;
    }
    
    /**
     * Método que permite determinar a quantidade de vidas do canhão.
     * @param v Número de vidas a ser estabelecido ao canhão.
     */
    public void setVida(int v){
        vida = v;
    }
    
    /**
     * Método que permite determinar se o canhão está indo para a esquerda ou não.
     * @param b True ou false
     */
    public void setGoLeft(boolean b){
        goLeft = b;
    }
    
    /**
     * Método que permite determinar se o canhão está indo para a direita ou não.
     * @param b True ou false
     */
    public void setGoRight(boolean b){
        goRight = b;
    }
    
    public void reiniciarPosicao(){
        this.setPosicao(0.1*this.getSW(), this.getSH() - 30);
    }
    
    /**
     * Método que permite aumentar ou diminuir a vida do canhão.
     * @param v Quantidade de vida a ser adicionada ou subtraida da atual vida do canhão.
     */
    public void addVida(int v){
        vida += v;
    }
    
    /**
     * Método que permite aumentar ou diminuir a quantidade de pontos do canhão.
     * @param p Quantidade de pontos a ser adicionada ou subtraida da quantidade atual
     * de pontos do canhão.
     */
    public void addPontos(int p){
        pontos += p;
    }
}

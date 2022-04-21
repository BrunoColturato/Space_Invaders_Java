package ElementosDoSistema;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

/**
 * Classe mãe de todos os elementos que podem se movimentar ou interagir com outros
 * elementos do jogo.
 * 
 * Segundo [1], "em jogos eletrônicos, sprite é o termo que designa uma entidade
 * visual", possuindo em sua constituição "uma imagem e uma posição, assim como a
 * velocidade (para entidades móveis) e o tamanho para calcular as caixas delimitantes
 * usadas para detecção de colisão".
 * 
 * Inspirado nessa ideia, criei esta classe para facilitar a manipulação dos
 * elementos gráficos do jogo (canhão, invasores, tela de fundo, etc).
 * 
 * Referências:
 * [1] https://gamedevelopment.tutsplus.com/pt/tutorials/introduction-to-javafx-for-game-development--cms-23835
 * 
 * @author Bruno Alvarenga Colturato 11200251
 */
public abstract class Sprite {
    
    /**
     * Imagem do elemento a ser definido.
     */
    private Image imagem;
    /**
     * Atributo que controla a posição em x do elemento na tela.
     */
    private double posX;
    /**
     * Atributo que controla a posição em y do elemento na tela.
     */
    private double posY;    
    /**
     * Atributo que controla a velocidade em x do elemento na tela.
     */
    private double vX;
    /**
     * Atributo que controla a velocidade em y do elemento na tela.
     */
    private double vY;
    /**
     * Largura da imagem que define o elemento.
     */
    private double width;
    /**
     * Altura da imagem que define o elemento.
     */
    private double height;
    /**
     * Largura da janela do jogo.
     */
    private double screenWidth;
    /**
     * Altura da janela do jogo.
     */
    private double screenHeight;
    
    /**
     * Cada classe possui um criterio diferente para dizer se o elemento está vivo 
     * ou não.
     * @return Retorna se o elemento está vivo.
     */
    public abstract boolean getVivo();
    
    /**
     * Construtor padrão de um objeto do tipo Sprite.
     */
    public Sprite()
    {
        posX = 0;
        posY = 0;    
        vX = 0;
        vY = 0;
    }
    
    /**
     * Construtor que permite definir as dimensões da tela na qual um objeto do 
     * tipo Sprite se encontra.
     * @param sw Largura da janela do jogo.
     * @param sh Altura da janela do jogo.
     */
    public Sprite(double sw, double sh)
    {
        screenWidth = sw;
        screenHeight = sh;
        posX = 0;
        posY = 0;    
        vX = 0;
        vY = 0;
    }
    
    /**
     * Construtor que permite definir as dimensões da tela na qual um objeto do 
     * tipo Sprite se encontra e suas velocidades iniciais.
     * @param sw Largura da janela do jogo.
     * @param sh Altura da janela do jogo.
     * @param vX Velocidade inicial do elemento em x.
     * @param vY Velocidade inicial do elemento em y.
     */
    public Sprite(double sw, double sh, double vX, double vY)
    {
        screenWidth = sw;
        screenHeight = sh;
        posX = 0;
        posY = 0;    
        this.vX = vX;
        this.vY = vY;
    }
    
    /**
     * Construtor que permite definir as dimensões da tela na qual um objeto do 
     * tipo Sprite se encontra, suas posições iniciais e suas velocidades iniciais.
     * @param sw Largura da janela do jogo.
     * @param sh Altura da janela do jogo.
     * @param posX Posição inicial do elemento em x.
     * @param posY Posição inicial do elemento em y.
     * @param vX Velocidade inicial do elemento em x.
     * @param vY Velocidade inicial do elemento em y.
     */
    public Sprite(double sw, double sh, double posX, double posY, double vX, double vY)
    {
        screenWidth = sw;
        screenHeight = sh;
        this.posX = posX;
        this.posY = posY;    
        this.vX = vX;
        this.vY = vY;
    }

    /**
     * Mpetodo que permite definir a imagem associada ao elemento do jogo.
     * @param path Caminho da imagem no pc do usuário.
     */
    public void setImagem(String path)
    {
        imagem = new Image(path);
        width = imagem.getWidth();
        height = imagem.getHeight();
    }

    /**
     * Método que permite definir uma posição x e y para o elemento.
     * @param x Posição x do elemento.
     * @param y Posição y do elemento.
     */
    public void setPosicao(double x, double y)
    {
        posX = x;
        posY = y;
    }
    
    /**
     * Método que permite adicionar um valor a posição x e y atuais do elemento.
     * Pode ser um valor positivo ou negativo.
     * @param x Valor a ser adicionado a posição x do elemento.
     * @param y Valor a ser adicionado a posição y do elemento.
     */
    public void addPosicao(double x, double y)
    {
        posX += x;
        posY += y;
    }
    
    /**
     * @return Retorna a largura da tela na qual se encontra o elemento.
     */
    public double getSW() {
        return screenWidth;
    }
    
    /**
     * @return Retorna a altura da tela na qual se encontra o elemento.
     */    
    public double getSH()
    {
        return screenHeight;
    }
    
    /**
     * @return Retorna a posição x do elemento. 
     */
    public double getX(){
        return posX;
    }
    
    /**
     * @return Retorna a posição y do elemento. 
     */
    public double getY()
    {
        return posY;
    }
    
    /**
     * @return Retorna a velocidade em x do elemento. 
     */
    public double getVX()
    {
        return vX;
    }
    
    /**
     * @return Retorna a velocidade em y do elemento. 
     */
    public double getVY()
    {
        return vY;
    }
    
    /**
     * Método que permite definir uma velocidade x e y para o elemento.
     * @param vX Velocidade em x do elemento.
     * @param vY Velocidade em x do elemento.
     */
    public void setVelocidade(double vX, double vY){
        this.vX = vX;
        this.vY = vY;
    }
    
    /**
     * Método que permite adicionar um valor a velocidade x e y atuais do elemento.
     * Pode ser um valor positivo ou negativo.
     * @param x Valor a ser adicionado a velocidade x do elemento.
     * @param y Valor a ser adicionado a velocidade y do elemento.
     */
    public void addVelocidade(double x, double y){
        vX += x;
        vY += y;
    }
    
    /**
     * Método que desenha a imagem que define o elemento do jogo.
     * @param gc Objeto do tipo GraphicsContext responsável por desenhar a imagem
     * na tela do jogo.
     */
    public void draw(GraphicsContext gc)
    {
        gc.drawImage( imagem, posX, posY );
    }
    
    /**
     * Método que retorna um objeto do tipo Rectangle2D por meio do qual podemos
     * saber as dimensões da imagem do nosso elemento e sua posição na tela do jogo.
     * @return Retorna um objeto do tipo Rectangle2D.
     */
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(posX,posY,width,height);
    }

    /**
     * Método que determina se houve uma colisão entre o objeto atual e um outro
     * passado como parâmetro. Para tanto, utilizamos o método já implementado pela
     * classe Rectangle2D de verificação de colisões.
     * @param s Objeto a se verificar se houve colisão.
     * @return Retorna se houve colisão ou não.
     */
    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }    
}

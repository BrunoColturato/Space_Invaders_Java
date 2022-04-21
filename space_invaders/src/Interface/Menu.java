package Interface;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Esta classe define os métodos responsáveis por controlar o funcionamento do 
 * menu do jogo.
 * @author Bruno Alvarenga Colturato 11200251
 */
public class Menu {
    
    /**
     * Obejto que define a imagem de inicio do menu.
     */
    private Image inicio;
    /**
     * Obejto que define a imagem de comandos do menu.
     */
    private Image comandos;
    /**
     * Obejto que define a imagem de dificuldades do menu.
     */
    private Image dificuldades;
    /**
     * Obejto que define a imagem caso a dificuldade "fácil" tenha sido selecionada.
     */
    private Image facil;
    /**
     * Obejto que define a imagem caso a dificuldade "médio" tenha sido selecionada.
     */
    private Image medio;
    /**
     * Obejto que define a imagem caso a dificuldade "difícil" tenha sido selecionada.
     */
    private Image dificil;
    /**
     * Variável auxiliar que ajuda a saber se devemos imprimir a tela de comandos
     * ou não.
     */
    private boolean showComandos;
    /**
     * Variável auxiliar que ajuda a saber se devemos imprimir a tela de dificuldades
     * ou não.
     */
    private boolean showDificuldades;
    
    /**
     * Construtor de um objeto do tipo Menu.
     */
    public Menu(){
        
        /**
         * Definição das imagens do menu.
         */
        inicio = new Image("Imagens/inicio.png");
        comandos = new Image("Imagens/comandos.png");
        dificuldades = new Image("Imagens/dificuldades.png");
        facil = new Image("Imagens/facil.png");
        medio = new Image("Imagens/medio.png");
        dificil = new Image("Imagens/dificil.png");
        
        /**
         * Iniciando atributos auxiliares.
         */
        showComandos = false;
        showDificuldades = false;
    }
    
    /**
     * @return Retorna se devemos imprimir a tela de comandos ou não.
     */    
    public boolean getShowComandos(){
        return showComandos;
    }
    
    /**
     * @return Retorna se devemos imprimir a tela de dificuldades ou não.
     */    
    public boolean getShowDificuldades(){
        return showDificuldades;
    }
    
    /**
     * Define se devemos mostrar a tela de comandos ou não.
     * @param b True ou false.
     */
    public void setShowComandos(boolean b){
        showComandos = b;
    }
    
    /**
     * Define se devemos mostrar a tela de dificuldades ou não.
     * @param b True ou false.
     */
    public void setShowDificuldades(boolean b){
        showDificuldades = b;
    }
    
    /**
     * Método que desenha a tela de início do menu.
     * @param gc Objeto do tipo GraphicsContext que desenha uma imagem na tela.
     */
    public void drawnInicio(GraphicsContext gc){
        gc.drawImage(inicio, 0, 0);
    }
    
    /**
     * Método que desenha a tela de comandos do menu.
     * @param gc Objeto do tipo GraphicsContext que desenha uma imagem na tela.
     */    
    public void drawnComandos(GraphicsContext gc){
        gc.drawImage(comandos, 0, 0);
    }
    
    /**
     * Método que desenha a tela de dificuldades do menu.
     * @param gc Objeto do tipo GraphicsContext que desenha uma imagem na tela.
     */
    public void drawnDificuldades(GraphicsContext gc){
        gc.drawImage(dificuldades, 0, 0);
    }
    
    /**
     * Método que desenha a tela de difuldades caso a opção fácil tenha sido selecionada.
     * @param gc Objeto do tipo GraphicsContext que desenha uma imagem na tela.
     */
    public void drawnFacil(GraphicsContext gc){
        gc.drawImage(facil, 0, 0);
    }
    
    /**
     * Método que desenha a tela de difuldades caso a opção médio tenha sido selecionada.
     * @param gc Objeto do tipo GraphicsContext que desenha uma imagem na tela.
     */
    public void drawnMedio(GraphicsContext gc){
        gc.drawImage(medio, 0, 0);
    }
    
    /**
     * Método que desenha a tela de difuldades caso a opção difícil tenha sido selecionada.
     * @param gc Objeto do tipo GraphicsContext que desenha uma imagem na tela.
     */
    public void drawnDificil(GraphicsContext gc){
        gc.drawImage(dificil, 0, 0);
    }
}

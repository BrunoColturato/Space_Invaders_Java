package Interface;

import ElementosDoSistema.*;
import Engine.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Esta classe é a responsável por controlar a parte gráfica do jogo, isto é, 
 * imprimir as imagens de menu e os elementos do jogo na tela.
 * Nesta classe também definimos as teclas que provocam ações no jogo. 
 * Além disso, controlamos o lopo do game, ou seja, verificamos a quantidade de
 * vidas disponíveis ao canhão e o número de aliens invasores vivos. A partir dessas
 * informações determinamos se o jogo acabou ou não.
 * @author Bruno Alvarenga Colturato 11200251
 */
public class ControleInterface {
    
    /**
     * Imagem de fundo do jogo: fixa.
     */
    private Image fundo;
    /**
     * Objeto que define os elementos do menu do jogo.
     */
    private Menu menu;
    /**
     * Objeto que controla a engine/ ações dos aliens (movimentação e tiros).
     */
    private AlienEngine aE;
    /**
     * Objeto que controla o movimento e os tiros do canhão.
     */
    private CanhaoEngine cE;
    /**
     * Objeto que controla a engine/ ações dos aliens (movimentação e tiros).
     */
    private GrupoAlienEngine gaE;
    /**
     * Objeto que controla a vida e o comportamento das barreiras.
     */
    private BarreiraEngine bE;
    /**
     * Atributo auxiliar para sabermos se o usuário solicitou o início do jogo.
     */
    private boolean iniciado;
    /**
     * Atributo auxiliar para sabermos se o usuário solicitou uma pausa no jogo.
     */
    private boolean pausado;
    /**
     * Atributo auxiliar para sabermos o comprimento definido para a tela do jogo.
     */
    private double screenWidth;
    /**
     * Atributo auxiliar para sabermos a altura definida para a tela do jogo.
     */
    private double screenHeight;
    /**
     * Atributo auxiliar que define a dificuldade do jogo: fácil, médio ou difícil.
     */
    private String dificuldade;
    
    /**
     * Construtor de um objeto do tipo ControleInterface
     * @param scene Objeto do tipo scene que define a tela do jogo.
     * @param gc Objeto do tipo GraphicsContext responsável por controlar a impressão
     * dos elementos do jogo.
     * @param sw Largura da tela do jogo.
     * @param sh Altura da tela do jogo.
     * @param c Objeto do tipo Canhao que define o canhão do jogo.
     * @param ga Objeto do tipo GrupoAlien que define o grupo de aliens do jogo.
     * @param b Array de barreira que define as 3 barreiras do jogo.
     */
    public ControleInterface(Scene scene, GraphicsContext gc, double sw, double sh, Canhao c, GrupoAlien ga, Barreira[] b){
        
        /**
         * Inicializando os atributos
         */
        iniciado = false;
        pausado = false;
        screenWidth = sw;
        screenHeight = sh;
        dificuldade = "";
        
        /**
         * Inicializando os objetos
         */
        menu = new Menu();
        aE = new AlienEngine();
        cE = new CanhaoEngine();
        gaE = new GrupoAlienEngine();
        bE = new BarreiraEngine();
        
        fundo = new Image("Imagens/fundo.png");        
        
        /**
         * Inicializando a posição das barreiras a partir das dimensões da tela
         * do jogo.
         */
        b[0].setPosicao(0.15 * screenWidth, 0.75 * screenHeight);
        b[1].setPosicao(0.45 * screenWidth, 0.75 * screenHeight);
        b[2].setPosicao(0.75 * screenWidth, 0.75 * screenHeight);
        
        /**
         * Chamando métodos que definem as teclas padrão do jogo e os estilo de texto
         * usado para impressão na tela.
         */
        this.definirTeclas(scene, c, ga);
        this.definirTexto(gc);
    }
    
    /**
     * Método que define a dificuldade do jogo. Dada uma dificuldade escolhida pelo
     * usuário, definimos a quantidade máxima de tiros aliens simultâneos na tela.
     * Quanto mais tiros, mais difícil fica o jogo.
     * @param s Dificuldade escolhida pelo usuário.
     * @param ga Objeto do tipo GrupoAlien no qual definimos a quantidade máxima
     * de tiros aliens simultâneos.
     */
    public void setDificuldade(String s, GrupoAlien ga){
        dificuldade = s;
        switch (dificuldade) {
            case "FÁCIL":
                ga.setNMaxAtaques(3);
                break;
            case "MÉDIO":
                ga.setNMaxAtaques(5);
                break;
            case "DIFÍCIL":
                ga.setNMaxAtaques(8);
                break;
        }
    }
    
    /**
     * Método que controla uma variável auxiliar responsável por indicar a interface
     * que o usuário deseja pausar o jogo.
     * O atributo "pausado" é modificado automaticamente ao pressionarmos a tecla "p".
     */
    public void pausar(){
        
        if(!pausado){
            pausado = true;
        }
        else{
            pausado = false;
        }
    }
    
    /**
     * Define o estilo de texto que aparece na tela por meio da função gc.fillText("").
     * @param gc Objeto GraphicContexto responsável por definir o estilo de texto.
     */
    public final void definirTexto(GraphicsContext gc){
        //Configurações de texto
        gc.setFill( Color.WHITE );
        Font theFont = Font.font( "Agency FB", FontWeight.BOLD, 20 );
        gc.setFont( theFont );
    }
    
    
    /**
     * Método que define as ações oriundas do pressionamento de teclas pelo usuário.
     * @param scene Objeto Scene que controla a janela do jogo.
     * @param c Objeto do tipo Canhao que sofre modificações conforme o pressionamento
     * de determinadas teclas.
     * @param ga Objeto do tipo GrupoAlien.
     */
    public final void definirTeclas(Scene scene, Canhao c, GrupoAlien ga){
        /**
         * Ações ao pressionarmos uma tecla.
         */
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch(event.getCode())
                {
                    /**
                     * Ações relacionadas ao menu.
                     */
                    case ENTER: iniciado = true; break;
                    case S: menu.setShowDificuldades(true);
                    case C: menu.setShowComandos(true); break;
                    case V: menu.setShowComandos(false); menu.setShowDificuldades(false); break;
                    case DIGIT1: setDificuldade("FÁCIL", ga); break;
                    case DIGIT2: setDificuldade("MÉDIO", ga); break;
                    case DIGIT3: setDificuldade("DIFÍCIL", ga); break;
                    
                    /**
                     * Ações possíveis dentro do loop do jogo.
                     */
                    case P:  pausar(); break;
                    
                    /**
                     * Ações relacionadas a movimentação do canhão.
                     */
                    case LEFT: c.setGoLeft(true); break;
                    case RIGHT: c.setGoRight(true); break;
                    case SPACE: cE.ativarTiro(c); break;
                }
            }
        });
        
        /**
         * Ações ao soltarmos uma tecla.
         */
        scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch(event.getCode())
                {
                    /**
                     * Ações relacionadas a movimentação do canhão.
                     */
                    case LEFT: c.setGoLeft(false); break;
                    case RIGHT: c.setGoRight(false); break;
                }
            }
        });
    }
    
    /**
     * Método principal que imprime os elementos na tela do jogo e define quais 
     * elementos devem aparecer.
     * @param gc Objeto do tipo GraphicsContext responsável por controlar a impressão
     * dos elementos do jogo.
     * @param c Objeto do tipo Canhao.
     * @param ga Objeto do tipo GrupoAlien.
     * @param b Array de Barreiras.
     */
    public void loopJogo(GraphicsContext gc, Canhao c, GrupoAlien ga, Barreira[] b){
        //Limpando a tela
        gc.clearRect(0, 0, screenWidth, screenHeight);

        //Desenhando o fundo
        gc.drawImage(fundo, 0, 0);
        
        //Se o jogo não foi iniciado, estamos no menu
        if(!iniciado){
            //Mostrar somente a tela de inicio
           if(!iniciado && !menu.getShowComandos()){
               menu.drawnInicio(gc);
           }

           //Mostrar a tela de comandos
           if(!iniciado && menu.getShowComandos()){
               menu.drawnComandos(gc);
           }

           //Mostrar a tela de dificuldades
           if(!iniciado && menu.getShowDificuldades()){
               switch (dificuldade) {
                    case "FÁCIL":
                        menu.drawnFacil(gc);
                        break;
                    case "MÉDIO":
                        menu.drawnMedio(gc);
                        break;
                    case "DIFÍCIL":
                        menu.drawnDificil(gc);
                        break;
                    default:
                        menu.drawnDificuldades(gc);
                        break;
               }
           }
        }
        //Caso o jogo tenha sido iniciado
        else{
            //Verificamos se o jogo está pausado
            if(pausado){
                c.draw(gc);
                ga.draw(gc);
                gc.fillText( "JOGO PAUSADO", 200, 300 );
            }
            /**
             * Caso o jogo não esteja pausado, realizamos o que é, de fato, o 
             * LOOP DO JOGO!
             * Na parte a seguir todos os elementos do jogo são desenhados. Além disso,
             * verificamos se o jogo acabou ou não e determinamos se um elemento
             * deve/pode ser desenhado na tela.
            */ 
            else if(c.getVida() > 0 && ga.getNumAliensVivos() > 0){

                //Eventos relacionados ao canhão
                cE.movimentar(c);
                cE.atirar(gc, c);
                cE.colisao(ga, c);
                c.draw(gc);

                //Eventos relacionados ao grupo de aliens
                gaE.atirar(gc, c, ga);
                if(!gaE.moveConjunto(ga))
                    c.setVida(0);                    
                gaE.colisao(c, ga);
                ga.draw(gc);
                
                // Eventos relacionados as barreiras
                bE.colisaoTiro(ga, b[0]);
                bE.colisaoAlien(ga, b[0]);
                bE.colisaoTiro(ga, b[1]);
                bE.colisaoAlien(ga, b[1]);
                bE.colisaoTiro(ga, b[2]);
                bE.colisaoAlien(ga, b[2]);
                
                // Desenho das barreiras
                if(b[0].getVivo())
                    b[0].draw(gc);
                if(b[1].getVivo())
                    b[1].draw(gc);
                if(b[2].getVivo())
                    b[2].draw(gc);

                //Mensagens
                gc.fillText( "<VIDAS:" + c.getVida() + ">", 0, 20 );
                gc.fillText( "<PONTOS:" + c.getPontos() + ">", screenWidth - 120, 20 );
                gc.fillText( "<MODO:" + dificuldade + ">", screenWidth/2, 20 );
            }
            /**
             * Caso o canhao nao possua mais vidas, o jogo acabou.
             */
            else if(c.getVida() == 0){
                c.draw(gc);

                //Mensagem final
                gc.fillText( "FIM DE JOGO: VOCÊ PERDEU!", 100, 300 );
                gc.fillText( "REINICIE O APLICATIVO SE QUISER JOGAR NOVAMENTE", 100, 350 );
           }
            /**
             * Caso não haja mais nenhum alien vivo, o jogo acabou.
             */
           else{
                c.draw(gc);

                //Mensagem final
                gc.fillText( "FIM DE JOGO: VOCÊ VENCEU NO MODO " + dificuldade +"!", 100, 300 );
                gc.fillText( "REINICIE O APLICATIVO SE QUISER JOGAR NOVAMENTE", 100, 350 );
            }
        }    
    }
}

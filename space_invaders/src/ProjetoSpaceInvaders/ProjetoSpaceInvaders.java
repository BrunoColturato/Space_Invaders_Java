package ProjetoSpaceInvaders;

import ElementosDoSistema.*;
import Interface.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import static javafx.application.Application.launch;

/**
 * Classe responsável pela criação dos objetos que definem o jogo e por chamar os
 * métodos que controlam o loop do mesmo.
 * @author Bruno Alvarenga Colturato 11200251
 */
public class ProjetoSpaceInvaders extends Application
{   
    @Override
    public void start(Stage stage)
    {   
        /**
         * Dimensoes da tela 
        */
        final double screenWidth = 700;
        final double screenHeight = 650;
        
        /**
         * Definindo objetos para correto funcionamento da janela do jogo. 
         */
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        /**
         * Criando os objetos que definem os elementos do jogo.  
         */
        Canhao canhao = new Canhao(screenWidth, screenHeight);
        GrupoAlien ga = new GrupoAlien(screenWidth, screenHeight);
        Barreira[] b = new Barreira[3];
        b[0] = new Barreira();
        b[1] = new Barreira();
        b[2] = new Barreira();
        
        /**
         * Objeto que controla a parte gráfica do jogo.
         */
        ControleInterface ci = new ControleInterface(scene, gc, screenWidth, screenHeight, canhao, ga, b);
        
        /**
         * Loop do jogo.
         */
        new AnimationTimer()
        {
            @Override
            public void handle(long time)
            {
                ci.loopJogo(gc, canhao, ga, b);
            }
            
        }.start();

        /**
         * Configurações da janela de jogo.
         */
        stage.setTitle("SPACE INVADERS - Bruno Alvarenga Colturato");
        stage.setScene(scene);
        
        /**
         * Não é possível redimensionar a tela!
         */
        stage.setResizable(false);
        
        /**
         * Definindo o ícone do jogo.
         */
        stage.getIcons().add(new Image("Imagens/icon.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) 
    {
        launch(args);
    }
}

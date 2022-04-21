package Engine;

import ElementosDoSistema.GrupoAlien;
import ElementosDoSistema.Canhao;
import javafx.scene.canvas.GraphicsContext;

/**
 * Esta classe foi criada para respeitar a modularização pedida pelo trabalho e 
 * é responsável por implementar a engine de um canhão (movimentação e resposta a
 * interações com outros elementos do jogo).
 * @author Bruno Alvarenga Colturato 11200251
 */
public class CanhaoEngine {
    /**
     * Atributo que permite controlar o tiro do canhão.
     */
    private TiroEngine tE;
    /**
     * Atributo importante para identificar colisões entre o canhão e os aliens.
     */
    private AlienEngine aE;
           
    /**
     * Contrutor de um objeto do tipo canhão.
     */
    public CanhaoEngine(){
        tE = new TiroEngine();
        aE = new AlienEngine();
    }
    
    /**
     * Método que implementa a movimentação de um canhão.
     * @param c Canhão que terá sua posição modificada.
     */
    public void movimentar(Canhao c){
        /**
         * A posição do canhão poderá ser modificadas respeitando os limites
         * da tela do jogo.
         */
        
        /**
         * Evitando ultrapassar parede esquerda da janela.
         */
        if(c.getGoLeft() && c.getX() >= 5)
            c.addPosicao(-c.getVX(), 0);
        
        /**
         * Evitando ultrapassar parede direita da janela.
         */
        if(c.getGoRight() && c.getX() <= (c.getSW() - c.getBoundary().getWidth() - 5))
            c.addPosicao(c.getVX(), 0);
    }
    
    /**
     * Método que desativa o tiro de um canhão.
     * @param c Canhão que terá seu tiro desativado.
     */
    public void desativarTiro(Canhao c){
        tE.desativar(c.getTiro());
    }
    
    /**
     * Método para forçar a contínua movimentação de um tiro (se existente).
     * @param gc Objeto do tipo GraphicsContext responsável por desenhar os elementos
     * do jogo na tela.
     * @param c Canhão que terá seu tiro modificado.
     */
    public void atirar(GraphicsContext gc, Canhao c){        
        //Se o tiro esta em movimento (é existente)
        if(c.getTiro().getGoUp()){
            tE.atirarCanhao(c.getTiro());//Movimentamos o tiro
            c.getTiro().draw(gc); //Desenhamos na tela
        }
    }
    
    /**
     * Método para iniciar processo de tiro a partir do meio do canhão (caso não
     * tenhamos um tiro já em movimento).Se já houver um tiro de canhão em movimento, 
     * o método não faz nada.
     * @param c Objeto do tipo canhão.
     */
    public void ativarTiro(Canhao c){
        tE.novoTiroCanhao(c);
    }
    
    /**
     * Este método verifica se o canhao foi atingido pelo tiro de algum alien.
     * @param ga Objeto do tipo GrupoAlien.
     * @param c Objeto do tipo Canhão.
     */
    public void colisao(GrupoAlien ga, Canhao c){        
        int i, j;
        
        for(i = 0; i < 5; i++){
            for(j = 0; j < 11; j++){
                
                /**
                 * Condições necessárias para verificarmos colisão entre o tiro
                 * de um alien e o canhão.
                 */
                if(ga.grupo[i][j].getVivo() && ga.grupo[i][j].getAtirando()){
                    
                    if(c.intersects(ga.grupo[i][j].getTiro())){
                        /**
                         * Desativando o tiro do canhao, reduzindo a vida e reposicionando.
                        */
                        tE.desativar(c.getTiro());
                        c.addVida(-1);
                        c.reiniciarPosicao();

                        /**
                         * Desativando tiro do alien e informando ao objeto ga que um tiro foi desativado.
                        */
                        aE.desativarTiro(ga.grupo[i][j]);
                        ga.tiroDesativado();
                    }
                }
            }
        }
    }   
}

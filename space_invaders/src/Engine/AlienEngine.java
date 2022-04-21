package Engine;

import ElementosDoSistema.Alien;
import javafx.scene.canvas.GraphicsContext;

/**
 * Esta classe foi criada para respeitar a modularização pedida pelo trabalho e 
 * é responsável por implementar a engine de um alien (movimentação e resposta a
 * interações com outros elementos do jogo).
 * @author Bruno Alvarenga Colturato 11200251
 */
public class AlienEngine {
    /**
     * A engine do alien precisa ter um objeto que controla a engine do tiro do
     * alien para que seja possível controlar o tiro do alien.
     */
    private TiroEngine tE;
    
    /**
     * Construtor padrão de um objeto do tipo AlienEngine.
     */
    public AlienEngine(){
        tE = new TiroEngine();
    }
    
    /**
     * Método que desativa o tiro do alien qualquer.
     * @param a Alien que terá seu tiro desativado.
     */
    public void desativarTiro(Alien a){
        tE.desativar(a.getTiro());
    }
    
    /**
     * Métod que cria um tiro alien para um dado alien.
     * @param a Alien que terá seu tiro criado.
     */
    public void novoTiro(Alien a){
        tE.novoTiroAlien(a);
    }
    
    /**
     * Método para forçar a contínua movimentação de um tiro (se existente) de um
     * alien qualquer.
     * @param gc Obejto do tipo GraphicsContext responsável por desenhar na tela 
     * os elementos do jogo.
     * @param a Alien que terá seu tiro movimentado.
     * @return  Retorna se o tiro está em movimento ou não.
     */
    public boolean atirar(GraphicsContext gc, Alien a){
        /**
         * Chamando método do obejto TiroEngine responsável pela moviementação 
         * do alien.
         */
        boolean statusTiro = tE.atirarAlien(a.getTiro());
            
        a.getTiro().draw(gc);
            
        return statusTiro;
    }
    
    /**
     * Método para iniciar processo de tiro a partir do meio de um alien (caso não
     * tenhamos um tiro já em movimento).
     * Se já houver um tiro de alien em movimento, o método não faz nada.
     * @param a Alien que terpa seu tiro iniciado na tela.
     */
    public void ativarTiro(Alien a){
        tE.novoTiroAlien(a);
    }
    
}

package Engine;

import ElementosDoSistema.Alien;
import ElementosDoSistema.Canhao;
import ElementosDoSistema.Tiro;

/**
 * Esta classe foi criada para respeitar a modularização pedida pelo trabalho e 
 * é responsável por implementar a engine de um tiro (movimentação e resposta a
 * interações com outros elementos do jogo).
 * @author Bruno Alvarenga Colturato 11200251
 */
public class TiroEngine {
    
    /**
     * Construtor padrão de um objeto do tipo TiroEngine. Não faz nada.
     */
    public TiroEngine(){
    }
    
    /**
     * Dado um tiro, desativos o mesmo (quer seja tiro de um canhão ou de um alien).
     * @param t Tiro a ser modificado.
     */
    public void desativar(Tiro t){
        
        t.setGoUp(false);
        t.setGoDown(false);
        
        t.setPosicao(1000, 1000);
    }
    
    /**
     * Dadao uma tiro de canhão, fazemos com que este se movimente para cima.
     * @param t Tiro a ser modificado.
     */
    public void subir(Tiro t){
        if(t.getGoUp())
            t.addPosicao(0, -t.getVY());    
    }
    
    /**
     * Dadao uma tiro de alien, fazemos com que este se movimente para baixo.
     * @param t Tiro a ser modificado.
     */
    public void descer(Tiro t){
        if(t.getGoDown())
            t.addPosicao(0, t.getVY());    
    }
    
    /**
     * Neste método iniciamos um novo tiro a partir do meio de um canhão.
     * @param c Canhão a partir do qual o tiro se inicia.
     */
    public void novoTiroCanhao(Canhao c){
        
        /**
         * Se o tiro ja estiver ativo, não fazemos nada
        */
        if(!c.getTiro().getGoUp()){
            /**
             * O tiro parte do meio do canhao (OBS: -2 obtido experimentalmente)
            */
            c.getTiro().setPosicao(c.getX() + (c.getBoundary().getWidth() / 2) - 2, c.getY());
            c.getTiro().setGoUp(true);
        }
    }
    
    /**
     * Neste método iniciamos um novo tiro a partir do meio de um alien.
     * @param a Alien a partir do qual o tiro se inicia.
    */
    public void novoTiroAlien(Alien a){
        
        /**
         * Se o tiro ja estiver ativo, não fazemos nada
        */
        if(!a.getTiro().getGoDown()){
            /**
             * O tiro parte do meio do canhao (OBS: -2 obtido experimentalmente)
            */
            a.getTiro().setPosicao(a.getX() + (a.getBoundary().getWidth() / 2), a.getY() + a.getBoundary().getHeight());
            a.getTiro().setGoDown(true);
        }
    }
    
    /**
     * Neste metodo pressupomos que o tiro ja esta ativado.Movemos o tiro para cima.
     * @param t Tiro a ser modificado.
     */
    public void atirarCanhao(Tiro t){
        /**
         * O tiro só pode se mover até a borda superior da tela
        */
        if(t.getY() + t.getBoundary().getHeight() >= 0){
            this.subir(t);
        }
        /**
         * Caso contrario desativamos o tiro
        */
        else{
            t.setGoUp(false);
        }
    }
    
    /**
     * Neste metodo pressupomos que o tiro ja esta ativado.Movemos o tiro para cima.
     * @param t Tiro a ser modificado.
     * @return Retorna se o tiro foi bem sucedido ou nao
    */
    public boolean atirarAlien(Tiro t){
        /**
         * O tiro só pode se mover até a borda inferior da tela ou até colidir com
         * um canhão
        */
        if(t.getY() <= t.getSH()){
            this.descer(t);
            return true;
        }
        /**
         * Caso contrario desativamos o tiro
        */
        else{
            t.setGoDown(false);
            return false;
        }
    }
}

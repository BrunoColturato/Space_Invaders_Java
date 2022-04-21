package Engine;

import ElementosDoSistema.Barreira;
import ElementosDoSistema.GrupoAlien;

/**
 * Esta classe foi criada para respeitar a modularização pedida pelo trabalho e 
 * é responsável por implementar a engine de uma barreira (movimentação e resposta a
 * interações com outros elementos do jogo).
 * @author Bruno Alvarenga Colturato 11200251
 */
public class BarreiraEngine {
    /**
     * A engine de uma barreira precisa ter um objeto que controla um alien para
     * podermos fazer verificações de colisões.
     */
    private AlienEngine aE;
    /**
     * A engine de uma barreira precisa ter um objeto que controla um grupo aliens
     * para podermos fazer verificações de colisões.
     */
    private GrupoAlienEngine gaE;
    
    public BarreiraEngine(){
        aE = new AlienEngine();
        gaE = new GrupoAlienEngine();
    }
    
    /**
     * Método responsável por determinar se houve colisão entre uma barreira e um 
     * tiro alien.
     * @param ga Grupo alien atual.
     * @param b Barreira com a qual verificamos se houve colisão com tiros aliens
     * ou não.
     */
    public void colisaoTiro(GrupoAlien ga, Barreira b){        
        /**
         * Percorremos o grupo alien para verificarmos a intersecção com cada tiro
         * de cada alien.
         */
        int i, j;
        
        for(i = 0; i < 5; i++){
            for(j = 0; j < 11; j++){
                
                /**
                 * Condições necessárias para a verificação de colisão.
                 */
                if(b.getVivo() && ga.grupo[i][j].getVivo() && ga.grupo[i][j].getAtirando()){
                    
                    /**
                     * Se houve colisão.
                     */
                    if(b.intersects(ga.grupo[i][j].getTiro())){
                        
                        /**
                         * Diminuimos a vida da barreira.
                        */
                        b.addVida(-10);
                        
                        /**
                         * Se necessário, mudamos a imagem da barreira para darmos
                         * a sensação de degradação gradual.
                        */
                        if(b.getVida() <= 50)
                            b.setImagem("Imagens/barreira-danificada.png");
                        
                        /**
                         * Desativando tiro do alien e informando ao objeto ga que um tiro foi desativado
                        */
                        aE.desativarTiro(ga.grupo[i][j]);
                        ga.tiroDesativado();
                    }
                }
            }
        }
    }
    
    /**
     * Este método permite verificar colisões "físicas" com o grupo de aliens,
     * ou seja, se o grupo de aliens e a barreira estão ocupando a mesma posição.
     * No caso de estarem ocupando a mesma posição, a barreira perde toda a sua 
     * vida de forma instantânea.
     * @param ga Grupo de aliens atual.
     * @param b Barreira com a qual querevemos verificar se houve colisão.
     */
    public void colisaoAlien(GrupoAlien ga, Barreira b){        
        /**
         * Chamando método que calcula a distância entre o grupo alien e a barreira.
         */
        int dist = gaE.calculaDistBarreira(b.getY(), ga);
        
        /**
         * Se estiverem ocupando a mesma posição, a barreira perde toda sua vida
         * de forma instantânea.
         */
        if(dist <= 10){
            b.setVida(0);
        }
    }
}

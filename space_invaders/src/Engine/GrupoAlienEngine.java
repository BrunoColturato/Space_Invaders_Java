package Engine;

import ElementosDoSistema.Canhao;
import ElementosDoSistema.GrupoAlien;
import javafx.scene.canvas.GraphicsContext;

/**
 * Esta classe foi criada para respeitar a modularização pedida pelo trabalho e 
 * é responsável por implementar a engine do grupo alien (movimentação e resposta a
 * interações com outros elementos do jogo).
 * @author Bruno Alvarenga Colturato 11200251
 */
public class GrupoAlienEngine {
    
    /**
     * Objeto que modifica a engine de um único alien.
     */
    private AlienEngine aE;
    /**
     * Objeto que controla a engine do canhão (necessário para verificar colisões).
     */
    private CanhaoEngine cE;
    /**
     * Atributo auxiliar que indica se o grupo de aliens está indo para a esquerda.
     */
    private boolean goLeft;
    /**
     * Atributo auxiliar que indica se o grupo de aliens está indo para a direita.
     */
    private boolean goRight;
    /**
     * Atributo auxiliar que indica se o grupo de aliens está indo para baixo.
     */
    private boolean goDown;
    
    /**
     * Construtor padrão de um objeto GrupoAlienEngine.
     */
    public GrupoAlienEngine(){
        /**
         * Criando os modificadores de engine auxiliares.
         */
        aE = new AlienEngine();
        cE = new CanhaoEngine();
        
        /**
         * Inicialiazando os atributos.
         */
        goLeft = false;
        goRight = true;
        goDown = false;
    }
    
    /**
     * Este método é o responsável por movimentar o grupo de invasores para a direita.
     * @param ga Objeto do tipo GrupoAlien.
     */
    private void movimentarDir(GrupoAlien ga) {
        int i, j;
        
        for(i=0; i<5; i++) {
            for(j=0; j<11; j++) {
                
                /**
                 * Aumentamos a posição x de cada invasor para gerar a sensação
                 * de movimentação para a direita.
                */
                if(ga.grupo[i][j].getVivo())
                    ga.grupo[i][j].addPosicao(ga.grupo[i][j].getVX(), 0);
            }
        }
    }
    
    /**
     * Este método é o responsável por movimentar o grupo de invasores para a esquerda. 
     * @param ga Objeto do tipo GrupoAlien.
    */
    private void movimentarEsq(GrupoAlien ga){
        int i, j;
        
        for(i=0; i<5; i++) {
            for(j=0; j<11; j++) {
                
                /**
                 * Diminuimos a posição x de cada invasor para gerar a sensação
                 * de movimentação para a esquerda.
                */
                if(ga.grupo[i][j].getVivo())
                    ga.grupo[i][j].addPosicao(-ga.grupo[i][j].getVX(), 0);
            }
        }
    }
    
    
    /**
     * Este método é o responsável por movimentar o grupo de invasores para baixo. 
     * @param ga Objeto do tipo GrupoAlien.
    */
    private void movimentarBaixo(GrupoAlien ga){
        int i, j;
        
        for(i=0; i<5; i++) {
            for(j=0; j<11; j++) {
                
                /**
                 * Aumentamos a posição y de cada invasor para gerar a sensação
                 * de movimentação para a baixo.
                */
                if(ga.grupo[i][j].getVivo())
                    ga.grupo[i][j].addPosicao(0, ga.grupo[i][j].getVY());
            }
        }
    }
    
    /**
     * Este método calcula a distância do alien mais a direita da parede direita.
     * @param ga Objeto do tipo GrupoAlien
     * @return Retorna a distância (em pixels) do alien mais a direita da parede
     * direita.
     */
    private int calculaDistDir(GrupoAlien ga){
        int distD=10000;
        int auxD;
        
        int i, j;
        
        for(i=0; i<5; i++) {
            for(j=0; j<11; j++) {
                /**
                 * Se o alien estiver vivo consideramos calcular a partir dele as distâncias.
                */
                if(ga.grupo[i][j].getVivo()) {
                    
                    /**
                     * Calculamos a distância entre os aliens e a parede direita.
                    */
                    auxD = (int) ( ga.grupo[i][j].getSW() - ( ga.grupo[i][j].getX() + ga.grupo[i][j].getBoundary().getWidth() ) );
                    if(auxD < distD) {
                        distD = auxD;
                    }
                }
            }
        }
        
        return distD;
    }
    
    /**
     * Este método calcula a distância do alien mais a esquerda da parede esquerda.
     * @param ga Objeto do tipo GrupoAlien
     * @return Retorna a distância (em pixels) do alien mais a esquerda da parede
     * esquerda.
     */
    private int calculaDistEsq(GrupoAlien ga){
        int distE=10000;
        int auxE;
        
        int i, j;
        
        for(i=0; i<5; i++) {
            for(j=0; j<11; j++) {
                /**
                 * Se o alien estiver vivo consideramos calcular a partir dele as distâncias.
                */
                if(ga.grupo[i][j].getVivo()) {
                    
                    /**
                     * Calculamos a distância entre os aliens e a parede esquerda.
                    */
                    auxE = (int) ( ga.grupo[i][j].getX() );
                    if(auxE < distE) {
                        distE = auxE;
                    }
                }
            }
        }
        
        return distE;
    }
    
    /**
     * Este método calcula a distância da linha padrão onde se encontra o canhão 
     * do alien mais abaixo do grupo.
     * @param ga Objeto do tipo GrupoAlien
     * @return Retorna a distância (em pixels) da linha padrão onde se encontra o canhão 
     * do alien mais abaixo do grupo.
     */
    private int calculaDistBaixo(GrupoAlien ga){
        int distB=10000;
        int auxB;
        
        int i, j;
        
        for(i=0; i<5; i++) {
            for(j=0; j<11; j++) {
                /**
                 * Se o alien estiver vivo consideramos calcular a partir dele as distâncias.
                */
                if(ga.grupo[i][j].getVivo()) {
                    
                    /**
                     * Calculamos a distância entre os alien e o canhão.
                    */
                    auxB = (int) ( ga.grupo[i][j].getSH() - ga.grupo[i][j].getY() );
                    if(auxB < distB) {
                        distB = auxB;
                    }
                }
            }
        }
        
        return distB;
    }
    
    /**
     * Este método calcula a distância de um dada barreira do alien mais abaixo
     * do grupo grupo alien.
     * @param posYBarreira Coordenada y da posição da barreira a ser considerada.
     * @param ga Objeto do tipo GrupoAlien
     * @return Retorna a distância (em pixels) da barreira do alien mais a abaixo
     * do grupo.
     */
    public int calculaDistBarreira(double posYBarreira, GrupoAlien ga){
        int distBarreira=10000;
        int auxBarreira;
        
        int i, j;
        
        for(i=0; i<5; i++) {
            for(j=0; j<11; j++) {
                /**
                 * Se o alien estiver vivo consideramos calcular a partir dele as distâncias
                */
                if(ga.grupo[i][j].getVivo()) {
                    
                    /**
                     * Calculamos a distância entre os alien e a barreira.
                    */
                    auxBarreira = Math.abs((int) ( posYBarreira - ( ga.grupo[i][j].getY() + ga.grupo[i][j].getBoundary().getHeight() ) ));
                    if(auxBarreira < distBarreira) {
                        distBarreira = auxBarreira;
                    }
                }
            }
        }
        
        return distBarreira;
    }
    
    /**
     * Este método é o responsável por decidir qual deve ser a movimentação do 
     * grupo considerando as distâncias das paredes direita e esquerda do jogo
     * e a distância do grupo ao canhão.
     * @param ga Objeto do tipo GrupoAlien.
     * @return Retorna se o movimento obedece as regras do jogo, isto é, não atingiu
     * o canhão.
     */
    public boolean moveConjunto(GrupoAlien ga) {
        int distD = this.calculaDistDir(ga);
        int distE = this.calculaDistEsq(ga);
        int distB = this.calculaDistBaixo(ga);
        
        /**
         * Atingiu o canhao
         * OBS: 50 obtido experimentalmente
        */
        if(distB < 50)
            return false; //Indica que o jogo deve ser finalizado
        
        /**
         * Se o grupo está indo para a direita e a distância até a parede direita
         * é maior que zero, devemos continuar indo para a direita.
        */
        if(distD > 0 && goRight == true) {
            this.movimentarDir(ga);
        }
        
        /**
         * Se estamos indo para a direita e encontramos a parede (distD=0), então
         * devemos descer o conjunto.
        */
        else if(distD <= 5 && goRight == true) {
            this.movimentarBaixo(ga);
            goRight = false;
        }
        
        /**
         * Caso nao estejamos indo para a direita (não passou nos caso de teste
         * anteriores) e o grupo está encostado na parede direita, devemos ir para
         * a esquerda.
        */
        else if(distD <= 5) {
            this.movimentarEsq(ga);
        }
        
        /**
         * Caso o grupo deva ir para a direita e nem deva descer (não passou 
         * nos caso de teste anteriores) o grupo deve ir para a esquerda.
        */
        else if(distE > 0) {
            this.movimentarEsq(ga);
        }
        
        /**
         * Caso o grupo esteja encostado na parede esquerda (distE=0) e não esteja 
         * indo para a direita (não passou nos casos de teste anteriores), então
         * o grupo deve ir para baixo.
         * O próximo movimento do grupo depois do atual deve ser ir para a direita,
         * portanto fazemos goRight = true.
        */
        else if(distE <= 5) {
            this.movimentarBaixo(ga);
            goRight = true;
        }
        
        return true;
    }
        
    /**
     * Este método determina qual alien pode realizar um novo ataque.Para ocorrer
     * um novo tiro, o alien e o canhão devem estar na mesma coluna, o alien não
     * pode ter um tiro em curso, e o alien imediatamente abaixo do alien atual 
     * deve estar morto.
     * @param c Objeto do tip canhão.
     * @param ga Objeto do tipo GrupoAlien.
     */
    private void novoAtaque(Canhao c, GrupoAlien ga) {        
        //Posição atual do meio do canhão
        double cX = c.getX() + c.getBoundary().getWidth()/2;
        
        int i,j;
        
        for(i=0; i<4; i++) {
            for(j=0; j<11; j++) {
                
                if(ga.getNAtaques() < ga.getNMaxAtaques()){
                    
                    /**
                    * Para considerarmos que o alien e o canhão estão na mesma coluna, devemos 
                    * ter que a distância (em x) entre centro do canhão (cX) e qualquer parte 
                    * do alien, isto é:
                    * grupo[i][j].getX() + grupo[i][j].getBoundary().getWidth() 
                    * deve ser menor que 5.
                    * OBS: 5 obtido experimentalmente.
                    */
                    if(!ga.grupo[i+1][j].getVivo() && ga.grupo[i][j].getVivo()){
                        /**
                        * Além disso, para haver certa ordem no jogo, somente podemos ter um novo
                        * tiro se a quantidade de tiros atual for menor que a quantidade máxima de tiros.
                        */
                        if( Math.abs(ga.grupo[i][j].getX() + ga.grupo[i][j].getBoundary().getWidth() - cX) <= 5 && !ga.grupo[i][j].getAtirando() ){
                            
                            aE.novoTiro(ga.grupo[i][j]);
                            ga.addNAtaques(1);
                        }
                    } 
                }
                  
            }
        }
        
        /**
         * Para os elementos da ultima linha de cima para baixo
        */
        for(j=0; j<11; j++) {
            
            if(ga.getNAtaques() < ga.getNMaxAtaques()){
                
                if(ga.grupo[4][j].getVivo()){
                    
                    if( Math.abs(ga.grupo[i][j].getX() + ga.grupo[i][j].getBoundary().getWidth() - cX) <= 5 && !ga.grupo[i][j].getAtirando() ){
                        aE.novoTiro(ga.grupo[i][j]);
                        ga.addNAtaques(1);
                    }
                }  
            }
        }
    }
    
    /**
     * Método para forçar a contínua movimentação dos tiros (se existentes).
     * @param gc Objeto do tipo GraphicContext responsável por desenhar os elementos
     * na tela do jogo.
     * @param c Objeto do tipo canhão.
     * @param ga Objeto do tipo GrupoAlien.
     */
    public void atirar(GraphicsContext gc, Canhao c, GrupoAlien ga){       
        int i, j;
        
        /**
         * Verificando a possibilidade de um novo tiro
        */
        this.novoAtaque(c, ga);
        
        for(i=0; i<5; i++) {
            for(j=0; j<11; j++) {
                
                if(ga.grupo[i][j].getVivo()){
                    
                    /**
                     * Se o tiro esta em movimento (é existente)
                    */
                    if(ga.grupo[i][j].getAtirando()){
                        
                        /**
                         * Movimentamos o tiro.
                         */
                        if(!aE.atirar(gc, ga.grupo[i][j])){
                            ga.addNAtaques(-1);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Este método verifica se algum alien foi atingido pelo tiro do canhão.
     * @param c Objeto do tipo canhão.
     * @param ga Objeto do tipo GrupoAlien.
     */
    public void colisao(Canhao c, GrupoAlien ga){        
        int i, j;
        
        for(i = 0; i < 5; i++){
            for(j = 0; j < 11; j++){
                
                if(ga.grupo[i][j].getVivo() && ga.grupo[i][j].intersects(c.getTiro())){
                    
                    /**
                     * Desativando o tiro do canhao
                    */
                    cE.desativarTiro(c);
                    /**
                     * Aumentando a pontuação do canhao conforme o alien atingido
                    */
                    if(i == 0)
                        c.addPontos(100);
                    else if(i == 1 || i == 2)
                        c.addPontos(70);
                    else
                        c.addPontos(50);
                    
                    /**
                     * Desativando o alien e possivel tiro
                    */
                    ga.grupo[i][j].setVivo(false);
                    
                    if(ga.grupo[i][j].getAtirando())
                        ga.addNAtaques(-1);
                    
                    aE.desativarTiro(ga.grupo[i][j]);
                    
                    /**
                     * Conforme a quantidade de aliens vivos, aumentamos sua velocidade
                    */
                    int aux = ga.getNumAliensVivos();
                    if(aux == 45 || aux == 35 || aux == 25 || aux == 15 || aux == 5 || aux == 1)
                        ga.aumentarVelocidade();
                }
            }
        }
    }
}

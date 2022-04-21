package ElementosDoSistema;

import javafx.scene.canvas.GraphicsContext;

/**
 * Esta classe define o grupo de aliens invasores do jogo.
 * @author Bruno Alvarenga Colturato 11200251
 */
public class GrupoAlien {
    
    /**
     * Matriz de aliens que formam o grupo de invasores.
     */
    public Alien[][] grupo;
    /**
     * Largura da tela do jogo.
     */
    private double screenWidth;
    /**
     * Altura da tela do jogo.
     */
    private double screenHeight;
    /**
     * Número de ataques/tiros de aliens ativos na tela. 
     */
    private int nAtaques;
    /**
     * Número máximo de ataques/tiros que podem estar ativos na tela.
     */
    private int nMaxAtaques;
    
    /**
     * Construtor de um objeto do tipo GrupoAlien.
     * @param sw Largura da tela do jogo.
     * @param sh Altura da tela do jogo.
     */
    public GrupoAlien(double sw, double sh){
        
        /**
         * Numero incial de ataques é 0.
         */
        nAtaques = 0;
        
        /**
         * Definindo as dimensões da tela do jogo.
         */
        screenWidth = sw;
        screenHeight = sh;
        
        /**
         * Iniciando a matriz de aliens conforme especificado pelo trabalho.
         */
        grupo = new Alien[5][11];
        
        /**
         * Ao criar os aliens DIFINIMOS A VELOCIDADE DE MOVIMENTAÇÃO
        */
        this.criaAliens(1, 10);
    }
    
    /**
     * @return Retorna o número de tiros ativos na tela.
     */
    public int getNAtaques(){
        return nAtaques;
    }
    
    /**
     * @return Retorna o número máximo de tiros permitidos na tela.
     */
    public int getNMaxAtaques(){
        return nMaxAtaques;
    }
    
    /**
     * Determina o número de tiros máximo.
     * @param n Número de tiros máximo.
     */
    public void setNMaxAtaques(int n){
        nMaxAtaques = n;
    }
    
    /**
     * Incrementa o atributo que controla o número de tiros aliens ativos na tela.
     * @param add Número de tiros a mais ativos na tela.
     */
    public void addNAtaques(int add){
        nAtaques += add;
    }
    
    /**
     * Incrementa o atributo que controla o número máximo de tiros aliens ativos na tela.
     * @param add Número de tiros a mais ativos que podemos ter na tela.
     */
    public void addNMaxAtaques(int add){
        nMaxAtaques += add;
    }
    
    /**
     * Dado que um tiro foi desativado, diminuimos o atributo que controla o 
     * número de tiros ativos na tela.
     */
    public void tiroDesativado(){
        nAtaques--;
    }
    
    /**
     * @return Retorna o número atual de aliens vivos.
     */
    public int getNumAliensVivos(){
        int i, j;
        
        int contador = 0;
        
        for(i=0; i<5; i++) {
            for(j=0; j<11; j++) {
                if(grupo[i][j].getVivo())
                    contador++;
            }
        }
        
        return contador;
    }
    
    /**
     * Método que permite reiniciarmos a vida, posição inicial e velocidade inicial
     * de cada alien do grupo de aliens.
     */
    public void reiniciarGrupo(){
        int contadorX  = -1, contadorY = -1; //Serve para aumentarmos continuamente a posicao dos aliens
        int i, j;
        
        for(i = 0; i < 5; i++){
            contadorY++;
            
            //"Zerando" o contador de distancia em X (iniciamos nova linha)
            contadorX = -1;
            
            for(j = 0; j < 11; j++){
                contadorX++;
                
                /**
                 * Revivendo os aliens.
                */ 
                grupo[i][j].setVivo(true);
                
                /**
                 * Reiniciando suas posicoes.
                 * OBS: Multiplicar por 1.5 e 1.7 e somar 10 e 40 foram obtidos experimentalmente
                 */
                grupo[i][j].setPosicao( 1.5 * contadorX * grupo[i][j].getBoundary().getWidth() + 10, 
                                        1.7 * contadorY * grupo[i][j].getBoundary().getHeight() + 40 );
                
                /**
                 * Reiniciando velocidade inicial.
                 */
                grupo[i][j].setVelocidade(0, 0);
            }
        }
    }
    
    /**
     * Este método desenha os aliens (vivos) na tela.
     * @param gc Objeto do tipo GraphicsContext
     */
    public void draw(GraphicsContext gc){
        
        int i, j;
        
        for(i = 0; i < 5; i++){
            for(j = 0; j < 11; j++){
                
                if(grupo[i][j].getVivo())
                    grupo[i][j].draw(gc);
            }
        }
    }
    
    /**
     * Este método aumenta a velocidade de cada alien do grupo por um fator
     * previamente determinado pelo desenvolvedor.
     */
    public void aumentarVelocidade(){
        int i, j;
        
        for(i = 0; i < 5; i++){
            for(j = 0; j < 11; j++){
                
                if(grupo[i][j].getVivo())
                    grupo[i][j].addVelocidade(0.3, 0);
            }
        }
    }
    
    /**
     * Método que inicia os objetos do tipo Alien na matriz de Aliens.
     * A matriz terá dimensões 5x11 (conforme especificado pelo trabalho).
     * Os aliens foram posicionadas conforme o tamanho da imagem que os define.
     */
    private void criaAliens(double vX, double vY){
        
        int i, j;
        int contadorX  = -1, contadorY = -1; //Serve para aumentarmos continuamente a posicao dos aliens
        
        for(i = 0; i < 5; i++){
            
            contadorY++;
            
            //"Zerando" o contador de distancia em X (iniciamos nova linha)
            contadorX = -1;
            
            for(j = 0; j < 11; j++){
                
                contadorX++;
                
                //Criando o objeto e DEFININDO VELOCIDADE DO TIRO (3º e 4º parametros)
                grupo[i][j] = new Alien(screenWidth, screenHeight, vX, vY, 0, 2);
                
                /**
                 * Definindo a imagem conforme a linha em que se encontra o alien.
                */
                switch(i){
                    case 0: grupo[i][j].setImagem("Imagens/alien3.png"); break;
                    case 1: grupo[i][j].setImagem("Imagens/alien2.png"); break;
                    case 2: grupo[i][j].setImagem("Imagens/alien2.png"); break;
                    case 3: grupo[i][j].setImagem("Imagens/alien1.png"); break;
                    case 4: grupo[i][j].setImagem("Imagens/alien1.png"); break;
                }
                
                /**
                 * Definindo posição inicial
                 * OBS: Multiplicar por 1.5 e 1.7 e somar 10 e 40 foram obtidos experimentalmente
                */
                grupo[i][j].setPosicao( 1.5 * contadorX * grupo[i][j].getBoundary().getWidth() + 10, 
                                        1.7 * contadorY * grupo[i][j].getBoundary().getHeight() + 40 );
            }
        }        
    }    
}

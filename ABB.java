import java.util.Comparator;

public class ABB<K, V> implements IMapeamento<K, V> {
    private No<K, V> raiz;
    private Comparator<K> comparador;

    private void init(Comparator<K> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }

    public ABB() { 
        init(null); 
    }

    public ABB(Comparator<K> comparador) { 
        init(comparador); 
    }

    @Override
    public boolean vazia() { 
        return this.raiz == null; 
    }

    
    public ABB<K,V> clone(){

        ABB<K,V> CópiaDaArvore = new ABB<>();

        if(this.raiz!=null){
            CópiaDaArvore.raiz = this.raiz.clone(this.raiz);
        }else{
            CópiaDaArvore.raiz = null;
        }

        return CópiaDaArvore;


    }
   

    // Métodos obrigatórios da interface (Stubs apenas para o código rodar)
    @Override
    public void inserir(K chave, V item) {
        // Sua implementação original de inserção vai aqui
    }

    @Override
    public void remover(K chave) throws Exception {
        
        raiz = remover(chave, raiz);

    }


    private No<K,V> remover( K chave, No<K,V> i) throws Exception{

        if(i==null){
            throw new Exception("ERRO");
        }

        int comp = comparador.compare(chave, i.getChave());

        if(comp>0){ //chave maior que elemento atual

            i.setDir(remover(chave, i.getDir()));

        }

        else if(comp<0){ //chave menor que o elmento atual

            i.setEsq(remover(chave, i.getEsq()));

        }

        else if(i.getDir()==null){ //caso so tenha o filho da esquerda - CASO DE 1 FILHO
            i=i.getEsq();
        }

        else if(i.getEsq()==null){ //caso so tenha o filho da direita - CASO 1 FILHO
            i=i.getDir();
        }

        else{ //caso tenha o filho da esquerda e da direita - CASO 2 FILHOS

            i.setEsq(maiorEsq(i, i.getEsq()));
            
        }

        return i;

    }

    private No<K,V> maiorEsq(No<K,V> i, No<K,V> j){

        if(j.getDir()==null){
            i.setChave(j.getChave()); //i copia o valor de j
            i.setItem(j.getItem()); //TALVEZ NAO SEJA OBRIGATORIO ESSA LINHA OLHA DPS
            j=j.getEsq(); // j avança pra esquerda p n ter o mesmo valor de i
        }
        else{
            j.setDir(maiorEsq(i, j.getDir()));
        }

        return j;

    }


    //----------------
    // agora o remover void

    //faz aqq

    // ------------



    public int contarFolhas(){
        return contarFolhas(this.raiz);
    }

    private int contarFolhas(No<K,V> i) {

        if(i==null){
            return 0;
        }

        if(i.getEsq()==null && i.getDir()==null){
            return 1;
        }

        return contarFolhas(i.getEsq()) + contarFolhas(i.getDir());

    }




    public void inserir(K chave, V item){

        raiz = inserir(raiz,chave,item);

    }

    private No<K,V> inserir(No<K,V> i, K chave, V item){

        if(i==null){
            return new No<>(chave,item);
        }

        int comp = this.comparador.compare(chave, i.getChave());

        if(comp>0){
            i.setDir(inserir(i.getDir(),chave,item));
        }
        else if(comp<0){
            i.setEsq(inserir(i.getEsq(), chave, item));
        }
        else{
            i.setItem(item);
        }

        return i;   

    }


    //lista de exercicios do gemini

    public int contarNos(No<K,V> i){

        if(i==null){
            return 0;
        }
        else{
            return 1+contarNos(i.getEsq())+contarNos(i.getDir());
        }

    }

    public int altura(No<K,V> i){

        if(i==null){
            return 0;
        }

        

        


    }

    public V pesquisar(K chave, No<K,V> i){

        if(i==null){
            return null;
        }

        int comp = this.comparador.compare(chave, i.getChave());

        if(comp>0){

            return pesquisar(chave, i.getDir());

        }
        else if(comp<0){
            return pesquisar(chave, i.getEsq());
        }
        else{
            return i.getItem();
        }

    }



    public K encontrarMaiorChave() throws Exception{

        if(this.raiz==null){
            throw new Exception("Árvore vazia.");
        }

        return encontrarMaiorChave(this.raiz);

    }

    private K encontrarMaiorChave(No<K,V> i){


        if(i.getDir()!=null){
            return encontrarMaiorChave(i.getDir());
        }
        else{
            return i.getChave();
        }

        
    }



    public boolean ehIgual(ABB<K,V> outra){


        if(this==outra){
            return true;
        }
        

        return ehIgual(this.raiz, outra.raiz);

    }

    private boolean ehIgual(No<K,V> i, No<K,V> j){


    }









}
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

        ABB<K,V> novaArvore = new ABB<>();

        novaArvore.raiz = this.raiz.clone();

        return novaArvore;

    }
   

    // Métodos obrigatórios da interface (Stubs apenas para o código rodar)
    @Override
    public void inserir(K chave, V item) {

        No<K,V> nóNovo = new No<>();
        nóNovo.setChave(chave);
        nóNovo.setItem(item);

        this.raiz=inserir(chave, item, this.raiz, nóNovo);

    }

    public No<K,V> inserir(K chave, V item, No<K,V> i, No<K,V> novoNo){

        if(i==null){
            return novoNo;
        }

        int comp = this.comparador.compare(chave, i.getChave());

        if(comp>0){
            i.setDir(inserir(chave, item, i.getDir(), novoNo));
        }
        else if(comp<0){
            i.setEsq(inserir(chave, item, i.getEsq(), novoNo));
        }
        else{
            i.setItem(item);
        }

        return i;


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




    

    //lista de exercicios do gemini

    public int contarNos(No<K,V> i){

        if(i==null){
            return 0;
        }
        else{
            return 1+contarNos(i.getEsq())+contarNos(i.getDir());
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

        if(i==null && j==null){
            return true;
        }
        if(i==null && j!=null){
            return false;
        }
        if(i!=null && j==null){
            return false;
        }



        if(!i.getItem().equals(j.getItem())){
            return false;
        }
        if(!i.getChave().equals(j.getChave())){
            return false;
        }

        return ehIgual(i.getDir(),j.getDir()) && ehIgual(i.getEsq(), j.getEsq());
     


    }



    

    public int tamanho(){
        return tamanho(this.raiz);
    }

    private int tamanho(No<K,V> i){

        if(i==null){
            return 0;
        }

        return 1+tamanho(i.getEsq())+tamanho(i.getDir());

    }

    public int altura(No<K,V> i){

        if(i==null){
            return 0;
        }

        return 1+Math.max(altura(i.getEsq()), altura(i.getDir()));

    }

    public K menorChave() throws Exception{

        if(this.raiz==null){
            throw new Exception("ARVORE VAZIA");
        }
        return menorChave(this.raiz);

    }

    private K menorChave(No<K,V> i){

        if(i.getEsq()==null){
            return i.getChave();
        }

        return menorChave(i.getEsq());

    }

    public void imprimirEmOrdem(No<K,V> i){

        if(i!=null){
            imprimirEmOrdem(i.getEsq());
            System.out.println(i.toString());
            imprimirEmOrdem(i.getDir());
        }

    }

    public int contadorDeFolhas(No<K,V> i){

        if(i==null){
            return 0;
        }

        if(i.getEsq()==null && i.getDir()==null){
            return 1;
        }


        return contadorDeFolhas(i.getEsq())+contadorDeFolhas(i.getDir());


    }


    public boolean contémChave(K chave) throws Exception{

        return contémChave(this.raiz, chave);

    }

    private boolean contémChave(No<K,V> i , K chave) throws Exception{

        if(i==null){
            return false; //n é exception, pq ai a chave n foi encontrada
        }

        int comp = this.comparador.compare(chave, i.getChave());

        if(comp>0){
            return contémChave(i.getDir(), chave);
        }
        else if(comp<0){
            return contémChave(i.getEsq(), chave);
        }
        else{
            return true;
        }


    }

    public ABB<K,V> espelho(){
        ABB<K,V> arvoreNova = new ABB<>();

        arvoreNova.raiz = espelho(this.raiz);

        return arvoreNova;

    }

    private No<K,V> espelho(No<K,V> i){

        if(i==null){
            return null;
        }

        No<K,V> nóEspelhado = new No<>();

        nóEspelhado.setItem(i.getItem());
        nóEspelhado.setChave(i.getChave());        

        nóEspelhado.setEsq(espelho(i.getDir()));
        nóEspelhado.setDir(espelho(i.getEsq()));

        return nóEspelhado;

    }

    public No<K,V> clonarArvoreAUTORAL(No<K,V> i){

        if(i==null){
            return null;
        }

        No<K,V> nóClone = new No<>();

        nóClone.setItem(i.getItem());
        nóClone.setChave(i.getChave());

        nóClone.setEsq(clonarArvoreAUTORAL(i.getEsq()));
        nóClone.setDir(clonarArvoreAUTORAL(i.getDir()));

        return nóClone;


    }















}
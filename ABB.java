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

}
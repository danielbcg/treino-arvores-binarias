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
    public V remover(K chave) {
        // Sua implementação original de remoção vai aqui
        return null;
    }
}
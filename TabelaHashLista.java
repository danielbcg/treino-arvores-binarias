public class TabelaHashLista<K, V> {

    private Lista<Entrada<K,V>>[] tabela;
    private int tamanho;

    public TabelaHashLista(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = (Lista<Entrada<K,V>>[]) new Lista[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new Lista<>();
        }
    }

    private int hash(K chave) {
        return Math.abs(chave.hashCode()) % tamanho;
    }

    public void inserir(K chave, V valor) {
        
        int posicao = hash(chave);

        Entrada<K,V> nova = new Entrada<>(chave, valor);

        Celula<Entrada<K,V>> aux = tabela[posicao].getPrimeiro().getProximo();

        while(aux!=null){

            if(aux.getItem().getChave().equals(chave)){ // se tiver uma chave igual n insere nada
                return;
            }

            aux=aux.getProximo();

        }
        
        tabela[posicao].inserirFim(nova);

    }

    public V pesquisar(K chave) {
        
        int posicao = hash(chave);

        Celula<Entrada<K,V>> aux = tabela[posicao].getPrimeiro().getProximo();

        while (aux!=null) {
            
            if(aux.getItem().getChave().equals(chave)){
                return aux.getItem().getValor();
            }
            aux=aux.getProximo();

        }
        return null;

        

    }

    public void remover(K chave) {
        
        int posicao= hash(chave);

        Celula<Entrada<K,V>> aux = tabela[posicao].getPrimeiro().getProximo();
        int indice=0;

        while(aux !=null ){

            if(aux.getItem().getChave().equals(chave)){
                tabela[posicao].remover(indice);
                return;
            }

            indice++;
            aux = aux.getProximo();

        }


    }

    public boolean vazia(){

        
        for(int i=0; i<tamanho;i++){
            if(!tabela[i].vazia()) return false; 
        }   
        return true;


    }


    public int contarElementos(){

        int qtd=0;

        for(int i=0; i<tamanho;i++){

            Celula<Entrada<K,V>> aux = tabela[i].getPrimeiro().getProximo();

            while(aux!=null){
                qtd++;
                aux=aux.getProximo();
            }

        }

        return qtd;

    }



    

   
    
    




    

}
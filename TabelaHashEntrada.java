public class TabelaHashEntrada<K, V> {

    private Entrada<K,V>[] tabela;
    private int tamanho;

    public TabelaHashEntrada(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = (Entrada<K,V>[]) new Entrada[tamanho];
        // tudo começa null
    }

    private int hash(K chave) {
        return Math.abs(chave.hashCode()) % tamanho;
    }

    public int rehash(int posicao){
        return (posicao+1)%tamanho;
    }

    public void inserir(K chave, V valor) {
        int posicao = hash(chave);

        Entrada<K,V> nova = new Entrada<>(chave,valor);

        if(tabela[posicao] == null || tabela[posicao].isRemovida()){
            tabela[posicao]=nova;
        }else{

            while(tabela[posicao] !=null && tabela[posicao].isRemovida()){
                posicao = rehash(posicao);
            }
            tabela[posicao]=nova;
        }

    }

    public V pesquisar(K chave) {
    
        int posicao=hash(chave);

        if(tabela[posicao]==chave){
            return tabela[posicao].getItem();
        }
        return null;

    }

    public void remover(K chave) {
        int posicao=hash(chave);

        if(tabela[posicao]!=null && tabela[posicao].getChave().equals(chave)){
            tabela[posicao].setRemovida(true);
        }
    }


    public Entrada<K,V>[] reparação(Entrada<K,V>[] tabela){

        Entrada<K,V>[] tabelaReparada = new Entrada[tamanho];

        for(int i=0; i<tamanho;i++){
            if(tabela[i]!=null && !tabela[i].isRemovida()){
                tabelaReparada[i]=tabela[i];
            }
        }

        return tabelaReparada;


    }

}
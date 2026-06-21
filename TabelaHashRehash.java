public class TabelaHashRehash {

    private int[] tabela;
    private int tamanho;

    public TabelaHashRehash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = -1;
        }
    }

    private int hash(int chave) {
        return chave%tamanho;
    }

    private int rehash(int posicao) {
        return (posicao+1)%tamanho;
    }

    public void inserir(int chave) {
       
        int posicao = hash(chave);
        
        while (tabela[posicao]!=-1) {

            posicao = rehash(posicao);
        
        }
        tabela[posicao]=chave;
    
    }

    public boolean pesquisar(int chave) {
        
        int posicao = hash(chave);
        int posicaoInicial = posicao;

        while(tabela[posicao] != chave){ // e o slot vazio?
            posicao= rehash(posicao);
            if(posicao==posicaoInicial){
                return false;
            }
        }
        return tabela[posicao]==chave;
    }

    public void remover(int chave) {
        
        int posicao = hash(chave);
        int posicaoInicial = posicao;

        while(tabela[posicao] != chave){
            posicao = rehash(posicao);
            if(posicao==posicaoInicial){
                throw new IllegalArgumentException("chave nao existe");
            }
        }
        tabela[posicao] = -1;


    }

    public void imprimir() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println("[" + i + "] = " + (tabela[i] == -1 ? "vazio" : tabela[i]));
        }
    }
}
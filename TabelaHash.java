public class TabelaHash {

    private int[] tabela;
    private int tamanho;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new int[tamanho];
        // inicializa tudo com -1 (posição vazia)
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = -1;
        }
    }

    private int hash(int chave) {
        return chave % tamanho;
    }

    public void inserir(int chave) {
        tabela[hash(chave)] = chave;
    }

    public boolean pesquisar(int chave) {
        if(tabela[hash(chave)]==chave){
            return true;
        }
        return false;
    }

    public void remover(int chave) {
        if(tabela[hash(chave)] == chave){
            tabela[hash(chave)]=-1;
        }
    }

    public void imprimir() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println("[" + i + "] = " + (tabela[i] == -1 ? "vazio" : tabela[i]));
        }
    }
}
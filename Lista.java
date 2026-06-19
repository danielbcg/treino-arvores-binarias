public class Lista<E> {
    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista() {
        primeiro = new Celula<>();
        ultimo = primeiro;
        tamanho = 0;
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void inserir(E novo, int posicao) {
        if (posicao < 0 || posicao > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        Celula<E> anterior = primeiro;
        for (int i = 0; i < posicao; i++) {
            anterior = anterior.getProximo();
        }

        Celula<E> novaCelula = new Celula<>(novo, anterior.getProximo());
        anterior.setProximo(novaCelula);

        if (anterior == ultimo) {
            ultimo = novaCelula;
        }

        tamanho++;
    }

    public E remover(int posicao) {
        if (vazia()) {
            throw new RuntimeException("Lista vazia!");
        }
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        Celula<E> anterior = primeiro;
        for (int i = 0; i < posicao; i++) {
            anterior = anterior.getProximo();
        }

        Celula<E> aRemover = anterior.getProximo();
        anterior.setProximo(aRemover.getProximo());

        if (aRemover == ultimo) {
            ultimo = anterior;
        }

        tamanho--;
        return aRemover.getItem();
    }


    public void inserirFim(E novo){

        Celula<E> celulaNova = new Celula<>();

        ultimo.setProximo(celulaNova);
        ultimo=celulaNova;
        tamanho++;

    }

    public void concatenar(Lista<E> outraLista){

        Celula<E> aux = outraLista.primeiro.getProximo();

        if(aux!=null){  
            outraLista.inserirFim(aux.getItem());
            aux=aux.getProximo();
        }

    }



    public void inserirInicio(E novo){

        Celula<E> celulaNova = new Celula<>(novo);

        celulaNova.setProximo(primeiro.getProximo());
        primeiro.setProximo(celulaNova);

    }


    //importante
    public Celula<E> getPrimeiro() {
        return primeiro;
    }


}
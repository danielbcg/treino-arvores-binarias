public class Lista<E> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista(){
        Celula<E> sentinela = new Celula<E>();
        this.primeiro = this.ultimo = sentinela;
        this.tamanho = 0;
    }

    public boolean vazia(){
        return (this.primeiro == this.ultimo);
    }

    public void inserirFinal(E valor){
        Celula<E> novaCelula = new Celula<>(valor);
        this.ultimo.setProximo(novaCelula);
        this.ultimo = novaCelula;
        this.tamanho++;
    }

    public int tamanho(){
        return tamanho;
    }

    public E get(int posicao){
        Celula<E> aux = this.primeiro.getProximo();
        for(int i = 0; i < posicao; i++){
            aux = aux.getProximo();
        }
        return aux.getItem();
    }
}
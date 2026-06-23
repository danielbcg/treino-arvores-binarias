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

        Celula<E> novaCelula = new Celula<>(novo);

        ultimo.setProximo(novaCelula);
        ultimo=novaCelula;
        tamanho++;

    }

    public void concatenar(Lista<E> lista){

        Celula<E> aux = lista.primeiro.getProximo();

        while(aux!=null){
            this.inserirFim(aux.getItem());
            aux=aux.getProximo();
        }

    }


    public void inserirInicio(E novo){

        Celula<E> celulaNova = new Celula<>(novo);

        celulaNova.setProximo(primeiro.getProximo());
        primeiro.setProximo(celulaNova);

    }





    public int ocorrencias(Lista<E> lista){

        return ocorrencias(primeiro.getProximo());

    }

    private int ocorrencias(Celula<E> atual){

        int quantia=0;

        E valor = atual.getItem();

        while (atual!=null) {
            
            if(atual.getItem().equals(valor)){
                quantia++;
            }

            

        }

        return quantia;

    }















    public void concatenation(Lista<E> outra){

        Celula<E> aux = outra.primeiro.getProximo();

        for(int i=0;i<outra.getTamanho();i++){

            this.inserir(aux.getItem(), this.getTamanho());
            aux=aux.getProximo();

        }

    }






    public E get(int posicao){

        Celula<E> aux = this.primeiro.getProximo();

        for(int i=0; i<posicao;i++){

            aux=aux.getProximo();


        }

        return aux.getItem();



    }








    public boolean valorExiste(E atual){

        Celula<E> aux = this.primeiro.getProximo();

        while(aux!=null){

            if(aux.getItem().equals(atual)){
                return true;
            }

            aux=aux.getProximo();

        }

        return false;


    }






























}
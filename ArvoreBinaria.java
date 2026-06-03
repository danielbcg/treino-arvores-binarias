package ArvoreBinaria;

public class ArvoreBinaria<E> {

    public No<E> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    No remover(int x, No i) throws Exception{

        if(i == null) {
            throw new Exception("Erro");
        }

        else if( x < i.elemento() ){
            i.esq = remover(x, i.esq);
        }

        else if( x > i.elemento() ){
            i.dir = remover(x, i.dir);
        }

        else if( i.dir == null ){
            i = i.esq;
        }

        else if( i.esq == null ){
            i = i.dir;
        }

        else{
            i.esq = maiorEsq(i,i.esq);
        }

        return i;
    }

    No maiorEsq(No i, No j){
        
        if(j.dir == null) {
            i.elemento = j.elemento;
            j=j.esq;
        }
        else{
            j.dir = maiorEsq(i, j.dir);
        }

        return j;
    }





    public void caminharCentral(No i){ //em-ordem = esquerda -> raiz -> direita

        if(i!=null){
            caminharCentral(i.esq);
            System.out.println(i+" ");
            caminharCentral(i.dir);
        }


    }

    public void caminharPreOrdem(No i){ //pre-ordem = raiz -> esquerda -> direita

        if(i!=null){
            System.out.println(i+" ");
            caminharPreOrdem(i.esq);
            caminharPreOrdem(i.dir);
        }

    }

    public void caminharPosOrdem(No i){ //pos-ordem = esquerda -> direita -> raiz

        if(i!=null){
            caminharPosOrdem(i.esq);
            caminharPosOrdem(i.dir);
            System.out.println(i+" ");
        }

    }










    
    
    // --- MÉTODOS AUXILIARES ---

    public int getMaior() {
        if (raiz == null) throw new IllegalArgumentException("Vazia");
        No<E> atual = raiz;
        while (atual.dir != null) {
            atual = atual.dir;
        }
        return (Integer) atual.elemento;
    }

    public int getMenor() {
        if (raiz == null) throw new IllegalArgumentException("Vazia");
        No<E> atual = raiz;
        while (atual.esq != null) {
            atual = atual.esq;
        }
        return (Integer) atual.elemento;
    }
}
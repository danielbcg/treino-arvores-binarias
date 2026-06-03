package ArvoreBinaria;

public class No<E> {
    // Atributos públicos permitem o uso de i.esq, i.dir e i.elemento
    public E elemento; 
    public No<E> esq;
    public No<E> dir;

    // Construtor atualizado para os novos nomes
    public No(E elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
}
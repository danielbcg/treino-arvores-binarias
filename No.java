public class No<K, V> {
    private K chave;
    private V item;
    private No<K, V> direita;
    private No<K, V> esquerda;

    // Métodos solicitados pelo enunciado
    public V getItem() {
        return this.item;
    }

    public void setItem(V item) {
        this.item = item;
    }

    public K getChave() {
        return this.chave;
    }

    public void setChave(K chave) {
        this.chave = chave;
    }

    public No<K, V> getDir() {
        return this.direita;
    }

    public void setDir(No<K, V> direita) {
        this.direita = direita;
    }

    public No<K, V> getEsq() {
        return this.esquerda;
    }

    public void setEsq(No<K, V> esquerda) {
        this.esquerda = esquerda;
    }

    public No<K,V> clone(No<K,V> nóAtual){

        if(nóAtual==null){
            return null;
        }

        No<K,V> nóClonado = new No<>();

        nóClonado.setChave(nóAtual.getChave());
        nóClonado.setItem(nóAtual.getItem());

        nóClonado.setEsq(clone(nóAtual.getEsq()));
        nóClonado.setDir(clone(nóAtual.getDir()));

        return nóClonado;

    }


    
}
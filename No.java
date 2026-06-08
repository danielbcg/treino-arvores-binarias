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

    public No<K,V> clone(No<K,V> i){

        No<K,V> nóClone = new No<>();

        if(i==null){
            return null;
        }

        nóClone.setItem(i.getItem());
        nóClone.setChave(i.getChave());

        nóClone.setEsq(clone(i.getEsq()));
        nóClone.setDir(clone(i.getDir()));

        return nóClone;

    }


    
}
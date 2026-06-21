public class Entrada<K,V> {
    private K chave;
    private V valor;
    private boolean removida;

    public Entrada(K chave, V valor){
        this.chave=chave;
        this.valor=valor;
        this.removida=false;
    }

    public boolean isRemovida(){
        return removida;
    }

    public void setRemovida(boolean removida){
        this.removida = removida;
    }
    
    public K getChave() {
        return chave;
    }

    public V getValor() {
        return valor;
    }
}

import java.util.Comparator;

public interface IMapeamento<K, V> {
    public boolean vazia();
    public void inserir(K chave, V item);
    public V remover(K chave);
}
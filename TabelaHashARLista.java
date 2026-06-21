public class TabelaHashARLista<K, V> implements IMapeamento<K, V> {
    private Entrada<K, V>[] t1;           // array principal, tamanho tamPrincipal
    private Lista<Entrada<K, V>> reserva; // única lista, área de reserva
    private int tamPrincipal;

    public TabelaHashARLista(int tamPrincipal) {
        this.tamPrincipal = tamPrincipal;
        this.t1 = (Entrada<K, V>[]) new Entrada[tamPrincipal];
        this.reserva = new Lista<>();
    }

    private int hash1(K chave) {
        return Math.abs(chave.hashCode()) % tamPrincipal;
    }

    public void inserir(K chave, V item) {
        
        int posicao = hash1(chave);
        Entrada<K,V> nova = new Entrada<>(chave,item);

        if(t1[posicao]!=null && !t1[posicao].isRemovida()){
            reserva.inserir(nova, reserva.getTamanho());
        }
        else{
            t1[posicao]=nova;
        }

    }


    public V pesquisar(K chave){

        int posicao = hash1(chave);

        if(t1[posicao]!=null && !t1[posicao].isRemovida() && !t1[posicao].getChave().equals(chave)){


            
        }

    }













    @Override
    public boolean vazia() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'vazia'");
    }

    @Override
    public V remover(K chave) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }
}
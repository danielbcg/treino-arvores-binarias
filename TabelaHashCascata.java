public class TabelaHashCascata<K, V> implements IMapeamento<K, V> {
    private Entrada<K, V>[] t1;
    private Entrada<K, V>[] t3;
    private ABB<K, V> arvore;
    private int tam1;
    private int tam3;

    public TabelaHashCascata(int tam1, int tam3) {
        this.tam1 = tam1;
        this.tam3 = tam3;
        this.t1 = (Entrada<K, V>[]) new Entrada[tam1];
        this.t3 = (Entrada<K, V>[]) new Entrada[tam3];
        this.arvore = new ABB<>();
    }

    private int hash1(K chave) { return Math.abs(chave.hashCode()) % tam1; }
    private int hash3(K chave) { return Math.abs(chave.hashCode()) % tam3; }
    private int rehash3(int posicao) { return (posicao + 1) % tam3; }

    public void inserir(K chave, V item) {
        
        int posicao = hash1(chave);

        Entrada<K,V> nova = new Entrada<K,V>(chave, item);

        if(t1[posicao]!=null && !t1[posicao].isRemovida()){


            int posicao3=hash3(chave);


                if(t3[posicao3]!=null && !t3[posicao3].isRemovida()){

                    int posicaoInicial = posicao3;

                    while (t3[posicao3]!=null && !t3[posicao3].isRemovida()) {

                        posicao3 = rehash3(posicao3);

                        if(posicao3==posicaoInicial){

                            this.arvore.inserir(chave, item);
                            return;
                        }

                    }

                    t3[posicao3]=nova;

                }else{
                    t3[posicao3]=nova;
                }

        }else{
            t1[posicao]=nova;
        }





    }   


    public boolean pesquisar(K chave){

        int posicao=hash1(chave);

        if(!t1[posicao].getChave().equals(chave) && t1[posicao]==null){

            int posicao3 = hash3(chave);

            if(!t3[posicao3].getChave().equals(chave)){ // DEPOIS REFAÇA    

                int posicaoInicial=posicao3;

                while (!t3[posicao3].getChave().equals(chave)) {
                    posicao3=rehash3(posicao3);
                    if(posicaoInicial==posicao3){
                        return arvore.contémChave(chave);
                    }

                }
                return true;



            }else{
                return true;
            }

        }

        else{
            return true;
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
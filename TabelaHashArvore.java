public class TabelaHashArvore<K, V> {

    private ABB<K,V>[] tabela;
    private int tamanho;

    public TabelaHashArvore(int tamanho) {
        this.tamanho = tamanho; 
        this.tabela = (ABB<K,V>[]) new ABB[tamanho];
        for (int i = 0; i < tamanho; i++) {
            this.tabela[i] = new ABB<>();
        }
    }

    private int hash(K chave) {
        return Math.abs(chave.hashCode()) % tamanho;
    }

    public boolean contemChave(K chave) {
        int posicao=hash(chave);

        return tabela[posicao].contémChave(chave);
    }

    public void remover(K chave) throws Exception{
        int posicao=hash(chave);

        tabela[posicao].remover(chave);
    }

    public void inserir(K chave, V valor){
        int posicao=hash(chave);

        tabela[posicao].inserir(chave,valor);
    }

    public Lista<V> obterTodosValores(){

        Lista<V> lista = new Lista<>();

        for(int i=0;i<tamanho;i++){
            
            tabela[i].adicionarValoresNaLista(lista);

        }

        return lista;

    }

    


    //dps termina isso
    public K chaveDoMaiorValor(){

        int maiorValor=0;



        for(int i=0;i<tamanho;i++){

            K maiorAtual = tabela[i].getMaior();

            int maiorInt = (int) maiorAtual;
            

            if(maiorInt > maiorValor){
                maiorValor=maiorInt;
            }

        }

        K resultado = (K) Integer.valueOf(maiorValor);

        return resultado;



    }


    //dps termina isso em cima
    












    public boolean valorExiste(V valor){

        for(int i=0; i<tamanho;i++){

            if(tabela[i].pesquisaValor(valor)==true){
                return true;
            }

        }

        return false;

    }






    public int contarNivelMaximo(){

        int maiorAltura=0;

        for(int i=0; i<tamanho;i++){

            if(tabela[i].getAltura()>maiorAltura){
                maiorAltura=tabela[i].getAltura();
            }

        }

        return maiorAltura;



    }









    public Lista<K> obterTodasAsChaves(){
    
        Lista<K> lista = new Lista<>();

        for(int i=0;i<tamanho;i++){

            tabela[i].todasAsChaves(lista);

        }
    
        return lista;


    }







    public boolean todasArvoresBalanceadas(){



        for(int i=0; i<tamanho;i++){

            if(tabela[i].balanceada()>1){
                return false;
            }

        }

        return true;



    }





    public V pesquisar(K chave){

        V valor = null;

        int posicao = hash(chave);

        valor = tabela[posicao].retornaValor(chave);

        return valor;


    }

























}
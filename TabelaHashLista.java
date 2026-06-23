public class TabelaHashLista<K, V> {

    private Lista<Entrada<K,V>>[] tabela;
    private int tamanho;

    public TabelaHashLista(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = (Lista<Entrada<K,V>>[]) new Lista[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new Lista<>();
        }
    }

    private int hash(K chave) {
        return Math.abs(chave.hashCode()) % tamanho;
    }

    public void inserir(K chave, V valor) {
        
        int posicao = hash(chave);

        Entrada<K,V> nova = new Entrada<>(chave, valor);

        Celula<Entrada<K,V>> aux = tabela[posicao].getPrimeiro().getProximo();

        while(aux!=null){

            if(aux.getItem().getChave().equals(chave)){ // se tiver uma chave igual n insere nada
                return;
            }

            aux=aux.getProximo();

        }
        
        tabela[posicao].inserirFim(nova);

    }

    public V pesquisar(K chave) {
       
        int posicao = hash(chave);


        Lista<Entrada<K,V>> atual = tabela[posicao];

        for(int i=0;i<atual.getTamanho();i++){

            Entrada<K,V> entradaAtual = atual.get(i);

            if(entradaAtual!=null && entradaAtual.getChave().equals(chave)){
                return entradaAtual.getValor();
            }
        }

        return null;


    }

    public void remover(K chave) {
        
        int posicao= hash(chave);

        Celula<Entrada<K,V>> aux = tabela[posicao].getPrimeiro().getProximo();
        int indice=0;

        while(aux !=null ){

            if(aux.getItem().getChave().equals(chave)){
                tabela[posicao].remover(indice);
                return;
            }

            indice++;
            aux = aux.getProximo();

        }


    }

    public boolean vazia(){

        
        for(int i=0; i<tamanho;i++){
            if(!tabela[i].vazia()) return false; 
        }   
        return true;


    }


    public int contarElementos(){

        int qtd=0;

        for(int i=0; i<tamanho;i++){

            Celula<Entrada<K,V>> aux = tabela[i].getPrimeiro().getProximo();

            while(aux!=null){
                qtd++;
                aux=aux.getProximo();
            }

        }

        return qtd;

    }



    

   

    //exercicios de fixaçao

    public int contarVazias(){

        int vazias=0;

        for(int i=0; i<tamanho;i++){

            if(tabela[i].vazia()){
                vazias++;
            }

        }

        return vazias;

    }




    public V valorNaPosicao(int indiceTabela, int indiceNaLista){

        if(tabela[indiceTabela] == null || indiceNaLista>=tabela[indiceTabela].getTamanho()){
            return null;
        }

        Entrada<K,V> aux = tabela[indiceTabela].remover(indiceNaLista);

        V valorDoIndice = aux.getValor();

        tabela[indiceTabela].inserir(aux, indiceNaLista);

        return valorDoIndice;

    }

    



    public V valorDaMaiorChave(){

        K maiorChave= null;
        V maiorValor= null;

        for(int i=0;i<tamanho;i++){

            for(int j=0; j<tabela[i].getTamanho();j++){

                Entrada<K,V> atual = tabela[i].get(j);

                if(maiorChave==null || atual.getChave().compareTo(maiorChave)>0){
                    maiorChave=atual.getChave();
                    maiorValor = atual.getValor();
                }


            }

        }

        return maiorValor;

    }







    
    public Lista<K> obterTodasChaves(){

        Lista<K> lista = new Lista<>();
        

        for(int i=0; i<tamanho;i++){

            Lista<Entrada<K,V>> atual = tabela[i];

            for(int j=0; j<tabela[i].getTamanho();j++){

                Entrada<K,V> entradaAtual = atual.get(j);
                K chaveAtual = entradaAtual.getChave();
                lista.inserir(chaveAtual, lista.getTamanho());

            }

        }
        return lista;

        
    }




    public Lista<V> obterValoresRepetidos(){

        Lista<V> listaAuxiliar = new Lista<>();
        Lista<V> resultado = new Lista<>();



        for(int i=0; i<tamanho;i++){

            Lista<Entrada<K,V>> atual = tabela[i];

            for(int j=0; j<tabela[i].getTamanho();j++){
            
                Entrada<K,V> valorAtual = atual.get(j);
                
                //se valor ja existe na lista 'listaAuxiliar' insere na 'resultado'

                if(listaAuxiliar.valorExiste(valorAtual.getValor())
                   && !resultado.valorExiste(valorAtual.getValor())){

                    resultado.inserir(valorAtual.getValor(), resultado.getTamanho());

                }

                //se valor ja existe na lista 'resultado' nao insere nela, só na auxiliar

                listaAuxiliar.inserir(valorAtual.getValor(), listaAuxiliar.getTamanho());

                
            

            }


        }


        return resultado;


    }





    public Lista<K> obterChavesComValorNulo(){

        Lista<K> resultado = new Lista<>();

        for(int i=0; i<tamanho;i++){

            Lista<Entrada<K,V>> atual = tabela[i];

            for(int j=0;j<atual.getTamanho();j++){

                Entrada<K,V> entradaAtual = atual.get(j);

                K chaveAtual = entradaAtual.getChave();
                V valorAtual = entradaAtual.getValor();

                //n pode ser .equals(null) pq da err, mas ta certo
                if(valorAtual==null){
                    resultado.inserir(chaveAtual,resultado.getTamanho());
                }


            }

        }


        return resultado;





    }








    public Lista<Entrada<K,V>> obterEntradasDaPosicao(int indice){

        Lista<Entrada<K,V>> lista = new Lista<>();

        for(int i=0;i<tabela[indice].getTamanho();i++){


            Entrada<K,V> entrada = tabela[indice].get(i);

            lista.inserir(entrada, lista.getTamanho());


        }

        return lista;


    }

    















    
    




    

}
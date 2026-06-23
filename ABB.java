import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ABB<K, V> implements IMapeamento<K, V> {
    private No<K, V> raiz;
    private Comparator<K> comparador;

    private void init(Comparator<K> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }

    public ABB() { 
        init(null); 
    }

    public ABB(Comparator<K> comparador) { 
        init(comparador); 
    }

    @Override
    public boolean vazia() { 
        return this.raiz == null; 
    }

    public No<K,V> getRaiz(){
        return raiz;
    }

    
    public ABB<K,V> clone(){

        ABB<K,V> novaArvore = new ABB<>();

        novaArvore.raiz = this.raiz.clone();

        return novaArvore;

    }
   

    // Métodos obrigatórios da interface (Stubs apenas para o código rodar)
    @Override
    public void inserir(K chave, V item) {

        No<K,V> nóNovo = new No<>();
        nóNovo.setChave(chave);
        nóNovo.setItem(item);

        this.raiz=inserir(chave, item, this.raiz, nóNovo);

    }

    public No<K,V> inserir(K chave, V item, No<K,V> i, No<K,V> novoNo){

        if(i==null){
            return novoNo;
        }

        int comp = this.comparador.compare(chave, i.getChave());

        if(comp>0){
            i.setDir(inserir(chave, item, i.getDir(), novoNo));
        }
        else if(comp<0){
            i.setEsq(inserir(chave, item, i.getEsq(), novoNo));
        }
        else{
            i.setItem(item);
        }

        return i;


    }

    @Override
    public void remover(K chave) throws Exception {
        
        raiz = remover(chave, raiz);

    }


    private No<K,V> remover( K chave, No<K,V> i) throws Exception{

        if(i==null){
            throw new Exception("ERRO");
        }

        int comp = comparador.compare(chave, i.getChave());

        if(comp>0){ //chave maior que elemento atual

            i.setDir(remover(chave, i.getDir()));

        }

        else if(comp<0){ //chave menor que o elmento atual

            i.setEsq(remover(chave, i.getEsq()));

        }

        else if(i.getDir()==null){ //caso so tenha o filho da esquerda - CASO DE 1 FILHO
            i=i.getEsq();
        }

        else if(i.getEsq()==null){ //caso so tenha o filho da direita - CASO 1 FILHO
            i=i.getDir();
        }

        else{ //caso tenha o filho da esquerda e da direita - CASO 2 FILHOS

            i.setEsq(maiorEsq(i, i.getEsq()));
            
        }

        return i;

    }

    private No<K,V> maiorEsq(No<K,V> i, No<K,V> j){

        if(j.getDir()==null){
            i.setChave(j.getChave()); //i copia o valor de j
            i.setItem(j.getItem()); //TALVEZ NAO SEJA OBRIGATORIO ESSA LINHA OLHA DPS
            j=j.getEsq(); // j avança pra esquerda p n ter o mesmo valor de i
        }
        else{
            j.setDir(maiorEsq(i, j.getDir()));
        }

        return j;

    }


    //----------------
    // agora o remover void

    //faz aqq

    // ------------



    public int contarFolhas(){
        return contarFolhas(this.raiz);
    }

    private int contarFolhas(No<K,V> i) {

        if(i==null){
            return 0;
        }

        if(i.getEsq()==null && i.getDir()==null){
            return 1;
        }

        return contarFolhas(i.getEsq()) + contarFolhas(i.getDir());

    }




    

    //lista de exercicios do gemini

    public int contarNos(No<K,V> i){

        if(i==null){
            return 0;
        }
        else{
            return 1+contarNos(i.getEsq())+contarNos(i.getDir());
        }

    }

    

    public V pesquisar(K chave, No<K,V> i){

        if(i==null){
            return null;
        }

        int comp = this.comparador.compare(chave, i.getChave());

        if(comp>0){

            return pesquisar(chave, i.getDir());

        }
        else if(comp<0){
            return pesquisar(chave, i.getEsq());
        }
        else{
            return i.getItem();
        }

    }



    public K encontrarMaiorChave() throws Exception{

        if(this.raiz==null){
            throw new Exception("Árvore vazia.");
        }

        return encontrarMaiorChave(this.raiz);

    }

    private K encontrarMaiorChave(No<K,V> i){


        if(i.getDir()!=null){
            return encontrarMaiorChave(i.getDir());
        }
        else{
            return i.getChave();
        }

        
    }



    public boolean ehIgual(ABB<K,V> outra){


        if(this==outra){
            return true;
        }
        

        return ehIgual(this.raiz, outra.raiz);

    }

    private boolean ehIgual(No<K,V> i, No<K,V> j){

        if(i==null && j==null){
            return true;
        }
        if(i==null && j!=null){
            return false;
        }
        if(i!=null && j==null){
            return false;
        }



        if(!i.getItem().equals(j.getItem())){
            return false;
        }
        if(!i.getChave().equals(j.getChave())){
            return false;
        }

        return ehIgual(i.getDir(),j.getDir()) && ehIgual(i.getEsq(), j.getEsq());
     


    }



    

    public int tamanho(){
        return tamanho(this.raiz);
    }

    private int tamanho(No<K,V> i){

        if(i==null){
            return 0;
        }

        return 1+tamanho(i.getEsq())+tamanho(i.getDir());

    }

    public int altura(No<K,V> i){

        if(i==null){
            return 0;
        }

        return 1+Math.max(altura(i.getEsq()), altura(i.getDir()));

    }

    public K menorChave() throws Exception{

        if(this.raiz==null){
            throw new Exception("ARVORE VAZIA");
        }
        return menorChave(this.raiz);

    }

    private K menorChave(No<K,V> i){

        if(i.getEsq()==null){
            return i.getChave();
        }

        return menorChave(i.getEsq());

    }

    public void imprimirEmOrdem(No<K,V> i){

        if(i!=null){
            imprimirEmOrdem(i.getEsq());
            System.out.println(i.toString());
            imprimirEmOrdem(i.getDir());
        }

    }

    public int contadorDeFolhas(No<K,V> i){

        if(i==null){
            return 0;
        }

        if(i.getEsq()==null && i.getDir()==null){
            return 1;
        }


        return contadorDeFolhas(i.getEsq())+contadorDeFolhas(i.getDir());


    }


    public boolean contémChave(K chave){

        return contémChave(this.raiz, chave);

    }

    private boolean contémChave(No<K,V> no , K chave){

        if(no==null){
            return false;
        }

        int comp = this.comparador.compare(chave, no.getChave());

        if(comp>0){
            return contémChave(no.getDir(),chave);
        }
        else if(comp<0){
            return contémChave(no.getEsq(), chave);
        }else{
            return true;
        }

    }

    public ABB<K,V> espelho(){
        ABB<K,V> arvoreNova = new ABB<>();

        arvoreNova.raiz = espelho(this.raiz);

        return arvoreNova;

    }

    private No<K,V> espelho(No<K,V> i){

        if(i==null){
            return null;
        }

        No<K,V> nóEspelhado = new No<>();

        nóEspelhado.setItem(i.getItem());
        nóEspelhado.setChave(i.getChave());        

        nóEspelhado.setEsq(espelho(i.getDir()));
        nóEspelhado.setDir(espelho(i.getEsq()));

        return nóEspelhado;

    }

    public No<K,V> clonarArvoreAUTORAL(No<K,V> i){

        if(i==null){
            return null;
        }

        No<K,V> nóClone = new No<>();

        nóClone.setItem(i.getItem());
        nóClone.setChave(i.getChave());

        nóClone.setEsq(clonarArvoreAUTORAL(i.getEsq()));
        nóClone.setDir(clonarArvoreAUTORAL(i.getDir()));

        return nóClone;


    }

    

    public int nivel(K chave) throws Exception{

        return nivel(chave, this.raiz);

    }

    private int nivel(K chave, No<K,V> i) throws Exception{

        if(i==null){
            return -1;
        }

        int comp = this.comparador.compare(chave,i.getChave());

        if(comp>0){
            if(nivel(chave,i.getDir())==-1){
                throw new Exception("CHAVE NAO ENCONTRADA");
            }
            return 1+nivel(chave,i.getDir());
        }
        else if(comp<0){
            if(nivel(chave,i.getEsq())==-1){
                throw new Exception("CHAVE NAO ENCONTRADA");
            }
            return 1+nivel(chave,i.getEsq());
        }
        else{
            return 0;
        }

    }


    public List<K> caminho (K chave){

        return caminho(chave, this.raiz, new ArrayList<>());

    }

    private List<K> caminho(K chave, No<K,V> i, List<K> lista){

        if(i==null){
            return null;
        }

        int comp = this.comparador.compare(chave, i.getChave());

        if(comp>0){
            lista.add(i.getChave());
            return caminho(chave, i.getDir(), lista);
        }
        else if(comp<0){
            lista.add(i.getChave());
            return caminho(chave, i.getEsq(), lista);
        }
        else{
            lista.add(i.getChave());
            return lista;
        }

    }


    public boolean iguais(ABB<K,V> outra){

        return iguais(this.raiz, outra.raiz)  ;      

    }

    private boolean iguais(No<K,V> i, No<K,V> j){

        if(i==null && j==null){
            return true;
        }

        if(i==null || j==null){ 
            return false;   
        }

        if(!i.getChave().equals(j.getChave())){
            return false;
        }

        return iguais(i.getEsq(), j.getEsq()) && iguais(i.getDir(), j.getDir());

    }



    public void inserirRec(K chave, V item){

        raiz = inserirRec(raiz, chave, item);

    }

    private No<K,V> inserirRec(No<K,V> i, K chave, V item){

        if(i==null){
            i = new No<>(chave, item);
        }

        int comp = this.comparador.compare(chave, i.getChave());

        if(comp>0){
            i.setDir(inserirRec(i.getDir(), chave, item));
        }
        else if(comp<0){
            i.setEsq(inserirRec(i.getEsq(), chave, item));
        }
        else{
            throw new IllegalArgumentException("CHAVE JA INSERIDA");
        }

        return i;


    }

    //dps faz o removerRec


    public ABB<K,V> recorte(K min, K max){
        return recorte(raiz, min, max);
    }

    private ABB<K,V> recorte(No<K,V> no, K min, K max){

        if(no==null){
            throw new IllegalArgumentException("ERRO");
        }

        int compMin = this.comparador.compare(min, no.getChave());
        int compMax = this.comparador.compare(max, no.getChave());

        if(compMin>0){
            return recorte(no.getDir(), min, max);
        }
        else if(compMax<0){
            return recorte(no.getEsq(), min, max);
        }
        else{

            ABB<K,V> esq = recorte(no.getEsq(), min, max);
            ABB<K,V> dir = recorte(no.getDir(), min, max);

            esq.inserir(no.getChave(), no.getItem());
            esq.inserirTodos(dir);

            return esq;

        }

    }

    public void inserirTodos(ABB<K,V> arvore){
        inserirTodos(raiz, arvore);
    }

    private void inserirTodos(No<K,V> i, ABB<K,V> arvore){

        if(i==null){
            throw new IllegalArgumentException("ERRO");
        }

        this.inserir(i.getChave(), i.getItem());
        arvore.inserirTodos(i.getEsq(), arvore);
        arvore.inserirTodos(i.getDir(), arvore);

        

    }



    public Lista<K> chavesMaiores(K chave){
        Lista<K> lista = new Lista<>();
        return chavesMaiores(raiz, chave,lista);
    }

    private Lista<K> chavesMaiores(No<K,V> no, K chave, Lista<K> lista){

        if(no==null){
            throw new IllegalArgumentException("NÓ VAZIO"); //pq tem q ser return lista? Depois olha melhor isso!
        }

        int comp = this.comparador.compare(chave, no.getChave());

        if(comp>0){
            return chavesMaiores(no.getDir(), chave,lista);
        }
        else{

            lista.inserirFinal(no.getChave());

            chavesMaiores(no.getEsq(), chave, lista);

            chavesMaiores(no.getDir(), chave, lista);

            return lista;

        }



    }


    




    //dps apaga se quiser

    public ABB<K,V> clonar(){
        ABB<K,V> cloneDaArvore = new ABB<>();
        cloneDaArvore.raiz = this.raiz.clonarNo();
        return cloneDaArvore;
    }





    public ABB<K,V> recorte( K min, K max){

        return recorte(raiz, min, max);

    }

    private ABB<K,V> recorte(No<K,V> no, K min, K max){

        if(no==null){
            return null;
        }

        int compMin = this.comparador.compare(min, no.getChave());
        int compMax = this.comparador.compare(max, no.getChave());

        if(compMin>0){
            return recorte(no.getDir(), min, max);
        }
        else if(compMax<0){
            return recorte(no.getEsq(), min, max);
        }
        else{

            ABB<K,V> subArvore = recorte(no.getEsq(), min, max);
            subArvore.inserir(no.getChave(), no.getItem());

            ABB<K,V> subDir = recorte(no.getDir(),min, max);

            subArvore.juntar(subDir);



            return subArvore;
        }

    }

    public ABB<K,V> juntar(ABB<K,V> outra){

        return juntar(outra.raiz, outra);

    }
    private ABB<K,V> juntar(No<K,V> no, ABB<K,V> outra){

        if(no==null){
            return null;
        }

        this.inserir(no.getChave(), no.getItem());

        juntar(no.getEsq(),outra);
        juntar(no.getDir(), outra);

        return this;

    }







    public Lista<K> chavesMaiores(K chave){
        return chavesMaiores(raiz, chave);
    }

    private Lista<K> chavesMaiores(No<K,V> no, K chave){

        if(no==null){
            return new Lista<>();
        }

        int comp = this.comparador.compare(chave, no.getChave());

        if(comp>=0){
            return chavesMaiores(no.getDir(), chave);
        }
        else{

            Lista<K> lista = chavesMaiores(no.getEsq(), chave);

            lista.inserirFim(no.getChave());

            Lista<K> dir = chavesMaiores(no.getDir(), chave);

            lista.concatenar(dir);


            return lista;
            

        }
    }

    //questao do claude

        public Lista<V> valoresPorNivel(int nivel){

            return valoresPorNivel(raiz, 0, nivel);

        }
        private Lista<V> valoresPorNivel(No<K,V> no, int nivelAtual, int nivelBuscado){

            if(no==null){
                return new Lista<>();
            }

            if(nivelAtual == nivelBuscado){
                Lista<V> lista = new Lista<>();
                lista.inserirFim(no.getItem());
                return lista;
            }
            else{

                Lista<V> esq = valoresPorNivel(no.getEsq(), nivelAtual+1, nivelBuscado);
                Lista<V> dir = valoresPorNivel(no.getDir(), nivelAtual+1, nivelBuscado);

                esq.concatenar(dir);
                return esq;

            }

            

        }




        public Lista<K> chavesDasFolhas(){

            if(this.raiz==null){
                return new Lista<>();
            }

            return chavesDasFolhas(raiz);

        }

        private Lista<K> chavesDasFolhas(No<K,V> no){

        
            if(no==null){
                return new Lista<>();
            }

            if(no.getEsq()==null && no.getDir()==null){

                Lista<K> lista = new Lista<>();
                lista.inserirFim(no.getChave());
                return lista;

            }
            else{
                
                Lista<K> esq = chavesDasFolhas(no.getEsq());
                Lista<K> dir = chavesDasFolhas(no.getDir());


                esq.concatenar(dir);
                return esq;

            }



        }



        public Lista<No<K,V>> way (K chave){

            return way(raiz, chave);

        }

        private Lista<No<K,V>> way (No<K,V> no, K chave){

            if(no==null){
                return new Lista<>();
            }

            int comp = this.comparador.compare(chave, no.getChave());

            if(comp>0){
               Lista<No<K,V>> lista = way(no.getDir(), chave);
               lista.inserirInicio(no);
               return lista;
            }
            else if(comp<0){
                Lista<No<K,V>> lista = way(no.getEsq(), chave);
                lista.inserirInicio(no);
                return lista;
            }
            else{
                Lista<No<K,V>> lista = new Lista<>();
                lista.inserirInicio(no);
                return lista;
                
            }
            
        }




        public Lista<K> emOrdemCrescente(){
            return emOrdemCrescente(raiz);
        }

        private Lista<K> emOrdemCrescente(No<K,V> no){

            if(no==null){
                return new Lista<>();
            }

            Lista<K> lista = emOrdemCrescente(no.getEsq());
            lista.inserirFim(no.getChave());
            Lista<K> dir = emOrdemCrescente(no.getDir());
            lista.concatenar(dir);

            return lista;

        }


        public Lista<K> emOrdemDecrescente(){
            return emOrdemDecrescente(raiz);
        }
        private Lista<K> emOrdemDecrescente(No<K,V> no){


            if(no==null){
                return new Lista<>();
            }

            Lista<K> dir = emOrdemDecrescente(no.getDir());
            dir.inserirFim(no.getChave());
            Lista<K> esq = emOrdemDecrescente(no.getEsq());
            dir.concatenar(esq);

            return dir;

        }


        public int contarUmFilho(){
            return contarUmFilho(raiz);
        }

        private int contarUmFilho(No<K,V> no){

            if(no==null){
                return 0;
            }

            if(no.getEsq()!=null && no.getDir()==null){
                return 1+contarUmFilho(no.getEsq());
            }
            else if(no.getEsq()==null && no.getDir()!=null){
                return 1+contarUmFilho(no.getDir());
            }
            else{
                return contarUmFilho(no.getEsq())+contarUmFilho(no.getDir());
            }

        }







        public int somaDosNiveis(){
            return somaDosNiveis(raiz, 0);
        }

        private int somaDosNiveis(No<K,V> no, int nivelAtual){

            if(no==null){
                return 0;
            }
            
            return nivelAtual+somaDosNiveis(no.getEsq(), nivelAtual+1)+somaDosNiveis(no.getDir(), nivelAtual+1);
        }







        public boolean eEstritamenteBinaria(){
            return eEstritamenteBinaria(raiz);
        }

        private boolean eEstritamenteBinaria(No<K,V> no){

            if(no==null){
                return true;
            }

            if(no.getEsq()!=null && no.getDir()==null){
                return false;
            }
            else if(no.getDir()==null && no.getEsq()!=null){
                return false;
            }
            else{
                return eEstritamenteBinaria(no.getEsq()) && eEstritamenteBinaria(no.getDir());
            }

        }





        public K maiorChaveNoIntervalo(K min, K max){
            return maiorChaveNoIntervalo(raiz, min, max);
        }

        private K maiorChaveNoIntervalo(No<K,V> no, K min, K max){

            if(no==null){
                return null;
            }

            int compMin = this.comparador.compare(min, no.getChave());
            int compMax = this.comparador.compare(max, no.getChave());

            if(compMin>0){
                return maiorChaveNoIntervalo(no.getDir(), min, max);
            }
            else if(compMax<0){
                return maiorChaveNoIntervalo(no.getEsq(), min, max);
            }
            else{
                
                


            }



        }



        public K[] emOrdemVetor(){
            return emOrdemVetor(raiz, 0, 0);
        }

        private void emOrdemVetor(No<K,V> no, K[] vetor, int[] indice){

            if(no==null){
                return;
            }

            emOrdemVetor(no.getEsq(), vetor, indice);
            vetor[indice[0]]=no.getChave();
            indice[0]++;
            emOrdemVetor(no.getDir(), vetor, indice);

        }


        public int getAltura(){

            return getAltura(raiz);

        }

        private int getAltura(No<K,V> no){

            if(no==null){
                return 0;
            }

            return 1+Math.max(getAltura(no.getEsq()),getAltura(no.getDir()));

        }



        

        









        public K[] emOrdemVetorInt(){
            K[] vetor = (K[]) new Object[tamanho()];
            emOrdemVetorInt(raiz, vetor, 0);
            return vetor;
        }

        private int emOrdemVetorInt(No<K,V> no, K[] vetor, int indice){

            if(no==null){
                return indice;
            }
            

            indice = emOrdemVetorInt(no.getEsq(), vetor, indice);
            vetor[indice]=no.getChave();
            indice++;
            indice = emOrdemVetorInt(no.getDir(), vetor, indice);

            return indice;


        }





        public K[] folhasVetor(){

            K[] vetor = (K[]) new Object[tamanho()];
            folhasVetor(raiz,vetor, 0);
            return vetor;

        }

        private int folhasVetor(No<K,V> no, K[] vetor, int indice){

            if(no==null){
                return indice;
            }

            indice = folhasVetor(no.getEsq(), vetor, indice);
            indice = folhasVetor(no.getDir(), vetor, indice);

            if(no.getEsq()==null && no.getDir()==null){
                vetor[indice]=no.getChave();
                indice++;
            }

            return indice;



        }




        public K[] emOrdemDecrescenteVetor(){
            K[] vetor = (K[]) new Object[tamanho()];
            emOrdemDecrescenteVetor(raiz, vetor, 0);
            return vetor;
        }

        private int emOrdemDecrescenteVetor(No<K,V> no, K[] vetor, int indice){


            if(no==null){
                return indice;
            }

            indice = emOrdemDecrescenteVetor(no.getDir(), vetor, indice);
            vetor[indice]=no.getChave();
            indice++;
            indice = emOrdemDecrescenteVetor(no.getEsq(), vetor, indice);


            return indice;



        }



        public K[] chavesPorNivelVetor(int nivel){
            K[] vetor = (K[]) new Object[tamanho()];
            chavesPorNivelVetor(raiz, vetor, 0, 0, nivel);
            return vetor;
        }

        private int chavesPorNivelVetor(No<K,V> no, K[] vetor, int indice, int nivelAtual, int nivelBuscado){

            if(no==null){
                return indice;
            }

            indice=chavesPorNivelVetor(no.getEsq(), vetor, indice, nivelAtual+1, nivelBuscado);
            if(nivelAtual==nivelBuscado){
                vetor[indice]=no.getChave();
                indice++;
            }
            indice=chavesPorNivelVetor(no.getDir(), vetor, indice, nivelAtual+1, nivelBuscado);

            return indice;

        }



        public Lista<V> valoresComChavePar(){
            return valoresComChavePar(raiz);
        }

        private Lista<V> valoresComChavePar(No<K,V>  no){

            if(no==null){
                return new Lista<>();
            }

            
            int chave = (int) no.getChave();
            
            Lista<V> esq = valoresComChavePar(no.getEsq());

            if(chave%2==0){
                esq.inserirFim(no.getItem());
            }

            Lista<V> dir = valoresComChavePar(no.getDir());

            esq.concatenar(dir);

            return esq;
                
        }



        public Lista<V> retornaValores(){

            Lista<V> lista = new Lista<>();
            retornaValores(raiz, lista);
            return lista;

        }

        private void retornaValores(No<K,V> no, Lista<V> lista){

            if(no==null){
                return;
            }

            retornaValores(no.getEsq(),lista);
            lista.inserirFim(no.getItem());
            retornaValores(no.getDir(),lista);

        }

        

            

        
    public void adicionarValoresNaLista(Lista<V> lista){
        adicionarValoresNaLista(raiz, lista);
    }

    private void adicionarValoresNaLista(No<K,V> no, Lista<V> lista){
        if(no==null) return;

        adicionarValoresNaLista(no.getEsq(), lista);
        lista.inserir(no.getItem(), lista.getTamanho());
        adicionarValoresNaLista(no.getDir(), lista);
    }



    //dps termina isso
    public K getMaiorItem(){
        if(raiz==null) return null;
        return getMaiorItem(raiz);
    }

    private K getMaiorItem(No<K,V> no){
    


    }

    //dps termina isso em cima















    public V retornaChave(K chave){

        return retornaChave(raiz,chave);

    }

    private V retornaChave(No<K,V> no, K chave){

        if(no==null){
            return null;
        }

        int comp = this.comparador.compare(chave, no.getChave());

        if(comp>0){
            return retornaChave(no.getDir(), chave);
        }
        else if(comp<0){
            return retornaChave(no.getEsq(), chave);
        }
        else{
            return no.getItem();
        }




        
    }





    public Lista<K> chavesMenores(K chave){
        return chavesMenores(raiz, chave);
    }

    private Lista<K> chavesMenores(No<K,V> no, K chave){

        if(no==null){
            return new Lista<>();
        }

        int comp = this.comparador.compare(chave, no.getChave());

        if(comp<=0){
            return chavesMenores(no.getEsq(), chave);
        }

        else{

            Lista<K> esq = chavesMenores(no.getEsq(), chave);
            esq.inserir(no.getChave(), esq.getTamanho());
            Lista<K> dir = chavesMenores(no.getDir(), chave);
            concatenando(esq, dir);


            return esq;

        }






    }


    public void concatenando(Lista<K> lista1, Lista<K> lista2){

        while(!lista2.vazia()){

            lista1.inserir(lista2.remover(0), lista1.getTamanho());

        }


    }





    public boolean pesquisaValor(V valor){
        return pesquisaValor(raiz, valor);
    }
    private boolean pesquisaValor(No<K,V> no, V valor){
        if(no==null){
            return false;
        }

        if(no.getItem().equals(valor)){
            return true;
        }

        return pesquisaValor(no.getEsq(), valor) || pesquisaValor(no.getDir(), valor);

    }










    public int contarMaiores(K chave){
        return contarMaiores(raiz, chave);
    }

    private int contarMaiores(No<K,V> no, K chave){

        if(no==null){
            return 0;
        }

        int comp = this.comparador.compare(chave, no.getChave());

        if(comp>=0){
            return contarMaiores(no.getDir(), chave);
        }
        else{
            return 1+contarMaiores(no.getEsq(), chave)+contarMaiores(no.getDir(), chave);
        }

    }














    public Lista<K> todasAsChaves(Lista<K> lista){ 
        return todasAsChaves(raiz,lista); 
    }

    private Lista<K> todasAsChaves(No<K,V> no, Lista<K> lista){

        if(no==null){
            return lista;
        }

        todasAsChaves(no.getEsq(),lista);
        lista.inserir(no.getChave(),lista.getTamanho());
        todasAsChaves(no.getDir(), lista);

        return lista;

    }








    public int profundidadeDaChave(K chave){ //raiz = nivel 1
        return profundidadeDaChave(raiz, chave, 1);
    }
    
    private int profundidadeDaChave(No<K,V> no, K chave, int nivel){

        if(no==null){
            return -1;
        }

        int comp = this.comparador.compare(chave, no.getChave());

        if(comp>0){
            return profundidadeDaChave(no.getDir(), chave, nivel+1);
        }

        else if(comp<0){
            return profundidadeDaChave(no.getEsq(), chave, nivel+1);
        }

        else{
            return nivel;
        }

    }






    //dps olha esses codigos de subarvore
    public boolean possuiSubarvore(ABB<K,V> outra){

        return possuiSubarvore(raiz,outra.raiz);

    }

    private boolean possuiSubarvore(No<K,V> noThis, No<K,V> noOutra){

        if(noThis==null && noOutra!=null){
            return false;
        }
        else if(noThis!=null && noOutra==null){
            return false;
        }


        if(comparaArvores(noThis, noOutra)==true){
            return true;
        }

        return possuiSubarvore(noThis.getEsq(), noOutra)||possuiSubarvore(noThis.getDir(), noOutra);


    }

    

    public boolean comparaArvores(No<K,V> no, No<K,V> noOutro){

        if(no==null && noOutro==null){
            return true;
        }

        if(no==null || noOutro==null){
            return false;
        }

        if(!no.getChave().equals(noOutro.getChave())){
            return false;
        }

        if(!no.getItem().equals(noOutro.getItem())){
            return false;
        }

        return comparaArvores(no.getEsq(), noOutro.getEsq())&&comparaArvores(no.getDir(), noOutro.getDir());

    }







    public ABB<K, V> filtrarPorValor(V valor){

        return filtrarPorValor(valor, raiz);

    }

    private ABB<K, V> filtrarPorValor(V valor, No<K,V> no){

        if(no==null){
            return new ABB<>();
        }

        ABB<K,V> esq = filtrarPorValor(valor, no.getEsq());
        
        if(no.getItem().equals(valor)){
            esq.inserir(no.getChave(), no.getItem());
        }

        ABB<K,V> dir = filtrarPorValor(valor, no.getDir());
        esq.concatenaçaoArvores(dir);

        
        return esq;



    }


    public void concatenaçaoArvores(ABB<K,V> outra){
        concatenaçaoArvores(outra.raiz);
    }

    private void concatenaçaoArvores(No<K,V> no){

        if(no==null){
            return;
        }

        concatenaçaoArvores(no.getEsq());
        this.inserir(no.getChave(), no.getItem());
        concatenaçaoArvores(no.getDir());

    }







    public int balanceada(){

        return balanceada(this.raiz);

    }

    private int balanceada(No<K,V> no){

        if(no==null){
            return 0;
        }



        int esq = balanceada(no.getEsq());

        int dir = balanceada(no.getDir());

        return 1+Math.max(esq, dir);


    }


















}
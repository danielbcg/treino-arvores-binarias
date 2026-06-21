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

    public No<K,V> getDir() {
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

    public No<K,V> clone(){

        No<K,V> nóClone = new No<>();

        nóClone.setChave(this.getChave());
        nóClone.setItem(this.getItem());


        
        if(this.getEsq()!=null){
            
            nóClone.setEsq(this.getEsq().clone());

        }
        
        if(this.getEsq()!=null){

            nóClone.setDir(this.getDir().clone());
        
        }

        return nóClone;

    }


    public int tamanho(){

        int esq=0;
        int dir=0;

        if(this.getEsq()!=null){
            esq = this.getEsq().tamanho();
        }
        if(this.getDir()!=null){
            dir = this.getDir().tamanho();
        }

        return 1+dir+esq;

    }

    public int altura(){

        int esq=0;
        int dir=0;

        if(this.getEsq()!=null){
            esq = this.getEsq().altura();
        }
        if(this.getDir()!=null){
            dir = this.getDir().altura();
        }

        int result = Math.max(esq, dir);

        return 1+result;

    }

    public K menorChave(){

        if(this.getEsq()==null){
            return this.getChave();
        }

        return this.getEsq().menorChave();

    }

    public void imprimirEmOrdem(){ //emOrdem = esquerda -> raiz -> direita

        if(this.getEsq()!=null){
            this.getEsq().imprimirEmOrdem();
        }

        System.out.println(this.toString());
        
        if(this.getDir()!=null){
            this.getDir().imprimirEmOrdem();
        }

    }



    public int contadorDeFolhas(){

        
        int esq=0;
        int dir=0;

        if(this.getEsq()==null && this.getDir()==null){
            return 1;
        }
        
        if(this.getEsq()!=null){
            esq = this.getEsq().contadorDeFolhas();
        }
        if(this.getDir()!=null){
            dir = this.getDir().contadorDeFolhas();
        }

        return esq+dir;
        

    }


    public No<K,V> espelhando(){

        No<K,V> noEspelhado = new No<>();

        noEspelhado.setChave(this.getChave());
        noEspelhado.setItem(this.getItem());
        

        if(this.getEsq()!=null){
            noEspelhado.setEsq(this.getDir().espelhando());
        }

        if(this.getDir()!=null){
            noEspelhado.setDir(this.getEsq().espelhando());
        }

        return noEspelhado;

    }

    //dps apaga se quiser
    public No<K,V> clonarNo(){

        No<K,V> NÓ_NOVO = new No<>();

        NÓ_NOVO.setChave(this.getChave());
        NÓ_NOVO.setItem(this.getItem());


        if(this.getEsq()!=null){
            NÓ_NOVO.setEsq(this.getEsq().clonarNo());
        }

        if(this.getDir()!=null){
            NÓ_NOVO.setDir(this.getDir().clonarNo());
        }
        return NÓ_NOVO;
    }

    
}
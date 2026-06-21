public class TabelaHashReserva {

    private int[] tabela;
    private int tamPrincipal;   // tamanho da área principal
    private int proxReserva;    // próxima posição livre na área de reserva

    public TabelaHashReserva(int tamPrincipal, int tamReserva) {
        this.tamPrincipal = tamPrincipal;
        this.proxReserva = tamPrincipal; // reserva começa após a área principal
        this.tabela = new int[tamPrincipal + tamReserva];
        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = -1;
        }
    }

    private int hash(int chave) {
        return chave % tamPrincipal;
    }

    public void inserir(int chave) {
        int posicao = hash(chave);

        if(tabela[posicao] != -1) {
            
            tabela[proxReserva]=chave;
            proxReserva++;

        }
        else{
            tabela[posicao]=chave;
        }



    }

    public boolean pesquisar(int chave) {

        int posicao = hash(chave);

        if(tabela[posicao]==chave){

            return true;
            

        }else{

            for(int i=tamPrincipal;i<tabela.length;i++){
                if(tabela[i]==chave){
                    return true;
                }
            }

            return false;

        }

    }

    public void remover(int chave) {

        if (tabela[hash(chave)]==chave) {

            tabela[hash(chave)]
            
        }
        


    }




}
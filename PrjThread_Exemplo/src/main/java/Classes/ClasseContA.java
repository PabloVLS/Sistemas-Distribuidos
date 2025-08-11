package Classes;
/**
 *
 * @author Pablo
 */
public class ClasseContA {
    String nome;
    int tamanhoContador;

    public ClasseContA(String nome, int tamanhoContador) {
        this.nome = nome;
        this.tamanhoContador = tamanhoContador;
    }
    
    public void contador(){
        for(int i =0; i < tamanhoContador; i++){
            System.out.println(""+nome+ " --> "+i);
        }
    }
}

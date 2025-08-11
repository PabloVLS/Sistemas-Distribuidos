package Classes;

/**
 *
 * @author Pablo
 */
public class ClasseContThreadC implements Runnable{
    String nome;
    int tamanhoContador;

    public ClasseContThreadC(String nome, int tamanhoContador) {
        this.nome = nome;
        this.tamanhoContador = tamanhoContador;
    }
    
    @Override
    public void run(){
        for(int i = 0; i < tamanhoContador; i++){
            System.out.println(""+nome+" -->"+i);
        }
    }
}

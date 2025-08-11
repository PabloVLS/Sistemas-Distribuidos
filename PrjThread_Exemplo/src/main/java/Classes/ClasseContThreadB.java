package Classes;

/**
 *
 * @author Pablo
 */
public class ClasseContThreadB extends Thread {
    String nome;
    int tamanhoContador;

    public ClasseContThreadB(String nome, int tamanhoContador) {
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

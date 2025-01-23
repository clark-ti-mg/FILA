import java.util.Scanner;

class Carta {
    int numero;

    Carta(int numero){
        this.numero = numero;
    }
}

class Fila {
    public static final int TAMANHO_MAX = 50;
    int inicio = 0;
    int fim = 0;
    int tamanho = 0;

    Carta vetor[] = new Carta[TAMANHO_MAX];

    public void enfileirar(Carta c) throws Exception {
        if (isFull()) {
            throw new Exception("Fila cheia.");
        }
        vetor[fim] = c;
        fim++;
        if (fim == TAMANHO_MAX) {
            fim = 0;
        }
        tamanho++;
    }

    public Carta desenfileirar() throws Exception {
        Carta c = vetor[inicio];
        if (isEmpty()) {
            throw new Exception("Fila vazia.");
        }
        inicio++;
        if (inicio == TAMANHO_MAX) {
            inicio = 0;
        }
        tamanho--;
        return c;
    }

    public boolean isFull() {
        return tamanho == TAMANHO_MAX;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

}

public class Ex3 {
    public static void main(String[] args) throws Exception {

        Scanner teclado = new Scanner(System.in);
        int qtdCartas;

        qtdCartas = teclado.nextInt();

        while(qtdCartas > 0 && qtdCartas != 0){
            Fila filaCartas = new Fila();
            Fila filaDescartadas = new Fila();

            for(int i = 1; i <= qtdCartas; i++){
                filaCartas.enfileirar(new Carta(i));
            }

            while(filaCartas.tamanho >= 2){
                filaDescartadas.enfileirar(filaCartas.desenfileirar());
                filaCartas.enfileirar(filaCartas.desenfileirar());
            }

            int tamanhoDesc = filaDescartadas.tamanho;
            System.out.print("Discarded cards: ");
            for(int i = 0; i < tamanhoDesc; i++){
                if(i != tamanhoDesc-1)
                    System.out.print(filaDescartadas.desenfileirar().numero + ", ");
                else
                    System.out.println(filaDescartadas.desenfileirar().numero);
            }
            System.out.println("Remaining card: " + filaCartas.desenfileirar().numero);

            qtdCartas = teclado.nextInt();
        }

        
        
    }
}

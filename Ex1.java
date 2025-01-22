import java.util.Random;
import java.util.Scanner;

class Pessoa {
    String senha;

    Pessoa(String senha) {
        this.senha = senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return this.senha;
    }
}

class Fila {
    public static final int TAMANHO_MAX = 50;
    int inicio = 0;
    int fim = 0;
    int tamanho = 0;

    Pessoa vetor[] = new Pessoa[TAMANHO_MAX];

    public void enfileirar(Pessoa p) throws Exception {
        if (isFull()) {
            throw new Exception("Fila cheia.");
        }
        vetor[fim] = p;
        fim++;
        if (fim == TAMANHO_MAX) {
            fim = 0;
        }
        tamanho++;
    }

    public Pessoa desenfileirar() throws Exception {
        Pessoa p = vetor[inicio];
        if (isEmpty()) {
            throw new Exception("Fila vazia.");
        }
        inicio++;
        if (inicio == TAMANHO_MAX) {
            inicio = 0;
        }
        tamanho--;
        return p;
    }

    public boolean isFull() {
        return tamanho == TAMANHO_MAX;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

}

public class Ex1 {
    public static void main(String[] args) throws Exception {
        Fila fila = new Fila();

        System.out.println("Bem vindo ao sistema de distribuição de senhas. Escolha sua ação:");
        Scanner teclado = new Scanner(System.in);

        while (!fila.isFull()) {
            System.out.println("[A] - Pegar senha");
            System.out.println("[B] - Chamar atendimento");
            String op = teclado.next();
            try {
                if (op.toUpperCase().equals("A")) {
                    String senha = Integer.toString(new Random().nextInt(1000));
                    fila.enfileirar(new Pessoa(senha));
                    System.out.println("Senha " + senha + " entrou na fila.");
                } else if (op.toUpperCase().equals("B")) {
                    Pessoa p = fila.desenfileirar();

                    System.out.println("Senha " + p.senha + " atendida.");
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Exceção ao executar a opção escolhida: " + e.getMessage());
            }

        }
        System.out.println("Fila cheia");
    }
}

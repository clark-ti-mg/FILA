import java.util.Scanner;

class Fila {
    int tamanho = 0;
    int inicio = 0;
    int fim = 0;
    int TAMANHO_MAX;
    int idControle = 0;

    Processo[] fila;

    Fila(int tam) {
        this.TAMANHO_MAX = tam;
        this.fila = new Processo[this.TAMANHO_MAX];
    }

    public void enfileirar(Processo p) throws Exception {
        if (isFull()) {
            throw new Exception("Lista cheia.");
        }
        fila[fim] = p;
        ++fim;
        if (fim == TAMANHO_MAX) {
            fim = 0;
        }

        ++tamanho;
        ++idControle;

    }

    public Processo desenfileirar() throws Exception {
        if (isVazio()) {
            throw new Exception("Fila vazia, não é possível retirar um elemento.");
        }
        Processo p = fila[inicio];
        ++inicio;
        if (inicio == TAMANHO_MAX) {
            inicio = 0;
        }
        --tamanho;
        return p;
    }

    public void imprimeConteudo() throws Exception {
        if (isVazio()) {
            throw new Exception("Fila vazia.");
        } else {
            Fila filaAux = new Fila(this.TAMANHO_MAX);

            int i = inicio;
            int count = 0;

            while (count < tamanho) {
                Processo p = fila[i];
                System.out.println("ID: " + p.id + ", Título: " + p.titulo);
                filaAux.enfileirar(p);
                i = (i + 1) % TAMANHO_MAX;
                count++;
            }

            this.fila = filaAux.fila;
            this.inicio = filaAux.inicio;
            this.fim = filaAux.fim;
            this.tamanho = filaAux.tamanho;
        }
    }

    public int localizar(int id) throws Exception {
        if (isVazio()) {
            throw new Exception("Fila vazia.");
        }
        int pos = -1;
        int i = inicio;
        int count = 0;

        while (count < tamanho) {
            if (fila[i].id == id) {
                pos = i;
                break;
            }
            i = (i + 1) % TAMANHO_MAX;
            count++;
        }

        return pos;
    }

    public void excluirTodos() throws Exception {
        if (isVazio()) {
            throw new Exception("Fila vazia.");
        }
        fila = new Processo[TAMANHO_MAX];
        tamanho = 0;
        inicio = 0;
        fim = 0;
        idControle = 0;
    }

    public boolean isFull() {
        return tamanho == TAMANHO_MAX;
    }

    public boolean isVazio() {
        return tamanho == 0;
    }
}

class Processo {
    int id;
    String titulo;

    Processo(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}


public class Ex2 {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        Fila fila = new Fila(10);

        String op = "";

        while (!op.equals("S")) {
            System.out.println("Opções:");
            System.out.println("[A] - Incluir processo");
            System.out.println("[B] - Excluir processo");
            System.out.println("[C] - Localizar por ID");
            System.out.println("[D] - Excluir todos");
            System.out.println("[E] - Imprimir todos");
            System.out.println("[S] - Sair");

            op = teclado.next().toUpperCase();
            teclado.nextLine();
            switch (op) {
                case "A":
                    try {
                        int id = fila.idControle;
                        System.out.println("Titulo:");
                        String titulo = teclado.nextLine();

                        fila.enfileirar(new Processo(id, titulo));
                        System.out.println("Processo inserido com sucesso");
                    } catch (Exception e) {
                        System.out.println("Não foi possível inserir o processo na fila. Exceção: " + e.getMessage());
                    }
                    break;

                case "B":
                    try {
                        Processo p = fila.desenfileirar();
                        System.out.println("Processo de id " + p.id + " excluído com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Não foi possível excluir o processo na fila. Exceção: " + e.getMessage());
                    }
                    break;
                case "C":
                    try {
                        System.out.println("ID que quer pesquisar:");
                        int id = teclado.nextInt();

                        int posicao = fila.localizar(id);
                        if (posicao > -1) {
                            System.out.println("Processo localizado na posição " + posicao + " da fila.");
                        } else {
                            System.out.println("O processo não está na fila.");
                        }
                    } catch (Exception e) {
                        System.out.println("Exceção ao tentar localizar o processo: " + e.getMessage());
                    }
                    break;
                case "D":
                    try {
                        fila.excluirTodos();
                        System.out.println("Todos os processos foram excluídos.");
                    } catch (Exception e) {
                        System.out.println("Exceção ao tentar excluir todos: " + e.getMessage());
                    }

                    break;

                case "S":
                    System.exit(0);
                    break;
                case "E":
                    try {
                        System.out.println("Processos na fila:");
                        fila.imprimeConteudo();

                    } catch (Exception e) {
                        System.out.println("Exceção ao tentar imprimir todos: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }

        }
    }
}

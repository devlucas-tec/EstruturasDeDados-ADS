public class SimpleLinkedList {


    Nodo cabeca;
    int tamanho;


    private class Nodo {
        int dado;
        Nodo prox;


        public Nodo(int dado) {
            this.dado = dado;
            this.prox = null;
        }
    }


    public SimpleLinkedList() {
        this.cabeca = null;
        this.tamanho = 0;
    }


    public void Adicionar(int novoDado) {
        Nodo novoNo = new Nodo(novoDado);


        if (cabeca == null) {
            cabeca = novoNo;
        } else {
            Nodo nodoAtual = cabeca;
            while (nodoAtual.prox != null) {
                nodoAtual = nodoAtual.prox;
            }
            nodoAtual.prox = novoNo;
        }
        this.tamanho++;
    }


    public int getTamanho() {
        return tamanho;
    }


    public boolean Adicionar(int index, int dado) {
        if (index < 1 || index > tamanho + 1) {
            return false;
        }


        Nodo novoNo = new Nodo(dado);


        if (index == 1) {
            novoNo.prox = cabeca;
            cabeca = novoNo;
        } else {
            Nodo nodoAtual = cabeca;
            for (int i = 1; i < index - 1; i++) {
                nodoAtual = nodoAtual.prox;
            }
            novoNo.prox = nodoAtual.prox;
            nodoAtual.prox = novoNo;
        }

        tamanho++;
        return true;
    }


    public Nodo getPosicao(int index) {
        if (index < 1 || index > tamanho) {
            return null;
        } else {
            Nodo nodoAtual = cabeca;
            for (int i = 1; i < index; i++) {
                nodoAtual = nodoAtual.prox;
            }
            return nodoAtual;
        }
    }

    public boolean remove(int index) {
        if (index < 1 || index > tamanho) {
            return false;
        } else if (index == 1) {
            cabeca = cabeca.prox;
        } else {
            Nodo nodoAtual = cabeca;
            for (int i = 1; i < index - 1; i++) {
                nodoAtual = nodoAtual.prox;
            }
            nodoAtual.prox = nodoAtual.prox.prox;
        }

        tamanho--;
        return true;
    }


    public void exibir() {
        Nodo noAtual = cabeca;
        System.out.print("Lista: ");
        while (noAtual != null) {
            System.out.print(noAtual.dado + " -> ");
            noAtual = noAtual.prox;
        }
        System.out.println("NULL");
    }


    public static void main(String[] args) {


        SimpleLinkedList minhaLista = new SimpleLinkedList();


        System.out.println("--- Lista Vazia ---");
        minhaLista.exibir();


        System.out.println("\n--- Adicionando elementos ---");
        minhaLista.Adicionar(10);
        minhaLista.Adicionar(25);
        minhaLista.Adicionar(42);


        minhaLista.exibir();


        System.out.println("\n--- Adicionando elemento na posição 2 (o 7) ---");
        minhaLista.Adicionar(2, 7);


        minhaLista.exibir();


        System.out.println("\n--- Adicionando mais um (77 no fim) ---");
        minhaLista.Adicionar(77);


        minhaLista.exibir();


        System.out.println("\n--- Removendo elemento no index 2 (o 7) ---");
        minhaLista.remove(2);


        minhaLista.exibir();

        System.out.println("\n--- Removendo o primeiro elemento (o 10) --- ");
        minhaLista.remove(1);

        minhaLista.exibir();
        System.out.println("Tamanho atual: " + minhaLista.getTamanho()); // Esperado: 3

        System.out.println("\n--- Exibindo valor na posição 3 (esperado: 77) ---");
        Nodo nodo = minhaLista.getPosicao(3);

        if (nodo == null) {
            System.out.println("NULL");
        } else {
            System.out.println("Valor: " + nodo.dado);
        }

        System.out.println("\n--- Exibindo valor em uma posição inválida (7) ---");
        Nodo nodo1 = minhaLista.getPosicao(7);

        if (nodo1 == null) {
            System.out.println("NULL");
        } else {
            System.out.println("Valor: " + nodo1.dado);
        }
    }
}
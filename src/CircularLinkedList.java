public class CircularLinkedList {

    Nodo cabeca;
    Nodo cauda;
    int tamanho;

    private class Nodo {
        int dado;
        Nodo prox;

        public Nodo(int dado) {
            this.dado = dado;
            this.prox = null;
        }
    }

    public CircularLinkedList() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void Adicionar(int novoDado) {
        Nodo novoNo = new Nodo(novoDado);

        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
            novoNo.prox = cabeca;
        } else {
            novoNo.prox = cabeca;
            cauda.prox = novoNo;
            cauda = novoNo;
        }
        this.tamanho++;
    }

    public boolean Adicionar(int index, int dado) {
        if (index < 1 || index > tamanho + 1) {
            return false;
        }

        if (index == 1) {
            Nodo novoNo = new Nodo(dado);
            if (cabeca == null) {
                cabeca = novoNo;
                cauda = novoNo;
                novoNo.prox = cabeca;
            } else {
                novoNo.prox = cabeca;
                cabeca = novoNo;
                cauda.prox = cabeca;
            }
            tamanho++;
            return true;
        }

        if (index == tamanho + 1) {
            Adicionar(dado);
            return true;
        }

        Nodo novoNo = new Nodo(dado);
        Nodo nodoAtual = cabeca;
        for (int i = 1; i < index - 1; i++) {
            nodoAtual = nodoAtual.prox;
        }
        novoNo.prox = nodoAtual.prox;
        nodoAtual.prox = novoNo;

        tamanho++;
        return true;
    }

    public Nodo getPosicao(int index) {
        if (index < 1 || index > tamanho) {
            return null;
        }
        Nodo nodoAtual = cabeca;
        for (int i = 1; i < index; i++) {
            nodoAtual = nodoAtual.prox;
        }
        return nodoAtual;
    }

    public boolean remove(int index) {
        if (index < 1 || index > tamanho) {
            return false;
        }

        if (index == 1) {
            if (tamanho == 1) {
                cabeca = null;
                cauda = null;
            } else {
                cabeca = cabeca.prox;
                cauda.prox = cabeca;
            }
        } else {
            Nodo nodoAtual = cabeca;
            for (int i = 1; i < index - 1; i++) {
                nodoAtual = nodoAtual.prox;
            }

            if (index == tamanho) {
                cauda = nodoAtual;
            }

            nodoAtual.prox = nodoAtual.prox.prox;
        }

        tamanho--;
        return true;
    }

    public void exibir() {
        System.out.print("Lista: ");
        if (cabeca == null) {
            System.out.println("NULL");
            return;
        }
        Nodo noAtual = cabeca;
        do {
            System.out.print(noAtual.dado + " -> ");
            noAtual = noAtual.prox;
        } while (noAtual != cabeca);

        System.out.println("(volta para " + cabeca.dado + ")");
    }

    public static void main(String[] args) {

        CircularLinkedList minhaLista = new CircularLinkedList();

        System.out.println("--- Lista Vazia ---");
        minhaLista.exibir();

        System.out.println("\n--- Adicionando elementos (no fim) ---");
        minhaLista.Adicionar(10);
        minhaLista.Adicionar(25);
        minhaLista.Adicionar(42);
        minhaLista.exibir();
        System.out.println("Tamanho: " + minhaLista.getTamanho());

        System.out.println("\n--- Adicionando elemento (7) na posição 2 ---");
        minhaLista.Adicionar(2, 7);
        minhaLista.exibir();

        System.out.println("\n--- Adicionando elemento (1) na posição 1 (cabeça) ---");
        minhaLista.Adicionar(1, 1);
        minhaLista.exibir();

        System.out.println("\n--- Adicionando elemento (99) na posição 6 (fim) ---");
        minhaLista.Adicionar(6, 99);
        minhaLista.exibir();

        System.out.println("\n--- Removendo elemento no index 3 (o 25) ---");
        minhaLista.remove(3);
        minhaLista.exibir();

        System.out.println("\n--- Removendo o primeiro elemento (o 1) --- ");
        minhaLista.remove(1);
        minhaLista.exibir();

        System.out.println("\n--- Removendo o último elemento (o 99) --- ");
        minhaLista.remove(minhaLista.getTamanho());
        minhaLista.exibir();

        System.out.println("\n--- Exibindo valor na posição 2 (o 7) ---");
        Nodo nodo = minhaLista.getPosicao(2);
        if (nodo == null) {
            System.out.println(nodo);
        } else {
            System.out.println("Valor: " + nodo.dado);
        }

        System.out.println("\n--- Exibindo valor na posição 1 (o 10) ---");
        Nodo nodo2 = minhaLista.getPosicao(1);
        System.out.println("Valor: " + nodo2.dado);
    }
}
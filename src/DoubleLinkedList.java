public class DoubleLinkedList {

    Nodo cabeca;
    Nodo cauda;
    int tamanho;

    private class Nodo {
        int dado;
        Nodo proximo;
        Nodo anterior;

        public Nodo(int dado) {
            this.dado = dado;
            this.proximo = null;
            this.anterior = null;
        }
    }

    public DoubleLinkedList() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public void Adicionar(int novoDado) {
        Nodo novoNo = new Nodo(novoDado);

        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            cauda.proximo = novoNo;
            novoNo.anterior = cauda;
            cauda = novoNo;
        }
        this.tamanho++;
    }

    public boolean Adicionar(int posicao, int dado) {
        if (posicao < 1 || posicao > tamanho + 1) {
            return false;
        }

        Nodo novoNo = new Nodo(dado);

        if (posicao == 1) {
            if (cabeca == null) {
                cabeca = novoNo;
                cauda = novoNo;
            } else {
                novoNo.proximo = cabeca;
                cabeca.anterior = novoNo;
                cabeca = novoNo;
            }
        } else if (posicao == tamanho + 1) {
            cauda.proximo = novoNo;
            novoNo.anterior = cauda;
            cauda = novoNo;
        } else {
            Nodo nodoAtual = cabeca;

            for (int i = 1; i < posicao - 1; i++) {
                nodoAtual = nodoAtual.proximo;
            }

            novoNo.proximo = nodoAtual.proximo;
            novoNo.anterior = nodoAtual;
            nodoAtual.proximo.anterior = novoNo;
            nodoAtual.proximo = novoNo;
        }
        this.tamanho++;
        return true;
    }

    public boolean remove(int posicao) {
        if (posicao < 1 || posicao > tamanho || cabeca == null) {
            return false;
        }

        if (tamanho == 1) {
            cabeca = null;
            cauda = null;
        } else if (posicao == 1) {
            cabeca = cabeca.proximo;
            cabeca.anterior = null;
        } else if (posicao == tamanho) {
            cauda = cauda.anterior;
            cauda.proximo = null;
        } else {
            Nodo nodoAtual = cabeca;
            for (int i = 1; i < posicao; i++) {
                nodoAtual = nodoAtual.proximo;
            }

            Nodo anterior = nodoAtual.anterior;
            Nodo proximo = nodoAtual.proximo;

            anterior.proximo = proximo;
            proximo.anterior = anterior;
        }

        this.tamanho--;
        return true;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Nodo getPosicao(int posicao) {
        if (posicao < 1 || posicao > tamanho) {
            return null;
        }

        Nodo nodoAtual;
        if (posicao > tamanho / 2) {
            nodoAtual = cauda;

            for (int i = tamanho; i > posicao; i--) {
                nodoAtual = nodoAtual.anterior;
            }
        } else {
            nodoAtual = cabeca;

            for (int i = 1; i < posicao; i++) {
                nodoAtual = nodoAtual.proximo;
            }
        }
        return nodoAtual;
    }

    public void exibir() {
        System.out.print("Lista: ");
        if (cabeca == null) {
            System.out.println("NULL");
            return;
        }
        Nodo noAtual = cabeca;
        while (noAtual != null) {
            System.out.print(noAtual.dado + " <-> ");
            noAtual = noAtual.proximo;
        }
        System.out.println("NULL");
    }


    public static void main(String[] args) {

        DoubleLinkedList minhaLista = new DoubleLinkedList();

        System.out.println("--- Adicionando elementos ---");
        minhaLista.Adicionar(10);
        minhaLista.Adicionar(20);
        minhaLista.Adicionar(40);
        minhaLista.Adicionar(3, 30);
        minhaLista.Adicionar(1, 5);
        minhaLista.exibir();
        System.out.println("Tamanho: " + minhaLista.getTamanho());

        System.out.println("\n--- Removendo o último (Posição 5: 40) ---");
        minhaLista.remove(5);
        minhaLista.exibir();

        System.out.println("\n--- Removendo a cabeça (Posição 1: 5) ---");
        minhaLista.remove(1);
        minhaLista.exibir();

        System.out.println("\n--- Removendo o meio (Posição 2: 30) ---");
        minhaLista.remove(2);
        minhaLista.exibir();
        System.out.println("Tamanho final: " + minhaLista.getTamanho());

        System.out.println("\n--- Buscando Posição 2 (esperado: 20) ---");
        Nodo nodoBuscado = minhaLista.getPosicao(2);
        System.out.println("Valor na posição 2: " + nodoBuscado.dado);
    }
}
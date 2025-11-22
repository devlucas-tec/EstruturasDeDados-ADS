package arvores;

public class ArvoreBinaria {

    Nodo raiz;


    public class Nodo {
        int valor;
        Nodo esq;
        Nodo dir;


        public Nodo(int valor) {
            this.valor = valor;
            this.esq = null;
            this.dir = null;
        }


        public Nodo getEsq() {
            return esq;
        }


        public void setEsq(Nodo esq) {
            this.esq = esq;
        }


        public Nodo getDir() {
            return dir;
        }


        public void setDir(Nodo dir) {
            this.dir = dir;
        }


        public int getValor() {
            return valor;
        }
    }




    public ArvoreBinaria () {
        raiz = null;


    }


    public void adicionar(int valor) {
        Nodo novo = new Nodo(valor);


        if(raiz == null) {
            raiz = novo;
        } else {
            adicionar(raiz, valor);
        }
    }


    public void adicionar(Nodo atual, int valor) {
        Nodo novo = new Nodo(valor);


        if (valor < atual.getValor()) {


            if (atual.getEsq() == null) {
                atual.setEsq(novo);
            } else {
                adicionar(atual.getEsq(), valor);
            }


        } else {


            if (atual.getDir() == null) {
                atual.setDir(novo);
            } else {
                adicionar(atual.getDir(), valor);
            }


        }
    }

    public boolean buscar(int valor) {
        return buscaRecursiva(raiz, valor);
    }

    public boolean buscaRecursiva(Nodo nodoAtual, int valor) {
        if (nodoAtual == null) {
            return false;
        }

        if (valor == nodoAtual.getValor()) {
            return true;
        }

        if (valor < nodoAtual.getValor()) {
            return buscaRecursiva(nodoAtual.getEsq(), valor);
        } else {
            return buscaRecursiva(nodoAtual.getDir(), valor);
        }
    }

    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    private Nodo removerRecursivo(Nodo nodoAtual, int valor) {

        if (nodoAtual == null) {
            return null;
        }

        if (valor < nodoAtual.getValor()) {
            nodoAtual.setEsq(removerRecursivo(nodoAtual.getEsq(), valor));

        } else if (valor > nodoAtual.getValor()) {
            nodoAtual.setDir(removerRecursivo(nodoAtual.getDir(), valor));

        } else {
            if (nodoAtual.getEsq() == null && nodoAtual.getDir() == null) {
                return null;
            }

            else if (nodoAtual.getEsq() == null) {
                return nodoAtual.getDir();
            }

            else if (nodoAtual.getDir() == null) {
                return nodoAtual.getEsq();
            }

            else {
                Nodo successor = encontrarMinimo(nodoAtual.getDir());

                nodoAtual.valor = successor.valor;

                nodoAtual.setDir(removerRecursivo(nodoAtual.getDir(), successor.valor));
            }
        }

        return nodoAtual;
    }

    private Nodo encontrarMinimo(Nodo nodo) {
        Nodo atual = nodo;
        while (atual.getEsq() != null) {
            atual = atual.getEsq();
        }
        return atual;
    }


    public void imprimirEmOrdem() {
        if (raiz == null) {
            System.out.println("Árvore vazia.");
        } else {
            imprimirEmOrdem(raiz);
            System.out.println(); // Pula linha no final
        }
    }

    private void imprimirEmOrdem(Nodo nodo) {
        if (nodo != null) {
            imprimirEmOrdem(nodo.getEsq());
            System.out.print(nodo.getValor() + " ");
            imprimirEmOrdem(nodo.getDir());
        }
    }

    public static void main(String[] args) {

        ArvoreBinaria arvore = new ArvoreBinaria();

        System.out.println("--- 1. Adicionando valores ---");
        arvore.adicionar(30);
        arvore.adicionar(70);
        arvore.adicionar(20);
        arvore.adicionar(40);
        arvore.adicionar(60);
        arvore.adicionar(80);

        System.out.print("Árvore atual (em ordem): ");
        arvore.imprimirEmOrdem(); // Deve imprimir: 20 30 40 50 60 70 80

        System.out.println("\n--- 2. Testando Busca ---");
        System.out.println("Buscar 40 (esperado: true):  " + arvore.buscar(40));
        System.out.println("Buscar 99 (esperado: false): " + arvore.buscar(99));

        System.out.println("\n--- 3. Testando Remoção (Caso 1: Folha) ---");
        System.out.println("Removendo 20 (folha)...");
        arvore.remover(20);
        System.out.println("Buscar 20 (esperado: false): " + arvore.buscar(20));
        System.out.print("Árvore atual (em ordem): ");
        arvore.imprimirEmOrdem(); // Deve imprimir: 30 40 50 60 70 80

        System.out.println("\n--- 4. Testando Remoção (Caso 3: Dois Filhos) ---");
        System.out.println("Removendo 70 (dois filhos: 60 e 80)...");
        arvore.remover(70); // O sucessor é 80
        System.out.println("Buscar 70 (esperado: false): " + arvore.buscar(70));
        System.out.print("Árvore atual (em ordem): ");
        arvore.imprimirEmOrdem(); // Deve imprimir: 30 40 50 60 80

        System.out.println("\n--- 5. Testando Remoção (Caso 3: Raiz) ---");
        System.out.println("Removendo 50 (raiz com dois filhos)...");
        arvore.remover(50); // O sucessor é 60
        System.out.println("Buscar 50 (esperado: false): " + arvore.buscar(50));
        System.out.print("Árvore atual (em ordem): ");
        arvore.imprimirEmOrdem(); // Deve imprimir: 30 40 60 80

        System.out.println("Valor da nova raiz (esperado: 60): " + arvore.raiz.getValor());

        System.out.println("\n--- 6. Testando Remoção (Caso 2: Um Filho) ---");
        arvore.adicionar(25);
        System.out.print("Árvore antes de remover 30: ");
        arvore.imprimirEmOrdem(); // 25 30 40 60 80

        System.out.println("Removendo 30 (um filho: 25)...");
        arvore.remover(30);
        System.out.print("Árvore final (em ordem): ");
        arvore.imprimirEmOrdem(); // Deve imprimir: 25 40 60 80
    }

}

package arvores;

public class ArvoreAvl {

    NoArvore raiz;

    public class NoArvore {
        int valor;
        NoArvore esq;
        NoArvore dir;
        int altura;

        public NoArvore(int valor) {
            this.valor = valor;
            this.dir = null;
            this.esq = null;
            this.altura = 1;
        }

        public NoArvore getDir() {
            return dir;
        }

        public void setDir(NoArvore dir) {
            this.dir = dir;
        }

        public NoArvore getEsq() {
            return esq;
        }

        public void setEsq(NoArvore esq) {
            this.esq = esq;
        }


        public int getValor() {
            return valor;
        }
    }

    public int calculaAltura(NoArvore no) {
        if (no == null) {
            return 0;
        }
        return no.altura;
    }


    public int calculaFb(NoArvore no) {
        if (no == null) {
            return 0;
        }
        return calculaAltura(no.esq) - calculaAltura(no.dir);
    }


    public void atualizarAltura(NoArvore no) {
        no.altura = 1 + Math.max(calculaAltura(no.esq), calculaAltura(no.dir));
    }


    public NoArvore rotacaoDir(NoArvore pai) {
        NoArvore filho = pai.esq;
        NoArvore neto = filho.dir;


        filho.dir = pai;
        pai.esq = neto;


        atualizarAltura(pai);
        atualizarAltura(filho);


        return filho;
    }


    public NoArvore rotacaoEsq(NoArvore pai) {
        NoArvore filho = pai.dir;
        NoArvore neto = filho.esq;

        filho.esq = pai;
        pai.dir = neto;

        atualizarAltura(pai);
        atualizarAltura(filho);

        return filho;
    }


    public NoArvore adicionar(NoArvore atual, int valor) {
        if (atual == null) {
            return new NoArvore(valor);
        }


        if (valor < atual.valor) {
            atual.esq = adicionar(atual.esq, valor);
        } else {
            atual.dir = adicionar(atual.dir, valor);
        }

        atualizarAltura(atual);

        int fb = calculaFb(atual);

        if (fb > 1 && valor < atual.esq.valor) {
            return rotacaoDir(atual);
        }

        if (fb < -1 && valor > atual.dir.valor) {
            return rotacaoEsq(atual);
        }

        if (fb > 1 && valor > atual.esq.valor) {
            atual.esq = rotacaoEsq(atual.esq);
            return rotacaoDir(atual);
        }

        if (fb < -1 && valor < atual.dir.valor) {
            atual.dir = rotacaoDir(atual.dir);
            return rotacaoEsq(atual);
        }

        return atual;
    }

    public void adicionar(int valor) {
        this.raiz = adicionar(this.raiz, valor);
    }




    static boolean Buscar (NoArvore No, int valor) {
        if (No == null) {
            System.out.println("Valor " + valor + " não encontrado.");
            return false;
        }
        if (No.valor == valor) {
            System.out.println("Valor " + valor + " encontrado.");
            return true;
        }
        if (valor < No.valor) {
            return Buscar(No.esq, valor);
        } else {
            return Buscar(No.dir, valor);
        }
    }




    boolean Buscar(int valor) {
        return Buscar(this.raiz, valor);
    }




    static NoArvore removerRecursivo(NoArvore No, int valor) {
        if (No == null) {
            return null;
        }




        if (valor < No.valor) {
            No.esq = removerRecursivo(No.esq, valor);
        } else if (valor > No.valor) {
            No.dir = removerRecursivo(No.dir, valor);
        } else {
            if (No.esq == null) {
                return No.dir;
            } else if (No.dir == null) {
                return No.esq;
            } else {
                NoArvore sucessor = encontrarMenor(No.dir);
                No.valor = sucessor.valor;
                No.dir = removerRecursivo(No.dir, sucessor.valor);
            }
        }
        return No;
    }




    static NoArvore encontrarMenor(NoArvore No) {
        while (No.esq != null) {
            No = No.esq;
        }
        return No;
    }




    void remover(int valor) {
        if (Buscar(this.raiz, valor)) {
            this.raiz = removerRecursivo(this.raiz, valor);
        }
    }




    static void ImprimirPreOrdem (NoArvore No) {
        if (No != null) {
            System.out.print(No.valor + " ");
            ImprimirPreOrdem(No.esq);
            ImprimirPreOrdem(No.dir);
        }
    }
    void ImprimirPreOrdem() {
        ImprimirPreOrdem(this.raiz);
    }




    static void ImprimirEmOrdem (NoArvore No) {
        if (No != null) {
            ImprimirEmOrdem(No.esq);
            System.out.print(No.valor + " ");
            ImprimirEmOrdem(No.dir);




        }
    }
    void ImprimirEmOrdem() {
        ImprimirEmOrdem(this.raiz);
    }




    static void ImprimirPosOrdem (NoArvore No) {
        if (No != null) {
            ImprimirPosOrdem(No.esq);
            ImprimirPosOrdem(No.dir);
            System.out.print(No.valor + " ");
        }
    }
    void ImprimirPosOrdem() {
        ImprimirPosOrdem(this.raiz);
    }








    public static void main(String[] args) {




        ArvoreAvl arvore = new ArvoreAvl();




        System.out.println("----- 1. Adicionando valores -----");
        arvore.adicionar(80);
        arvore.adicionar(70);
        arvore.adicionar(75);
        arvore.adicionar(90);
        arvore.adicionar(30);
        System.out.println();




        System.out.println("----- 2. Imprimindo Árvore Atual -----");




        System.out.println("Árvore Atual (pré-ordem)");
        arvore.ImprimirPreOrdem();
        System.out.println();




        System.out.println("Árvore Atual (em ordem)");
        arvore.ImprimirEmOrdem();
        System.out.println();




        System.out.println("Árvore Atual (pós-ordem)");
        arvore.ImprimirPosOrdem();
        System.out.println("\n");




        System.out.println("----- 3. Buscando valores -----");
        arvore.Buscar(99);
        arvore.Buscar(30);
        arvore.Buscar(80);
        System.out.println();




        System.out.println("----- 4. Removendo valores -----");
        arvore.remover(75);
        System.out.println("Após remover 75 (em ordem):");
        arvore.ImprimirEmOrdem();
        System.out.println();
        arvore.remover(70);
        System.out.println("Após remover 70 (em ordem):");
        arvore.ImprimirEmOrdem();
        System.out.println();
        arvore.remover(100);
    }
}






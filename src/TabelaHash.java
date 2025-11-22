import java.util.Objects;

class Aluno {
    private int matricula;
    private String nome;

    public Aluno(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Aluno(mat=" + matricula + ", nome='" + nome + "')";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return matricula == aluno.matricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}

public class TabelaHash {

    private Aluno[] tabela;
    private int tamanho;
    private int quantidade;

    private static final double FATOR_DE_CARGA_MAXIMO = 0.75;

    private static final Aluno ALUNO_DELETADO = new Aluno(-1, "DELETADO");

    public TabelaHash() {
        this.tamanho = 7;
        this.tabela = new Aluno[this.tamanho];
        this.quantidade = 0;
    }

    private int funcaoHash(int matricula) {
        return Math.abs(matricula % this.tamanho);
    }

    private void redimensionamento() {
        System.out.println("--- REDIMENSIONANDO ---");
        System.out.println("Tabela cheia (" + this.quantidade + "/" + this.tamanho + "). Dobrando capacidade.");

        int tamanhoAntigo = this.tamanho;
        Aluno[] tabelaAntiga = this.tabela;

        this.tamanho = (tamanhoAntigo * 2) + 1;
        this.tabela = new Aluno[this.tamanho];
        this.quantidade = 0;

        for (int i = 0; i < tamanhoAntigo; i++) {
            Aluno aluno = tabelaAntiga[i];
            if (aluno != null && aluno != ALUNO_DELETADO) {
                inserirValor(aluno.getMatricula(), aluno.getNome());
            }
        }
        System.out.println("--- REDIMENSIONAMENTO COMPLETO (Novo tamanho: " + this.tamanho + ") ---");
    }

    public void inserirValor(int matricula, String nome) {
        if ((double) (this.quantidade + 1) / this.tamanho > FATOR_DE_CARGA_MAXIMO) {
            redimensionamento();
        }

        int posicaoInicial = funcaoHash(matricula);
        int indice = posicaoInicial;

        for (int i = 0; i < this.tamanho; i++) {
            indice = (posicaoInicial + i) % this.tamanho;

            if (tabela[indice] == null || tabela[indice] == ALUNO_DELETADO) {
                tabela[indice] = new Aluno(matricula, nome);
                this.quantidade++;
                System.out.println("Inserido: " + nome + " (mat=" + matricula + ") na posição " + indice);
                return;
            }

            if (tabela[indice].getMatricula() == matricula) {
                System.out.println("Atualizado: " + tabela[indice].getNome() + " -> " + nome + " (mat=" + matricula + ") na posição " + indice);
                tabela[indice].setNome(nome);
                return;
            }

            System.out.println("COLISÃO na pos " + indice + " para mat " + matricula + ". Procurando próxima...");
        }

        System.out.println("ERRO: Não foi possível inserir " + nome + ". Tabela cheia e sem redimensionamento?");
    }

    public String pegarValor(int matricula) {
        int posicaoInicial = funcaoHash(matricula);
        int indice = posicaoInicial;

        for (int i = 0; i < this.tamanho; i++) {
            indice = (posicaoInicial + i) % this.tamanho;

            if (tabela[indice] == null) {
                System.out.println("Busca por mat " + matricula + ": Posição " + indice + " está NULA. Item não encontrado.");
                return null;
            }

            if (tabela[indice] == ALUNO_DELETADO) {
                System.out.println("Busca por mat " + matricula + ": Posição " + indice + " está DELETADA. Continuando...");
                continue;
            }

            if (tabela[indice].getMatricula() == matricula) {
                System.out.println("Busca por mat " + matricula + ": Encontrado na Posição " + indice + ".");
                return tabela[indice].getNome();
            }
        }

        System.out.println("Busca por mat " + matricula + ": Volta completa na tabela. Item não encontrado.");
        return null;
    }

    public boolean removerValor(int matricula) {
        int posicaoInicial = funcaoHash(matricula);
        int indice = posicaoInicial;

        for (int i = 0; i < this.tamanho; i++) {
            indice = (posicaoInicial + i) % this.tamanho;

            if (tabela[indice] == null) {
                System.out.println("Remoção mat " + matricula + ": Posição " + indice + " está NULA. Item não encontrado.");
                return false;
            }

            if (tabela[indice] == ALUNO_DELETADO) {
                continue;
            }

            if (tabela[indice].getMatricula() == matricula) {
                tabela[indice] = ALUNO_DELETADO;
                this.quantidade--;
                System.out.println("Remoção mat " + matricula + ": Item removido da Posição " + indice + ".");
                return true;
            }
        }

        System.out.println("Remoção mat " + matricula + ": Volta completa na tabela. Item não encontrado.");
        return false;
    }

    public void imprimirTabela() {
        System.out.println("\n--- ESTADO ATUAL DA TABELA (Tamanho: " + this.tamanho + ", Qtd: " + this.quantidade + ") ---");
        for (int i = 0; i < this.tamanho; i++) {
            String valor;
            if (tabela[i] == null) {
                valor = "null";
            } else if (tabela[i] == ALUNO_DELETADO) {
                valor = "DELETADO";
            } else {
                valor = tabela[i].toString();
            }
            System.out.println("Índice " + i + ": " + valor);
        }
        System.out.println("---------------------------------------------------\n");
    }

    public static void main(String[] args) {
        TabelaHash tabelaHash = new TabelaHash();

        tabelaHash.inserirValor(10, "Ana");
        tabelaHash.inserirValor(17, "Beto");
        tabelaHash.inserirValor(24, "Carla");
        tabelaHash.inserirValor(5, "Daniel");
        tabelaHash.inserirValor(12, "Elisa");

        tabelaHash.imprimirTabela();

        System.out.println("\n*** FORÇANDO REDIMENSIONAMENTO ***");
        tabelaHash.inserirValor(1, "Fabio");

        tabelaHash.imprimirTabela();

        System.out.println("\n*** TESTANDO BUSCAS ***");
        System.out.println("Buscando Beto (mat 17): " + tabelaHash.pegarValor(17));
        System.out.println("Buscando Ana (mat 10): " + tabelaHash.pegarValor(10));
        System.out.println("Buscando Inexistente (mat 99): " + tabelaHash.pegarValor(99));

        System.out.println("\n*** TESTANDO REMOÇÃO ***");
        tabelaHash.removerValor(17);
        tabelaHash.imprimirTabela();

        System.out.println("\n*** TESTANDO BUSCA PÓS-REMOÇÃO ***");
        System.out.println("Buscando Beto (mat 17): " + tabelaHash.pegarValor(17));
        System.out.println("Buscando Carla (mat 24): " + tabelaHash.pegarValor(24));

        System.out.println("\n*** TESTANDO INSERÇÃO EM SLOT DELETADO ***");
        tabelaHash.inserirValor(32, "Gabriela");
        tabelaHash.imprimirTabela();
    }
}
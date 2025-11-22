package algoritmosDeOrdenacao;

public class QuickSort {
    private void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int menor, int maior) {
        if (menor < maior) {
            int p = partition(arr, menor, maior);

            quickSort(arr, menor, p - 1);
            quickSort(arr, p + 1, maior);
        }
    }

    private int partition(int[] arr, int menor, int maior) {
        int pivo = arr[maior];
        int i = (menor - 1);

        for (int j = menor; j < maior; j++) {
            // A troca deve ocorrer APENAS se o elemento atual for menor que o pivô
            if (arr[j] < pivo) {
                i++;
                // Lógica de swap movida para dentro do if
                int aux = arr[i];
                arr[i] = arr[j];
                arr[j] = aux;
            }
        }

        // Troca final do pivô para sua posição correta
        int aux = arr[i + 1];
        arr[i + 1] = arr[maior];
        arr[maior] = aux;
        return i + 1;
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        int[] dados = {10, 7, 8, 9, 1, 5};
        System.out.println("Array não ordenado:");
        qs.printArray(dados);

        qs.sort(dados);

        System.out.println("Array ordenado:");
        qs.printArray(dados);
    }
}
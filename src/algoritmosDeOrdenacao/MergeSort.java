package algoritmosDeOrdenacao;

public class MergeSort {


    public void mergeSort ( int [] a) {
        if ( a == null || a.length <= 1) {
            return;
        }
        mergeSortRecursivo(a, 0, a.length -1);


    }
    private void mergeSortRecursivo(int[] a, int esquerda, int direita) {
        if (esquerda < direita) {
            int pontoMedio = esquerda + (direita - esquerda) /2;


            mergeSortRecursivo(a, esquerda, pontoMedio);
            mergeSortRecursivo(a, pontoMedio + 1, direita);


            merge(a, esquerda, pontoMedio, direita);
        }
    }


    private void merge(int[] a, int esquerda, int pontoMedio, int direita) {
        int n1 = pontoMedio - esquerda + 1;
        int n2 = direita - pontoMedio;
        int[] L = new int[n1];
        int[] R = new int[n2];


        for (int i = 0; i < n1; i++) {
            L[i] = a[esquerda + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = a[pontoMedio + 1 + j];
        }


        int i = 0, j = 0, k = esquerda;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }


        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }


    public static void main(String[] args) {
        int[] arr = {90, 32, 7, 47, 87, 14, 2, 1, 0, 5};


        System.out.println("Array desordenado: ");
        for (int n : arr){
            System.out.print(n + " ");
        }


        System.out.println("\nArray ordenado com MergeSort: ");
        MergeSort ms = new MergeSort();
        ms.mergeSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}



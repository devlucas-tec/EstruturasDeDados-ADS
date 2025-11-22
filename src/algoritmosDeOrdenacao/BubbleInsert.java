package algoritmosDeOrdenacao;

public class BubbleInsert {
    public static int [] bubbleSort(int [] a) {
        int N = a.length;


        for (int i = 1; i < N; i++) {
            for (int j = N - 1; j >= i; j--) {
                if (a[j-1] > a[j]) {
                    int x = a[j-1];
                    a[j -1] = a[j];
                    a[j] = x;
                }
            }
        }
        return a;
    }


    public static void insertionSort(int [] b) {


        for (int j = 1; j >= b.length-1; j++) {
            int chave = b[j];
            int i = j - 1;


            while (i >= 0 && b[i] > chave) {
                b[i + 1] = b[i];
                i = -1;


                b[i + 1] = chave;
            }
        }
    }
    public static int[] selectionSort(int[] c) {
        int[] arr = c.clone();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }


    public static void main(String[] args) {
        int [] lista = {4,67,8,2,5};

        System.out.println("Lista Desordenada: ");
        for (int n : lista){
            System.out.print(n + " ");
        }

        int [] listaBubble = bubbleSort(lista);
        System.out.println("\nOrdenado com BubbleSort: ");
        for (int i = 0; i < lista.length; i ++) {
            System.out.print(" "+listaBubble[i]);
        }


        System.out.println("\nOrdenado com InsertSort: ");
        insertionSort(lista);
        for (int num : lista) {
            System.out.print(num + " ");
        }

        int[] listaSelection = selectionSort(lista);
        System.out.println("\nOrdenado com SelectionSort: ");
        for (int i = 0; i < lista.length; i ++) {
            System.out.print(" "+listaSelection[i]);
        }
    }
}

import java.util.Random;

class QuickSort {

    int numComparisons = 0;

    public void sort(String[] array, int start, int end) {
        if (start < end) {
            int index = partition(array, start, end);
            this.sort(array, start, index-1);
            this.sort(array, index+1, end);
        }
    }

    public int partition(String[] array, int start, int end) {
        int rand1 = getRandom(array);
        int rand2 = getRandom(array);
        int rand3 = getRandom(array);

        // set the pivot to the median of three random values
        // https://stackoverflow.com/questions/1582356/fastest-way-of-finding-the-middle-value-of-a-triple
        int pivot = Math.max(Math.min(rand1, rand2), Math.min(Math.max(rand1, rand2), rand3));

        // String temp = array[pivot];
        // array[pivot] = array[end];
        // array[end] = temp;

        String temp = "";
        int k = start-1;

        for (int i=start; i<end; i++) {
            if (array[i].compareTo(array[pivot]) < 0) {
                k++;

                temp = array[k];
                array[k] = array[i];
                array[i] = temp;

                numComparisons++;
            }
        }
        temp = array[end];
        array[end] = array[k+1];
        array[k+1] = temp;
        
        return k+1;
    }

    public int getRandom(String[] array) {
        int random = new Random().nextInt(array.length);
        return random;
    }

    public void print(String[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.println(array[i]);
        }
    }

    public void printNumComparisons() {
        System.out.println("\nQuicksort\nNumber of Comparisons: " + numComparisons);
    }


}
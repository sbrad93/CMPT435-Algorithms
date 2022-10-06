import java.util.Random;

class QuickSort {

    int numComparisons = 0;

    public void sort(String[] array, int start, int end) { 
        if (start < end) { 
            int partitionIndex = partition(array, start, end);  
            sort(array, start, partitionIndex-1); 
            sort(array, partitionIndex+1, end); 
        } 
    }

    int partition(String[] array, int start, int end) { 
        // places pivot in correct postion,
        // all smaller elements to the left,
        // and all greater elements to the right

        // this function places the pivot (the median of three random values) at the end of the array
        setRandomPivot(array, start, end);
        String pivot = array[end]; 
      
        // index of string that's 'smaller' (closer to top of alphabet)
        int i = start-1;

        for (int j=start; j<end; j++) { 
            // current element is smaller than pivot
            if (array[j].compareTo(pivot) < 0) { 
                i++; 
                swap(array, i, j);
                numComparisons++;
            } 
        } 
        // end is the pivot index
        swap(array, i+1, end);
        return i+1; 
    } 

    public void setRandomPivot(String[] array, int start, int end) { 
        // find three random index values between start and end
        Random rand= new Random(); 
        int rand1 = rand.nextInt(end-start)+start;
        int rand2 = rand.nextInt(end-start)+start;
        int rand3 = rand.nextInt(end-start)+start;

        // calculate the median of the three values
        // https://stackoverflow.com/questions/1582356/fastest-way-of-finding-the-middle-value-of-a-triple
        int pivot = Math.max(Math.min(rand1, rand2), Math.min(Math.max(rand1, rand2), rand3));
          
        swap(array, pivot, end);
    }

    public void swap(String[] array, int x, int y) {
        // swaps two elements in an array
        String temp = array[x];
        array[x] = array[y];
        array[y] = temp;
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
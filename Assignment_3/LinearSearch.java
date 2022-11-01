class LinearSearch {

    int numComparisons = 0;
    double comparisonTotal = 0;

    LinearSearch(){
        this.numComparisons = 0;
        this.comparisonTotal = 0;
    }

    public int search(String[] array, String target) {
        this.numComparisons = 0;
        int i = 0;
        int foundIndex = -1;

        while (i < array.length) {
            // Loop through entire array until target element is found
            if (array[i].compareTo(target.toLowerCase()) == 0) {
                foundIndex = i;
                break;
            }
            this.numComparisons++;
            i++;
        }
        // Track the total number of comparisons to find an average later
        this.comparisonTotal += this.numComparisons;
        return foundIndex;
    }

    // Print the number of comparisons for each search
    public void printNumComparisons() {
        System.out.println("\nNumber of Comparisons: " + this.numComparisons);
    }

    // Print the average number of comparisons after a given number of searches
    public void printAvgComparison(int total) {
        System.out.printf("\nLinear Search: Average Number of Comparisons:  %.2f %n", this.comparisonTotal/total);
    }
}
class LinearSearch {

    int numComparisons = 0;
    int comparisonTotal = 0;

    LinearSearch(){
        this.numComparisons = 0;
        this.comparisonTotal = 0;
    }

    public int search(String[] array, String search) {
        this.numComparisons = 0;
        int i = 0;
        int foundIndex = -1;

        while (i < array.length) {
            // Loop through entire array until search is found
            if (array[i].compareTo(search.toLowerCase()) == 0) {
                foundIndex = i;
                // System.out.println(foundIndex + " " + search);
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
        System.out.println("\nLinear Search\nNumber of Comparisons: " + this.numComparisons);
    }

    // Print the average number of comparisons after a certain number of searches
    public void printAvgComparison(int total) {
        System.out.println("\nLinear Search\nAverage Number of Comparisons: " + this.comparisonTotal/total);
    }
}
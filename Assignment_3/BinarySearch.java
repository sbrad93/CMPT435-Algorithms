class BinarySearch {

    int numComparisons = 0;
    double comparisonTotal = 0;

    BinarySearch(){
        this.numComparisons = 0;
        this.comparisonTotal = 0;
    }

    public int search(String[] array, String target, int start, int end) {
        int foundIndex = -1;
        int midPoint = (start+end)/2;
        this.numComparisons++;

        if (target.compareTo(array[midPoint]) == 0) {
            foundIndex = midPoint;
        } else if (target.compareTo(array[midPoint]) < 0) {
            foundIndex = search(array, target, start, midPoint-1);
        } else {
            foundIndex = search(array, target, midPoint+1, end);
        }
        return foundIndex;
    }

    // Print the number of comparisons for each search
    public void printNumComparisons() {
        System.out.println("\nNumber of Comparisons: " + this.numComparisons);
    }

    // Print the average number of comparisons after a certain number of searches
    public void printAvgComparison(int total) {
        System.out.printf("\nBinary Search: Average Number of Comparisons:  %.2f %n", this.comparisonTotal/total);
    }
}
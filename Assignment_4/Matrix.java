class Matrix {

    private String[][] matrixArr = null;

    public Matrix(int numVertices) {
        this.matrixArr = new String[numVertices][numVertices];
        this.init();
    }

    // Initializes the matrix array with dashes
    public void init() {
        for (int i=0; i<this.matrixArr.length; i++) {
            for (int j=0; j<this.matrixArr[i].length; j++) {
                this.matrixArr[i][j] = "-";
            }
        }
    }

    // Creates an entry in the matrix array with a value of "1"
    public void createEntry(int rowEle, int columnEle) {
        for (int i=0; i<this.matrixArr.length; i++) {
            for (int j=0; j<this.matrixArr[i].length; j++) {
                if (i==rowEle && j==columnEle) {
                    // vertices start at 1 instead of 0,
                    // so we have to account for that when plotting in the matrix
                    this.matrixArr[i-1][j-1] = "1";
                }
            }
        }
    }

    // Prints the matrix array in matrix format
    public void print() {
        for (int i=0; i<this.matrixArr.length; i++) {
            for (int j=0; j<this.matrixArr[i].length; j++) {
                System.out.print(this.matrixArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Matrix getMatrix() {
        return this;
    }
}
class Matrix {

    private String[][] matrixArr = null;
    private String[] verticeArr = null;

    public Matrix(String[] vertices) {
        this.matrixArr = new String[vertices.length][vertices.length];
        this.verticeArr = vertices;
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
    public void createEntry(String rowEle, String columnEle) {
        for (int i=0; i<this.matrixArr.length; i++) {
            for (int j=0; j<this.matrixArr[i].length; j++) {
                if (this.verticeArr[i].compareTo(rowEle) == 0 && this.verticeArr[j].compareTo(columnEle) == 0) {
                    this.matrixArr[i][j] = "1";
                    this.matrixArr[j][i] = "1";
                }
            }
        }
    }

    // Prints the matrix array in matrix format
    public void print() {
        System.out.println();
        for (int i=0; i<this.matrixArr.length; i++) {
            System.out.print(this.verticeArr[i] + " ");
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
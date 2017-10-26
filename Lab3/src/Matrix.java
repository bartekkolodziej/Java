public class Matrix {

    double[]data;
    int rows;
    int cols;
    //...
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }

    Matrix(double[][] d){
        int max = 0;
        for(int i=0; i<d.length; i++){
            if(d[i].length > max) max = d[i].length;
        }

        this.rows = d.length;
        this.cols = max;
        data = new double[rows*cols];
        int k=0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(j < d[i].length){
                    data[k] = d[i][j];
                    k++;
                }else
                {
                    data[k] = 0;
                    k++;
                }
            }
        }
    }



    double[][] asArray(){
        double[][] array_to_return = new double[this.rows][this.cols];

        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++) array_to_return[i][j] = this.data[i*this.cols + j];
        }

        return array_to_return;
    }



    double get(int r,int c){

        return data[r * this.cols + c];
    }


    void set (int r,int c, double value){
        data[r * this.cols + c] = value;
    }


    public String toString(){
        StringBuilder buf = new StringBuilder();
        int k=0;
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0; j<this.cols; j++) {
                buf.append(data[k] + ",");
                k++;
            }
            buf.append("] \n");
        }
        //...
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        this.cols = newCols;
        this.rows = newRows;
    }

    int[] shape(){
        int[] dimensions = new int[2];
        dimensions[0] = this.cols;
        dimensions[1] = this.rows;
        return dimensions;
    }

    Matrix add(Matrix m){
        if(m.cols != this.cols || m.rows != this.rows) throw new RuntimeException(String.format("Wrong dimensions"));
        Matrix tmp = new Matrix(m.rows, m.cols);
        int k=0;
        for(int i=0; i<m.rows * m.cols; i++){
            tmp.data[i] = this.data[i] + m.data[i];
        }
        return tmp;
    }

    Matrix sub(Matrix m){
        if(m.cols != this.cols || m.rows != this.rows) throw new RuntimeException(String.format("Wrong dimensions"));
        Matrix tmp = new Matrix(m.rows, m.cols);
        int k=0;
        for(int i=0; i<m.rows * m.cols; i++){
            tmp.data[i] = this.data[i] - m.data[i];
        }
        return tmp;
    }

    Matrix mul(Matrix m){
        if(m.cols != this.cols || m.rows != this.rows) throw new RuntimeException(String.format("Wrong dimensions"));
        Matrix tmp = new Matrix(m.rows, m.cols);
        int k=0;
        for(int i=0; i<m.rows * m.cols; i++){
            tmp.data[i] = this.data[i] * m.data[i];
        }
        return tmp;
    }

    Matrix div(Matrix m){
        if(m.cols != this.cols || m.rows != this.rows) throw new RuntimeException(String.format("Wrong dimensions"));
        Matrix tmp = new Matrix(m.rows, m.cols);
        int k=0;
        for(int i=0; i<m.rows * m.cols; i++){
            if(m.data[i] == 0) throw new RuntimeException(String.format("Div by 0"));
            else tmp.data[i] = this.data[i] / m.data[i];
        }
        return tmp;
    }

    Matrix add(double w){

        Matrix tmp = new Matrix(this.rows, this.cols);
        int k=0;
        for(int i=0; i<this.rows * this.cols; i++){
            data[i] += w;
        }
        return tmp;
    }

    Matrix sub(double w){
        Matrix tmp = new Matrix(this.rows, this.cols);
        int k=0;
        for(int i=0; i<this.rows * this.cols; i++){
            data[i] -= w;
        }
        return tmp;
    }

    Matrix mul(double w){
        Matrix tmp = new Matrix(this.rows, this.cols);
        int k=0;
        for(int i=0; i<this.rows * this.cols; i++){
            data[i] *= w;
        }
        return tmp;
    }

    Matrix div(double w){
        if(w == 0) throw new RuntimeException(String.format("Div by 0"));
        Matrix tmp = new Matrix(this.rows, this.cols);
        int k=0;
        for(int i=0; i<this.rows * this.cols; i++){
            data[i] += w;
        }
        return tmp;
    }

    Matrix dot(Matrix m){
        if(this.cols != m.rows) throw new RuntimeException(String.format("Wrong dimensions"));
        Matrix tmp = new Matrix(this.rows, m.cols);
        int k=0;
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<m.cols; j++){
                tmp.data[k] = this.data[i*cols + j] * m.data[j*cols + i];
                //niedokonczone
            }

        }
        return tmp;
    }


    public static void main(String[] args) {

    }

}
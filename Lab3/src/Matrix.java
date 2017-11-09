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

    Matrix(double[][] d){ //create matrix based on 2d array
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



    double[][] asArray(){ //return matrix as 2d array
        double[][] array_to_return = new double[this.rows][this.cols];
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++) array_to_return[i][j] = this.data[i*this.cols + j];
        }

        return array_to_return;
    }



    double get(int r,int c){ //get data

        return data[r * this.cols + c];
    }


    void set (int r,int c, double value){
        data[r * this.cols + c] = value;
    } //set data


    public String toString(){ //return matrix as a String
        StringBuilder buf = new StringBuilder();
        int k=0;
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0; j<this.cols; j++) {
                buf.append(data[k] + ",");
                k++;
            }
            buf.deleteCharAt(buf.length()-1);
            buf.append("]\n");
        }
        buf.deleteCharAt(buf.length()-1);
        buf.append("]\n");
        return buf.toString();
    }

    void reshape(int newRows,int newCols) { //swap rows and columns
        if (rows * cols != newRows * newCols)
            throw new IllegalArgumentException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        this.cols = newCols;
        this.rows = newRows;
        double[][] tmp = this.asArray();
        int k = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                this.data[k] = tmp[j][i];
                k++;
            }
        }
    }

    int[] shape(){ //return dimensions of the array
        int[] dimensions = new int[2];
        dimensions[0] = this.rows;
        dimensions[1] = this.cols;
        return dimensions;
    }

    Matrix add(Matrix m){ //add matrix m to this matrix and return new matrix
        if(m.cols != this.cols || m.rows != this.rows) throw new RuntimeException(String.format("Wrong dimensions"));
        Matrix matrix_to_return = new Matrix(m.rows, m.cols);
        for(int i=0; i<m.rows * m.cols; i++){
            matrix_to_return.data[i] = this.data[i] + m.data[i];
        }
        return matrix_to_return;
    }

    Matrix sub(Matrix m){ //subtract matrix m form this matrix and return new matrix
        if(m.cols != this.cols || m.rows != this.rows) throw new RuntimeException(String.format("Wrong dimensions"));
        Matrix matrix_to_return = new Matrix(m.rows, m.cols);
        for(int i=0; i<m.rows * m.cols; i++){
            matrix_to_return.data[i] = this.data[i] - m.data[i];
        }
        return matrix_to_return;
    }

    Matrix mul(Matrix m){ //multiply elements of matrix m by elemenents of this matrix and return new matrix
        if(m.cols != this.cols || m.rows != this.rows) throw new RuntimeException(String.format("Wrong dimensions"));
        Matrix matrix_to_return = new Matrix(m.rows, m.cols);
        for(int i=0; i<m.rows * m.cols; i++){
            matrix_to_return.data[i] = this.data[i] * m.data[i];
        }
        return matrix_to_return;
    }

    Matrix div(Matrix m){ //divide elements of this matrix by elements of matrix m and return new matrix
        if(m.cols != this.cols || m.rows != this.rows) throw new RuntimeException(String.format("Wrong dimensions"));
        Matrix matrix_to_return = new Matrix(m.rows, m.cols);
        for(int i=0; i<m.rows * m.cols; i++){
            if(m.data[i] == 0) throw new RuntimeException(String.format("Div by 0"));
            else matrix_to_return.data[i] = this.data[i] / m.data[i];
        }
        return matrix_to_return;
    }

    Matrix add(double w){ //add w to every element of this matrix and return new matrix
        Matrix matrix_to_return = new Matrix(this.rows, this.cols);
        for(int i=0; i<this.rows * this.cols; i++){
            matrix_to_return.data[i] = w + this.data[i];
        }
        return matrix_to_return;
    }

    Matrix sub(double w){ //subtract w form every element of this matrix and return new matrix
        Matrix matrix_to_return = new Matrix(this.rows, this.cols);
        for(int i=0; i<this.rows * this.cols; i++){
            matrix_to_return.data[i] = this.data[i] - w;
        }
        return matrix_to_return;
    }

    Matrix mul(double w){ //multiply every element of this matrix by w and return new matrix
        Matrix matrix_to_return = new Matrix(this.rows, this.cols);
        for(int i=0; i<this.rows * this.cols; i++){
            matrix_to_return.data[i] = this.data[i] * w;
        }
        return matrix_to_return;
    }

    Matrix div(double w){ //divide every element of this matrix by w and return new matrix
        if(w == 0) throw new RuntimeException(String.format("Div by 0"));
        Matrix matrix_to_return = new Matrix(this.rows, this.cols);
        for(int i=0; i<this.rows * this.cols; i++){
            matrix_to_return.data[i] = this.data[i] / w;
        }
        return matrix_to_return;
    }

    Matrix dot(Matrix m){ //multiply this matrix by matrix m and return new matrix
        if(this.cols != m.rows) throw new RuntimeException(String.format("Wrong dimensions"));
        double[][] tmp_this = this.asArray();
        double[][] tmp_m = m.asArray();
        Matrix matrix_to_return = new Matrix(this.rows, m.cols);
        for(int i=0; i<matrix_to_return.rows; i++){
            for(int j=0; j<matrix_to_return.cols; j++){
                for(int k=0; k<matrix_to_return.rows; k++)
                    matrix_to_return.data[i*this.cols + j] += tmp_this[i][k] * tmp_m[k][j];
            }
        }
        return matrix_to_return;
    }

    double frobenius(){
        double sum=0;
        for(int i=0; i<this.cols * this.rows; i++)
            sum += Math.abs(this.data[i]) * Math.abs(this.data[i]);
        return Math.sqrt(sum);
    }

    //////// GRUPA C /////////

    Matrix sumRows(){
        Matrix matrix_to_return = new Matrix(1, this.cols);
        double[][] tmp = this.asArray();
        int k=0;
        for(int i=0; i<this.cols; i++){
            for(int j=0; j<this.rows; j++) {
                matrix_to_return.data[k] += tmp[j][i];
            }
            k++;
        }
        return matrix_to_return;
    }

    /////////////////////////


}
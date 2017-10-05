package com.giancarlo.tesis.Scenes;

/**
 *
 * @author Giancarlo y Diego
 */
public class NNetwork {

    static final float NUM_ONE = 1.0f;
    static final float DEN_ONE = 1.0f;

    public NNetwork() {

    }

    /*
     fórmula derivada = (X*(X-1))
     fórmula sin derivada = 1 / (1 + exp(-X*theta))
     */
    public float[][] nonlin(float[][] z, boolean isDerivative) {

        int cols = z[0].length;
        int rows = z.length;

        float den = 0, num = 0;

        if (isDerivative) {
            // TODO: backpropagation
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    z[i][j] *= (1 - z[i][j]);
                }
            }
            return z;
        } else {
            // fastforward propagation
            // formula => 1/(1 + exp(-z))
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    z[i][j] = (float) (NUM_ONE / (DEN_ONE + Math.exp(floatToDouble(-1.0f * z[i][j]))));
                }
            }
            return z;
        }
    }

    public float[][] transpose(float arr[][]) {

        int cols = arr[0].length;
        int rows = arr.length;
        float[][] temp = new float[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[j][i] = arr[i][j];
            }
        }
        return temp;
    }

    // la dimension de X(17,5) y de theta(5,4) => resultando z(17,4)
    public float[][] multiply(float X[][], float theta[][]) {
        int f1 = X.length;
        int c1 = X[0].length;
        int c2 = theta[0].length;
        float[][] layer = new float[f1][c2];
        int i, j, k;
        for (i = 0; i < f1; i++) {
            for (j = 0; j < c2; j++) {
                layer[i][j] = 0;
                for (k = 0; k < c1; k++) {
                    layer[i][j] += (X[i][k] * theta[k][j]);
                }

            }
        }
        layer = nonlin(layer, false);
        return layer;
    }

    public float[][] multiplyElementWise(float arr1[][], float[][] arr2) {
        int rows = arr1.length;
        int cols = arr1[0].length;
        float[][] temp = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = (arr1[i][j] * arr2[i][j]);
            }
        }
        return temp;
    }

    // (17, 4)
    public float[][] addInterceptor(float[][] arr) {
        int cols = arr[0].length;
        int rows = arr.length;
        float[][] temp = new float[rows][cols + 1];
        // Desplazamos los valores
        for (int i = 0; i < rows; i++) {
            for (int j = cols; j >= 1; j--) {
                temp[i][j] = arr[i][j - 1];
            }
        }
        // agregamos el interceptador
        for (int i = 0; i < rows; i++) {
            temp[i][0] = 1.0f;
        }
        return temp;
    }

    public int[] getMaxByRow(float[][] arr) {
        int cols = arr[0].length;
        int rows = arr.length;
        int[] indexes = new int[rows];
        int idx;
        for (int i = 0; i < rows; i++) {
            float maxi = -1000000000.0f;
            idx = -1;
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] > maxi) {
                    maxi = arr[i][j];
                    idx = j;
                }
            }
            indexes[i] = idx;
        }
        return indexes;
    }

    private double floatToDouble(float num) {
        return Double.valueOf(Float.toString(num));
    }

    public float acurracy(int[] pred, float[][] y) {
        int n = pred.length;
        int correct = 0;
        for (int i = 0; i < n; i++) {
            int val = (int) y[i][0];
            //System.out.println("OR: " + val + " - PRED: " + pred[i]);
            if (val == pred[i]) {
                correct++;
            }
        }
        return correct;
    }

    public float[][] subtract(float[][] arr, float[][] y) {
        // del tamaño de z3
        int cols = arr[0].length;
        int rows = arr.length;
        float[][] error = new float[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                error[i][j] = (y[i][0] - arr[i][j]);
            }
        }
        return error;
    }

    public float[][] sum(float[][] arr1, float[][] arr2) {
        int rows = arr1.length;
        int cols = arr1[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr1[i][j] += arr2[i][j];
            }
        }
        return arr1;
    }

    public float[][] abs(float[][] arr) {

        int rows = arr.length;
        int cols = arr[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = Math.abs(arr[i][j]);
            }
        }
        return arr;
    }

    public float meanAll(float[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        float total = 0;
        for (int i = 0; i < rows; i++) {
            float sum = 0;
            for (int j = 0; j < cols; j++) {
                sum += arr[i][j];
            }
            total += (sum / cols);
        }
        return (total / rows);
    }

    public float totalError(float[][] arr) {
        float error = meanAll(abs(arr));
        return error;
    }
}

package com.giancarlo.tesis.Scenes;

import java.util.Random;

/**
 *
 * @author Giancarlo y Diego
 */
public class Data {

    private int nFeatures;
    private int nExamples;
    private float[][] X;
    private float[][] y;

    // de manera estática creamos los pesos para las dos capas de la red neuronal
    // dimensiones (17,4)
    private float[][] theta1; // dimensiones (4, 5)
    private float[][] theta2; // dimensiones (3, 5)

    public Data() {

    }

    public int getnFeatures() {
        return nFeatures;
    }

    public void setnFeatures(int nFeatures) {
        this.nFeatures = nFeatures;
    }

    public int getnExamples() {
        return nExamples;
    }

    public void setnExamples(int nExamples) {
        this.nExamples = nExamples;
    }

    public float[][] getX() {
        return X;
    }

    public void setX(float[][] X) {
        this.X = X;
    }

    public float[][] getY() {
        return y;
    }

    public void setY(float[][] y) {
        this.y = y;
    }

    public float[][] getTheta1() {
        return this.theta1;
    }

    public void setTetha1(float[][] theta1) {
        this.theta1 = theta1;
    }

    public float[][] getTheta2() {
        return this.theta2;
    }

    public void setTetha2(float[][] theta2) {
        this.theta2 = theta2;
    }

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

    public float[][] generateWeights(int n, int m) {
        float[][] theta = new float[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                theta[i][j] = generateRandom(-1, 1);
            }
        }
        return theta;
    }

    private float generateRandom(float min, float max) {
        float alet = min + (float) Math.random() * (max - min);
        //alet = 2 * alet - 1;
        return alet;
    }

    private float generateRandomEpsilon(float min, float max, float epsilon) {
        float alet = min + (float) Math.random() * (max - min);
        alet = alet * (2 * epsilon) - epsilon;
        //alet = 2 * alet - 1;
        return alet;
    }

    private float generateRandomSeed(float min, float max) {
        Random random = new Random();
        float alet = 2 * random.nextFloat() - 1;
        return alet;
    }
}

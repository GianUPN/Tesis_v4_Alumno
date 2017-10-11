package com.giancarlo.tesis.Scenes;

public class RNANetwork {

    static final float NUM_ONE = 1.0f;
    static final float DEN_ONE = 1.0f;

    public RNANetwork() {

    }
    static final int L1 = 4, L2 = 3;

    public static int calcular_dificultad_siguiente(float[][]X) {

        // ingresar los datos desde la app



        /*
        //Ingresar datos desde formulario
        float dificultad;
        //Scanner in = new Scanner(System.in);
        //dificultad = in.nextFloat();
        reader.getDataFromForm(1.0f, 12.0f, 4.0f,6.0f);
        Data objData = reader.getAllData();
        */
        //float [][] theta1,theta2;

        float[][] theta1={
                {0.032107234f,	-0.48615962f,	0.79486394f,	0.855585f,	0.23853147f	},
                {0.6753856f,	-0.6925923f,	0.75593495f,	-0.5667248f,	0.5786716f	},
                {0.38746583f,	0.9439857f,	0.5932449f,	-0.71407723f,	-0.2859354f	},
                {0.11284208f,	-0.6518376f,	-0.20413184f,	-0.97096103f,	-0.53691614f	}
        };

        float [][] theta2 = {
                {-0.535703f,	-0.9353592f,	0.70818007f,	-0.31604832f,	-0.42182356f	},
                {0.22076297f,	0.57177126f,	-0.09266496f,	-0.5493983f,	0.38956165f   },
                {-0.17309141f,	0.867291f,	-0.041444063f,	0.0052491426f,	-0.3624835f	}
        };

        // float[][] X, y;

        // obtenemos los datos de la matriz X , y


        //X = objData.getX();
        //y = objData.getY();

        // agregamos la columna de 1's (interceptor)


        // generamos los valores de theta1 (con la cantidad de caracteristicas de X)
        // objData.setTetha1(objData.generateWeights(L1, X[0].length));

        // obtenemos los valores de theta1
        //theta1 = objData.getTheta1();

        // creamos la red neuronal
        NNetwork nn = new NNetwork();

        // realizamos los calculos para la capa oculta
        float[][] z2 = nn.multiply(X, nn.transpose(theta1));

        // agregamos la columna de 1's (interceptor - capa oculta)
        float[][] a2 = nn.addInterceptor(z2);

        // generamos los valores de theta1 (con la cantidad de caracteristicas de la capa oculta[1])
        //objData.setTetha2(objData.generateWeights(L2, a2[0].length));

        // obtenemos los valores de theta2
        //theta2 = objData.getTheta2();

        // calculamos el valor de salida para la prediccion dimensiones(17x3)
        float[][] z3 = nn.multiply(z2, nn.transpose(theta2));
        // prediccion
        int[] pred = nn.getMaxByRow(z3);

        printMatrix(theta1);

        System.out.println("");

        printMatrix(theta2);

        printVector(pred);

        //System.out.println("Exactitud: " + nn.acurracy(pred, y) / y.length * 100);

//            //BACKPROPAGATION
//            float[][] z3_error = nn.subtract(z3, y);
//
//            //if(qq % 10000 == 0) {
//                float error = nn.meanAll(nn.abs(z3_error));
//                System.out.println("Error: " + error);
//            //}
//
//            float[][] z3_delta = nn.multiplyElementWise(z3_error, nn.nonlin(z3, true));
//
//            float[][] z2_error = nn.multiply(z3_delta, nn.transpose(theta2));
//            float[][] z2_delta = nn.multiplyElementWise(z2_error, nn.nonlin(z2, true));
//
//            theta2 = nn.sum(theta2, nn.multiply(nn.transpose(z2), z3_delta));
//            theta1 = nn.sum(theta1, nn.transpose(nn.multiply(nn.transpose(X), z2_delta)));
        return pred[0];
    }

    private static void printMatrix(float[][] arr) {
        int cols = arr[0].length;
        int rows = arr.length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    private static void printVector(int[] arr) {
        int rows = arr.length;
        for (int i = 0; i < rows; i++) {
            System.out.println(arr[i]);
        }
    }


}

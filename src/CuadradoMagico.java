
/**
 * @author Jose Perez Hurtado
 */

/**
 * Comprobador de Cuadrado Magico.
 *
 */

class CuadradoMagico {

    private static int constanteMagica;

    public static boolean testFilas(int[] vector, int constanteMagica) {
        int sumAux = 0;
        for (int i = 0; i < vector.length; i++) {
            sumAux += vector[i];
            if (sumAux > constanteMagica)
                return false;
        }
        if (sumAux != constanteMagica)
            return false;
        return true;
    }

    public static boolean testColumnas(int[][] matriz, int dim, int constanteMagica) {
        int sumAux;

        for (int col = 0; col < dim; col++) {
            sumAux = 0;
            for (int fila = 0; fila < dim; fila++) {
                sumAux += matriz[fila][col];
                if (sumAux > constanteMagica)
                    return false;
            }
            if (sumAux != constanteMagica)
                return false;
        }
        return true;
    }

    public static boolean testDiagonal(int[][] matriz, int dim) {
        // Comprueba si las diagonales suman los mismo

        // Sacado test diagonal de testmagic como metodo y llamado hacia ese metodo
        int sumaDiagonalPrincipal = 0;
        int sumaDiagonalMenor = 0;
        for (int fila = 0; fila < dim; fila++) {
            sumaDiagonalPrincipal += matriz[fila][fila];
            if (sumaDiagonalPrincipal > constanteMagica)
                return false;
            sumaDiagonalMenor += matriz[fila][dim - 1 - fila];
            if (sumaDiagonalMenor > constanteMagica)
                return false;
        }

        if ((sumaDiagonalPrincipal != constanteMagica) || (sumaDiagonalMenor != constanteMagica)) {
            return false;
        }
        return true;
    }

    public static boolean contFilas(int[][] matriz, int dim) {
        // Comprueba si cada fila suma lo mismo //sacado test filas como nuevo método
        for (int fila = 0; fila < dim; fila++) {
            if (!testFilas(matriz[fila], constanteMagica))
                return false;
        }
        if (!testColumnas(matriz, dim, constanteMagica)) {
            return false;
        }
        return true;
    }

    public static boolean compNumeros(int[][] matriz, int dim) {

        //código sacado y creado un método

        boolean[] test = new boolean[dim * dim];
        int max = dim * dim;
        int element;
        for (int fila = 0; fila < dim; fila++) {
            for (int col = 0; col < dim; col++) {
                element = matriz[fila][col];
                if ((element > max) || (element <= 0))
                    return false;
                if (test[element - 1])
                    return false;
                test[element - 1] = true;
            }
        }
        return true;
    }

    public static boolean testMagic(int[][] matriz, int dim) {
        constanteMagica = 0;
        for (int fila = 0; fila < dim; fila++) {
            constanteMagica += matriz[fila][0];
        }
        if (!testDiagonal(matriz, dim) || !contFilas(matriz, dim)) { //llamado contfilas aqui
            return true;
        }
        return false;
    }

    public static boolean testNormal(int[][] matriz, int dim) {

        int constanteMagica = dim * (dim * dim + 1) / 2;

        // comprueba si todos los numeros estan presentes
        // valor por defecto falso

        if (testDiagonal(matriz, dim) || contFilas(matriz, dim) || compNumeros(matriz, dim)) { //cambiados por los nuevos metodos y llamados hacia aqui
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int magicSquareMars[][] = {{11, 24, 7, 20, 3},
                {4, 12, 25, 8, 16},
                {17, 5, 13, 21, 9},
                {10, 18, 1, 14, 22},
                {23, 6, 19, 2, 15}};
        int magicSquareJupiter[][] = {{4, 14, 15, 1},
                {9, 7, 6, 12},
                {5, 11, 10, 8},
                {16, 2, 3, 13}};
        int magicSquareSagradaFamilia[][] = {{1, 14, 14, 4},
                {11, 7, 6, 9},
                {8, 10, 10, 5},
                {13, 2, 3, 15}};
        if (testNormal(magicSquareJupiter, 4)) {
            System.out.println("El Cuadrado Magico Jupiter es un cuadrado magico normal");
        } else {
            System.out.println("El Cuadrado Magico Jupiter NO es un cuadrado magico normal");
        }

        if (testNormal(magicSquareMars, 5)) {
            System.out.println("El Cuadrado Magico Marte es un cuadrado magico normal");
        } else {
            System.out.println("El Cuadrado Magico Marte NO es un cuadrado magico normal");
        }

        if (testNormal(magicSquareSagradaFamilia, 4)) {
            System.out.println("El Cuadrado Magico Sagrada Famila es un cuadrado magico normal");
        } else {
            System.out.println("El Cuadrado Magico Sagrada Famila NO es un cuadrado magico normal");
            if (testMagic(magicSquareSagradaFamilia, 4))
                System.out.println("Aunque SI es un Cuadrado Magico");
            else
                System.out.println("No es un Cuadrado Magico");
        }
    }
}



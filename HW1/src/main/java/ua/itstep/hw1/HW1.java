/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ua.itstep.hw1;

/**
 *
 * @author pronc
 */
public class HW1 {

    public static void main(String[] args) {
        int[][] Matrix = {
            {1, 2},
            {3, 4}
        };
        int[] Vector = {5, 6};
        int[] Result = new int[Matrix.length];
        
        for (int ii = 0; ii < Matrix.length; ii++) {
            int Summ = 0;
            for (int jj = 0; jj < Matrix[ii].length; jj++) {
                Summ += Matrix[ii][jj] * Vector[jj];
            }
            Result[ii] = Summ;
        }
        for (int k = 0; k < Result.length; k++) {
            System.out.print(Result[k]);
            if (k < Result.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}

package org.ekno.sudoku;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        for(int i = 0; i < 10; i++){
            Grid g = new Grid();
            g.randomize();
            System.out.println();
        }
    }
}

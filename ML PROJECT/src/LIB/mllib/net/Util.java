package LIB.mllib.net;

import java.util.Random;
import LIB.mathlib.DS.np1D_Array;
import LIB.mathlib.DS.np2D_Array;


public class Util
{

    public static np2D_Array[] batchFormation(np1D_Array[] in, int size)
    {
        np2D_Array[] result = new np2D_Array[(int) Math.ceil((double) in.length / size)];
        for (int i = 0; i < result.length; i++)
        {
            int pos = i * size;
            np1D_Array[] part;
            if (pos + size > in.length)
            {
                part = new np1D_Array[in.length - pos];
            } else
            {
                part = new np1D_Array[size];
            }
            for (int j = 0; j < part.length; j++)
            {
                part[j] = in[pos + j];
            }
            result[i] = new np2D_Array(part);
        }
        return result;
    }

    private static void swap(np1D_Array[] array, int i, int j)
    {
        np1D_Array temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void randomShuffle(np1D_Array[] dataIn, np1D_Array[] dataOut)
    {
        Random rand = new Random();
        for (np1D_Array data : dataIn)
        {
            int i1 = rand.nextInt(dataIn.length);
            int i2 = rand.nextInt(dataIn.length);
            swap(dataIn, i1, i2);
            swap(dataOut, i1, i2);
        }
    }
}

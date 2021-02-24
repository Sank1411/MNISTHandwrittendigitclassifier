package LIB.mllib.options.initialization;

import java.util.Random;
import LIB.mathlib.DS.np2D_Array;
import LIB.mathlib.DS.np1D_Array;
public class Normal_Initialise extends Initialise_weight_biase
{
    @Override
    public np1D_Array[] random_init_biases(int[] sizes)
    {
        Random rand = new Random();
        np1D_Array[] biases = new np1D_Array[sizes.length - 1];

        for (int i = 0; i < sizes.length - 1; i++)
        {
            double[] temp = new double[sizes[i + 1]];
            for (int j = 0; j < sizes[i + 1]; j++)
            {
                temp[j] = rand.nextGaussian();
            }
            biases[i] = new np1D_Array(temp);
        }

        return biases;
    }
    @Override
    public np2D_Array[] random_init_weight(int[] sizes)
    {
        Random rand = new Random();
        np2D_Array[] weights = new np2D_Array[sizes.length - 1];

        for (int i = 0; i < sizes.length - 1; i++)
        {
            double[][] temp = new double[sizes[i + 1]][sizes[i]];
            double normalising_factor = Math.sqrt(sizes[i]);
            for (int j = 0; j < sizes[i + 1]; j++)
            {
                for (int k = 0; k < sizes[i]; k++)
                {
                    temp[j][k] = rand.nextGaussian() / normalising_factor;
                }
            }
            weights[i] = new np2D_Array(temp);
        }

        return weights;
    }

}

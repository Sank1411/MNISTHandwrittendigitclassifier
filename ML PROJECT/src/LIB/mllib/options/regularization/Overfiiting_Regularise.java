package LIB.mllib.options.regularization;

import LIB.mathlib.DS.np2D_Array;

public class Overfiiting_Regularise extends Regularization
{

    @Override
    public np2D_Array regularise_weights(np2D_Array weights, double learning_rate, double lambda, int n)
    {
        double factor = (learning_rate * lambda) / n;
        double[][] res = new double[weights.get_row_size()][weights.get_col_size()];
        double[][] temp = weights.getArray();
        for (int i = 0; i < weights.get_row_size(); i++)
        {
            for (int j = 0; j < weights.get_col_size(); j++)
            {
                if (temp[i][j] < 0)
                {
                    res[i][j] = temp[i][j] + factor;
                }
                else
                {
                    res[i][j] = temp[i][j] - factor;
                }
            }
        }
        return new np2D_Array(res);
    }

}

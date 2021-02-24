package LIB.mllib.options.activation;

import LIB.mathlib.DS.np2D_Array;
import LIB.mathlib.DS.np1D_Array;
public class RectifiedLinearUnit extends ActivationFunction
{
    private double activate(double x) 
    {
        return Math.max(0.0,x);
    }

    @Override
    public np1D_Array activateVector(np1D_Array v)
    {
        double[] res = new double[v.getSize()];
        double[] temp = v.getArray();
        for (int i = 0; i < v.getSize(); i++)
        {
            res[i] = activate(temp[i]);
        }
        return new np1D_Array(res);
    }

    @Override
    public np2D_Array activateMatrix(np2D_Array M)
    {
        double[][] res = new double[M.get_row_size()][M.get_col_size()];
        double[][] temp_mat = M.getArray();
        for (int i = 0; i < M.get_row_size(); i++)
        {
            for (int j = 0; j < M.get_col_size(); j++)
            {
                res[i][j] = activate(temp_mat[i][j]);
            }
        }
        return new np2D_Array(res);
    }

    private double derivedActivate(double x)
    {
        if(x>0.0)
        {
            return 1.0;
        }
        else
        {
            return 0.0;
        }
    }

    @Override
    public np1D_Array DerivedActivation_on_Vector(np1D_Array v)
    {
        double[] res = new double[v.getSize()];
        double[] temp = v.getArray();
        for (int i = 0; i < v.getSize(); i++)
        {
            res[i] = derivedActivate(temp[i]);
        }
        return new np1D_Array(res);
    }

    @Override
    public np2D_Array DerivedActivation_on_Matrix(np2D_Array M)
    {
        double[][] res = new double[M.get_row_size()][M.get_col_size()];
        double[][] temp_mat = M.getArray();
        for (int i = 0; i < M.get_row_size(); i++)
        {
            for (int j = 0; j < M.get_col_size(); j++)
            {
                res[i][j] = derivedActivate(temp_mat[i][j]);
            }
        }
        return new np2D_Array(res);
    }
    
}

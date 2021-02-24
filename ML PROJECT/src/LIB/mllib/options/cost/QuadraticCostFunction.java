package LIB.mllib.options.cost;

import LIB.mathlib.DS.np2D_Array;
import LIB.mathlib.DS.np1D_Array;
import LIB.mllib.net.NN;
import LIB.mllib.options.activation.ActivationFunction;

/**
 * Represents the quadratic cost function. C = 1/(2n) sum(squared(length(y-a)))
 *
 */
public class QuadraticCostFunction extends CostFunction
{
    @Override
    public double totalCost(NN net, np1D_Array[] dataIn, np1D_Array[] dataOut)
    {
        double sum = 0;
        np1D_Array[] errorCols = new np2D_Array(dataOut).subtractMatrix(net.feedforward(new np2D_Array(dataIn))).getCols();
        for (int i = 0; i < dataIn.length; i++)
        {
            sum += Math.pow(errorCols[i].length(), 2);
        }
        return (1.0 / (2.0 * dataIn.length)) * sum;
    }
    
    @Override
    public np2D_Array Error(np2D_Array calcOut, np2D_Array dataOut, np2D_Array values, ActivationFunction activationFunction)
    {
        return calcOut.subtractMatrix(dataOut).inplaceMUL(activationFunction.DerivedActivation_on_Matrix(values));
    }
}

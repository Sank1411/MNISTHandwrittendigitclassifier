package LIB.mllib.options.cost;

import LIB.mllib.net.NN;
import LIB.mathlib.DS.np2D_Array;
import LIB.mathlib.DS.np1D_Array;
import LIB.mllib.options.activation.ActivationFunction;

public abstract class CostFunction
{

    public abstract double totalCost(NN net, np1D_Array[] dataIn, np1D_Array[] dataOut);

    public abstract np2D_Array Error(np2D_Array calcOut, np2D_Array dataOut, np2D_Array values, ActivationFunction activationFunction);
}

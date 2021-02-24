package LIB.mllib.net;

import LIB.mathlib.DS.np1D_Array;
import LIB.mathlib.DS.np2D_Array;
import LIB.mllib.options.activation.ActivationFunction;
import LIB.mllib.options.cost.CostFunction;
import LIB.mllib.options.initialization.Normal_Initialise;
import LIB.mllib.options.initialization.Initialise_weight_biase;

public class NN
{

    private final int size;
    private np1D_Array[] biase;
    private np2D_Array[] weight;
    private final np2D_Array[] backpropUtil;
    private final np2D_Array[] feedforwardUtil;
    private final ActivationFunction activationFunction;
    
    public NN(ActivationFunction Act,int... sizes)
    {
        this( Act, new Normal_Initialise(), sizes);
    }
    
    public NN(ActivationFunction Function, Initialise_weight_biase weightInitialization, int... sizes)
    {
        this(weightInitialization.random_init_weight(sizes), weightInitialization.random_init_biases(sizes), sizes.length, Function);
    }
        
    public NN(np2D_Array[] weight, np1D_Array[] biase, int size, ActivationFunction activationFunction)
    {
        this.size = size;
        this.biase = biase;
        this.weight = weight;
        this.backpropUtil = new np2D_Array[size - 1];
        this.feedforwardUtil = new np2D_Array[size];
        this.activationFunction = activationFunction;
    }

    public np2D_Array feedforward(np2D_Array in)
    {
        feedforwardUtil[0] = in;
        for (int i = 0; i < size - 1; i++)
        {
            backpropUtil[i] = weight[i].Matmul(feedforwardUtil[i]).addVector(biase[i]);
            feedforwardUtil[i + 1] = activationFunction.activateMatrix(backpropUtil[i]);
        }
        return feedforwardUtil[size - 1];
    }

    public void backpropagate(np2D_Array trainingIn, np2D_Array trainingOut, np2D_Array[] weightErrors, np1D_Array[] biasErrors, CostFunction costFunction)
    {
        np2D_Array error = costFunction.Error(feedforward(trainingIn), trainingOut, backpropUtil[size - 2], activationFunction);
        for (int i = size - 2; i >= 0; i--)
        {
            weightErrors[i] = error.Matmul(feedforwardUtil[i].transpose());
            biasErrors[i] = error.sumCols();
            if (i > 0)
            {
                error = weight[i].transpose().Matmul(error).inplaceMUL(activationFunction.DerivedActivation_on_Matrix(backpropUtil[i - 1]));
            }
        }
    }

    public int getSize()
    {
        return size;
    }

    public np1D_Array[] getBiases()
    {
        return biase;
    }

    public np2D_Array[] getWeights()
    {
        return weight;
    }

    public ActivationFunction getActivationFunction()
    {
        return activationFunction;
    }

    public void setWeights(np2D_Array[] weight)
    {
        this.weight = weight;
    }

    public void setBiases(np1D_Array[] biase)
    {
        this.biase = biase;
    }
}

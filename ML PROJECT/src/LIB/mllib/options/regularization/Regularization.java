package LIB.mllib.options.regularization;

import LIB.mathlib.DS.np2D_Array;

public abstract class Regularization
{
    public abstract np2D_Array regularise_weights(np2D_Array weights, double learningRate, double lambda, int n);
}

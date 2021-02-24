package LIB.mllib.options.activation;

import LIB.mathlib.DS.np1D_Array;
import LIB.mathlib.DS.np2D_Array;


public abstract class ActivationFunction
{

    public abstract np1D_Array activateVector(np1D_Array v);

    public abstract np2D_Array activateMatrix(np2D_Array M);

    public abstract np1D_Array DerivedActivation_on_Vector(np1D_Array v);

    public abstract np2D_Array DerivedActivation_on_Matrix(np2D_Array M);
}

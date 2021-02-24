package LIB.mllib.options.initialization;

import LIB.mathlib.DS.np2D_Array;
import LIB.mathlib.DS.np1D_Array;

public abstract class Initialise_weight_biase
{

    public abstract np1D_Array[] random_init_biases(int[] sizes);

    public abstract np2D_Array[] random_init_weight(int[] sizes);
}

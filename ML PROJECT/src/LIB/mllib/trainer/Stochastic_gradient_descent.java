package LIB.mllib.trainer;

import LIB.mathlib.DS.np2D_Array;
import LIB.mathlib.DS.np1D_Array;
import LIB.mllib.net.Util;
import LIB.mllib.net.NN;
import LIB.mllib.options.cost.CostFunction;
import LIB.mllib.options.regularization.Regularization;

public class Stochastic_gradient_descent
{

    private final NN net;
    private np1D_Array[] testIn;
    private np1D_Array[] testOut;
    private np1D_Array[] trainingIn;
    private np1D_Array[] trainingOut;
    private final CostFunction costFunction;
    private final Regularization regularization;

    public Stochastic_gradient_descent(NN net, CostFunction costFunction, Regularization regularization)
    {
        this.net = net;
        this.costFunction = costFunction;
        this.regularization = regularization;
    }

    public void train(int epochs, double learning_rate, double lambda, int batchSize, boolean evaluate)
    {
        if (trainingIn == null || trainingOut == null || testIn == null || testOut == null)
        {
            System.out.println("\n--- No data found. Give proper training and test data. ---");
        }

        System.out.printf("%n---Training NN---%n"
                + "Training data size: %d, Test data size: %d, Batch size: %d%n"
                + "Epochs: %d, Learning rate: %.2f, Lambda: %.2f%n%n",
                trainingIn.length, testIn.length, batchSize, epochs, learning_rate, lambda);
        for (int i = 0; i < epochs; i++)
        {
            trainEpoch(i, learning_rate, lambda, batchSize, evaluate);
        }
        System.out.printf("Result:%n"
                + "Training accuracy: %.2f%n"
                + "            error: %f%n"
                + "   Test  accuracy: %.2f%n"
                + "            error: %f%n",
                test(trainingIn, trainingOut),
                costFunction.totalCost(net, trainingIn, trainingOut),
                test(testIn, testOut),
                costFunction.totalCost(net, testIn, testOut));
    }

    private void trainEpoch(int epoch, double learningRate, double lambda, int batchSize, boolean evaluate)
    {
        Util.randomShuffle(trainingIn, trainingOut);
        np2D_Array[] dataIn = Util.batchFormation(trainingIn, batchSize);
        np2D_Array[] dataOut = Util.batchFormation(trainingOut, batchSize);
        for (int j = 0; j < trainingIn.length / (double) batchSize; j++)
        {
            trainBatch(dataIn[j], dataOut[j], learningRate, lambda, trainingIn.length);
        }
        System.out.printf("Epoch: %d%n",
                epoch + 1);
        if (evaluate) {
            System.out.printf("Training accuracy: %.2f%n"
                    + "            error: %f%n"
                    + "   Test  accuracy: %.2f%n"
                    + "            error: %f%n",
                    test(trainingIn, trainingOut),
                    costFunction.totalCost(net, trainingIn, trainingOut),
                    test(testIn, testOut),
                    costFunction.totalCost(net, testIn, testOut));
        }
    }

    private void trainBatch(np2D_Array trainingIn, np2D_Array trainingOut, double learningRate, double lambda, int n)
    {
        int size = net.getSize();
        np2D_Array[] weights = net.getWeights();
        np1D_Array[] biases = net.getBiases();
        np2D_Array[] weightErrors = new np2D_Array[size - 1];
        np1D_Array[] biasErrors = new np1D_Array[size - 1];
        for (int i = 0; i < size - 1; i++)
        {
            weightErrors[i] = new np2D_Array(weights[i].get_row_size(), weights[i].get_col_size());
            biasErrors[i] = new np1D_Array(biases[i].getSize());
        }
        net.backpropagate(trainingIn, trainingOut, weightErrors, biasErrors, costFunction);
        double stochasticFactor = learningRate / trainingIn.get_col_size();
        for (int i = 0; i < size - 1; i++)
        {
            if (regularization == null)
            {
                weights[i] = weights[i].subtractMatrix(weightErrors[i].mulScalar(stochasticFactor));
            }
            else
            {
                weights[i] = regularization.regularise_weights(weights[i], learningRate, lambda, n).subtractMatrix(weightErrors[i].mulScalar(stochasticFactor));
            }
            biases[i] = biases[i].subtract(biasErrors[i].multiplyScalar(stochasticFactor));
        }
    }

    private double test(np1D_Array[] testIn, np1D_Array[] testOut)
    {
        int correct = 0;
        double[][] result = net.feedforward(new np2D_Array(testIn)).getArray();
        for (int j = 0; j < testIn.length; j++)
        {
            int max = 0;
            for (int i = 1; i < result.length; i++)
            {
                if (result[i][j] > result[max][j])
                {
                    max = i;
                }
            }
            if (testOut[j].getArray()[max] == 1.0)
            {
                correct++;
            }
        }
        return (double) correct / testIn.length * 100.0;
    }

    public void setTrainingData(np1D_Array[] trainingIn, np1D_Array[] trainingOut)
    {
        this.trainingIn = trainingIn;
        this.trainingOut = trainingOut;
    }

    public void setTestData(np1D_Array[] testIn, np1D_Array[] testOut)
    {
        this.testIn = testIn;
        this.testOut = testOut;
    }
}

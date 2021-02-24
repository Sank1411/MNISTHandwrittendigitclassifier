package LIB.mathlib.DS;

public class np2D_Array
{

    private final int n, m;
    private final double[][] A;

    public np2D_Array(double[][] A)
    {
        this.A = A;
        this.n = A.length;
        this.m = A[0].length;
    }

    public np2D_Array(int n, int m)
    {
        this(new double[n][m]);
    }

    public np2D_Array(np1D_Array... cols)
    {
        this(cols[0].getSize(), cols.length);
        for (int j = 0; j < m; j++)
        {
            double[] b = cols[j].getArray();
            for (int i = 0; i < n; i++)
            {
                A[i][j] = b[i];
            }
        }
    }

    public np2D_Array transpose()
    {
        np2D_Array C = new np2D_Array(m, n);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                C.A[j][i] = A[i][j];
            }
        }
        return C;
    }

    public np2D_Array addVector(np1D_Array b)
    {
        np2D_Array C = new np2D_Array(n, m);
        double[] temp = b.getArray();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                C.A[i][j] = A[i][j] + temp[i];
            }
        }
        return C;
    }

    public np2D_Array addMatrix(np2D_Array B)
    {
        np2D_Array C = new np2D_Array(n, m);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                C.A[i][j] = A[i][j] + B.A[i][j];
            }
        }
        return C;
    }

    public np2D_Array subtractMatrix(np2D_Array B)
    {
        np2D_Array C = new np2D_Array(n, m);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                C.A[i][j] = A[i][j] - B.A[i][j];
            }
        }
        return C;
    }

    public np2D_Array Matmul(np2D_Array B)
    {
        np2D_Array C = new np2D_Array(n, B.m);
        for (int i = 0; i < n; i++)
        {
            for (int k = 0; k < B.n; k++)
            {
                for (int j = 0; j < B.m; j++)
                {
                    C.A[i][j] += A[i][k] * B.A[k][j];
                }
            }
        }
        return C;
    }

    public np2D_Array inplaceMUL(np2D_Array B)
    {
        np2D_Array C = new np2D_Array(n, m);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                C.A[i][j] = A[i][j] * B.A[i][j];
            }
        }
        return C;
    }

    public np1D_Array mulVec(np1D_Array b)
    {
        double[] c = new double[n];
        double[] temp = b.getArray();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                c[i] += A[i][j] * temp[j];
            }
        }
        return new np1D_Array(c);
    }

    public np2D_Array mulScalar(double s)
    {
        np2D_Array C = new np2D_Array(n, m);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                C.A[i][j] = s * A[i][j];
            }
        }
        return C;
    }

    public np1D_Array sumCols()
    {
        double[] c = new double[n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                c[i] += A[i][j];
            }
        }
        return new np1D_Array(c);
    }

    public np1D_Array[] getCols()
    {
        np1D_Array[] result = new np1D_Array[m];
        for (int j = 0; j < m; j++)
        {
            double[] v = new double[n];
            for (int i = 0; i < n; i++)
            {
                v[i] = A[i][j];
            }
            result[j] = new np1D_Array(v);
        }
        return result;
    }

    public int get_row_size()
    {
        return n;
    }

    public int get_col_size()
    {
        return m;
    }

    public double[][] getArray()
    {
        return A;
    }

    @Override
    public boolean equals(Object x)
    {
        if (!(x instanceof np2D_Array))
        {
            return false;
        }
        np2D_Array B = ((np2D_Array) x);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (A[i][j] != B.A[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }
}

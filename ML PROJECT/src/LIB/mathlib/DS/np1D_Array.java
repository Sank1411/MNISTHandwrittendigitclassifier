package LIB.mathlib.DS;

public class np1D_Array
{

    private final int size;
    private final double[] a;
    
    public np1D_Array(int size)
    {
        this.a = new double[size];
        this.size = size;
    }

    public np1D_Array(double... v)
    {
        this.a = v;
        this.size = v.length;
    }

    public np1D_Array addition(np1D_Array b)
    {
        np1D_Array c = new np1D_Array(size);
        for (int i = 0; i < size; i++)
        {
            c.a[i] = a[i] + b.a[i];
        }
        return c;
    }

    public np1D_Array subtract(np1D_Array b)
    {
        np1D_Array c = new np1D_Array(size);
        for (int i = 0; i < size; i++)
        {
            c.a[i] = a[i] - b.a[i];
        }
        return c;
    }

    public np1D_Array inplaceMUL(np1D_Array b)
    {
        np1D_Array c = new np1D_Array(size);
        for (int i = 0; i < size; i++)
        {
            c.a[i] = a[i] * b.a[i];
        }
        return c;
    }

    public np1D_Array multiplyScalar(double s)
    {
        np1D_Array c = new np1D_Array(size);
        for (int i = 0; i < size; i++)
        {
            c.a[i] = a[i] * s;
        }
        return c;
    }

    public double dotProd(np1D_Array b)
    {
        double s = 0;
        for (int i = 0; i < size; i++)
        {
            s += a[i] * b.a[i];
        }
        return s;
    }

    public double length()
    {
        return Math.sqrt(dotProd(this));
    }

    public int getSize()
    {
        return size;
    }

    public double[] getArray()
    {
        return a;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof np1D_Array))
        {
            return false;
        }
        np1D_Array b = (np1D_Array) o;
        for (int i = 0; i < size; i++)
        {
            if (a[i] != b.a[i])
            {
                return false;
            }
        }
        return true;
    }
}

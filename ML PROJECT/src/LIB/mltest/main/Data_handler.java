package LIB.mltest.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import LIB.mathlib.DS.np1D_Array;

public class Data_handler
{

    public static np1D_Array[] importData(String fileName)
    {
        try
        {
            System.out.println("\n   Importing data   \nfile: " + fileName);
            GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(fileName));
            byte[] magic = new byte[4];
            gzip.read(magic);
            int magicNum = bytesToInt(magic);
            System.out.println("magic num: " + magicNum);
            switch (magicNum)
            {
                case 2049:
                    return import_Labels(gzip);
                case 2051:
                    return import_Images(gzip);
                default:
                    System.err.println("This is not a valid file. magic num: " + magicNum);
                    break;
            }
        }
        catch (IOException ex)
        {
            System.err.println("Error while reading file:\n" + ex);
        }
        return new np1D_Array[0];
    }

    private static int bytesToInt(byte[] bytes)
    {
        return ((bytes[0] & 0xFF) << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF));
    }

    private static np1D_Array[] import_Labels(GZIPInputStream gzip) throws IOException
    {
        byte[] itemCountBytes = new byte[4];
        gzip.read(itemCountBytes);
        int itemCount = bytesToInt(itemCountBytes);
        System.out.println("item count: " + itemCount);
        np1D_Array[] data = new np1D_Array[itemCount];
        for (int i = 0; i < itemCount; i++)
        {
            double[] vec = new double[10];
            vec[gzip.read()] = 1.0;
            data[i] = new np1D_Array(vec);
        }
        System.out.println("finished");
        return data;
    }

    private static np1D_Array[] import_Images(GZIPInputStream gzip) throws IOException
    {
        byte[] infoBytes = new byte[4];
        gzip.read(infoBytes);
        int itemCount = bytesToInt(infoBytes);
        gzip.read(infoBytes);
        int rowCount = bytesToInt(infoBytes);
        gzip.read(infoBytes);
        int colCount = bytesToInt(infoBytes);
        System.out.println("item count: " + itemCount);
        System.out.println("row count: " + rowCount);
        System.out.println("col count: " + colCount);
        np1D_Array[] data = new np1D_Array[itemCount];
        int pixelCount = rowCount * colCount;
        for (int i = 0; i < itemCount; i++)
        {
            double[] vec = new double[pixelCount];
            for (int j = 0; j < pixelCount; j++)
            {
                vec[j] = gzip.read() / 255.0;
            }
            data[i] = new np1D_Array(vec);
        }
        System.out.println("finished");
        return data;
    }
}

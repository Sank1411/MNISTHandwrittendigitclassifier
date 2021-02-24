package LIB.mltest.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import LIB.mllib.net.NN;
import LIB.mllib.options.activation.ActivationFunction;
import LIB.mllib.options.activation.RectifiedLinearUnit;
import LIB.mllib.options.activation.SigmoidFunction;
import LIB.mllib.options.activation.Tanh;
import static java.lang.Integer.parseInt;
import javax.swing.JOptionPane;

public class Frame extends JFrame
{
    
    public static final int DRAW_SIZE = 700;

    private BufferedImage image;
    private NN net;
    private final Drawing_handler drawPanel;
    private final Button_handler buttonPanel;
    private final Mouse_handler mouse;
    private boolean predicted = false;

    public Frame()
    {
        super("Neural Network Test");
        String input =  JOptionPane.showInputDialog(null, "Insert Hidden layers seperated by spaces");
        String[] items = input.split(" ");
        int size = items.length;
        int[] arr = new int[size+2];
        arr[0] = 784;
        arr[size+1] = 10;
        for(int i=1;i<=size;i++)
        {
            try 
            {
                arr[i] = parseInt(items[i-1]);
            }
            catch (NumberFormatException nfe)
            {
            };
        }
        int active_type =  parseInt(JOptionPane.showInputDialog(null, "Insert 1 for sigmoid\nInsert 2 for Relu\nInsert 3 for Tanh"));
        ActivationFunction active;
        switch (active_type)
        {
            case 1:
                active = new SigmoidFunction();
                break;
            case 2:
                active = new RectifiedLinearUnit();
                break;
            default:
                active = new Tanh();
                break;
        }
        net = new NN(active,arr);
        image = new BufferedImage(DRAW_SIZE, DRAW_SIZE, BufferedImage.TYPE_INT_ARGB_PRE);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        mouse = new Mouse_handler(this);

        buttonPanel = new Button_handler(this);
        drawPanel = new Drawing_handler(this);

        add(drawPanel);
        add(buttonPanel);

        setSize(DRAW_SIZE + 300, DRAW_SIZE);
        setResizable(false);
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
        repaint();
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public Graphics2D getGraphics2D()
    {
        return (Graphics2D) image.getGraphics();
    }

    public Button_handler getButtonPanel()
    {
        return buttonPanel;
    }

    public void setNet(NN nn)
    {
        this.net = nn;
    }

    public NN getNet()
    {
        return net;
    }

    public Mouse_handler getMouse()
    {
        return mouse;
    }

    public boolean isPredicted()
    {
        return predicted;
    }

    public void setPredicted(boolean predicted)
    {
        this.predicted = predicted;
        buttonPanel.getPredictButton().setEnabled(!predicted);
    }

    public static void main(String[] args)
    {
        new Frame();
    }
}

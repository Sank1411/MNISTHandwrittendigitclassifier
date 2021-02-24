package LIB.mltest.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse_handler extends MouseAdapter
{

    private static final int BRUSH_RADIUS = 30;
    private final Frame frame;
    private Thread autoPredict;

    public Mouse_handler(Frame frame)
    {
        this.frame = frame;
    }

    private void AutoPredict()
    {
        if (autoPredict != null && !autoPredict.isInterrupted())
        {
            autoPredict.interrupt();
        }
        autoPredict = new Thread(() -> {
            try
            {
                Thread.sleep(2000);
                if (!frame.isPredicted())
                {
                    frame.getButtonPanel().predictButtonActionPerformed();
                }
            }
            catch (InterruptedException ex)
            {
            }
        });
        autoPredict.start();
    }

    private void draw(MouseEvent m)
    {
        if (frame.isPredicted())
        {
            frame.getButtonPanel().resetButtonActionPerformed();
        }
        Graphics2D g = frame.getGraphics2D();
        g.setColor(Color.BLACK);
        g.fillOval(m.getX() - BRUSH_RADIUS, m.getY() - BRUSH_RADIUS, 2 * BRUSH_RADIUS, 2 * BRUSH_RADIUS);
        frame.repaint();
        AutoPredict();
    }

    @Override
    public void mouseClicked(MouseEvent m)
    {
        draw(m);
    }

    @Override
    public void mouseDragged(MouseEvent m)
    {
        draw(m);
    }
}

package LIB.mltest.main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Drawing_handler extends JPanel
{

    private final Frame frame;

    public Drawing_handler(Frame frame)
    {
        super();
        this.frame = frame;
        setPreferredSize(new Dimension(Frame.DRAW_SIZE, Frame.DRAW_SIZE));

        addMouseListener(frame.getMouse());
        addMouseMotionListener(frame.getMouse());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(frame.getImage(), 0, 0, Frame.DRAW_SIZE, Frame.DRAW_SIZE, this);
    }
}

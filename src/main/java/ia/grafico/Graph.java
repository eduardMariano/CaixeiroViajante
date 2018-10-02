package ia.grafico;

import java.awt.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.util.List;

public class Graph extends JComponent {

    private JPanel panel = new JPanel();
    private int X1, Y1, X2, Y2;
    private Graphics2D g;
    private Image img, background, undoTemp, redoTemp;
    private Rectangle shape;

    protected void paintComponent(Graphics g1) {
        if (img == null) {
            img = createImage(getSize().width, getSize().height);
            g = (Graphics2D) img.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            clear();

        }
        g1.drawImage(img, 0, 0, null);

        if (shape != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.draw(shape);
        }
    }

    public void clear() {
        if (background != null) {
            setImage(copyImage(background));
        } else {
            g.setPaint(Color.white);
            g.fillRect(0, 0, getSize().width, getSize().height);
            g.setPaint(Color.black);
        }
        repaint();
    }

    private void setImage(Image img) {
        g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setPaint(Color.black);
        this.img = img;
        repaint();
    }

    private BufferedImage copyImage(Image img) {
        BufferedImage copyOfImage = new BufferedImage(getSize().width,
                getSize().height, BufferedImage.TYPE_INT_RGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        return copyOfImage;
    }

//    private void paintComponent(Graphics g, Point p1, Point p2){
//        int x1 = (int) p1.getX();
//        int y1 = (int) p1.getY();
//        int x2 = (int) p2.getX();
//        int y2 = (int) p2.getY();
//        g.drawLine( x1, y1, x2, y2 );
//        panel.paintComponents(g);
//
//    }

    public void plot(List<Point> pontos) {
//        for (int i = 0; i < pontos.size(); i++){
//            if (i != (pontos.size() - 1)) {
//                paintComponent(pontos.get(i), pontos.get(i+1));
//            } else {
//                paintComponentpontos.get(i), pontos.get(0));
//            }
//
//        }
        printGraph();
    }

    private void printGraph(){
        JFrame frame = new JFrame("Gráfico");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( 500, 500 );
        frame.setVisible( true );
        frame.add( this.panel );
    }

}
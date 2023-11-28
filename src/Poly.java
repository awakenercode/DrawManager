
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

// Σχήματα που σχεδιάζουν πολύγωνο (τετράγωνο, ορθογώνιο, τρίγωνο, αστέρι)
public abstract class Poly extends Shape{
    int xPoly[];
    int yPoly[];
    Polygon poly;
    public Poly(Point upleft, Point upright, Point bottomright, Point bottomleft, Color fill, Color outline) {
        super(upleft, upright, bottomright, bottomleft, fill, outline);
    }

    @Override
    public void design(Graphics g) {
        g.setColor(getFill());
        g.fillPolygon(poly);
        g.setColor(getOutline());
        g.fillPolygon(poly);
    }

    
    
}

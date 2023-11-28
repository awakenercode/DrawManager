
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

// Κύκλοι, ελλείψεις. 
public class Rounded extends Shape{

    public Rounded(Point upleft, Point upright, Point bottomright, Point bottomleft, Color fill) {
        super(upleft, upright, bottomright, bottomleft, fill, fill);
    }

    @Override
    public Shape copy(Point p, Point r) {
        Point upleft = new Point(),upright = new Point(),bottomright = new Point(),bottomleft = new Point();
        upleft.x = this.getUpleft().x- (p.x - r.x);
        upleft.y = this.getUpleft().y- (p.y - r.y);
        upright.x = this.getUpright().x- (p.x - r.x);
        upright.y = this.getUpright().y- (p.y - r.y);
        bottomright.x = this.getBottomright().x- (p.x - r.x);
        bottomright.y = this.getBottomright().y- (p.y - r.y);
        bottomleft.x = this.getBottomleft().x- (p.x - r.x);
        bottomleft.y = this.getBottomleft().y- (p.y - r.y);
        Rounded rounded = new Rounded(upleft,upright,bottomright,bottomleft,getFill());
        return rounded;
    }

    @Override
    public void design(Graphics g) {
        g.setColor(getFill());
        g.fillOval(getUpleft().x, getUpleft().y, 
                (int)getUpleft().distance(getUpright()), (int)getUpleft().distance(getBottomleft()));
    }
    
}

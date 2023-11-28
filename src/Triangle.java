
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class Triangle extends Poly{
    public Triangle(Point upleft, Point upright, Point bottomright, Point bottomleft, Color fill, Color outline) {
        super(upleft, upright, bottomright, bottomleft, fill, outline);
        setPoly();
    }
    private void setPoly(){
        xPoly = new int[3]; 
        xPoly[0] = getUpleft().x;
        xPoly[1] = getUpright().x;
        xPoly[2] = getBottomleft().x + (int)(getBottomright().distance(getBottomleft())/2);
        yPoly = new int[3]; 
        yPoly[0] = getUpleft().y;
        yPoly[1] = getUpright().y;
        yPoly[2] = getBottomright().y;
        poly = new Polygon(xPoly, yPoly, xPoly.length);
    }
    @Override
    public void move(Point p, Point r) {
        super.move(p, r); //To change body of generated methods, choose Tools | Templates.
        setPoly();
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
        Triangle triangle = new Triangle(upleft,upright,bottomright,bottomleft,getFill(),getOutline());
        return triangle;
    }
    
}

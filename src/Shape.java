
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
abstract class Shape implements Serializable{
    // Τα 4 σημεία, πάνω αριστερά, πάνω δεξιά, κάτω δεξιά, κάτω αριστερά
    // που περιλεμβάνουν το σχήμα
    private Point upleft,upright,bottomright,bottomleft;
    // Χρώμα γεμίσματος και χρώμα περιγράμματος (δεν έχουν όλα τα σχήματα και τα 2,
    // αυτά θα έχουν ίσα fill και Outline
    private Color fill,outline;
    
    public Shape(Point upleft, Point upright, Point bottomright, Point bottomleft, Color fill, Color outline) {
        this.upleft = upleft;
        this.upright = upright;
        this.bottomright = bottomright;
        this.bottomleft = bottomleft;
        this.fill = fill;
        this.outline = outline;
    }

    public Point getUpleft() {
        return upleft;
    }

    public Point getUpright() {
        return upright;
    }

    public Point getBottomright() {
        return bottomright;
    }

    public Point getBottomleft() {
        return bottomleft;
    }
    // Μετακίνηση από p σε r
    public void move(Point p,Point r){
        upleft.x -=p.x - r.x;
        upleft.y -=p.y - r.y;
        upright.x -=p.x - r.x;
        upright.y -=p.y - r.y;
        bottomright.x -=p.x - r.x;
        bottomright.y -=p.y - r.y;
        bottomleft.x -=p.x - r.x;
        bottomleft.y -=p.y - r.y;
        
    }
    // Αντιγραφή από p σε r
    abstract public Shape copy(Point p,Point r);
    // Αν είναι το σημείο ανάμεσα στα upleft .... 
    public boolean inShape(Point p){
        if (p.x<upleft.x) // αριστερότερα
            return false;
        if (p.x>upright.x) // δεξιότερα
            return false;
        if (p.y<upleft.y) // πιο πάνω
            return false;
        if (p.y>bottomleft.y) // πιο κάτω
            return false;
        return true; // αλλιώς μέσα
    }
    public Color getFill() {
        return fill;
    }

    public Color getOutline() {
        return outline;
    }
    
    abstract public void design(Graphics g);
}

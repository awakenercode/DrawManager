/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;


/**
 *
 * @author crist
 */
// Κλάση βοηθητική, περιέχει μία λίστα με όλα τα σχήματα που έχει σχεδιάσει ο χρήστης

public class DrawManager implements Serializable{
    // λίστα σχημάτων
    LinkedList<Shape> shapes;
    // Αν καλεστεί ο κατασκευαστής αρχικοποιεί την λίστα σχημάτων χωρίς αρχικά σχήματα
    public DrawManager() {
        
        shapes = new LinkedList<Shape>();
    }
    // Καλεί για σχεδίαση την design κάθε σχήματος ένα ένα
    public void design(Graphics g){
        for(Shape s:shapes){
            s.design(g);
        }
    }
    // Βοηθητική στατική μέθοδος. Επιστρέφει το πάνω αριστερά σημείο
    // από δύο σημεία που ορίζουν ένα επίπεδο
    static Point getUpleft(Point p,Point r) {
        Point upleft = new Point();
        upleft.x = (p.x<r.x?p.x:r.x);
        upleft.y = (p.y<r.y?p.y:r.y);
        return upleft;
    }

    // Βοηθητική στατική μέθοδος. Επιστρέφει το πάνω δεξιά σημείο
    // από δύο σημεία που ορίζουν ένα επίπεδο
    static Point getUpright(Point p,Point r) {
        Point upright = new Point();
        upright.x = (p.x>r.x?p.x:r.x);
        upright.y = (p.y<r.y?p.y:r.y);
        return upright;
    }

    // Βοηθητική στατική μέθοδος. Επιστρέφει το κάτω δεξιά σημείο
    // από δύο σημεία που ορίζουν ένα επίπεδο
    static Point getBottomright(Point p,Point r) {
        Point bottomright = new Point();
        bottomright.x = (p.x>r.x?p.x:r.x);
        bottomright.y = (p.y>r.y?p.y:r.y);
        return bottomright;
    }

    // Βοηθητική στατική μέθοδος. Επιστρέφει το κάτω αριστερά σημείο
    // από δύο σημεία που ορίζουν ένα επίπεδο
    static Point getBottomleft(Point p,Point r) {
        Point bottomleft = new Point();
        bottomleft.x = (p.x<r.x?p.x:r.x);
        bottomleft.y = (p.y>r.y?p.y:r.y);
        return bottomleft;
    }
    // Ελέγχει αν το σημείο p βρίσκεται σε κάποιο σχήμα
    Shape inShape(Point p){
        for(Shape s:shapes){
            if (s.inShape(p))
                return s;
        }
        return null;
    }
}


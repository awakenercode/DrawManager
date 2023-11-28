
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author crist
 */
// Η App υλοποιεί το παραθυρικό περιβάλλον της εφαρμογής
public class App extends JFrame{
    public App(DrawManager drawManager) throws HeadlessException {
        this.setTitle("Shape design App");
        // Λίστα αλφαρημθητικών για επιλογή σχήματος
        String []s = {"Rectangles","Squares","Ellipses","Circles","Triangles"};
         // Επιλογή σχημάτων
         JComboBox<String> shapes = new JComboBox(s);
         // προσθήκη του ComboBox στο πάνω μέρος του παραθύρου
        add(shapes,BorderLayout.NORTH);
        // Λίστα χρωμάτων για επιλογή χρώματος
        Color []c = {Color.black, Color.blue, Color.cyan, Color.darkGray,
                    Color.gray, Color.green};
        JComboBox<Color> colors = new JComboBox(c);
        // προσθήκη του colors στο κάτω μέρος του παραθύρου
        add(colors,BorderLayout.SOUTH);
        // Αποθηκευμένα σχήματα σε δομή λίστας
        Draw draw = new Draw(drawManager,shapes,colors);
        // // προσθήκη του draw panel στο κέντρο του παραθύρου
        add(draw,BorderLayout.CENTER);
        // κάνει το παράθυρο ορατό
        setVisible(true);
        // το μέγεθος του παραθύρου
        setSize(400,400);
        // με το κλείσιμο του παραθύρου κλείνει και η εφαρμογή
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Αν το παράθυρο κλείσει η DrawManager μαζί με τη λίστα τηςε αποθηκεύεται 
        // στο αρχείο draw.dat.
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("draw.dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(drawManager);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fos.close();
                    } catch (IOException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        });
    }
    
  
}
// Το πάνελ στο οποίο σχεδιάζονται τα σχήματα
class Draw extends JPanel{
    JComboBox<Color> colors;
    JComboBox<String> shapes;
    DrawManager drawManager;
    // Στον κατασκευαστή του draw panel, Περνάμε σαν παράμετρο τα 2
    // ComboBox ώστε να μπορούμε να δούμε τί σχήμα και τί χρώμα είναι
    // επιλεγμένο ώστε να δημιουργήσουμε το σχήμα. Για να καλέσουμε 
    public Draw(DrawManager drawManager,JComboBox shapes, JComboBox<Color> colors) {
        this.drawManager = drawManager;
        this.shapes = shapes;
        this.colors = colors;
        // ακούει για ενέργειες ποντικιού
        addMouseListener(new MouseListener(){
            Point p,r;
            boolean move,copy;
            Shape s;
            @Override
            public void mouseClicked(MouseEvent e) {
                
                // double click
                if (e.getClickCount() == 2){
                    s = drawManager.inShape(p);
                    if (s!=null){
                        drawManager.shapes.remove(s);
                    }
                    
                }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
                move = false;
                copy = false;
                s = null;
                // το σημείο που πάτησε ο χρήστης
                p = e.getPoint();
                // επιστρέφεςι Null αν δεν πάτησε σε σχήμα
                s = drawManager.inShape(p);
                // αν πάτησε σε σχήμα
                if (s!=null){
                    // αν πάτησε με right click το σχήμα αντιγράφεται
                    if (e.getButton()==MouseEvent.BUTTON3){
                        copy = true;
                    }
                    // με αριστερό click μετακινείται
                    else{
                        move = true;
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                // το σημείο στο οποίο αφέθηκε το ποντικί
                r = e.getPoint();
                // αν βρισκόμαστε σε λειτουργία μετακίνησης κάποιου shape
                // καλούμε τη Move του shape
                if (move){
                    s.move(p, r);
                }
                // αντίστοιχα με την copy
                else if (copy){
                    drawManager.shapes.add(s.copy(p, r));
                    
                }
                // Δεν έχει επιλεχθεί κάποιο σχήμα, δημιουργία σχήματος.
                else{
                    Point upleft,upright,bottomright,bottomleft;
                    upleft = DrawManager.getUpleft(p, r);
                    upright = DrawManager.getUpright(p, r);
                    bottomright = DrawManager.getBottomright(p, r);
                    bottomleft = DrawManager.getBottomleft(p, r);
                    // Στα σχήματα τετράγωνο και κύκλος θέλουμε οι
                    // αποστάσεις στον άξονα x και y να είναι ίσες
                    if (shapes.getSelectedItem().toString().equals("Squares")
                            || shapes.getSelectedItem().toString().equals("Circles")){
                        int distancex = (int)upleft.distance(upright);
                        int distancey = (int)upleft.distance(bottomleft);
                        if (distancex < distancey){
                            bottomleft.y = bottomright.y = upleft.y + distancex;  
                        }
                        else{
                            upright.x = bottomright.x = upleft.x + distancey;
                        }
                    }
                    if (shapes.getSelectedItem().toString().equals("Rectangles") ||
                            shapes.getSelectedItem().toString().equals("Squares")){
                            
                        drawManager.shapes.add(
                                new Rectangle(upleft,upright,bottomright,bottomleft,
                                      (Color)colors.getSelectedItem(), 
                                       (Color)colors.getSelectedItem()
                                ));
                    }
                    if (shapes.getSelectedItem().toString().equals("Triangles")){
                        drawManager.shapes.add(
                                new Triangle(upleft,upright,bottomright,bottomleft,
                                      (Color)colors.getSelectedItem(), 
                                       (Color)colors.getSelectedItem()
                                ));
                    }
                    if (shapes.getSelectedItem().toString().equals("Ellipses") ||
                            shapes.getSelectedItem().toString().equals("Circles")){
                            
                        drawManager.shapes.add(
                                new Rounded(upleft,upright,bottomright,bottomleft,
                                      (Color)colors.getSelectedItem()
                                ));
                    }
                }
                
                
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        
        
        });
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawManager.design(g);
    }
}

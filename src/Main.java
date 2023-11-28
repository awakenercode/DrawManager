
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//ΣΤΥΛΙΑΝΟΠΟΥΛΟΣ ΝΙΚΟΛΑΟΣ 3212017182
//ΤΡΙΑΝΤΑΦΥΛΛΟΥ ΧΡΗΣΤΟΣ 3212017194 
/**
 *
 * @author crist
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DrawManager drawManager;
        // Με το που εκκιενί η εφαρμογή προσπαθεί να ανοίξει το αρχέιο με όνομα 
        // draw.dat
        FileInputStream fis = null;
        try {
            // ανοίγει το αρχείο και αν δεν το βρεί πετά exception
            fis = new FileInputStream("draw.dat");
            
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Το αρχείο βρέθηκε, διαβάζει το αντικείμενο drawManager από το αρχείο
            // με ότι σχήμα είχε αυτό μέσα.
            drawManager = (DrawManager)ois.readObject();
            fis.close();
            // δεν βρέθηκε αρχείο
        } catch (FileNotFoundException ex) {
            // αν δεν υπάρχει το αρχείο δημιουργεί το drawManager άδειο, χωρίς στοιχεία
            // στη λίστα
            drawManager = new DrawManager();
        } catch (IOException | ClassNotFoundException ex) {
            drawManager = new DrawManager();
        }
        // Δημιουργεί στιγμιότυπο του παραθύρου περνώντας ως παράμετρο
        // την κλάση DrawManager που διαχερι΄ζεται και περιέχει την λίστα σχημάτων
       App app = new App(drawManager);
    }
    
}

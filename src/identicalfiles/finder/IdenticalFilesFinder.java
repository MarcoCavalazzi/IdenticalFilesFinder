/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identicalfiles.finder;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mark
 */
public class IdenticalFilesFinder extends Frame {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Indentical Files Finder started");
        SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new IdenticalFilesFinder();  // Let the constructor do the job
         }
      });
    }
    
    public IdenticalFilesFinder(){
        // GUI instructions
        JFrame guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Duplicates Finder");
        guiFrame.setSize(600,500);
        guiFrame.setLocationRelativeTo(null); //This will center the JFrame in the middle of the screen
        
        //The first JPanel contains a JLabel and JCombobox
        TextArea tarea = new TextArea(2000, 2); // X rows, Y columns
        tarea.setEditable(false);
        tarea.append("test, te");
        
        JButton vegFruitBut = new JButton( "Remove unselected files");
        guiFrame.add(tarea);
        guiFrame.add(vegFruitBut,BorderLayout.SOUTH);
        
        
        final File folder = new File("C:\\Users\\Mark\\Documents");
        final MultiMap<Integer, String> filesList = new MultiMap<>();
        
        listFilesForFolder(folder, filesList);
        printResults(filesList);
        // Let's not freeze the PC over the GUI refreshing.
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(IdenticalFilesFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void listFilesForFolder(File folder, MultiMap filesList){
        // Reading the input
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                
                listFilesForFolder(fileEntry, filesList);
            
            } else {
                filesList.add(fileEntry.length(), fileEntry.getName());
            }
        }
    }
    
    private void printResults(MultiMap filesList){
        boolean noFilesFoundFlag = true;
        Set keys = filesList.keySet();
        // Printing the results
        for (Object i : keys) 
        {
            List files = filesList.get(i);
            if(files.size() > 1){ // If there are possible duplicates...
                System.out.println("i = "+i);
                System.out.println(filesList.get(i));
                System.out.println();
                noFilesFoundFlag = false;
            }
        }
        
        if(noFilesFoundFlag){
            System.out.println("No identical files found.");
        }
    }
    
}

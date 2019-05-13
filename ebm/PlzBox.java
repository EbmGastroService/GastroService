// created on 21.09.2009 at 13:51
// by Mourad El bakry
//Postleitzahl ComboBox
package ebm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.options.ausTeilen;

public class PlzBox extends JPanel {

    JComboBox <String>jcb;
    JTextField plzjtf, ort;
    String[] plzString;
    String Select;
    int Wahl;

    public PlzBox() {
        plzString = open();
        Select = plzString[0];
        String[] SelectStr = ausTeilen.komma(Select);
        plzjtf = new JTextField(SelectStr[0]);
        ort = new JTextField(SelectStr[1]);
        jcb = new JComboBox<String>(plzString);
        jcb.setEditable(true);
        jcb.addActionListener(new PlzAction());
        jcb.addItemListener(new MyItemListener());
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));
        jcb.setAlignmentX(Component.LEFT_ALIGNMENT);
        plzjtf.setAlignmentX(Component.LEFT_ALIGNMENT);
        ort.setAlignmentX(Component.LEFT_ALIGNMENT);
        boxPanel.add(jcb);
        boxPanel.add(plzjtf);
        boxPanel.add(ort);
        setLayout(new BorderLayout());
        add(boxPanel);
    }

    private String[] open() {
        return new com.search.sucheDate("gastro/date/plz.dat").myDaten();
    }

    void save(String file, String date, boolean wie) {
        com.units.save.file(file, date, wie);
    }
    void save(String file, String[] date, boolean wie) {
        com.units.save.filefelde(file, date, wie);
    }


    public String Eingabe() {
        return plzjtf.getText() + "," + ort.getText();
    }

    void updat() {
        if (Wahl < 0) {
            save("gastro/date/plz.dat", Eingabe(), true);
        }else{
	 plzString[Wahl]=Eingabe();
	save("gastro/date/plz.dat", plzString, false);
	}
        //System.out.println(">>Zeile:" + Wahl + "," + plzjtf.getText() + "," + ort.getText());
    }

    class PlzAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           // JComboBox <String>cb = (JComboBox <String>) e.getSource();
            String newSelection = (String) jcb.getSelectedItem();
            Select = newSelection;
            Wahl = (Integer) jcb.getSelectedIndex();
            updat();
            System.out.println("Zeile:" + Wahl + "," + Select);
            String[] str = ausTeilen.komma(Select);
            plzjtf.setText(str[0]);
            ort.setText(str[1]);
        }
    }
    class MyItemListener implements ItemListener {
     // This method is called only if a new item has been selected.
     @Override
     public void itemStateChanged(ItemEvent evt) {
    // JComboBox cb = (JComboBox <String>) evt.getSource();
     Object item = evt.getItem();	  		  		 			  	
     if (evt.getStateChange() == ItemEvent.SELECTED) {      // Item was just selected
	  String[] str = ausTeilen.komma(""+item);
            plzjtf.setText(str[0]);
            ort.setText(str[1]);		
     } else if (evt.getStateChange() == ItemEvent.DESELECTED) {      // Item is no longer selected
	    
     }
     }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Posleitzahl Box");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new PlzBox();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

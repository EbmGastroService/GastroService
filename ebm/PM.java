package ebm;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * ProgressMonitorDemo.java requires these files:
 *   LongTask.java
 *   SwingWorker.java
 */
 public class PM extends JFrame {//implements ActionListener {
    public final static int ONE_SECOND = 1000;

    private ProgressMonitor progressMonitor;
    private Timer timer;
    private JButton startButton;
    private ebm.LT task;
    private JTextArea taskOutput;
    private String newline = "\n";

    public PM() {
//        super(new BorderLayout());
        task = new ebm.LT(500);
    	

        //Create the demo's UI.
     /*   startButton = new JButton("Start");
        startButton.setActionCommand("start");
        startButton.addActionListener(this);*/

        //taskOutput = new JTextArea(5, 20);
        //taskOutput.setMargin(new Insets(5,5,5,5));
        //taskOutput.setEditable(false);

        //add(startButton, BorderLayout.PAGE_START);
       // add(new JScrollPane(taskOutput), BorderLayout.CENTER);
       // setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    	

        //Create a timer.
        timer = new Timer(ONE_SECOND, new TimerListener());
    	start();
    }
     public PM(int mt) {
     	task = new ebm.LT(mt);
     	timer = new Timer(ONE_SECOND, new TimerListener());
    	start();
     }

    /**
     * The actionPerformed method in this class
     * is called each time the Timer "goes off".
     */
    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            progressMonitor.setProgress(task.getCurrent());
            String s = task.getMessage();
            if (s != null) {
                progressMonitor.setNote(s);
                /*taskOutput.append(s + newline);
                taskOutput.setCaretPosition(
                taskOutput.getDocument().getLength())*/;
            }
            if (progressMonitor.isCanceled() || task.isDone()) {
                progressMonitor.close();
                task.stop();
                Toolkit.getDefaultToolkit().beep();
                timer.stop();
                if (task.isDone()) {
                //	new com.options.MyOp().fehler("Program wird Beendet!");
                	Runtime.getRuntime().exit(0);
                    //taskOutput.append("Task completed." + newline);
                } else {
                    //taskOutput.append("Task canceled." + newline);

                }
                //startButton.setEnabled(true);
            }
        }
    }

    /**
     * Called when the user presses the start button.
     */
    /*public void actionPerformed(ActionEvent evt) {
        progressMonitor = new ProgressMonitor(PM.this,
                                  "Running a Long Task",
                                  "", 0, task.getLengthOfTask());
        progressMonitor.setProgress(0);
        progressMonitor.setMillisToDecideToPopup(2 * ONE_SECOND);

        startButton.setEnabled(false);
        task.go();
        timer.start();
    }*/
     public void start() {
     	setTitle("PM");
     	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        progressMonitor = new ProgressMonitor(PM.this,
                                  "Aufgaben beenden Bitte warten!",
                                  "", 0, task.getLengthOfTask());
        progressMonitor.setProgress(0);
        progressMonitor.setMillisToDecideToPopup(2 * ONE_SECOND);

     //   startButton.setEnabled(false);
        task.go();
        timer.start();
     	pack();
        //setVisible(true);
     	setLocation(500,200);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
   /* private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ProgressMonitorDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new PM();
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
            public void run() {
                createAndShowGUI();
            }
        });
    }*/
}

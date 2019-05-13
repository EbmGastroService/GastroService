// created on 07.09.2007 at 22:43

/** Uses a SwingWorker to perform a time-consuming (and utterly fake) task. */

/* 
 * LongTask.java is used by:
 *   ProgressBarDemo.java
 *   ProgressBarDemo2.java
 *   ProgressMonitorDemo
 */
 package ebm;
public class LT{
    private int lengthOfTask;
    private int current = 0;
    private boolean done = false;
    private boolean canceled = false;
    private String statMessage;

    public LT() {
        //Compute length of task...
        //In a real program, this would figure out
        //the number of bytes to read or whatever.
        lengthOfTask = 1000;
    }
     public LT(int myt) {
     	lengthOfTask=myt;
     }

    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go() {
        final SW worker = new SW() {
            public Object construct() {
                current = 0;
                done = false;
                canceled = false;
                statMessage = null;
                return new ActualTask();
            }
        };
        worker.start();
    }

    /**
     * Called from ProgressBarDemo to find out how much work needs
     * to be done.
     */
    public int getLengthOfTask() {
        return lengthOfTask;
    }

    /**
     * Called from ProgressBarDemo to find out how much has been done.
     */
    public int getCurrent() {
        return current;
    }

    public void stop() {
        canceled = true;
        statMessage = null;
    }

    /**
     * Called from ProgressBarDemo to find out if the task has completed.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Returns the most recent status message, or null
     * if there is no current status message.
     */
    public String getMessage() {
        return statMessage;
    }

    /**
     * The actual long running task.  This runs in a SwingWorker thread.
     */
    class ActualTask {
        ActualTask() {
            //Fake a long task,
            //making a random amount of progress every second.
            while (!canceled && !done) {
                try {
                	int total=0;
                    Thread.sleep(1000); //sleep for a second
                    current += Math.random() * 100; //make some progress
                    if (current >= lengthOfTask) {
                        done = true;
                        current = lengthOfTask;
                    }
                    total+=current;
                    statMessage = "bereit erledigt " +current +""+
                                  " von " + lengthOfTask + ".";
                } catch (InterruptedException e) {
                   // System.out.println("ActualTask interrupted");
                }
            }
        }
    }
}

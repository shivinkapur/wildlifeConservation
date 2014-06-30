import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
public class gui implements  ActionListener{

    // Definition of global values and items that are part of the GUI.
    int redScoreAmount = 0;
    int blueScoreAmount = 0;

    JPanel titlePanel, scorePanel, buttonPanel;
    JLabel redLabel, blueLabel, redScore, blueScore;
    JButton redButton, blueButton, resetButton;

    public JPanel createContentPane (){

        // We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        // Creation of a Panel to contain the title labels
      

        // Creation of a Panel to contain the score labels.
        scorePanel = new JPanel();
        scorePanel.setLayout(null);
        scorePanel.setLocation(10, 10);
        scorePanel.setSize(700, 700);
        totalGUI.add(scorePanel);

        redScore = new JLabel(""+redScoreAmount);
        redScore.setLocation(0, 0);
        redScore.setSize(700, 700);
        redScore.setHorizontalAlignment(0);
        scorePanel.add(redScore);

     

        // Creation of a Panel to contain all the JButtons.
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(750, 40);
        buttonPanel.setSize(260, 70);
        totalGUI.add(buttonPanel);

        // We create a button and manipulate it using the syntax we have
        // used before. Now each button has an ActionListener which posts 
        // its action out when the button is pressed.
        redButton = new JButton("Extraction");
        redButton.setLocation(0, 0);
        redButton.setSize(120, 30);
        redButton.addActionListener(this);
        buttonPanel.add(redButton);

        blueButton = new JButton("Transformation");
        blueButton.setLocation(130, 0);
        blueButton.setSize(120, 30);
        blueButton.addActionListener(this);
        buttonPanel.add(blueButton);

        resetButton = new JButton("Reset Score");
        resetButton.setLocation(0, 40);
        resetButton.setSize(250, 30);
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);
        
        totalGUI.setOpaque(true);
        return totalGUI;
    }

    // This is the new ActionPerformed Method.
    // It catches any events with an ActionListener attached.
    // Using an if statement, we can determine which button was pressed
    // and change the appropriate values in our GUI.
    public void actionPerformed(ActionEvent e)
 {

        if(e.getSource() == redButton)
        {
	Extraction ex=new Extraction();
	String args[]={"A","B"};
	try{
		ex.main(args);
	}
	catch(Exception exc)
	{}
	String x=new String();
           // redScoreAmount = redScoreAmount + 1;
	try{
	x=readregion();
           	}
	catch(Exception ex2){}
	redScore.setText(""+x);
        }
       else if(e.getSource() == blueButton)
        {
            //blueScoreAmount = blueScoreAmount + 1;
           // blueScore.setText(""+blueScoreAmount);
        }
        else if(e.getSource() == resetButton)
        {
            redScoreAmount = 0;
            //blueScoreAmount = 0;
            //redScore.setText(""+redScoreAmount);
            blueScore.setText(""+blueScoreAmount);
        }
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame(" ETL Tool ");

        //Create and set up the content pane.
        gui demo = new gui();
        frame.setContentPane(demo.createContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
	public String readregion()throws IOException
	{
	BufferedReader br=new BufferedReader(new FileReader("region.txt"));
	String x="<html>",y="";
	y=br.readLine();
	while(y!=null)					//Normal Extraction
	{
		x=x+y+"<br>";
		y=br.readLine();
	}
	x=x+"</html>";
	return x;	
	}
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
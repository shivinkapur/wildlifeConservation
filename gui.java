import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
public class gui implements  ActionListener
{
    int redScoreAmount = 0;
    int blueScoreAmount = 0;

    JPanel titlePanel, scorePanel, buttonPanel;
    JLabel redLabel, blueLabel, redScore, blueScore;
    JButton redButton, blueButton, resetButton,kmeansButton,knnButton;

    public JPanel createContentPane (){

        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);
        scorePanel = new JPanel();
        scorePanel.setLayout(null);
        scorePanel.setLocation(10, 10);
        scorePanel.setSize(400, 700);
        totalGUI.add(scorePanel);

        redScore = new JLabel(""+redScoreAmount);
        redScore.setLocation(0, 0);
        redScore.setSize(400, 700);
        redScore.setHorizontalAlignment(0);
        scorePanel.add(redScore);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(450, 40);
        buttonPanel.setSize(260, 70);
        totalGUI.add(buttonPanel);

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
       kmeansButton = new JButton("Kmeans");
        kmeansButton.setLocation(0, 40);
       kmeansButton.setSize(120, 30);
       kmeansButton.addActionListener(this);
       buttonPanel.add(kmeansButton);     
	 knnButton = new JButton("knn");
        knnButton.setLocation(130, 40);
      knnButton.setSize(120, 30);
       knnButton.addActionListener(this);
       buttonPanel.add(knnButton);     
        totalGUI.setOpaque(true);
        return totalGUI;
    }

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
           
	try{
	x=readoutput("region.txt");
           	}
	catch(Exception ex2){}
	redScore.setText(""+x);
        }
       else if(e.getSource() == blueButton)
        {
	Transformation trans=new Transformation();
	String args[]={"A","B"};
	try{
		trans.main(args);
	}
	catch(Exception exc)
	{}
	String x=new String();
           
	try{
	x=readoutput("regiontransform.txt");
           	}
	catch(Exception ex2){}
	redScore.setText(""+x);
        }
       else if(e.getSource() == kmeansButton)
        {
	KmeanSpecies km=new KmeanSpecies();
	String args[]={"A","B"};
	try{
		km.main(args);
	}
	catch(Exception exc)
	{}
	String x=new String();
           
	try{
	x=readoutput("kmeansout.txt");
           	}
	catch(Exception ex2){}
	redScore.setText(""+x);
        }
	else if(e.getSource() == knnButton)
        {
	knn kn=new knn();
	String args[]={"A","B"};
	try{
		kn.main(args);
	}
	catch(Exception exc)
	{}
	String x=new String();
           
	try{
	x=readoutput("knnout.txt");
           	}
	catch(Exception ex2){}
	redScore.setText(""+x);
        }
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame(" ETL Tool ");

        gui demo = new gui();
        frame.setContentPane(demo.createContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
	public String readoutput(String f)throws IOException
	{
	BufferedReader br=new BufferedReader(new FileReader(f));
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
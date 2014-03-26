package nqueens;

/**
 * The View class which sets up the gui that the user will see when running the program.		
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class NQueensView extends JFrame {

	private JButton mySolveButton;
	private JButton[][] myGridButtons;
	private JPanel myGridPanel , myButtonPanel;
	private int myQueens;
	private ButtonListener mySolveListener;
	private JLabel myMaxSolutions;
	private int mySolutions = 0;
	
	/**
	 * constructor for the view sets up the frame and the buttons along with the panels that will display 
	 * the grid of the according nqueens size. 
	 * @param controller
	 */
	public NQueensView (NQueensController controller) 
	{
		
		this.setQueens();
		this.setSize(750, 750);
		this.setLocationRelativeTo(null);
		this.setTitle("NQueens");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.associateListeners(controller);
		
		
		mySolveButton = new JButton("Solve Puzzle");
		myGridButtons = new JButton[myQueens][myQueens];
		myMaxSolutions = new JLabel("Solutions: " + mySolutions);
		
		myGridPanel = new JPanel( new GridLayout( myQueens, myQueens , myQueens , myQueens));
		myButtonPanel = new JPanel ( new FlowLayout());
		
		mySolveButton.addMouseListener(mySolveListener);
		
		myButtonPanel.add(mySolveButton);
		myButtonPanel.add(myMaxSolutions);
		
		for( int i = 0 ; i < myGridButtons.length ; i ++)
		{
			for ( int j = 0 ; j < myGridButtons[i].length ; j ++)
			{
				myGridButtons[i][j] = new JButton();
				myGridButtons[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				myGridPanel.add(myGridButtons[i][j]);
			}
		}
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(myGridPanel, BorderLayout.CENTER);
		contentPane.add(myButtonPanel, BorderLayout.SOUTH);
		contentPane.validate();
	}
	
	public void associateListeners(NQueensController controller)
    {
        Class<? extends NQueensController> controllerClass;
        Method solveGridMethod ;
               
        controllerClass = controller.getClass();
        
        solveGridMethod = null;
        
        try
        {
             solveGridMethod = controllerClass.getMethod("solve", (Class<?>[])null);
        }
        catch(NoSuchMethodException exception)
        {
           String error;

           error = exception.toString();
           System.out.println(error);
        }
        catch(SecurityException exception)
        {
           String error;

           error = exception.toString();
           System.out.println(error);
        }
        
        mySolveListener = new ButtonListener( controller , solveGridMethod );
        
    }
	
	/**
	 * sets the grid size for the according nqueens problem. Does not accept strings as an answer and will prompt again.
	 */
	public void setQueens()
	{
		while( myQueens <= 0)
		{
			try
			{
				
				myQueens = Integer.parseInt(JOptionPane.showInputDialog(" Choose the size of the grid!"));
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Must be number!");
			}
		}
			 
	}
	
	/**
	 * returns the grid size specified by the user
	 * @return myQueens for the size of the grid
	 */
	public int getQueens()
	{
		return myQueens;
	}
	
	/**
	 * takes in the boolean board and sets the grid to the corresponding postions where a queen should be placed
	 * @param board
	 */
	public void setGrid(boolean[][] board )
	{
		for( int i = 0 ; i < board.length ; i ++)
		{
			for ( int j = 0 ; j < board[i].length ; j ++)
			{
				if( board[i][j] == true)
				{
					myGridButtons[i][j].setBackground(Color.MAGENTA);
					myGridButtons[i][j].setText("Q");
				}
				
			}
		}
	}
	
	/**
	 * sets the max number of solutions in the view
	 * @param solutions
	 */
	public void setSolutions( int solutions)
	{
		mySolutions = solutions;
		myMaxSolutions.setText("Solutions: " + mySolutions);
	}
	
}

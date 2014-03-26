package nqueens;

/**
 * Very simple controller that just solves the corresponding nqueens problem.
 * @author Sebastian Florez
 *
 */
public class NQueensController {

	private NQueensModel myModel;
	private NQueensView myView;
	
	/**
	 * constructor that sets up the view and the model. The model asks the view for the grid size specified by the user
	 */
	public NQueensController()
	{
		
		myView = new NQueensView(this);
		myModel = new NQueensModel(myView.getQueens());
		
	}
	
	/**
	 * method that solves the nqueens puzzle and sets the view board and the max number of solutions
	 */
	public void solve()
	{
		myModel.solvePuzzle();
		boolean[][] board = myModel.getFirstBoard();
		myView.setGrid(board);
		myView.setSolutions(myModel.getCounter());
	}
	
	
}



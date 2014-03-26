package nqueens;

/**
 * Model class that solves the corresponding nqueens problem recursively.
 * @author Sebass
 *
 */
public class NQueensModel {

	
	private int myNumQueens = 0;
	private boolean[][] myBoard;
	private int myCounter = 0;
	private boolean[][] myFirstBoard;
	
	/**
	 * sets up the size of the board based on the parameter
	 * @param nQueens
	 */
	public NQueensModel( int nQueens)
	{
		myNumQueens = nQueens;
		myBoard = new boolean[nQueens][nQueens];
	}
	
	/**
	 * public method that is called from the controller, it calls the private method within the class to solve the puzzle
	 * starting at the first column
	 * @return boolean
	 */
	public boolean solvePuzzle()
	{
		solvePuzzle(0);
		return true;
	}
	
	/**
	 * solves the puzzle recursively, increments a counter every time a solution is found and sets myFirstBoard to a clone of 
	 * the first completed board.
	 * @param nColumns
	 * @return boolean
	 */
	private boolean solvePuzzle(int nColumns)
	{
		if( nColumns >= myNumQueens)
		{
			myFirstBoard = this.clone();
			myCounter ++;
		}
		else
		{
			for ( int i = 0; i < myBoard.length ; i ++)
			{
				if( isSafeMove( i, nColumns) == true)
				{
					this.placeQueen(i, nColumns);
					if( solvePuzzle( nColumns +1) == true)
					{
						return true;
					}
					else
					{
						this.removeQueen(i,nColumns);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Method that determines if the row, and column passed in is a safe move to make in terms of the nqueens rules.
	 * @param row
	 * @param col
	 * @return boolean
	 */
	private boolean isSafeMove( int row, int col)
	{
		if( col == 0)
		{
			return true;
		}
		if( checkUpperDiag( row , col) && checkLowerDiag( row, col) && checkLeft( row, col ))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Method to check the upper diagonal of the grid.
	 * @param row
	 * @param col
	 * @return boolean 
	 */
	private boolean checkUpperDiag( int row, int col)
	{
		
		for ( int i = row , j = col ; j >= 0 && i >= 0; i -- , j--)
		{
			if( myBoard[i][j])
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method to check the lower diagonal of the grid.
	 * @param row
	 * @param col
	 * @return boolean 
	 */
	private boolean checkLowerDiag( int row, int col)
	{
		
		for ( int i = row, j = col; j>=0 && i < myBoard.length ; i++, j--)
		{
			if(myBoard[i][j])
			{
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Method to check the entire row of the grid.
	 * @param row
	 * @param col
	 * @return boolean 
	 */
	private boolean checkLeft( int row, int col)
	{
		for ( int i = col ; i >= 0 ; i --)
		{
			if( myBoard[ row ][ i ] == true)
			{
				return false;
			}
		}
		
		return true;
		
	}
	
	/**
	 * @pre a queen is not already in the location
	 * @post Method to place a queen on the specified row and column only if there is not a queen there already
	 * @param row
	 * @param col
	 * @return boolean 
	 */
	private boolean placeQueen( int row, int col)
	{
		if( myBoard[row][col] == false )
		{
			myBoard[row][col] = true;
			return true;
		}
		return false;
	}
	
	/**
	 * @pre a queen is in the location
	 * @post Method to remove a queen on the specified row and column only if there is a queen there already
	 * @param row
	 * @param col
	 * @return boolean 
	 */
	private boolean removeQueen( int row, int col)
	{
		if( myBoard[row][col] == true)
		{
			myBoard[row][col] = false;
			return false;
		}
		return false;
	}
	
	public String toString()
	{
		String string;
		string = "Grid Size is : " + myNumQueens + " \nMax number of solutions is: " + myCounter;
		return string;
	}
	
	public static void main( String[] args)
	{
		NQueensModel model = new NQueensModel(4);
		model.solvePuzzle();
		System.out.println(model.getCounter());
	}
	
	// Accessors
	
	/**
	 * returns the max number of solutions 
	 * @return myCounter
	 */
	public int getCounter()
	{
		return myCounter;
	}
	
	/**
	 * returns the First answer of a corresponding nqueens problem
	 * @return myFirstBoard
	 */
	public boolean[][] getFirstBoard()
	{
		return myFirstBoard;
	}
	
	/**
	 * returns the grid size
	 * @return
	 */
	public int getNumQueens()
	{
		return myNumQueens;
	}
	
	/**
	 * method that makes a copy of the first answer to an nqueens problem.
	 * @return a boolean board array of the first answer
	 */
	public boolean[][] clone()
	{
		boolean[][] board = new boolean[myNumQueens][myNumQueens];
		
		for ( int i = 0 ; i < myBoard.length ; i ++)
		{
			for ( int j = 0 ; j < myBoard[i].length ; j ++)
			{
				board[i][j] = myBoard[i][j];
			}
		}
		
		return board;
	}
}

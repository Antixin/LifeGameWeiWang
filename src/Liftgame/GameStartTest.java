package Liftgame;

import org.junit.Before;
import org.junit.Test;

public class GameStartTest {
	private DrawMap drawMap = null;
	private int rows = 50;
	private int cols = 50;
	private int[][] cellStatus = new int[rows][rows];
	
	@Before
	public void setUp() throws Exception {
		drawMap = new DrawMap();
		GameStart.getInstance().choiceCellsImage(0);
		cellStatus = GameStart.getInstance().getCellNowStatus();
		drawMap.StartTime();
	}

	@Test
	public void testUpdateCells() {
		GameStart.getInstance().updateCells();
		cellStatus = GameStart.getInstance().getCellNowStatus();
		for(int i=0; i<cellStatus.length; i++){
			for(int j=0; j<cellStatus[i].length; j++){
				System.out.print(cellStatus[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------");
	}

	@Test
	public void testOnlyOneCell() {
		int count = 0;
		count = GameStart.getInstance().onlyOneCell(21, 21);
		if(count == 3){
			System.out.println(true);
		}
		System.out.println("-------------------------------------------");
	}

	@Test
	public void testGetCellNowStatus() {
		cellStatus = GameStart.getInstance().getCellNowStatus();
		for(int i=0; i<cellStatus.length; i++){
			for(int j=0; j<cellStatus[i].length; j++){
				System.out.print(cellStatus[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------");
	}

	@Test
	public void testGetCellNext() {
		cellStatus = GameStart.getInstance().getCellNext();
		for(int i=0; i<cellStatus.length; i++){
			for(int j=0; j<cellStatus[i].length; j++){
				System.out.print(cellStatus[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------");
	}

}

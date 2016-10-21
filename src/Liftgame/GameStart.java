/**
 * 
 */
package Liftgame;

/**
 * @author Anti
 *
 */
public class GameStart {
	/**
	 * @生命游戏
	 */
	private static GameStart instance = null;
	private int rows = 50;
	private int cols = 50;
	private int[][] cellNowStatus = new int[rows][cols];		//细胞的状态, 1为生，0 为死
	private int[][] cellNext = new int[rows][cols];		//过渡细胞状态
	
	protected GameStart() {
//		InitCellsMap();
	}
	
	//初始化生命游戏地图
//	protected void InitCellsMap(){
		
//		for(int i=0; i<rows; i++){
//			for(int j=0; j< cols; j++){
//				cellNowStatus[i][j] = 0;
//				cellNext[i][j] = 0;
//			}
//		}
		
//		for(int i=0; i<rows; i++){
//			for(int j=0; j< cols; j++){
//				if((i > 20 && i <= 30) && (j >20 && j <= 30)){
//					cellNowStatus[i][j] = 1;
//					cellNext[i][j] = 1;
//				}else {
//					cellNowStatus[i][j] = 0;
//					cellNext[i][j] = 0;
//				}
//			}
//		}
//	}
	
	//更新生命游戏数据
	public void updateCells() {
		int index = 0;		//用于判断规则的，2保持，3复活，其它均为死状态
		
		//循环遍历一次细胞面板
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				//细化下去的核心算法，直到一个细胞之上
				index = onlyOneCell(i, j);
				if(index == 3){
					cellNext[i][j] = 1;
				}else if (index == 2) {
					//保持状态，什么也不用操作
				}else {
					cellNext[i][j] = 0;
				}
			}
		}
		
		//更新cellNowStatus[rows][cols]
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				cellNowStatus[i][j] = cellNext[i][j];
			}
		}
	}
	
	//计算单个细胞周围的生死个数
	protected int onlyOneCell(int row, int col) {
		int count = 0;		//记录周围细胞生死个数
		
		for(int i=row-1; i<=row+1; i++){
			for(int j=col-1; j<=col+1; j++){
				if (i < 0 || i >= rows || j < 0 || j >= cols) {  
                    continue;  
                }
				if(cellNowStatus[i][j] == 1){
					count++;
				}
			}
		}
		
		if(cellNowStatus[row][col] == 1){
			count--;
		}
		
		return count;
	}

	public static GameStart getInstance() {
		if(instance == null){
			instance = new GameStart();
		}
		
		return instance;
	}
	
	//获取细胞现在的生死状态
	public int[][] getCellNowStatus() {
		return cellNowStatus;
	}

	//改变细胞的生死状态
	public void setCellNowStatus(int[][] cellNowStatus) {
		this.cellNowStatus = cellNowStatus;
	}
	
	public int[][] getCellNext() {
		return cellNext;
	}

	public void setCellNext(int[][] cellNext) {
		this.cellNext = cellNext;
	}

	public static void main(String[] args) {
		GameStart.getInstance();
	}
	
	public void choiceCellsImage(int choice) {
		if(choice == 0){
			for(int i=0; i<rows; i++){
				for(int j=0; j< cols; j++){
					if((i > 20 && i <= 30) && (j >20 && j <= 30)){
						cellNowStatus[i][j] = 1;
						cellNext[i][j] = 1;
					}else {
						cellNowStatus[i][j] = 0;
						cellNext[i][j] = 0;
					}
				}
			}
		}else if (choice == 1) {
			int index = 0;		//用于标志
			
			for(int i=0; i<rows; i++){
				for(int j=0; j< cols; j++){
					index = (int)(Math.random()*10);
					if(index > 6){
						cellNowStatus[i][j] = 1;
						cellNext[i][j] = 1;
					}else {
						cellNowStatus[i][j] = 0;
						cellNext[i][j] = 0;
					}
				}
			}
		}
	}
}

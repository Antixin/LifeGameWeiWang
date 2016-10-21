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
	 * @������Ϸ
	 */
	private static GameStart instance = null;
	private int rows = 50;
	private int cols = 50;
	private int[][] cellNowStatus = new int[rows][cols];		//ϸ����״̬, 1Ϊ����0 Ϊ��
	private int[][] cellNext = new int[rows][cols];		//����ϸ��״̬
	
	protected GameStart() {
//		InitCellsMap();
	}
	
	//��ʼ��������Ϸ��ͼ
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
	
	//����������Ϸ����
	public void updateCells() {
		int index = 0;		//�����жϹ���ģ�2���֣�3���������Ϊ��״̬
		
		//ѭ������һ��ϸ�����
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				//ϸ����ȥ�ĺ����㷨��ֱ��һ��ϸ��֮��
				index = onlyOneCell(i, j);
				if(index == 3){
					cellNext[i][j] = 1;
				}else if (index == 2) {
					//����״̬��ʲôҲ���ò���
				}else {
					cellNext[i][j] = 0;
				}
			}
		}
		
		//����cellNowStatus[rows][cols]
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				cellNowStatus[i][j] = cellNext[i][j];
			}
		}
	}
	
	//���㵥��ϸ����Χ����������
	protected int onlyOneCell(int row, int col) {
		int count = 0;		//��¼��Χϸ����������
		
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
	
	//��ȡϸ�����ڵ�����״̬
	public int[][] getCellNowStatus() {
		return cellNowStatus;
	}

	//�ı�ϸ��������״̬
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
			int index = 0;		//���ڱ�־
			
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

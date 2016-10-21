package Liftgame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;

public class DrawMap extends JFrame implements ActionListener{

	private JPanel contentPane;
	private final int rows = 50;
	private final int cols = 50;
	private int[][] cellStatus = new int[rows][cols];	//承接GameStart传过来的数据
	private final int widthPanel = 750;			//画板的宽度
	private final int heightPanel = 650;		//画板的高度
	private final int widthRect = widthPanel/rows;		//矩形的宽度
	private final int heightRect = heightPanel/cols;	//矩形的高度
	private final int deviationWD = 15;		//绘制面板向右位移的尺度
	private final int deviationHT = 34;		//绘制面板向下位移的尺度
	private boolean isStart = false;		//控制游戏是否只能是单线程进行
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawMap frame = new DrawMap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 构造函数，初始化面板，开始游戏
	 */
	public DrawMap() {
		InitDrawMap();
	}

	//绘制初始化面板
	protected void StartTime() {
		this.repaint();
		updateGame();
	}
	
	//定时实现更新
	protected void updateGame(){
		new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						//这里还存在着一些问题
						Thread.sleep(1000);
						GameStart.getInstance().updateCells();
						DrawMap.this.repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	//真正的实现绘图
	@Override
	public void paint(Graphics gcs) {
		super.paint(gcs);
		int ws = deviationWD + 8;	
		int hs = deviationHT + 31;
		cellStatus = GameStart.getInstance().getCellNowStatus();
		
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				if(cellStatus[i][j] == 0){
					gcs.drawRect(i*widthRect+ws, j*heightRect+hs, widthRect, heightRect);
				}else if (cellStatus[i][j] == 1) {
					gcs.fillRect(i*widthRect+ws, j*heightRect+hs, widthRect, heightRect);
				}
			}
		}
	}

	protected void InitDrawMap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 120, 800, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//面板项
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setSize(750, 650);
		panel.setLocation(15, 35);
		contentPane.add(panel, BorderLayout.CENTER);
		
		//正方形按钮
		JButton btnNewButton = new JButton("\u6B63\u65B9\u56FE\u5F62");
		btnNewButton.setBounds(164, 5, 100, 25);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		//随机图形按钮
		JButton btnNewButton_1 = new JButton("\u968F\u673A\u56FE\u5F62");
		btnNewButton_1.setBounds(412, 5, 100, 25);
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		
		if("正方图形".equals(str)){
			//初始图形为正方形
			if(!isStart){
				GameStart.getInstance().choiceCellsImage(0);
				StartTime();
				isStart = true;
			}
		}else if ("随机图形".equals(str)) {
			//初始图形为随机图形
			if(!isStart){
				GameStart.getInstance().choiceCellsImage(1);
				StartTime();
				isStart = true;
			}
		}
	}
}

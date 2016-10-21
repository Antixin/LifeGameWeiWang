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
	private int[][] cellStatus = new int[rows][cols];	//�н�GameStart������������
	private final int widthPanel = 750;			//����Ŀ��
	private final int heightPanel = 650;		//����ĸ߶�
	private final int widthRect = widthPanel/rows;		//���εĿ��
	private final int heightRect = heightPanel/cols;	//���εĸ߶�
	private final int deviationWD = 15;		//�����������λ�Ƶĳ߶�
	private final int deviationHT = 34;		//�����������λ�Ƶĳ߶�
	private boolean isStart = false;		//������Ϸ�Ƿ�ֻ���ǵ��߳̽���
	

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
	 * ���캯������ʼ����壬��ʼ��Ϸ
	 */
	public DrawMap() {
		InitDrawMap();
	}

	//���Ƴ�ʼ�����
	protected void StartTime() {
		this.repaint();
		updateGame();
	}
	
	//��ʱʵ�ָ���
	protected void updateGame(){
		new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						//���ﻹ������һЩ����
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

	//������ʵ�ֻ�ͼ
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
		
		//�����
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setSize(750, 650);
		panel.setLocation(15, 35);
		contentPane.add(panel, BorderLayout.CENTER);
		
		//�����ΰ�ť
		JButton btnNewButton = new JButton("\u6B63\u65B9\u56FE\u5F62");
		btnNewButton.setBounds(164, 5, 100, 25);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		//���ͼ�ΰ�ť
		JButton btnNewButton_1 = new JButton("\u968F\u673A\u56FE\u5F62");
		btnNewButton_1.setBounds(412, 5, 100, 25);
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		
		if("����ͼ��".equals(str)){
			//��ʼͼ��Ϊ������
			if(!isStart){
				GameStart.getInstance().choiceCellsImage(0);
				StartTime();
				isStart = true;
			}
		}else if ("���ͼ��".equals(str)) {
			//��ʼͼ��Ϊ���ͼ��
			if(!isStart){
				GameStart.getInstance().choiceCellsImage(1);
				StartTime();
				isStart = true;
			}
		}
	}
}

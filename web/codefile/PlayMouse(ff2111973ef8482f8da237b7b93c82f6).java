package 打地鼠游戏;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayMouse extends JFrame implements Runnable{
	int m=0;
	int num=0;
	static JLabel mouseback;
	JLabel score;
	ImageIcon icon;
	ImageIcon iconmouse;
	JLabel []mouse;
	JButton start1;
	static JButton start2,start3;
	private PlayMouse(){
		this.setTitle("打地鼠游戏");
		this.setBounds(200, 200, 798, 480);	
		this.setResizable(false);
		this.getContentPane().setLayout(null);			//把它默认的布局管理器设为null
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		icon=new ImageIcon(this.getClass().getResource("1.jpg"));
		mouseback=new JLabel(icon);
		mouseback.setBounds(0, 0, 798, 480);
		//将鼠标变成锤子
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("src/打地鼠游戏/0.png"),new Point(),"self"));
		
		mouse=new JLabel[9];
		iconmouse=new ImageIcon(this.getClass().getResource("2.png"));
		for(int i=0;i<9;i++){
			mouse[i]=new JLabel();
			mouse[i].setSize(iconmouse.getIconWidth(),iconmouse.getIconHeight());
			mouse[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Object object=e.getSource();
					if(object instanceof JLabel){
						JLabel jlable=(JLabel)object;
						if(jlable.getIcon()!=null){
							num++;
							score.setText("您的得分是:"+ num+"分");
						}
						jlable.setIcon(null);
					}
				}
			});
			this.getContentPane().add(mouse[i]);						
		}
		mouse[0].setLocation(155,95);
		mouse[1].setLocation(340,95);
		mouse[2].setLocation(535,95);
		mouse[3].setLocation(125,185);
		mouse[4].setLocation(340,185);
		mouse[5].setLocation(540,185);
		mouse[6].setLocation(116,280);
		mouse[7].setLocation(340,288);
		mouse[8].setLocation(560,288);
		
		score=new JLabel("您的得分是:     分");
		score.setBounds(500,5,300,100);
		score.setFont(new Font("",10,30));
		score.setForeground(Color.yellow);
		this.getContentPane().add(score);
		
		start1=new JButton("暂停");
		start1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				m=1;
				
			}
		});
		start1.setBorder(BorderFactory.createEtchedBorder());		//让文字充满整个按钮空间
		start1.setBackground(Color.yellow);
		start1.setBounds(300,10,80,80);
		start1.setFont(new Font("",50,30));
		start1.setFocusPainted(false);			//去掉按钮文字周围的框框
		start1.setForeground(Color.black);
		this.getContentPane().add(start1);
		
	}
	
	public void run() {
		Random random=new Random();
		while(true){
			try {
				Thread.sleep(500);				//等待出现时间
				int i=random.nextInt(9);
				if(mouse[i].getIcon()==null){
					mouse[i].setIcon(iconmouse);
					Thread.sleep(1000);			//让它存在的持续时间
					if(mouse[i].isShowing()){
						mouse[i].setIcon(null);
					}
				}
				if(m==1){
					m=0;
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		Random random=new Random();
		PlayMouse p1=new PlayMouse();
		Thread []t=new Thread[1000];
		for(int j=0;j<t.length;j++){
			t[j]=new Thread(p1);
		}
		start2=new JButton("开始游戏");
		start2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				t[random.nextInt(1000)].start();
			}
		});
		start2.setBorder(BorderFactory.createEtchedBorder());		//让文字充满整个按钮空间
		start2.setBackground(Color.yellow);
		start2.setBounds(50,10,200,80);
		start2.setFont(new Font("",50,30));
		start2.setFocusPainted(false);			//去掉按钮文字周围的框框
		start2.setForeground(Color.black);
		p1.getContentPane().add(start2);
		
		start3=new JButton("继续");
		start3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				t[random.nextInt(1000)].start();
			}
		});
		start3.setBorder(BorderFactory.createEtchedBorder());		//让文字充满整个按钮空间
		start3.setBackground(Color.yellow);
		start3.setBounds(400,10,80,80);
		start3.setFont(new Font("",50,30));
		start3.setFocusPainted(false);			//去掉按钮文字周围的框框
		start3.setForeground(Color.black);
		p1.getContentPane().add(start3);
		
		p1.getContentPane().add(mouseback);
		p1.setVisible(true);			//放在最后
}
}

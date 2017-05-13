package hw4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label_1, label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnClear = new JButton("clear");
		btnClear.setBounds(27, 215, 88, 23);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField.setText("");}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnClear);
		
		textField = new JTextField();
		textField.setBounds(144, 60, 178, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDd = new JLabel("enter X:");
		lblDd.setBounds(64, 64, 88, 15);
		frame.getContentPane().add(lblDd);
		
		JLabel lbly = new JLabel("enter Y:");
		lbly.setBounds(64, 103, 75, 15);
		frame.getContentPane().add(lbly);
		
		textField_1 = new JTextField();
		textField_1.setBounds(144, 100, 178, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("the length of X and Y'sLCS:");
		lblNewLabel.setBounds(78, 141, 199, 15);
		frame.getContentPane().add(lblNewLabel);
		
		final JLabel label = new JLabel("");
		label.setBounds(276, 141, 46, 15);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel_1 = new JLabel("LCS:");
		lblNewLabel_1.setBounds(78, 166, 118, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel label_1 = new JLabel("");
		label_1.setBounds(196, 166, 67, 15);
		frame.getContentPane().add(label_1);
		
		JButton btnNewButton = new JButton("execute");
		btnNewButton.setBounds(176, 215, 87, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int i,j;
			        String X = textField.getText();  /* String X */
			        String Y = textField_1.getText();    /* String Y */
			        /* initialize the n x m matrix B and C for dynamic programming 
			         * B[i][j] stores the directions, C[i][j] stores the length of LCS of
			         * X[0..i-1] and Y[0..j-1]
			         */ 
			        int n = X.length();
			        int m = Y.length();
			        int[][] C = new int[n+1][m+1];
			        int[][] B = new int[n+1][m+1];
				
			        /* C[i][0] = 0 for 0<=i<=n */
			        for (i = 0; i <= n; i++) {
			            C[i][0] = 0;
			        }
				
			        /* C[0][j] = 0 for  0<=j<=m */
			        for (j = 0; j <= m; j++) {
			            C[0][j] = 0;
			        }
			        
			        /* dynamic programming */
			        for (i = 1; i <= n; i++) {
			            for (j = 1; j <= m; j++) {
			                if (X.charAt(i-1) == Y.charAt(j-1)) {
			                    C[i][j]=C[i-1][j-1]+1;
			                    B[i][j]=1;  /* diagonal */
			                }
			                else if (C[i-1][j]>=C[i][j-1]) {
			                    C[i][j]=C[i-1][j];
			                    B[i][j] = 2;  /* down */
			                }
			                else {
			                    C[i][j]=C[i][j-1];     
			                    B[i][j]=3;   /* forward */
			                }
			            }
			        }
			        /* Backtracking */
			        String lcs = new String();
			        i=n;
			        j=m;
			        while (i!=0 && j!=0) {
			            if (B[i][j] ==1) {   /* diagonal */
			                lcs =X.charAt(i-1) + lcs;
			                i = i - 1;
			                j = j - 1;
			            }
			            if (B[i][j] == 2) {  /* up */
			                i = i - 1;
			            }
			            if (B[i][j] == 3) {  /* backward */
			                j = j - 1;
			            }
			        }
			        
			        /* print out the result */
			        label.setText(""+ C[n][m]);
			        label_1.setText(lcs);
				}
		});
		frame.getContentPane().add(btnNewButton);
		
		final JButton btnCancel = new JButton("cancel");
		btnCancel.setBounds(312, 215, 87, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.windowForComponent(btnCancel).dispose();}
		});
		frame.getContentPane().add(btnCancel);
		

	}
}

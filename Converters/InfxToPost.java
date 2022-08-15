package Converters;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;

public class InfxToPost extends JFrame {

	private JPanel contentPane;
	private JTextField input;
	private JTextField output;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfxToPost frame = new InfxToPost();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InfxToPost() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 519);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Infix To Postfix Converter");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 25, 743, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Infix Expression ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(84, 117, 212, 31);
		contentPane.add(lblNewLabel_1);
		
		input = new JTextField();
		input.setBounds(84, 159, 618, 31);
		contentPane.add(input);
		input.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Your Postfix Expression is");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(84, 271, 212, 31);
		contentPane.add(lblNewLabel_2);
		
		output = new JTextField();
		output.setBounds(84, 330, 618, 31);
		contentPane.add(output);
		output.setColumns(10);
		output.setEditable(false);
		
		
		JButton convert = new JButton("Convert");
		convert.addActionListener(new ActionListener() {
			public int pre(char c) {
				switch(c) {
				case '+':
				case '-':
					return 1;
				case '*':
				case '/':
					return 2;
				case '^':
					return 3;
				}
				return -1;
			}
			public void actionPerformed(ActionEvent e) {
				Stack<Character> stk = new Stack<>();
				String s = input.getText();
				String ans = "";
				for(int i=0;i<s.length()-1;i++) {
					if(Character.isLetterOrDigit(s.charAt(i))) {
						ans+=s.charAt(i);
					}
					else if(s.charAt(i)=='(') {
						stk.push(s.charAt(i));
					}
					else if(s.charAt(i)==')') {
						while(!stk.isEmpty() && stk.peek()!='(') {
							ans+=stk.pop();
						}
						stk.pop();
					}
					else {
						while(!stk.isEmpty() && pre(s.charAt(i)) <= pre(stk.peek())) {
							ans+=stk.pop();
						}
						stk.push(s.charAt(i));
					}
				}
				while(!stk.isEmpty()) {
					if(stk.peek()=='(') {
						System.out.print("Invalid Expression");
					}
					ans+=stk.pop();
				}
				output.setText(ans);
			}
		});
		convert.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		convert.setBounds(333, 217, 121, 31);
		contentPane.add(convert);
	}
}

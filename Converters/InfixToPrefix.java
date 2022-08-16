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

public class InfixToPrefix extends JFrame {

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
					InfixToPrefix frame = new InfixToPrefix();
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
	public InfixToPrefix() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 519);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Infix To Prefix Converter");
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
		
		JLabel lblNewLabel_2 = new JLabel("Your Prefix3x Expression is");
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
			public String reverse(char[] ch,int i,int n) {
				char temp;
				while(i<n) {
					temp = ch[i];
					ch[i]=ch[n];
					ch[n]=temp;
					i++;
					n--;
				}
				return String.valueOf(ch);
			}
			public int pre(char c) {
				if(c=='-' || c=='+') {
					return 1;
				}
				else if(c=='*' || c=='/') {
					return 2;
				}
				else if(c=='^') {
					return 3;
				}
				return 0;
			}
			public String infToPost(char[] ch) {
				System.out.println("ch = "+ch);
				String inf = '('+String.valueOf(ch)+')';
				
				Stack<Character> stk = new Stack<>();
				String ans = "";
						
						for(int i=0;i<inf.length();i++) {
							if(Character.isLetterOrDigit(inf.charAt(i))) {
								ans+=inf.charAt(i);
							}
							else if(inf.charAt(i)=='(') {
								stk.add('(');
							}
							else if(inf.charAt(i)==')') {
								while(stk.peek()!='(') {
									ans+=stk.peek();
									stk.pop();
								}
								stk.pop();
							}
							else {
								if(!Character.isLetterOrDigit(stk.peek())) 
								{
									while((pre(inf.charAt(i))<pre(stk.peek())) || (pre(inf.charAt(i)) <= pre(stk.peek()) && inf.charAt(i) =='^')) 
									{
										ans+=stk.peek();
										stk.pop();
									}
									stk.add(inf.charAt(i));
								}
							}
						}
						while(!stk.isEmpty()) {
							ans+=stk.pop();
						}
						return ans;
			}
			public void actionPerformed(ActionEvent e) {
				String s = input.getText();
				char[] ch = s.toCharArray();

				//reverse string
				String rev = reverse(ch,0,ch.length-1);
				ch = rev.toCharArray();
				
				
				for(int i=0;i<ch.length;i++) {
					if(ch[i]=='(') {
						ch[i]=')';
						i++;
					}
					else if(ch[i]==')') {
						ch[i]='(';
						i++;
					}
				}
				
				String ans = infToPost(ch);
				ans = reverse(ans.toCharArray(),0,ch.length-1);;
				
				output.setText(ans);
			}
		});
		convert.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		convert.setBounds(333, 217, 121, 31);
		contentPane.add(convert);
	}
}

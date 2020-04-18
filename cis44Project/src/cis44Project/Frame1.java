package cis44Project;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Frame1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JTable table;
	private JTextField textFieldIndex;
	private JTextField textFieldName;
	private JTextField textFieldImportance;
	private JTextField textFieldCategory;
	private JTextField textFieldDueDate;
	
	
	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
		connection = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1127, 717);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Task Manager");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setBounds(10, 10, 151, 23);
		frame.getContentPane().add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 124, 700, 524);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblIndex = new JLabel("Index");
		lblIndex.setBounds(10, 139, 45, 13);
		frame.getContentPane().add(lblIndex);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 187, 45, 13);
		frame.getContentPane().add(lblName);
		
		JLabel lblImportance = new JLabel("Importance");
		lblImportance.setBounds(10, 238, 99, 13);
		frame.getContentPane().add(lblImportance);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(10, 289, 89, 13);
		frame.getContentPane().add(lblCategory);
		
		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setBounds(10, 343, 45, 13);
		frame.getContentPane().add(lblDueDate);
		
		textFieldIndex = new JTextField();
		textFieldIndex.setBounds(20, 162, 96, 19);
		frame.getContentPane().add(textFieldIndex);
		textFieldIndex.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(20, 209, 96, 19);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldImportance = new JTextField();
		textFieldImportance.setBounds(20, 261, 96, 19);
		frame.getContentPane().add(textFieldImportance);
		textFieldImportance.setColumns(10);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setBounds(20, 314, 96, 19);
		frame.getContentPane().add(textFieldCategory);
		textFieldCategory.setColumns(10);
		
		textFieldDueDate = new JTextField();
		textFieldDueDate.setBounds(20, 366, 96, 19);
		frame.getContentPane().add(textFieldDueDate);
		textFieldDueDate.setColumns(10);
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.setBounds(10, 417, 85, 21);
		frame.getContentPane().add(btnAddTask);
		
		JButton btnRemoveTask = new JButton("Remove Task");
		btnRemoveTask.setBounds(136, 417, 119, 21);
		frame.getContentPane().add(btnRemoveTask);
	}
}

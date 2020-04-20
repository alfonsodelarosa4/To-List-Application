package cis44Project;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame1 {

	
	private JFrame frame;
	
	private JComboBox<String> comboBoxSort;

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
	private JTextField textFieldName;
	private JTextField textFieldImportance;
	private JTextField textFieldCategory;
	private JTextField textFieldDueDateMonth;
	private JTextField textFieldDueDateDay;
	private JTextField textFieldDueDateYear;
	
	
	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
		connection = sqliteConnection.dbConnector();
	}
	
	public void refresh()
	{
		sortArray();
		updateTable();
	}
	
	public void sortArray()
	{
		switch((String) comboBoxSort.getSelectedItem())
		{
		case "Sort by Name":
			//sort name algorithm
			break;
		case "Sort by Due Date":
			//sort due date algorithm
			break;
		case "Sort by Category":
			//sort category algorithm
			break;
		case "Sort by Importance":
			//sort importance algorithm
			break;
		}
	}
	
	public void updateTable()
	{
		/*
		//delete contents of table
		try {
			String query = "delete * from Frame1";
			
			PreparedStatement pst = connection.prepareStatement(query);
			
			pst.execute();
						
			pst.close();
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		//add elements from bag array to table
		
		*/
	}
	
	public void fillComboBox()
	{
		comboBoxSort.addItem("Sort by Name");
		comboBoxSort.addItem("Sort by Due Date");
		comboBoxSort.addItem("Sort by Category");
		comboBoxSort.addItem("Sort by Importance");
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
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(10, 10, 221, 37);
		frame.getContentPane().add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(257, 125, 700, 524);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(10, 145, 119, 13);
		frame.getContentPane().add(lblName);
		
		JLabel lblImportance = new JLabel("Importance");
		lblImportance.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblImportance.setBounds(10, 197, 99, 26);
		frame.getContentPane().add(lblImportance);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCategory.setBounds(10, 255, 99, 26);
		frame.getContentPane().add(lblCategory);
		
		JLabel lblDueDateMonth = new JLabel("Due Date (Month)");
		lblDueDateMonth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDueDateMonth.setBounds(10, 320, 215, 13);
		frame.getContentPane().add(lblDueDateMonth);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldName.setBounds(10, 168, 215, 30);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldImportance = new JTextField();
		textFieldImportance.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldImportance.setBounds(9, 222, 216, 30);
		frame.getContentPane().add(textFieldImportance);
		textFieldImportance.setColumns(10);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldCategory.setBounds(10, 280, 215, 30);
		frame.getContentPane().add(textFieldCategory);
		textFieldCategory.setColumns(10);
		
		textFieldDueDateMonth = new JTextField();
		textFieldDueDateMonth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldDueDateMonth.setBounds(10, 343, 215, 30);
		frame.getContentPane().add(textFieldDueDateMonth);
		textFieldDueDateMonth.setColumns(10);
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add element with a default constructor
				//textFieldName.getText()
				//textFieldImportance.getText()
				//textFieldCategory.getText()
				//textFieldDueDate.getText()
				
				refresh();
				
			}
		});
		btnAddTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddTask.setBounds(10, 566, 153, 21);
		frame.getContentPane().add(btnAddTask);
		
		JButton btnRemoveTask = new JButton("Remove Task");
		btnRemoveTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRemoveTask.setBounds(10, 628, 153, 21);
		frame.getContentPane().add(btnRemoveTask);
		
		JButton btnUpdateTask = new JButton("Update Task");
		btnUpdateTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdateTask.setBounds(10, 597, 153, 21);
		frame.getContentPane().add(btnUpdateTask);
		
		JLabel lblNames = new JLabel("by Ali Altimimi and Alfonso De La Rosa");
		lblNames.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNames.setBounds(10, 46, 360, 37);
		frame.getContentPane().add(lblNames);
		
		JLabel lblTaskEditor = new JLabel("Task Editor");
		lblTaskEditor.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTaskEditor.setBounds(10, 119, 165, 20);
		frame.getContentPane().add(lblTaskEditor);
		
		JLabel lblTaskTable = new JLabel("Task Table");
		lblTaskTable.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTaskTable.setBounds(260, 93, 153, 37);
		frame.getContentPane().add(lblTaskTable);
		
		comboBoxSort = new JComboBox<String>();
		comboBoxSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		comboBoxSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxSort.setBounds(434, 94, 184, 26);
		frame.getContentPane().add(comboBoxSort);
		
		JLabel lblDueDateDay = new JLabel("Due Date (Day)");
		lblDueDateDay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDueDateDay.setBounds(10, 378, 215, 26);
		frame.getContentPane().add(lblDueDateDay);
		
		textFieldDueDateDay = new JTextField();
		textFieldDueDateDay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldDueDateDay.setColumns(10);
		textFieldDueDateDay.setBounds(10, 406, 215, 30);
		frame.getContentPane().add(textFieldDueDateDay);
		
		JLabel lblDueDateYear = new JLabel("Due Date (Year)");
		lblDueDateYear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDueDateYear.setBounds(10, 439, 215, 26);
		frame.getContentPane().add(lblDueDateYear);
		
		textFieldDueDateYear = new JTextField();
		textFieldDueDateYear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldDueDateYear.setColumns(10);
		textFieldDueDateYear.setBounds(10, 467, 215, 30);
		frame.getContentPane().add(textFieldDueDateYear);
		
		fillComboBox();
	}
}

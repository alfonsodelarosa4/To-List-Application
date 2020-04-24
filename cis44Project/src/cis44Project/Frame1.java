package cis44Project;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame1 {

	//create bag array class here
	public BagInterface<Task> taskList;
	
	private JFrame frame;
	
	private JComboBox<String> comboBoxSort;
	private JComboBox<String> comboBoxDatabase;
	
	private int selectedIndex;

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
	}
	
	/*
	 * 
	 * sorts array
	 * empties and refills table
	 */
	public void refresh()
	{
		sortList();
		refillTable();
	}
	
	public String toMonthString (int month)
	{
		switch(month)
		{
		case 1:
			return "January";
		case 2: 
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";		
		}
		return "January";
	}
	
	public int toMonthInt (String month)
	{
		switch(month)
		{
		case "January":
			return 1;
		case "February": 
			return 2;
		case "March":
			return 3;
		case "April":
			return 4;
		case "May":
			return 5;
		case "June":
			return 6;
		case "July":
			return 7;
		case "August":
			return 8;
		case "September":
			return 9;
		case "October":
			return 10;
		case "November":
			return 11;
		case "December":
			return 12;		
		}
		return 1;
	}
	
	//FIX: when sorting algorithms are created
	public void sortList()
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
		default : //sort by Name
			
			break;
		}
	}
	
	//FIX: when Task class is created
	public void addTask(String name, int importance, String catergory, String month, int day, int year)
	{
		
		
		Task newTask = new Task();
		
		//add element with a default constructor
		taskList.add(newTask);
	}
	
	public void removeSelectedTask()
	{
		if(selectedIndex == -1)
		{
		
		JOptionPane.showMessageDialog(null, "No index was selected");
		}
		else
		{
			taskList.removeEntry(selectedIndex);
		}
	}
	
	
	//TEST: when Task class is created
	public void refillList()
	{
		try {
			//count number of entries from table
			int tableEntries = 0;
			
			String query = "SELECT COUNT(*) FROM TaskTable";
			PreparedStatement pst = connection.prepareStatement(query);
					
			ResultSet rs = pst.executeQuery(query);
            while (rs.next()){
                tableEntries = rs.getInt(1);
            }
            
            
			for(int i = 0; i < tableEntries; i++)
			{
				query = "select * from TaskTable where Index='"+ Integer.toString(i) +"'";
			
				pst = connection.prepareStatement(query);
			
				rs = pst.executeQuery();
			
				while(rs.next())
				{
					//addTask(String name, int importance, String catergory, int month, int day, int year)
					addTask(rs.getString("Name"),Integer.parseInt(rs.getString("Importance")),rs.getString("Category"),rs.getString("Due Date: Month"),Integer.parseInt(rs.getString("Due Date: Day")),Integer.parseInt(rs.getString("Due Date: Year")));
				}
			
								
			}
			pst.close();
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//FIX: Something wrong
	public void refillTable()
	{
		//delete contents of table
		String query;
		PreparedStatement pst;
		try {
			query = "delete from TaskTable";
			
			pst = connection.prepareStatement(query);
			
			pst.execute();
						
			pst.close();
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		//add elements from bag array to table
		try {
			query = "insert into TaskTable (Index,Name,Importance,Category,Due Date: Month, Due Date: Day, Due Date: Year) values (?,?,?,?,?,?,?)";
			
			//FIX!!!!!!!!!!!!!!!!!!!!!!
			pst = connection.prepareStatement(query);
			for(int i = 0; i < taskList.getCurrentSize(); i++)
			{
				query = "insert into TaskTable (Index,Name,Importance,Category,Due Date: Month, Due Date: Day, Due Date: Year) values (?,?,?,?,?,?,?)";
				
				pst = connection.prepareStatement(query);
				
				Task current = taskList.retrieve(i);
				
				pst.setString(1, Integer.toString(i) );
				pst.setString(2, current.getName() );
				pst.setString(3, Integer.toString(current.getImportance()) );
				pst.setString(4, current.getCategory() );
				pst.setString(5, toMonthString(current.getDueDateMonth() ));
				pst.setString(6, Integer.toString(current.getDueDateDay()) );
				pst.setString(7, Integer.toString(current.getDueDateYear()) );
			
				pst.execute();
			}
			
			//JOptionPane.showMessageDialog(null, "Data Saved");
			
			
			pst.close();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	//FIX: Something wrong?
	public void fillComboBoxSort()
	{
		comboBoxSort.addItem("Sort by Name");
		comboBoxSort.addItem("Sort by Due Date");
		comboBoxSort.addItem("Sort by Category");
		comboBoxSort.addItem("Sort by Importance");
	}
	
	//FIX: something wrong?
	public void fillComboBoxDatabase()
	{
		comboBoxDatabase.addItem("ALFONSO'S");
		comboBoxDatabase.addItem("ALI'S");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//need this to connect to database
		//connection = sqliteConnection.dbConnector("ALFONSO'S");
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1127, 717);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Task Manager");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(10, 10, 221, 37);
		frame.getContentPane().add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					selectedIndex = table.getSelectedRow();
					
					Task current = taskList.retrieve(selectedIndex);
					
					
					textFieldName.setText(current.getName());
					textFieldImportance.setText(Integer.toString(current.getImportance()));
					textFieldCategory.setText(current.getCategory());
					textFieldDueDateMonth.setText(toMonthString(current.getDueDateMonth()));
					textFieldDueDateDay.setText(Integer.toString(current.getDueDateDay()));
					textFieldDueDateYear.setText(Integer.toString(current.getDueDateYear()));
					
					
				} catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
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
				
				String name = textFieldName.getText();
				int importance = Integer.parseInt(textFieldImportance.getText());
				String category = textFieldCategory.getText();
				String month = toMonthString(toMonthInt(textFieldDueDateMonth.getText()));
				int day = Integer.parseInt(textFieldDueDateDay.getText());
				int year = Integer.parseInt(textFieldDueDateYear.getText());
				
				addTask(name, importance, category, month, day, year);
				
				refresh();
				
			}
		});
		btnAddTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddTask.setBounds(10, 566, 153, 21);
		frame.getContentPane().add(btnAddTask);
		
		JButton btnRemoveTask = new JButton("Remove Task");
		btnRemoveTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove task
				removeSelectedTask();
				refresh();
				
				//change text fields
		
				textFieldName.setText("");
				textFieldImportance.setText("");
				textFieldCategory.setText("");
				textFieldDueDateMonth.setText("");
				textFieldDueDateDay.setText("");
				textFieldDueDateYear.setText("");
			}
		});
		btnRemoveTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRemoveTask.setBounds(10, 628, 153, 21);
		frame.getContentPane().add(btnRemoveTask);
		
		JButton btnUpdateTask = new JButton("Update Task");
		btnUpdateTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove selected task
				removeSelectedTask();
				refresh();
				
				//add new task
				String name = textFieldName.getText();
				int importance = Integer.parseInt(textFieldImportance.getText());
				String category = textFieldCategory.getText();
				String month = toMonthString(toMonthInt(textFieldDueDateMonth.getText()));
				int day = Integer.parseInt(textFieldDueDateDay.getText());
				int year = Integer.parseInt(textFieldDueDateYear.getText());
				
				addTask(name, importance, category, month, day, year);
				refresh();
			}
		});
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
		
		JLabel lblSort = new JLabel("Sort");
		lblSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSort.setBounds(434, 65, 119, 26);
		frame.getContentPane().add(lblSort);
		
		comboBoxDatabase = new JComboBox<String>();
		comboBoxDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connection = sqliteConnection.dbConnector((String) comboBoxDatabase.getSelectedItem());
				refillList();
				
				refresh();
			}
		});
		comboBoxDatabase.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxDatabase.setBounds(773, 93, 184, 26);
		frame.getContentPane().add(comboBoxDatabase);
		
		JLabel lblDatabase = new JLabel("Database");
		lblDatabase.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatabase.setBounds(773, 65, 119, 26);
		frame.getContentPane().add(lblDatabase);
		
		fillComboBoxSort();
		fillComboBoxDatabase();
		taskList = new ArrayBag<>();
		selectedIndex = -1;
	}
}

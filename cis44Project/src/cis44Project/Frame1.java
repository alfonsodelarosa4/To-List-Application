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

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

import java.util.*;

import java.time.*;


public class Frame1 {

	//create bag array class here
	public BagInterface<Task> taskList;
	public Task clicked;
	
	private JFrame frmTaskManager;
	
	private JComboBox<String> comboBoxSort;
	private JComboBox<String> comboBoxDatabase;
	private JComboBox<String> comboBoxDueDate;
	
	private int selectedIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frmTaskManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JTextField textFieldName;
	private JTextField textFieldImportance;
	private JTextField textFieldCategory;
	private JTextField textFieldDueDateMonth;
	private JTextField textFieldDueDateDay;
	private JTextField textFieldDueDateYear;
	private JTable table;
	
	
	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
		
	}
	
	// method that returns the string value of a month from the int parameter of the month
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
	
	// method that returns the int value of a month from the string parameter of the month
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
	
	//sorts the bag array based on the item selected from the combo box sort
	//FIX: when sorting algorithms are created
	public void sortBagArray()
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
	
	/*
	public void refreshTable()
	{
		try {
			//replacing * with 'columnName1' or even 'columnName2,columnName3'
			//* selects all of the columns
			String query = "select * from TaskTable";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			rs.close();
			pst.close();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	*/
	
	//adds a task object to the TaskList bag array
	public void addTask(String name, int importance, String category, int month, int day, int year)
	{
				
		Task newTask = new Task(name,importance, category, month, day, year);
		
		//add element with a default constructor
		taskList.add(newTask);
	}
	
	//removes a task object from the TaskList bag array
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
	
	//deletes elements from the GUI table
	public void deleteTableElements()
	{
		try {
			String query;
			PreparedStatement pst;
			query = "delete from TaskTable";
			
			pst = connection.prepareStatement(query);
			
			pst.execute();
						
			pst.close();
			
		} catch (Exception eb)
		{
			eb.printStackTrace();
		}
	}
	
	//copy elements from database to bag array
	public void copyDatabaseToBagArray()
	{
		System.out.println("Contents of TaskList before refillList(): " + taskList);
		try {
			//count number of entries from table
			/*
			 
			
			String query = "SELECT COUNT(*) FROM TaskTable";
			PreparedStatement pst = connection.prepareStatement(query);
					
			ResultSet rs = pst.executeQuery();
			
            while (rs.next()){
                tableEntries = rs.getInt(1);
            }
            */
           
			//empty bag array
			while(!taskList.isEmpty())
            	taskList.remove();
			
			//copy elements from database to bag array
			String query = "select * from TaskTable";
			PreparedStatement pst = connection.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
					//addTask(String name, int importance, String catergory, int month, int day, int year)
				addTask(rs.getString("Name"),Integer.parseInt(rs.getString("Importance")),rs.getString("Category"),toMonthInt(rs.getString("MonthDue")),Integer.parseInt(rs.getString("DayDue")),Integer.parseInt(rs.getString("YearDue")));
			}
			
			pst.close();
			
		} catch (Exception ea)
		{
			ea.printStackTrace();
		}
		
		System.out.println("Contents of TaskList after refillList(): " + taskList);
		refresh();
	}
	
	//copy elements from bag array to database
	public void copyBagArrayToDatabase()
	{
		if(!taskList.isEmpty())
		{
			for(int i = 1; i <= taskList.getCurrentSize(); i++)
			{
				
				try {
					String query = "insert into TaskTable (Name, Importance, Category, MonthDue, DayDue, YearDue) values (?, ?, ?, ?, ?, ?)";
				
				PreparedStatement pst = connection.prepareStatement(query);
				
				Task current = taskList.retrieve(i-1);
				
				pst.setString(1, current.getName() );
				pst.setInt(2, current.getImportance() );
				pst.setString(3, current.getCategory() );
				pst.setString(4, toMonthString(current.getDueDateMonth() ));
				pst.setInt(5, current.getDueDateDay() );
				pst.setInt(6, current.getDueDateYear() );
			
				pst.execute();
				pst.close();
				} catch (Exception ec)
				{	
					ec.printStackTrace();
				}
			}
		}
	}
	
	
	//copy elements from database to GUI table
	public void copyDatabaseToGUITable()
	{
		//copy elements from database to GUI table
		try {
			String query = "select * from TaskTable";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			rs.close();
			pst.close();
			
		} catch (Exception ef)
		{
			ef.printStackTrace();
		}
	}
	
	//refresh calls several functions to update the bag array, database, and the GUI
	public void refresh()
	{
		//sorts elements in bag array
		sortBagArray();
		
		//delete contents of GUI table
		deleteTableElements();		

		//copy elements from bag array to database
		copyBagArrayToDatabase();
		
		//copy elements from database to GUI table
		copyDatabaseToGUITable();
		System.out.println("Contents of TaskList after refresh(): " + taskList);
	}
	
	// the following three methods fill the contents of the combo boxes in the GUI
	public void fillComboBoxSort()
	{
		comboBoxSort.addItem("Sort by Name");
		comboBoxSort.addItem("Sort by Due Date");
		comboBoxSort.addItem("Sort by Category");
		comboBoxSort.addItem("Sort by Importance");
	}
	
	
	public void fillComboBoxDatabase()
	{
		comboBoxDatabase.addItem("ALFONSO'S");
		comboBoxDatabase.addItem("ALI'S");
	}
	
	public void fillComboBoxDueDate()
	{
		comboBoxDueDate.addItem("Today");
		comboBoxDueDate.addItem("Tomorrow");
		comboBoxDueDate.addItem("2 Days Later");
		comboBoxDueDate.addItem("3 Days Later");
		comboBoxDueDate.addItem("Next Week");
		comboBoxDueDate.addItem("Next Month");
		comboBoxDueDate.addItem("Next Year");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//need this to connect to database
		connection = sqliteConnection.dbConnector("ALFONSO'S");
		
		frmTaskManager = new JFrame();
		frmTaskManager.getContentPane().setBackground(new Color(0, 102, 153));
		frmTaskManager.setTitle("Task Manager");
		frmTaskManager.setBounds(100, 100, 921, 691);
		frmTaskManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTaskManager.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Task Manager");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblTitle.setBounds(10, 10, 221, 37);
		frmTaskManager.getContentPane().add(lblTitle);
		
		JLabel lblName = new JLabel("Task Name:");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblName.setBounds(10, 133, 119, 13);
		frmTaskManager.getContentPane().add(lblName);
		
		JLabel lblImportance = new JLabel("Importance (0 - 2):");
		lblImportance.setForeground(new Color(255, 255, 255));
		lblImportance.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblImportance.setBounds(10, 184, 215, 26);
		frmTaskManager.getContentPane().add(lblImportance);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(new Color(255, 255, 255));
		lblCategory.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCategory.setBounds(11, 237, 99, 26);
		frmTaskManager.getContentPane().add(lblCategory);
		
		JLabel lblDueDateMonth = new JLabel("Month (text):");
		lblDueDateMonth.setForeground(new Color(255, 255, 255));
		lblDueDateMonth.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDueDateMonth.setBounds(10, 385, 215, 26);
		frmTaskManager.getContentPane().add(lblDueDateMonth);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textFieldName.setBounds(10, 156, 221, 30);
		frmTaskManager.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldImportance = new JTextField();
		textFieldImportance.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textFieldImportance.setBounds(10, 210, 221, 30);
		frmTaskManager.getContentPane().add(textFieldImportance);
		textFieldImportance.setColumns(10);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textFieldCategory.setBounds(10, 263, 221, 30);
		frmTaskManager.getContentPane().add(textFieldCategory);
		textFieldCategory.setColumns(10);
		
		textFieldDueDateMonth = new JTextField();
		textFieldDueDateMonth.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textFieldDueDateMonth.setBounds(10, 414, 221, 30);
		frmTaskManager.getContentPane().add(textFieldDueDateMonth);
		textFieldDueDateMonth.setColumns(10);
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.setBackground(Color.WHITE);
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = textFieldName.getText();
				int importance = Integer.parseInt(textFieldImportance.getText());
				String category = textFieldCategory.getText();
				int month = toMonthInt(textFieldDueDateMonth.getText());
				int day = Integer.parseInt(textFieldDueDateDay.getText());
				int year = Integer.parseInt(textFieldDueDateYear.getText());
				
				addTask(name, importance, category, month, day, year);
				
				refresh();
				
			}
		});
		btnAddTask.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnAddTask.setBounds(37, 509, 165, 34);
		frmTaskManager.getContentPane().add(btnAddTask);
		
		JButton btnRemoveTask = new JButton("Remove Task");
		btnRemoveTask.setBackground(Color.WHITE);
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
		btnRemoveTask.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnRemoveTask.setBounds(37, 597, 165, 34);
		frmTaskManager.getContentPane().add(btnRemoveTask);
		
		JButton btnUpdateTask = new JButton("Update Task");
		btnUpdateTask.setBackground(Color.WHITE);
		btnUpdateTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove selected task
				removeSelectedTask();
				refresh();
				
				//add new task
				String name = textFieldName.getText();
				int importance = Integer.parseInt(textFieldImportance.getText());
				String category = textFieldCategory.getText();
				int month = toMonthInt(textFieldDueDateMonth.getText());
				int day = Integer.parseInt(textFieldDueDateDay.getText());
				int year = Integer.parseInt(textFieldDueDateYear.getText());
				
				addTask(name, importance, category, month, day, year);
				refresh();
			}
		});
		btnUpdateTask.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnUpdateTask.setBounds(37, 553, 165, 34);
		frmTaskManager.getContentPane().add(btnUpdateTask);
		
		JLabel lblNames = new JLabel("by Ali Altimimi and Alfonso De La Rosa");
		lblNames.setForeground(new Color(255, 255, 255));
		lblNames.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNames.setBounds(10, 46, 360, 37);
		frmTaskManager.getContentPane().add(lblNames);
		
		JLabel lblTaskEditor = new JLabel("Task Editor");
		lblTaskEditor.setForeground(new Color(255, 255, 255));
		lblTaskEditor.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTaskEditor.setBounds(10, 101, 165, 20);
		frmTaskManager.getContentPane().add(lblTaskEditor);
		
		JLabel lblTaskTable = new JLabel("Task Table");
		lblTaskTable.setForeground(new Color(255, 255, 255));
		lblTaskTable.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTaskTable.setBounds(260, 93, 153, 37);
		frmTaskManager.getContentPane().add(lblTaskTable);
		
		comboBoxSort = new JComboBox<String>();
		fillComboBoxSort();
		comboBoxSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				refresh();
				
			}
		});
		comboBoxSort.setFont(new Font("Segoe UI", Font.BOLD, 18));
		comboBoxSort.setBounds(403, 94, 184, 26);
		frmTaskManager.getContentPane().add(comboBoxSort);
		
		JLabel lblDueDateDay = new JLabel("Day (1-31):");
		lblDueDateDay.setForeground(new Color(255, 255, 255));
		lblDueDateDay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDueDateDay.setBounds(10, 441, 91, 26);
		frmTaskManager.getContentPane().add(lblDueDateDay);
		
		textFieldDueDateDay = new JTextField();
		textFieldDueDateDay.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textFieldDueDateDay.setColumns(10);
		textFieldDueDateDay.setBounds(10, 469, 91, 30);
		frmTaskManager.getContentPane().add(textFieldDueDateDay);
		
		JLabel lblDueDateYear = new JLabel("Year:");
		lblDueDateYear.setForeground(new Color(255, 255, 255));
		lblDueDateYear.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDueDateYear.setBounds(131, 441, 91, 26);
		frmTaskManager.getContentPane().add(lblDueDateYear);
		
		textFieldDueDateYear = new JTextField();
		textFieldDueDateYear.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textFieldDueDateYear.setColumns(10);
		textFieldDueDateYear.setBounds(131, 469, 100, 30);
		frmTaskManager.getContentPane().add(textFieldDueDateYear);
		
		JLabel lblSort = new JLabel("Sort");
		lblSort.setForeground(new Color(255, 255, 255));
		lblSort.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSort.setBounds(403, 65, 119, 26);
		frmTaskManager.getContentPane().add(lblSort);
		
		comboBoxDatabase = new JComboBox<String>();
		fillComboBoxDatabase();
		
		comboBoxDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteTableElements();
				connection = sqliteConnection.dbConnector((String) comboBoxDatabase.getSelectedItem());
				copyDatabaseToBagArray();
				refresh();
			}
		});
		
		comboBoxDatabase.setFont(new Font("Segoe UI", Font.BOLD, 18));
		comboBoxDatabase.setBounds(716, 93, 184, 26);
		frmTaskManager.getContentPane().add(comboBoxDatabase);
		
		JLabel lblDatabase = new JLabel("Database");
		lblDatabase.setForeground(new Color(255, 255, 255));
		lblDatabase.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDatabase.setBounds(716, 65, 119, 26);
		frmTaskManager.getContentPane().add(lblDatabase);
		
		JLabel lblDueDateOf = new JLabel("Due Date of Task");
		lblDueDateOf.setForeground(new Color(255, 255, 255));
		lblDueDateOf.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		lblDueDateOf.setBounds(10, 303, 221, 26);
		frmTaskManager.getContentPane().add(lblDueDateOf);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(260, 133, 640, 509);
		frmTaskManager.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBoxDueDate = new JComboBox<String>();
		comboBoxDueDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDate dueDate = LocalDate.now();
				System.out.println((String) comboBoxDueDate.getSelectedItem());
				
				switch((String) comboBoxDueDate.getSelectedItem())
				{
				case "Today":
					
					break;
				case "Tomorrow":
					dueDate = dueDate.plusDays(1);
					break;
				case "2 Days Later":
					dueDate = dueDate.plusDays(2);
					break;
				case "3 Days Later":
					dueDate = dueDate.plusDays(3);
					break;
				case "Next Week":
					dueDate = dueDate.plusDays(7);
					break;
				case "Next Month":
					dueDate = dueDate.plusMonths(1);
					dueDate = dueDate.withDayOfMonth(1);
					break;
				case "Next Year":
					dueDate = dueDate.plusYears(1);
					dueDate = dueDate.withMonth(1);
					dueDate = dueDate.withDayOfMonth(1);
					break;
				}
				
				textFieldDueDateMonth.setText(toMonthString(dueDate.getMonthValue()));
				textFieldDueDateDay.setText(Integer.toString(dueDate.getDayOfMonth()));
				textFieldDueDateYear.setText(Integer.toString(dueDate.getYear()));
				
			}
		});
		comboBoxDueDate.setBounds(10, 351, 221, 32);
		frmTaskManager.getContentPane().add(comboBoxDueDate);
		
		fillComboBoxDueDate();
		
		JLabel lblSelectADue = new JLabel("Select a date or type below:");
		lblSelectADue.setForeground(Color.WHITE);
		lblSelectADue.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSelectADue.setBounds(10, 326, 221, 26);
		frmTaskManager.getContentPane().add(lblSelectADue);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					
					selectedIndex = table.getSelectedRow();
					System.out.println("Row selected = " + selectedIndex);
					
					clicked = taskList.retrieve(selectedIndex);
					System.out.println("Selected task =" + clicked);
					
					textFieldName.setText(clicked.getName());
					textFieldImportance.setText(Integer.toString(clicked.getImportance()));
					textFieldCategory.setText(clicked.getCategory());
					textFieldDueDateMonth.setText(toMonthString(clicked.getDueDateMonth()));
					textFieldDueDateDay.setText(Integer.toString(clicked.getDueDateDay()));
					textFieldDueDateYear.setText(Integer.toString(clicked.getDueDateYear()));
					
				} catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		taskList = new ArrayBag<>();
		selectedIndex = -1;
		copyDatabaseToBagArray();
		
		
	}
}

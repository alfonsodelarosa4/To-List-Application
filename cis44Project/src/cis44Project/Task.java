package cis44Project;

public class Task implements Comparable<Task> {
	// 
	
	
	private String name;
	private String category;
	private int month;
	private int day;
	private int year;
	private int importance;
	private static String sortFactor = "name";
	
	private final int MAX_IMPORTANCE = 2;
	
	private final int MIN_IMPORTANCE = 0;
	
	public Task()
	{
		setName("");
		setCategory("");
		setDueDateMonth(0);
		setDueDateDay(0);
	}
	
	public Task(String name, int importance, String category, int month, int day, int year)
	{
		setName(name);
		setCategory(category);
		setDueDateMonth(month);
		setDueDateDay(day);
		setDueDateYear(year);
		setImportance(importance);
	}
	
	public void setSortFactor(String sort)
	{
		switch(sort)
		{
		case "name":
			sortFactor = sort;
		break;
			
		case "category":
			sortFactor = sort;
		break;
		
		case "month":
			sortFactor = sort;
		break;
		
		case "day":
			sortFactor = sort;
		break;
		
		case "year":
			sortFactor = sort;
		break;
		
		case "importance":
			sortFactor = sort;
		break;
			
		default:
			sortFactor = "name";
		break;
		}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setDueDateMonth(int month) 
	{
		this.month = month;
		if(this.month < 1)
			this.month = 1;
		if(this.month > 12)
			this.month = 12;
	}
	
	public int getDueDateMonth()
	{
		return month;
	}
	
	public void setDueDateDay(int day)
	{
		this.day = day;
		if(this.day < 0)
			this.day = 0;
		if(this.day > 31)
			this.day = 31;
	}
	
	public int getDueDateDay()
	{
		return day;
	}
	
	public void setDueDateYear(int year)
	{
		this.year = year;
		if(this.year < 0)
			this.year = 0;
	}
	
	public int getDueDateYear()
	{
		return year;
	}
	
	public void setImportance(int importance)
	{
		this.importance = importance;
		if(this.importance > MAX_IMPORTANCE)
			this.importance = MAX_IMPORTANCE;
		
		if(this.importance < MIN_IMPORTANCE)
			this.importance = MIN_IMPORTANCE;
	}
	
	public int getImportance()
	{
		return importance;
	}
	
	public int compareTo(Task obj)
    //-ve this < obj; 0 this == obj; +ve this > onj
	{
		Task t = null;
		try
		{
			t = (Task) obj;
		}
		catch(Exception e)
		{
			System.out.println("Passed class is not Task");
			System.exit(0);
		}
		
		switch(sortFactor)
		{
		case "name":
			return name.compareTo(t.name);
			
		case "category":
			return category.compareTo(t.category);
		
		case "month":
			return Integer.compare(month, t.month);
		
		case "day":
			return Integer.compare(day, t.day);
		
		case "year":
			return Integer.compare(year, t.year);
		
		case "importance":
			return Integer.compare(importance, t.importance);
			
		default:
			return name.compareTo(t.name);
		}
		
	}
	
	public String toString()
	{
		return "Task " + name +" of importance " + importance + " in category " + category + " is due " + month + " " + day +", " + year;
	}
	
	
}

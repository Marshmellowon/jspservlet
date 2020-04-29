package example;

public class EmpDTO {
	String id;
	String name;
	int salary;
	String depart;
	
	public EmpDTO () {}
	public EmpDTO(String id, String name, int salary, String depart) { 
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.depart = depart;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	
	
	
	

}

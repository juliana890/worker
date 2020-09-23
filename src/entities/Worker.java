package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {

	private String name;
	private WorkerLevel level;
	private Double baseSalary;
	//Associa��o da classe Department
	private Department department;
	//Trabalhador possui v�rios contratos, somente inicia a lista vazia
	private List<HourContract> contracts = new ArrayList<>();
	
	//Construtor padr�o
	public Worker() {}

	//Geramos o construtor sem a lista, pq quando voc� tem um atributo que possui muitos ele n�o � inclu�do no construtor
	public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public void setLevel(WorkerLevel level) {
		this.level = level;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}
	
	//M�todo para adicionar contratos na lista do trabalhador
	public void addConstract(HourContract contract) {
		contracts.add(contract);
	}
	
	//M�todo para remover contratos na lista do trabalhador
	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}
	
	//M�todo para verificar quanto o trabalhador ganhou em determinado m�s e ano
	public double income(int year, int month){
		double sum = baseSalary;
		Calendar cal = Calendar.getInstance();
		for(HourContract c: contracts) {
			//Pegamos a data do contrato e setamos no objeto Calendar, assim definimos a data no nosso calend�rio
			cal.setTime(c.getDate());
			//Dessa forma extraimos o ano do contrato c no for each
			int c_year = cal.get(Calendar.YEAR);
			//Dessa forma extraimos o m�s do contrato c no for each
			int c_month = 1 + cal.get(Calendar.MONTH);
			
			//Verificando se o contrato � do mesmo m�s e ano para acrescentar na soma
			if(year == c_year && month == c_month) {
				sum += c.totalValue();
			}
		}
		
		return sum;
	}
}

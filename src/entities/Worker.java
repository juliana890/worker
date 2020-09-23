package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {

	private String name;
	private WorkerLevel level;
	private Double baseSalary;
	//Associação da classe Department
	private Department department;
	//Trabalhador possui vários contratos, somente inicia a lista vazia
	private List<HourContract> contracts = new ArrayList<>();
	
	//Construtor padrão
	public Worker() {}

	//Geramos o construtor sem a lista, pq quando você tem um atributo que possui muitos ele não é incluído no construtor
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
	
	//Método para adicionar contratos na lista do trabalhador
	public void addConstract(HourContract contract) {
		contracts.add(contract);
	}
	
	//Método para remover contratos na lista do trabalhador
	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}
	
	//Método para verificar quanto o trabalhador ganhou em determinado mês e ano
	public double income(int year, int month){
		double sum = baseSalary;
		Calendar cal = Calendar.getInstance();
		for(HourContract c: contracts) {
			//Pegamos a data do contrato e setamos no objeto Calendar, assim definimos a data no nosso calendário
			cal.setTime(c.getDate());
			//Dessa forma extraimos o ano do contrato c no for each
			int c_year = cal.get(Calendar.YEAR);
			//Dessa forma extraimos o mês do contrato c no for each
			int c_month = 1 + cal.get(Calendar.MONTH);
			
			//Verificando se o contrato é do mesmo mês e ano para acrescentar na soma
			if(year == c_year && month == c_month) {
				sum += c.totalValue();
			}
		}
		
		return sum;
	}
}

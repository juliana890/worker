package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program_Worker {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Criamos um SimpleDateFormat para receber a data nesse formato
		
		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String workerName = sc.nextLine();
		System.out.print("Level: ");
		String workerLevel = sc.nextLine();
		System.out.print("Base Salary: ");
		double baseSalary = sc.nextDouble();
		
		//Instânciando a classe Worker com as variáveis
		// WorkerLevel.valueOf(workerLevel) <-> Criando uma instância do tipo enumerada WorkerLevel no valor equivalente ao que foi digitado
		//Departamento é um outro objeto do tipo Department, o trabalhador faz parte de um departamento
		//new Department(departmentName) <-> Instânciamos um departamento com a variável departmentName que estará associado ao trabalhador
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));
		
		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();
		
		for(int i = 1; i <=n; i++){
			System.out.println("Enter contract #" + i + " data");
			System.out.print("Date (DD/MM/YYYY): ");
			Date contractDate = sdf.parse(sc.next());//Tranformando o que o usário digita na nossa data formatada
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			//Instânciando o contrato
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			//Para associar esse contrato com o trabalhador fazemos o seguinte:
			worker.addConstract(contract);
		}
		
		System.out.print("\nEnter month and year to calculate income (MM/YYYY): ");
		String monthAndYear = sc.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2)); //Usamos o substring para extrair o mês
		int year = Integer.parseInt(monthAndYear.substring(3)); //Usamos o substring para extrair o ano pegando da posição 3 em diante
		
		//Imprimindo os dados
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName()); //Assim acessamos o Department pela classe worker e buscamos o nome do departamento
		System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));
		
		
		sc.close();

	}

}

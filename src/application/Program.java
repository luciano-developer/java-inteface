package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println("Entre com os dados do aluguel:");
		System.out.print("Model do carro:");
		String model = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy HH:mm): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Retorno (dd/MM/yyyy HH:mm): ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(model));
		
		System.out.print("Entre com o preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rs = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rs.processInvoice(cr);
		
		System.out.println();
		System.out.println("Contrato: ");
		System.out.print("Pagamento básico: "+ String.format("%.2f%n", cr.getInvoice().getBasicPayment()));
		System.out.print("Taxa: "+ String.format("%.2f%n", cr.getInvoice().getTax()));
		System.out.print("Total a pagar: "+ String.format("%.2f%n", cr.getInvoice().getTotalPayment()));
		
		sc.close();
	}

}

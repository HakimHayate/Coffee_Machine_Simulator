package coffee_machine;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Machine machine = new Machine(400, 540, 120, 9, 550);
		Scanner scanner = new Scanner(System.in);
		
		while (machine.getState() != MachineState.OFF) {
			machine.excuting(scanner.nextLine());
		}
	}
}

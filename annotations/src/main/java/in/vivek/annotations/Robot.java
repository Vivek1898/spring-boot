package in.vivek.annotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Robot {
	
	// Inject the Chip class
	// di f
    @Autowired
	private Chip chip;
    
	public Robot() {
		System.out.println("Robot constructor");
	}
	
	public void  doWork () {
       String chipType = chip.ChipType();
       
		if (chipType.equals("128-BYTE EEPROM")) {
			System.out.println("Robot is working");
		} else {
			System.out.println("Robot is not working");
		}
    }

}

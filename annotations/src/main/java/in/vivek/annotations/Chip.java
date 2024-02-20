package in.vivek.annotations;
import org.springframework.stereotype.Component;	

@Component
public class Chip {
	public Chip() {
		System.out.println("Chip constructor");
	}
	public String ChipType() {
		return "128-BYTE EEPROM";
	}
	
}
 
//package car.vivek;
// @Witfout interface
//public class car extends Engine {
//	
////	private IEngine engine;
//	public void drive() {
//		int status = super.start();
//		if (status >= 1) {
//			System.out.println("Journey Started..");
//		} else {
//			System.out.println("Engine Trouble..");
//		}
//	}
//}

package car.vivek;

public class car {

	//Field Injection
//     IEngine eng;
     
     private IEngine eng;

    //Constructor Injection 
//    public car(IEngine eng) {
//        this.eng = eng;
//    }
    
    //Setter Injection
	public void setEngine(IEngine eng) {
		this.eng = eng;
	}
    
    public void drive() {
        int status = eng.start();
        if (status >= 1) {
            System.out.println("Journey Started..");
        } else {
            System.out.println("Engine Trouble..");
        }
    }
}






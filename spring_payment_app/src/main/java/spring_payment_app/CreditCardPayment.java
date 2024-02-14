package spring_payment_app;



	public class CreditCardPayment implements IPayment {

		@Override
		public boolean processPayment(double billAmt) {
		
			System.out.println("Credit Card Payment Processed..");
			return true;
		}
	}




package th.ac.ru.uthai.karfai.main;

import java.text.DecimalFormat;

import th.ac.ru.uthai.karfai.model.Data;

public class Calculator {
	public static double billCal(double wat) {
		double bill = 0;
		double tmp = 0;

		if (wat < 50) {
			bill = 0;
		} else if (wat < 150) {
			if (wat > 100) {
				tmp = wat - 100;
				wat = wat - tmp;
				bill = bill + tmp * 2.2734;
			}
			if (wat > 35) {
				tmp = wat - 35;
				wat = wat - tmp;
				bill = bill + tmp * 2.1800;
			}
			if (wat > 25) {
				tmp = wat - 25;
				wat = wat - tmp;
				bill = bill + tmp * 1.7968;
			}
			if (wat > 16) {
				tmp = wat - 16;
				wat = wat - tmp;
				bill = bill + tmp * 1.5445;
			}
			if (wat > 6) {
				tmp = wat - 6;
				wat = wat - tmp;
				bill = bill + tmp * 1.3576;
			}
			bill = bill + 8.19;
		} else if(wat>150){
			if(wat>400){
				tmp = wat - 400;
				wat = wat - tmp;
				bill = bill + tmp * 2.9780;
			}
			if(wat>150){
				tmp = wat - 150;
				wat = wat - tmp;
				bill = bill + tmp * 2.7781;
			}
			tmp = wat;
			bill = bill + tmp * 1.8047;
			bill = bill + 40.90;

		}
		bill/=1000.0;
		
			return roundTwoDecimals(bill);
	}
	
	
	public static double watCal(Data data){
		double totalWat = 0;
		double time = ((data.getHour() + data.getMinute()/60.0)*(data.getDay()));// hour
		//������ �� �� �Ѻ �ҷ������
		totalWat = time  * data.getAmount()*data.getWat();
		return roundTwoDecimals(totalWat);
	}
	
	public static Double roundTwoDecimals(double d) {
        DecimalFormat twoDecimals = new DecimalFormat("#.##");
        return Double.valueOf(twoDecimals.format(d));
	}

}

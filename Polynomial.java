package lab01;

public class Polynomial {
		double [] coef;
		
	public Polynomial(){
		this.coef = new double [0];
	}
	public Polynomial(double [] a){
			this.coef = a;
		}
	public Polynomial add (Polynomial a) {
		int max = 0; 
		if(this.coef.length < a.coef.length) {
			max = a.coef.length;
		}
		else {
			max = this.coef.length;
		}
		
		double [] b = new double [max];
		
		int i = 0;
		while (i < this.coef.length) {
			b[i] = b[i] + this.coef[i];
			i++;
		}
		
		int j = 0;
		while (j < a.coef.length){
			b[j] = b[j] + a.coef[j]; 
			j++;
		}
		Polynomial c = new Polynomial(b);
		return c;
	}
	public double evaluate (double x){
		if(this.coef.length == 1) {
			return this.coef[0];
		}
		int l = this.coef.length;
		double [] a = new double [l];
		for (int i = 0; i < l; i++){
			a[i] = this.coef[i] * Math.pow(x, i);
		}
		double sum = 0.0;
		for (int j = 0; j < l; j++) {
			sum = sum + a[j];
		}
		return sum;
	}
	
	public boolean hasRoot (double a){
		if (this.evaluate(a) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}

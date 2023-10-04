package b07lab1;

import java.io.FileNotFoundException;

public class Driver {
public static void main(String [] args) throws FileNotFoundException {

double [] coef_1 = {1.0,2.0,-3.0};
double [] coef_2 = {4.0,5.0,6.0};
int [] expo_1 = {0,1,2};
int [] expo_2 = {1,3,4};
Polynomial poly_1 = new Polynomial(coef_1, expo_1);
Polynomial poly_2 = new Polynomial(coef_2, expo_2);
Polynomial poly_3 = poly_1.multiply(poly_2);
for (int i = 0; i < poly_3.coef.length; i++){
    System.out.println(poly_3.expo[i]);
    System.out.println(poly_3.coef[i]);

}

poly_1.saveToFile("test1");

}
}
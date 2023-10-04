package b07lab1;

import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;


public class Polynomial {
		double [] coef;
		int [] expo;
		
	public Polynomial(){
		this.coef = new double [0];
		this.expo = new int [0];
	}
	public Polynomial(double [] a, int [] b){
			this.coef = a;
			this.expo = b;
		}
	
	public Polynomial add (Polynomial a) {
		int max = a.expo.length + this.expo.length; 
		for (int i = 0; i<a.expo.length; i++) {
			int temp = a.expo[i];
			for (int j = 0; j<this.expo.length; j++) {
				if (this.expo[j] == temp) {
					max = max - 1;
				}
			}
		}

		double [] new_coef = new double [max];
		int [] new_expo = new int [max];
		int index = 0;

		for(int i = 0; i<a.expo.length; i++){
			int temp_expo = a.expo[i];
			double temp_coef = a.coef[i];
			new_expo[i] = temp_expo;
			new_coef[i] = temp_coef;
			index++;
		}

		for(int i = 0; i<this.expo.length; i++){
			int temp_expo = this.expo[i];
			double temp_coef = this.coef[i];
			boolean in = false;
			for(int j = 0; j<new_expo.length; j++){
				if(temp_expo == new_expo[j]){
					new_coef[j] = new_coef[j] + temp_coef;
					in = true;
				}
			}
			if(!in){
				new_expo[index] = temp_expo;
				new_coef[index] = temp_coef;
				index++;
			}
		}
		Polynomial poly = new Polynomial(new_coef, new_expo);
		return poly;
	}
	
	public double evaluate (double x){
		if(this.coef.length == 1) {
			return this.coef[0] * Math.pow(x, this.expo[0]);
		}
		int l = this.coef.length;
		double [] a = new double [l];
		for (int i = 0; i < l; i++){
			a[i] = this.coef[i] * Math.pow(x, this.expo[i]);
		}
		double sum = 0.0;
		for (int j = 0; j < l; j++) {
			sum = sum + a[j];
		}
		return sum;
	}
	
	public boolean hasRoot (double a){
		if(this.evaluate(a) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Polynomial multiply (Polynomial x) {
		int max = this.coef.length * x.coef.length;
		int [] test_expo = new int [max];
		int [] used = new int [max];
		int count = max;

		for (int i=0; i<this.coef.length; i++){
			int temp_expo = this.expo[i];
			for(int j=0; j<x.coef.length;j++){
				test_expo[i+j] = temp_expo + x.expo[j];
			}
		}
		for (int i=0; i<max; i++){
			used[i] = -1;
		}

		for(int i = 0; i<test_expo.length; i++){
			int temp_expo = test_expo[i];
			boolean in = false;
			for(int j=0; j<used.length; j++){
				if(temp_expo == used[j]){
					in=true;
					count--;
				}
			}
			if(!in){
				used[i] = temp_expo;
			}
		}
		int [] new_expo = new int[count];
		double [] new_coef = new double[count];

		int index = 0;
		for (int i=0; i< this.coef.length; i++){
			int temp_expo = this.expo[i];
			double temp_coef = this.coef[i];
			for (int j=0; j<x.coef.length; j++) {
				int expo = temp_expo + x.expo[j];
				double coef = temp_coef * x.coef[j];
				boolean in = false;
				for(int k=0; k<new_expo.length; k++) {
					if(expo == new_expo[k]) {
						new_coef[k] = new_coef[k] + coef;
						in = true;
					}
				}
				if(!in){
					new_expo[index] = expo;
					new_coef[index] = coef;
					index++;
				}
			}
		}
		Polynomial result = new Polynomial(new_coef, new_expo);
		return result;
	}

	public void saveToFile (String file_name) throws FileNotFoundException {
		PrintStream ps = new PrintStream(file_name);
		ps.print(this.coef[0]);
		ps.print("x");
		if(this.expo[0] != 0){
			ps.print(this.expo[0]);
		}
		for(int i=1; i<this.coef.length; i++){
			if(this.coef[i] > 0){
				ps.print("+");
				ps.print(this.coef[i]);
			}
			else{
				ps.print(this.coef[i]);
			}

			if(this.expo[i] != 0){
				ps.print("x");
				ps.print(this.expo[i]);
			}
		}
	}
	
	
}
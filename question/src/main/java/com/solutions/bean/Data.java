package com.solutions.bean;

import java.util.Arrays;


public class Data {
 private int max_weight;
 private int[] weights;
 private int[] values;
 
 public Data(int max_weight,int length){
	 this.max_weight=max_weight;
	 this.weights=new int[length];
	 this.values=new int[length];
 }

public int getMax_weight() {
	return max_weight;
}

public void setMax_weight(int max_weight) {
	this.max_weight = max_weight;
}

public int[] getWeights() {
	return weights;
}

public void setWeights(int[] weights) {
	this.weights = weights;
}

public int[] getValues() {
	return values;
}

public void setValues(int[] values) {
	this.values = values;
}
 


public String toString(){
	StringBuffer data = new StringBuffer();
	data.append("Max Weight: ").append(this.max_weight);
	data.append("Weights: ").append(Arrays.asList(this.weights).toString());
	data.append("Values: ").append(Arrays.asList(this.values).toString());
	return data.toString();
}
 
 
}

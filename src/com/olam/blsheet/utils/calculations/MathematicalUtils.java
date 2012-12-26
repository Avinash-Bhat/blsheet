package com.olam.blsheet.utils.calculations;

public class MathematicalUtils {
	
	public static float FloatDivision(float total,int noMembers){
		float result = 0f;
		try{
			if(total!=0 && noMembers!=0 ){
				result = total/noMembers;
				result = Round(result,2);
				System.out.println("Perhead Share : "+result);
			}
		}catch (NumberFormatException ne) {
			ne.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static float Round(float value, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  value = value * p;
		  float tmp = Math.round(value);
		  return (float)tmp/p;
		  }
	public static void main(String[] args) {
		  float num = 6f;
		  float num2 =27.5f;
		  float num3 = FloatDivision( num2,6);
		  System.out.println(num3);
		  
		  float round = Round(num,2);
		  System.out.println("Rounded data: " + round);
		  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;


import java.text.NumberFormat;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class NumberToWordsConverter {
    
    public static final String[] units={ "","One","Two","Three","Four","Five","Six",
        "Seven","Eigth","Nine","Ten","Eleven","Twelve","Thirteen", "Fourteen","Fifteen","Sixteen",
        "Seventeen","Eighteen","Ninetee"};
    
    public static final String[] tens={
        "",                     // 0
        "",                     // 1
        "Twenty",               // 2
        "Thrity",               // 3
        "Fourty",               // 4
        "Fifty",                // 5
        "Sixty",                // 6
        "Seventy",              // 7
        "Eighty",               // 8
        "Ninety",               // 9
    };
    
    public static String convert(final int n){
        
            if(n < 0)
            {
                return "minus" + convert (-n);
            }
            if(n < 20)
            {
                return units[n];
            }
            
            if(n<100)
            {
                return tens[n/10]+((n % 10 !=0)?" ":" ")+units[n%10];
            }
            
            if(n<1000)
            {
                return units[n/100]+" Hundred "+((n % 100 !=0)?" ":" ")+ convert (n%100);
            }
            
            if(n<100000)
            {
                return convert(n/1000)+" Thousand "+((n % 10000 !=0)?" ":" ")+ convert (n%1000);
            }
            
            if(n<10000000)
            {
                return convert(n/100000) + " Hundred Thousand " + ((n % 100000 !=0)?" ":" ")+ convert (n%100000);
            } 
            
            return convert(n/1000000) + " Million " + ((n % 1000000 !=0)?" ":" ")+ convert (n%1000000);
            
    }
    
    
    public static void main(final String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter The Amount : ");
        int n=sc.nextInt();
         
        
        System.out.println(convert(n)+" Only");
        
        
        
    }
    
 
    
}

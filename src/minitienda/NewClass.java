package minitienda;

import java.util.Scanner;

public class NewClass {
    
    public static void main(String[] args) {
        
        int opcion = 1;
        Scanner teclado = new Scanner(System.in);
        
        while(opcion == 1){
            
            System.out.println("¿Desea porducir otro ticket?");
            System.out.println("1.SI");
            System.out.println("2.NO");
            
            opcion = teclado.nextInt();
            
        }
    
    }
    
}

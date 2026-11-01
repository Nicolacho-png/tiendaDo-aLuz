package minitienda;

import java.util.Scanner;
import minitienda.conexionBD.OperacionesBD;

public class MiNiTienda {
    
    public static final double DESCUENTO_MIEMBRO_VIP = 0.15;  
    
    public static double calculadorDescuento(boolean booleanVip, double descuento, double subTotal, double total){
       
        if(booleanVip == true){
        
            descuento = 0.0;
            descuento = subTotal * DESCUENTO_MIEMBRO_VIP;
            total = subTotal - descuento;
            System.out.println("\n [info] ¡El descuento VIP se ha aplicado con exito! ");
           
        }else if(booleanVip == false){
            
            total = subTotal;
            System.out.println("\n [info] El cliente no cuenta con membresia. No aplica descuento.");
            
        }else{
            
            total = subTotal;
            System.out.println("[ADVERTENCIA] Opcion invalida. Se cobrara la tarifa normal.");
            
        }
      
        return total;
    }
    
    public static boolean validacionObsequios(int cantProductos){
        
        boolean validacion;
        
        if(cantProductos >= 5){
            
            validacion = true;
            
            
        }else{
        
            validacion = false;
            
            
        }
        
        return validacion;
    }
    
    public static String seleccionObsequio(int cantProductos, Scanner teclado){
        
        String obsequio = "No aplica";
        
        if(validacionObsequios(cantProductos) == true){
            
            int opcionObsequio;
            
            
            System.out.println("\n==================================");
            System.out.println("\t OBSEQUIO");
            System.out.println("==================================");
            System.out.println("¡Enhorabuena! El cliente ganó un obsequio por llevar 5 o más artículos.");
            System.out.println("Seleccione el obsequio que pida el cliente: ");
            System.out.println("1. 1 libra de papas");
            System.out.println("2. 1 libra de arroz");
            System.out.println("3. 1 libra de azucar");
            
            opcionObsequio = teclado.nextInt();

                if(opcionObsequio == 1){

                    obsequio = "1 Libra de papas";
                    

                }else if( opcionObsequio == 2){

                    obsequio = "1 Libra de arroz";
                    

                }else if( opcionObsequio == 3){

                    obsequio = "1 Libra de azucar";
                    

                }else{

                    obsequio = "No aplica";
                    
                }
        }
        
        return obsequio;
        
    }
    
    public static double calcularCambio(double pagoCliente, double total){
        
        double cambio = 0.0;
        
        if(pagoCliente > total){

                cambio = pagoCliente - total;
                System.out.println("Cambio: " + cambio);

            }else if(pagoCliente == total){
                
                System.out.println("Sin cambio");
            
            }else{

                cambio = total - pagoCliente;
                
                System.out.println("Pago incompleto. Faltan: $ " + cambio );

            }
        return cambio;
    }
    
    public static void reporteCaja(String nombresClientes[],double pagosClientes[], double totalVentas){
        
        System.out.println("==================================");
        System.out.println("\tRESUMEN DE CAJA");
        System.out.println("==================================");
        
        
        
        int i = 0;
        
        while(nombresClientes[i] != null ){
            
            System.out.println("Cliente " + (i + 1) +" " +nombresClientes[i]);
            System.out.println("\tValor de compra: " + pagosClientes[i]);
            System.out.println("__________________________________");           
            
            i ++;
        }
        
        System.out.println("TOTAL:    " + totalVentas);
        
    }
    
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        double subTotal = 0.0;
        double total = 0.0;
        int cantProductos = 0;
        double pagoCliente = 0.0;
        
        String nombresClientes [] = new String [100];
        double pagosClientes [] = new double [100];
        int precioTotal [] = new int [100];
        
        int totalClientes = 0;
        double totalVentas = 0.0; 
        
        double cambio = 0.0;
        int inicioFin = 1;
 
        while(inicioFin == 1){ 
            
            //En esta seccion se declaran los nombres, cantidad de productos y valor de la compra
            
            System.out.println("==================================");
            System.out.println("Bienvenido a la tienda de Doña Luz");
            System.out.println("==================================");
            
            conexionBD.conectar();

            System.out.println("Ingrese el nombre del cliente: ");
            
            nombresClientes [totalClientes] = teclado.nextLine();

            System.out.println("Ingrese la cantidad de productos a pagar: ");

            cantProductos = teclado.nextInt();

            System.out.println("Ingrese el monto total de los productos: ");

            subTotal = teclado.nextDouble();

            System.out.println("Ingrese el monto con el que paga el cliente: ");

            pagoCliente = teclado.nextDouble();

            
            //En esta seccion se determina el valor total de la compra en caso de tener o no tener membresia VIP
            
            int opcionDescuento = 0;
            
            System.out.println("¿El cliente cuenta con una membresia VIP?");
            System.out.println("1. Si, el cliente  cuenta con mebresia VIP");
            System.out.println("2. No, el cliente no cuenta con membresia VIP");

            opcionDescuento = teclado.nextInt();
            
            total = subTotal;
            
            double descuento = 0.0;
            
            if(opcionDescuento == 1 ){
                
                boolean vip = true;
                total = calculadorDescuento(vip,descuento,subTotal,total);

            }else if(opcionDescuento == 2){
                
                boolean vip = false;
                total = calculadorDescuento(vip,descuento,subTotal,total);
                
            }else{
                
                boolean vip = false;
                total = calculadorDescuento(vip,descuento,subTotal,total);
                
            }

            //En esta seccion se define si el cliente recibe o no un obsequio por comprar 5 o mas articulos
            
            validacionObsequios(cantProductos);
            
            String obsequio = seleccionObsequio(cantProductos, teclado);
           
            //En esta seccion se crea el ticket con toda la informacion de la compra

            System.out.println("\n==================================");
            System.out.println("\tTICKET DE VENTA");
            System.out.println("==================================");
            System.out.println("Nombre del cliente: " + nombresClientes[totalClientes]);
            System.out.println("Monto con el que pago: " + pagoCliente);
            System.out.println("Subtotal: $" + subTotal);
            System.out.println("Precio total: $" + total);
            
            pagosClientes [totalClientes] = total;
            
            //En esta seccion se calcula el cambio de la persona
            
            OperacionesBD.registrarVenta(nombresClientes[totalClientes], cantProductos, total, obsequio);
            
            calcularCambio(pagoCliente, total);

            System.out.println("Obsequio: " + obsequio);

            System.out.println("==================================");
            System.out.println("\t¡GRACIAS POR COMPRAR!");
            System.out.println("=================================="); 
        
            System.out.println("\n¿Desea generar otro ticket?");
            System.out.println("1. SI");
            System.out.println("2. NO. Mostrar resumen de caja acumulado");
            
            inicioFin = teclado.nextInt();
            
            teclado.nextLine();
            
            totalVentas = totalVentas + pagosClientes[totalClientes];
            

            
            totalClientes ++;
            
            
            

        }
        
        reporteCaja(nombresClientes,pagosClientes,totalVentas);
        
        
        
    }    
}

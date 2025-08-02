package Principal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) {
        int opcionMenu = 0;
        int monto = 0;
        String par = "";

        // Se crea el cliente HTTP una sola vez, es reutilizable.
        HttpClient client = HttpClient.newHttpClient();

        // Se crea una instancia de Gson para convertir JSON a objetos Java.
        // setPrettyPrinting() formatea la salida del JSON para que sea más legible.
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        Scanner teclado = new Scanner(System.in);
        Converter converter = new Converter();


        String menu = """
                
                **************************************************
                *    Bienvenido al conversor de monedas          *
                *                                                *
                * 1 -> Dolar ==> Peso Argentino                  *
                * 2 -> Peso Argentino ==> Dolar                  *
                * 3 -> Dolar ==> Real Brasileño                  *
                * 4 -> Real Brasileño ==> Dolar                  *
                * 5 -> Dolar ==> Peso Colombiano                 *
                * 6 -> Peso Colombiano ==> Dolar                 *
                * 7 -> Salir                                     *
                *                                                *
                **************************************************
                Elija una opción:
                """;
        while (opcionMenu != 7) {
            System.out.println(menu);
            if (teclado.hasNextInt()) {
                opcionMenu = teclado.nextInt();
                teclado.nextLine(); // Consumir el salto de línea pendiente
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.nextLine(); // Limpiar la entrada inválida
                continue; // Volver al inicio del bucle
            }
            switch (opcionMenu) {
                case 1:
                    System.out.println("Dólar ==> Peso Argentino\n");
                    System.out.println("¿Qué monto desea convertir?:\n");
                    monto = teclado.nextInt();

                    if (monto < 1) {
                        System.out.println("Por favor, ingrese un número válido.");
                        break;
                    }try {
                        double resultado = converter.convertir("/USD/ARS/", monto);
                        System.out.printf("El total de Pesos Argentinos que tiene es: %.2f ARS\n", resultado);
                    } catch (Exception e) {
                        System.out.println("Error en la conversión: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Peso Argentino ==> Dolar\n");
                    System.out.println("Que monto desea convertir:\n");
                    monto = teclado.nextInt();
                    if (monto<1){
                        System.out.println("Por favor, ingrese un número válido.");
                        break;
                    }try {
                        double resultado = converter.convertir("/ARS/USD/", monto);
                        System.out.printf("El total de Dolares que tiene es: %.2f USD\n", resultado);
                    }catch (Exception e) {
                        System.out.println("Error en la conversión: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Dolar ==> Real Brasileño\n");
                    System.out.println("Que monto desea convertir:\n");
                    monto = teclado.nextInt();
                    if (monto<1){
                        System.out.println("Por favor, ingrese un número válido.");
                        break;
                    }try {
                    double resultado = converter.convertir("/USD/BRL/", monto);
                    System.out.printf("El total de Reales Brasileños que tiene es: %.2f BRL\n", resultado);
                }catch (Exception e) {
                    System.out.println("Error en la conversión: " + e.getMessage());
                }
                    break;
                case 4:
                    System.out.println("Real Brasileño ==> Dolar\n");
                            System.out.println("Que monto desea convertir:\n");
                    monto = teclado.nextInt();
                    if (monto<1){
                        System.out.println("Por favor, ingrese un número válido.");
                        break;
                    }try {
                    double resultado = converter.convertir("/BRL/USD/", monto);
                    System.out.printf("El total de Dolares que tiene es: %.2f USD\n", resultado);
                }catch (Exception e) {
                    System.out.println("Error en la conversión: " + e.getMessage());
                }
                    break;
                case 5:
                    System.out.println("Dolar ==> Peso Colombiano\n");
                            System.out.println("Que monto desea convertir:\n");
                    monto = teclado.nextInt();
                    if (monto<1){
                        System.out.println("Por favor, ingrese un número válido.");
                        break;
                    }try {
                    double resultado = converter.convertir("/USD/COP/", monto);
                    System.out.printf("El total de Pesos Colombianos que tiene es: %.2f USD\n", resultado);
                }catch (Exception e) {
                    System.out.println("Error en la conversión: " + e.getMessage());
                }
                    break;
                case 6:
                    System.out.println("Peso Colombiano ==> Dolar\n");
                            System.out.println("Que monto desea convertir:\n");
                    monto = teclado.nextInt();
                    if (monto<1){
                        System.out.println("Por favor, ingrese un número válido.");
                        break;
                    }try {
                    double resultado = converter.convertir("/COP/USD/", monto);
                    System.out.printf("El total de Dolares que tiene es: %.2f USD\n", resultado);
                }catch (Exception e) {
                    System.out.println("Error en la conversión: " + e.getMessage());
                }
                    break;
                case 7:
                    System.out.println("Gracias por usar nuestro servicio. ¡Hasta pronto!");
                    break;
                default:
                System.out.println("Opción no válida, por favor intente de nuevo.");
            }

        }
        teclado.close(); // Cerrar el scanner al final
    }
}

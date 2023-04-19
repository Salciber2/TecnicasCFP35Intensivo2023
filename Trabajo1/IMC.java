import java.text.DecimalFormat;
import java.util.Scanner;

public class IMC {
    public static void main(String[] args) {
        
        // Declaramos variables
        Scanner leer = new Scanner(System.in);
        DecimalFormat decimal = new DecimalFormat("#.00");
        
        // Variables principales para calcular el IMC
        double peso, altura, coeficienteIMC;
        // Variables para indicar el estado según corresponda
        byte indiceEstado;
        String [] estados = {
            "Delgadez muy severa",
            "Delgadez severa",
            "Delgadez",
            "Peso correcto",
            "Sobrepeso",
            "Obesidad moderada",
            "Obesidad severa",
            "Obesidad mórbida"
        };

        // Variables para obtener la opción que el usuario seleccione del menú
        String opcionStr;
        char opcion = 'a';
        // Variables para indicar al flujo si se salió del programa o del menú
        boolean salir = false, salirMenu = false;

        //Colores ANSI
        String textoRojo            ="\033[31m";            
        String textoVerde           ="\033[32m";         
        String textoAmarillo        ="\033[33m";
        String textoColorOriginal   ="\033[0m";

        // Empezamos con el programa que ve el usuario
        System.out.println("#####################################");
        System.out.println("#   Programa para calcular el IMC   #");
        System.out.println("#####################################");

        // Entramos a la iteración del programa, mientras no salgamos, se repite
        while (!salir) {
            // Iteración del menú, mientras la opción sea incorrecta, se repite
            do {
                System.out.println();
                System.out.println("Seleccione una opción");
                System.out.println("1- ¿Que es IMC?");
                System.out.println("2- Calcular IMC");
                System.out.println("0- Salir");
                System.out.println();
                System.out.print("Opcion: ");
                opcionStr = leer.nextLine();

                switch (opcionStr) {
                    case "1": case "2": case "0":
                        opcion = opcionStr.charAt(0);
                        salirMenu = true;
                        break;
                    default:
                        System.out.println("La opción es incorrecta");
                        break;
                }
            } while (!salirMenu);

            // Opciónes del menú, si es opción 2 calculamos el IMC
            System.out.println();
            if (opcion == '1') {
                System.out.println("El IMC es el Índice de Masa Corporal," + 
                    " un coeficiente por el cual se establece si una persona tiene obesidad o extrema delgadez," + 
                    " o si por el contrario tiene sólo algunos kilos de más.");
            } else if (opcion == '2') {
                // Pedimos el peso y altura al usuario
                System.out.print("Ingrese su altura en metros: ");
                altura = Double.parseDouble(leer.nextLine().replace(",","."));
                System.out.print("Ingrese su peso en kilogramos: ");
                peso = Double.parseDouble(leer.nextLine().replace(",","."));
                coeficienteIMC = peso / (altura * altura);

                /*
                    Rangos de los estados
                    0 - Menos de 15     Delgadez muy severa
                    1 - 15 a 15.9		Delgadez severa
                    2 - 16 a 18.4		Delgadez
                    3 - 18.5 a 24.9     Peso correcto
                    4 - 25 a 29.9		Sobrepeso
                    5 - 30 a 34.9		Obesidad moderada
                    6 - 35 a 39.9		Obesidad severa
                    7 - 40 o más		Obesidad mórbida
                 */

                /*
                    Definimos el estado según el rango, obteniendo el indice
                    para ponerlo dentro del vector estados[]
                */
                if (coeficienteIMC < 15)                                indiceEstado = 0;
                else if (coeficienteIMC >= 15 && coeficienteIMC < 16)   indiceEstado = 1;
                else if (coeficienteIMC >= 16 && coeficienteIMC < 18.5) indiceEstado = 2;
                else if (coeficienteIMC >= 18.5 && coeficienteIMC < 25) indiceEstado = 3;
                else if (coeficienteIMC >= 25 && coeficienteIMC < 30)   indiceEstado = 4;
                else if (coeficienteIMC >= 30 && coeficienteIMC < 35)   indiceEstado = 5;
                else if (coeficienteIMC >= 15 && coeficienteIMC < 40)   indiceEstado = 6;
                else                                                    indiceEstado = 7;

                // Usamos los colores ANSI, no funciona en todas las consolas
                switch (indiceEstado) {
                    case 0: case 1: case 6: case 7:
                        System.out.println(textoRojo);
                        break;
                    case 2: case 4: case 5:
                        System.out.println(textoAmarillo);
                        break;
                    case 3:
                        System.out.println(textoVerde);
                        break;
                }

                // Mostramos los resultados al usuario
                System.out.println("Coeficiente: " + decimal.format(coeficienteIMC));
                System.out.println("Su estado es: " + estados[indiceEstado]);
                System.out.print(textoColorOriginal);
            } else {
                System.out.println("Saliendo...");
                leer.close();
                salir = true;
            }
            
        }
    }
}
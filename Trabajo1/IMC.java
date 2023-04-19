import java.text.DecimalFormat;
import java.util.Scanner;

public class IMC {
    public static void main(String[] args) {
        
        Scanner leer = new Scanner(System.in);
        DecimalFormat decimal = new DecimalFormat("#.00");
        double peso, altura, coeficienteIMC;
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
        byte indiceEstado;
        String opcionStr;
        char opcion = 'a';
        boolean salir = false, menu = false;

        //Colores ANSI
        String textoRojo            ="\033[31m";            
        String textoVerde           ="\033[32m";         
        String textoAmarillo        ="\033[33m";
        String textoColorOriginal   ="\033[0m";


        System.out.println("#####################################");
        System.out.println("#   Programa para calcular el IMC   #");
        System.out.println("#####################################");

        while (!salir) {
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
                        menu = true;
                        break;
                    default:
                        System.out.println("La opción es incorrecta");
                        break;
                }
            } while (!menu);

            System.out.println();
            if (opcion == '1') {
                System.out.println("El IMC es el Índice de Masa Corporal," + 
                    " un coeficiente por el cual se establece si una persona tiene obesidad o extrema delgadez," + 
                    " o si por el contrario tiene sólo algunos kilos de más.");
            } else if (opcion == '2') {
                System.out.print("Ingrese su altura en metros: ");
                altura = Double.parseDouble(leer.nextLine().replace(",","."));
                System.out.print("Ingrese su peso en kilogramos: ");
                peso = Double.parseDouble(leer.nextLine().replace(",","."));
                coeficienteIMC = peso / (altura * altura);

                /*
                    0 - Menos de 15     Delgadez muy severa
                    1 - 15 a 15.9		Delgadez severa
                    2 - 16 a 18.4		Delgadez
                    3 - 18.5 a 24.9     Peso correcto
                    4 - 25 a 29.9		Sobrepeso
                    5 - 30 a 34.9		Obesidad moderada
                    6 - 35 a 39.9		Obesidad severa
                    7 - 40 o más		Obesidad mórbida
                 */

                if (coeficienteIMC < 15)                                indiceEstado = 0;
                else if (coeficienteIMC >= 15 && coeficienteIMC < 16)   indiceEstado = 1;
                else if (coeficienteIMC >= 16 && coeficienteIMC < 18.5) indiceEstado = 2;
                else if (coeficienteIMC >= 18.5 && coeficienteIMC < 25) indiceEstado = 3;
                else if (coeficienteIMC >= 25 && coeficienteIMC < 30)   indiceEstado = 4;
                else if (coeficienteIMC >= 30 && coeficienteIMC < 35)   indiceEstado = 5;
                else if (coeficienteIMC >= 15 && coeficienteIMC < 40)   indiceEstado = 6;
                else                                                    indiceEstado = 7;

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

                System.out.println("Coeficiente: " + decimal.format(coeficienteIMC));
                System.out.println("Su estado es: " + estados[indiceEstado]);
                System.out.print(textoColorOriginal);
            } else {
                System.out.println("Saliendo...");
                salir = true;
            }
            
        }
        
        leer.close();
    }
}
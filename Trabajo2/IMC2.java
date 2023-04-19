import java.text.DecimalFormat;

public class IMC {
    //Colores ANSI
    static String textoRojo            ="\033[31m";            
    static String textoVerde           ="\033[32m";         
    static String textoAmarillo        ="\033[33m";
    static String textoColorOriginal   ="\033[0m";

    public static void main(String[] args) {        
        // Declaramos variables
        DecimalFormat decimal = new DecimalFormat("#.00");        
        // Variables principales para calcular el IMC
        double peso, altura, coefIMC;

        // Comienzo lógica del programa
        cartelBienvenida();
        // Tomamos el peso y altura de los argumenos de consola
        if (args.length == 2) {
            // Obtenemos las variables desde los argumentos de consola y calculamos el IMC
            altura = Double.parseDouble(args[0].replace(",","."));           // Punto 1  
            peso = Double.parseDouble(args[1].replace(",","."));             // Punto 1
            coefIMC = calcularMasaCorporal(altura, peso);                                       // Punto 2

            // Mostramos los resultados al usuario
            System.out.println("Coeficiente: " + decimal.format(coefIMC));
            System.out.println(obtenerEstado(coefIMC));                                         // Punto 3
        } else {
            System.out.println("Debe ingresar dos parámetros por consola, primero altura en metros, un espacio, luego peso en kilogramos.");
        }
    }

    private static void cartelBienvenida() {
        System.out.println("#####################################");
        System.out.println("#   Programa para calcular el IMC   #");
        System.out.println("#####################################");
    }

    private static double calcularMasaCorporal(double altura, double peso) {
        return peso / (altura * altura);
    }

    private static String obtenerEstado(double coefIMC) {
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
            para ponerlo dentro del vector estados[] y le ponemos color
        */
        if (coefIMC < 15)                         return textoRojo        + "Su estado es: " + estados[0] + textoColorOriginal;
        else if (coefIMC >= 15 && coefIMC < 16)   return textoRojo        + "Su estado es: " + estados[1] + textoColorOriginal;
        else if (coefIMC >= 16 && coefIMC < 18.5) return textoAmarillo    + "Su estado es: " + estados[2] + textoColorOriginal;
        else if (coefIMC >= 18.5 && coefIMC < 25) return textoVerde       + "Su estado es: " + estados[3] + textoColorOriginal;
        else if (coefIMC >= 25 && coefIMC < 30)   return textoAmarillo    + "Su estado es: " + estados[4] + textoColorOriginal;
        else if (coefIMC >= 30 && coefIMC < 35)   return textoAmarillo    + "Su estado es: " + estados[5] + textoColorOriginal;
        else if (coefIMC >= 15 && coefIMC < 40)   return textoRojo        + "Su estado es: " + estados[6] + textoColorOriginal;
        else                                      return textoRojo        + "Su estado es: " + estados[7] + textoColorOriginal;
    }
}
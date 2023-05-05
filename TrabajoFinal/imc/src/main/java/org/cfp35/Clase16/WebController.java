package org.cfp35.Clase16;

import java.text.DecimalFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WebController {

    @GetMapping({"/", "/imc"})
    public String getIMC(
        Model model,
        @RequestParam(name = "peso", required = false, defaultValue = "0") double peso,
        @RequestParam(name = "altura", required = false, defaultValue = "0") double altura
    ) {
        DecimalFormat decimal = new DecimalFormat("#.#");
        double coefIMC = calcularMasaCorporal(altura, peso);

        if (peso != 0 && altura != 0) {
            byte indice = calcularEstado(coefIMC);
            model.addAttribute("imc", decimal.format(coefIMC));
            model.addAttribute("estado", obtenerEstado(coefIMC, indice));
        }        
        return "imc.html";
    }

    private static double calcularMasaCorporal(double altura, double peso) {
        return peso / (altura * altura);
    }

    private static byte calcularEstado(double coefIMC) {
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
        */
        if (coefIMC < 15)                         return 0;
        else if (coefIMC >= 15 && coefIMC < 16)   return 1;
        else if (coefIMC >= 16 && coefIMC < 18.5) return 2;
        else if (coefIMC >= 18.5 && coefIMC < 25) return 3;
        else if (coefIMC >= 25 && coefIMC < 30)   return 4;
        else if (coefIMC >= 30 && coefIMC < 35)   return 5;
        else if (coefIMC >= 15 && coefIMC < 40)   return 6;
        else                                      return 7;
    }

    private static String obtenerEstado(double coefIMC, byte indiceIMC) {
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
        return "Su estado es: " + estados[indiceIMC];
    }
}

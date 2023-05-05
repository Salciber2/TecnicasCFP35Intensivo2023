let btnCalcular = document.getElementById('btnCalcular')

btnCalcular.addEventListener("click", calcular())

// Funciones

function calcular() {
    let cajaIMC = document.getElementById('cajaIMC')
    let imc = cajaIMC.value
    let cajaEstado = document.getElementById('cajaEstado')
    let claseOriginal = "form-control cajaTexto text-center my-2"
    
    switch (calcularIndiceEstado(imc)) {
        case 0: case 1: case 6: case 7:
            cajaIMC.className = claseOriginal + " bg-danger"
            cajaEstado.className = claseOriginal + " bg-danger"
            break;
        case 2: case 4: case 5:
            cajaIMC.className = claseOriginal + " bg-warning"
            cajaEstado.className = claseOriginal + " bg-warning"
            break;
        case 3:
            cajaIMC.className = claseOriginal + " bg-success"
            cajaEstado.className = claseOriginal + " bg-success"
            break;
        case -1: default:
            cajaIMC.className = claseOriginal
            cajaEstado.className = claseOriginal
            break;
    }
}

function calcularIndiceEstado(coefIMC) {
    coefIMC = parseFloat(coefIMC)
    if (coefIMC == "" || isNaN(coefIMC))        return -1;
    else if (coefIMC > 0 && coefIMC < 15)       return 0;
    else if (coefIMC >= 15 && coefIMC < 16)     return 1;
    else if (coefIMC >= 16 && coefIMC < 18.5)   return 2;
    else if (coefIMC >= 18.5 && coefIMC < 25)   return 3;
    else if (coefIMC >= 25 && coefIMC < 30)     return 4;
    else if (coefIMC >= 30 && coefIMC < 35)     return 5;
    else if (coefIMC >= 15 && coefIMC < 40)     return 6;
    else                                        return 7;
}
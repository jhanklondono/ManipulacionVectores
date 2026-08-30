import java.util.Scanner;

// Taller de vectores - operaciones basicas (busqueda, mayor/menor, multiplos, suma y promedio)
// Autor: (agregar nombre)
public class VectorNumeros {

    static final int TAMANIO = 15;
    static final int LIMITE_MIN = 10;
    static final int LIMITE_MAX = 100;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int[] numeros = new int[TAMANIO];

        // Primero se llena el vector, esto es obligatorio antes de poder usar el menu
        llenarVector(numeros);
        System.out.println("\nVector cargado correctamente.");
        imprimirVector(numeros);

        // Ahora se despliega el menu para que el usuario elija que operacion quiere ver
        int opcion;
        do {
            opcion = mostrarMenu();

            switch (opcion) {
                case 1:
                    System.out.println("\nVector actual:");
                    imprimirVector(numeros);
                    break;
                case 2:
                    buscarNumero(numeros);
                    break;
                case 3:
                    mostrarMayorYMenor(numeros);
                    break;
                case 4:
                    buscarMultiplos(numeros);
                    break;
                case 5:
                    int total = sumarVector(numeros);
                    System.out.println("\nSuma total de los elementos: " + total);
                    break;
                case 6:
                    int suma = sumarVector(numeros);
                    generarVectorSobrePromedio(numeros, suma);
                    break;
                case 0:
                    System.out.println("\nSaliendo del programa...");
                    break;
                default:
                    System.out.println("\nOpcion invalida, elige un numero del menu.");
            }

        } while (opcion != 0);

        sc.close();
    }

    // Imprime el menu y valida que el usuario escriba un numero dentro del rango de opciones.
    static int mostrarMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Mostrar el vector");
        System.out.println("2. Buscar un numero en el vector");
        System.out.println("3. Mostrar el mayor y el menor valor");
        System.out.println("4. Buscar multiplos de un numero X");
        System.out.println("5. Calcular la suma total");
        System.out.println("6. Generar vector con numeros sobre el promedio");
        System.out.println("0. Salir");
        System.out.print("Elige una opcion: ");

        int opcion = sc.nextInt();
        return opcion;
    }

    // Pide los 15 numeros uno por uno y revisa que esten en el rango pedido.
    // Si el usuario se sale del rango, se le vuelve a pedir el mismo dato sin avanzar de posicion.
    static void llenarVector(int[] numeros) {
        System.out.println("Ingresar " + TAMANIO + " numeros entre " + LIMITE_MIN + " y " + LIMITE_MAX + ".");

        int i = 0;
        while (i < TAMANIO) {
            System.out.print("Numero " + (i + 1) + ": ");
            int valor = sc.nextInt();

            if (valor < LIMITE_MIN || valor > LIMITE_MAX) {
                System.out.println("Ese numero esta fuera de rango (" + LIMITE_MIN + " - " + LIMITE_MAX + "), intenta de nuevo.");
            } else {
                numeros[i] = valor;
                i++;
            }
        }
    }

    // Recorre el vector buscando el numero que el usuario indique.
    static void buscarNumero(int[] numeros) {
        System.out.print("\nQue numero quieres buscar en el vector? ");
        int buscado = sc.nextInt();

        int posicion = -1;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == buscado) {
                posicion = i;
                break; // ya lo encontramos, no hace falta seguir recorriendo
            }
        }

        if (posicion != -1) {
            System.out.println("Encontrado en la posicion " + posicion + " del vector.");
        } else {
            System.out.println("El numero " + buscado + " no esta en el vector.");
        }
    }

    // Recorre el vector una sola vez comparando cada valor contra el mayor y el menor actuales.
    static void mostrarMayorYMenor(int[] numeros) {
        int mayor = numeros[0];
        int menor = numeros[0];

        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > mayor) {
                mayor = numeros[i];
            }
            if (numeros[i] < menor) {
                menor = numeros[i];
            }
        }

        System.out.println("\nEl mayor valor del vector es: " + mayor);
        System.out.println("El menor valor del vector es: " + menor);
    }

    // Pide un numero X y revisa cuales elementos del vector son multiplos de ese numero.
    static void buscarMultiplos(int[] numeros) {
        int x;
        do {
            System.out.print("\nIngresa un numero X (distinto de 0) para revisar sus multiplos: ");
            x = sc.nextInt();
            if (x == 0) {
                System.out.println("No se puede dividir entre 0, ingresa otro numero.");
            }
        } while (x == 0);

        boolean encontroAlguno = false;
        System.out.println("Multiplos de " + x + " dentro del vector:");

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] % x == 0) {
                System.out.println("- " + numeros[i] + " (posicion " + i + ")");
                encontroAlguno = true;
            }
        }

        if (!encontroAlguno) {
            System.out.println("No hay ningun multiplo de " + x + " en el vector.");
        }
    }

    // Suma todos los elementos del vector y regresa el total.
    static int sumarVector(int[] numeros) {
        int suma = 0;
        for (int valor : numeros) {
            suma += valor;
        }
        return suma;
    }

    // Con la suma ya calculada, saca el promedio y arma un nuevo vector
    // solo con los numeros que quedaron por encima de ese promedio.
    static void generarVectorSobrePromedio(int[] numeros, int suma) {
        double promedio = (double) suma / numeros.length;
        System.out.printf("%nPromedio del vector: %.2f%n", promedio);

        int cantidadMayores = 0;
        for (int valor : numeros) {
            if (valor > promedio) {
                cantidadMayores++;
            }
        }

        if (cantidadMayores == 0) {
            System.out.println("Ningun numero del vector supera el promedio.");
            return;
        }

        int[] sobrePromedio = new int[cantidadMayores];
        int pos = 0;
        for (int valor : numeros) {
            if (valor > promedio) {
                sobrePromedio[pos] = valor;
                pos++;
            }
        }

        System.out.println("Numeros por encima del promedio:");
        imprimirVector(sobrePromedio);
        System.out.println("En total hay " + cantidadMayores + " numero(s) por encima del promedio.");
    }

    // Metodo que uso varias veces para no repetir el mismo for de impresion en todos lados.
    static void imprimirVector(int[] v) {
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i]);
            if (i != v.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
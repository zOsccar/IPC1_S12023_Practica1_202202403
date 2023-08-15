package practica1;
import java.util.Scanner;
public class Practica1 {
    
private static final int MAX_CLIENTES = 100;
private static String[][] clientes = new String[MAX_CLIENTES][3]; // Matriz para almacenar clientes (NIT, Nombre, Apellido)
private static int numClientes = 0; // Contador de clientes registrados
private static String clienteSesion = null;

private static final int MAX_VEHICULOS = 100;
private static String[][] vehiculos = new String[MAX_VEHICULOS][5]; // Matriz para almacenar vehículos
private static int numVehiculos = 0; // Contador de vehículos registrados

private static final int MAX_DESCUENTOS = 10; // Número máximo de descuentos especiales
private static int[] minDiasDescuento = new int[MAX_DESCUENTOS]; // Matriz para almacenar el número mínimo de días
private static int[] porcentajeDescuento = new int[MAX_DESCUENTOS]; // Matriz para almacenar el porcentaje de descuento
private static int numDescuentos = 0; // Contador de descuentos registrados
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú Inicial ===");
            System.out.println("1. Ingresar como administrador");
            System.out.println("2. Ingresar como usuario");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (autenticarAdmin()) {
                        ingresarComoAdministrador();
                    } else {
                        System.out.println("Credenciales incorrectas. Volviendo al menú inicial.");
                    }
                    break;
                case 2:
                    ingresarComoUsuario();
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
            }
        } while (opcion != 3);
    }

    public static boolean autenticarAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();

        return usuario.equals("admin_202202403") && contrasena.equals("202202403");
    }

    public static void ingresarComoAdministrador() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú Administrador ===");
            System.out.println("1. Agregar nuevos vehículos");
            System.out.println("2. Agregar descuentos especiales");
            System.out.println("3. Realizar reportes");
            System.out.println("4. Mostrar usuarios registrados");
            System.out.println("5. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarVehiculos();
                    break;
                case 2:
                    agregarDescuentos();
                    break;
                case 3:
                    realizarReportes();
                    break;
                case 4:
                    MostrarUsuarios();
                    break;
                case 5:
                    System.out.println("Cerrando sesión de administrador.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
            }
        } while (opcion != 5);
    }

public static void agregarVehiculos() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Agregar Nuevo Vehículo ===");

    System.out.print("Marca: ");
    String marca = scanner.nextLine();
    System.out.print("Línea: ");
    String linea = scanner.nextLine();
    System.out.print("Modelo: ");
    String modelo = scanner.nextLine();
    System.out.print("Placa: ");
    String placa = scanner.nextLine();

    // Verificar si la placa ya existe en el sistema
    boolean placaExistente = false;
    for (int i = 0; i < numVehiculos; i++) {
        if (vehiculos[i][3] != null && vehiculos[i][3].equals(placa)) {
            placaExistente = true;
            break;
        }
    }

    if (placaExistente) {
        System.out.println("La placa ingresada ya existe en el sistema.");
        return;
    }

    System.out.print("Costo por día de alquiler (en quetzales): ");
    double costoPorDia = scanner.nextDouble();

    if (costoPorDia <= 0) {
        System.out.println("El costo por día de alquiler debe ser mayor a 0.");
        return;
    }

    // Agregar el nuevo vehículo al sistema
    vehiculos[numVehiculos][0] = marca;
    vehiculos[numVehiculos][1] = linea;
    vehiculos[numVehiculos][2] = modelo;
    vehiculos[numVehiculos][3] = placa;
    vehiculos[numVehiculos][4] = String.valueOf(costoPorDia);

    numVehiculos++;

    System.out.println("Vehículo agregado exitosamente.");
}
    
    public static void MostrarUsuarios() {
        System.out.println("=== Lista de Usuarios Registrados ===");
        System.out.println("NIT \t Nombre \t Apellido");

        for (int i = 0; i < numClientes; i++) {
            String nit = clientes[i][0];
            String nombre = clientes[i][1];
            String apellido = clientes[i][2];

            System.out.println(nit + " \t " + nombre + " \t " + apellido);
        }

        System.out.println("=====================================");
    }

 public static void agregarDescuentos() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Agregar Descuento Especial ===");

        System.out.print("Número de días mínimo de alquiler: ");
        int minDias = scanner.nextInt();

        if (minDias <= 0) {
            System.out.println("El número de días mínimo debe ser mayor a 0.");
            return;
        }

        System.out.print("Porcentaje de descuento: ");
        int porcentaje = scanner.nextInt();

        if (porcentaje <= 0 || porcentaje >= 100) {
            System.out.println("El porcentaje de descuento debe estar entre 1 y 99.");
            return;
        }

        // Agregar el nuevo descuento al sistema
        minDiasDescuento[numDescuentos] = minDias;
        porcentajeDescuento[numDescuentos] = porcentaje;

        numDescuentos++;

        System.out.println("Descuento especial agregado exitosamente.");
    }


public static void realizarReportes() {
    System.out.println("=== Menú Reportes ===");
    System.out.println("Generando Reporte según Marca...");
    generarReporteMarcas();
    System.out.println("Generando Reporte según Modelo...");
    generarReporteModelos();
    System.out.println("Presione Enter para regresar al menú anterior.");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // Esperar a que el usuario presione Enter
}

public static void generarReporteMarcas() {
    System.out.println("=== Reporte 1: Según Marca ===");
    System.out.println("Marca                 Total de días rentado");

    String[] marcas = new String[numVehiculos];
    int[] diasRentadoPorMarca = new int[numVehiculos];
    int totalDiasRentado = 0;

    for (int i = 0; i < numVehiculos; i++) {
        String marca = vehiculos[i][0];
        int diasRentado = 0;

        for (int j = 0; j < numClientes; j++) {
            if (clientes[j][0] != null && clientes[j][0].equals(clienteSesion)) {

                diasRentado = 5;
                break;
            }
        }

        int index = -1;
        for (int j = 0; j < totalDiasRentado; j++) {
            if (marcas[j] != null && marcas[j].equals(marca)) {
                index = j;
                break;
            }
        }

        if (index == -1) {
            marcas[totalDiasRentado] = marca;
            diasRentadoPorMarca[totalDiasRentado] += diasRentado;
            totalDiasRentado++;
        } else {
            diasRentadoPorMarca[index] += diasRentado;
        }
    }

    // Ordenar los arreglos en paralelo por días rentados en orden descendente
    for (int i = 0; i < totalDiasRentado - 1; i++) {
        for (int j = i + 1; j < totalDiasRentado; j++) {
            if (diasRentadoPorMarca[j] > diasRentadoPorMarca[i]) {
                int tempDias = diasRentadoPorMarca[i];
                diasRentadoPorMarca[i] = diasRentadoPorMarca[j];
                diasRentadoPorMarca[j] = tempDias;

                String tempMarca = marcas[i];
                marcas[i] = marcas[j];
                marcas[j] = tempMarca;
            }
        }
    }

    // Mostrar el reporte ordenado
    for (int i = 0; i < totalDiasRentado; i++) {
        String marca = marcas[i];
        int diasRentado = diasRentadoPorMarca[i];

        System.out.printf("%-20s%d%n", marca, diasRentado);
    }

    System.out.println("                             ----------");
    System.out.printf("%-20s%d%n%n", "Total", totalDiasRentado);
}

public static void generarReporteModelos() {
    System.out.println("=== Reporte 2: Según Modelo ===");
    System.out.println("Modelo               Total de días rentado");

    String[] modelos = new String[numVehiculos];
    int[] diasRentadoPorModelo = new int[numVehiculos];
    int totalDiasRentado = 0;

    for (int i = 0; i < numVehiculos; i++) {
        String modelo = vehiculos[i][2];
        int diasRentado = 0;

        for (int j = 0; j < numClientes; j++) {
            if (clientes[j][0] != null && clientes[j][0].equals(clienteSesion)) {
                diasRentado = calcularDiasRentados(modelo, clientes[j][0]);
                break;
            }
        }

        int index = -1;
        for (int j = 0; j < totalDiasRentado; j++) {
            if (modelos[j] != null && modelos[j].equals(modelo)) {
                index = j;
                break;
            }
        }

        if (index == -1) {
            modelos[totalDiasRentado] = modelo;
            diasRentadoPorModelo[totalDiasRentado] += diasRentado;
            totalDiasRentado++;
        } else {
            diasRentadoPorModelo[index] += diasRentado;
        }
    }

    for (int i = 0; i < totalDiasRentado - 1; i++) {
        for (int j = i + 1; j < totalDiasRentado; j++) {
            if (diasRentadoPorModelo[j] > diasRentadoPorModelo[i]) {
                int tempDias = diasRentadoPorModelo[i];
                diasRentadoPorModelo[i] = diasRentadoPorModelo[j];
                diasRentadoPorModelo[j] = tempDias;

                String tempModelo = modelos[i];
                modelos[i] = modelos[j];
                modelos[j] = tempModelo;
            }
        }
    }

    for (int i = 0; i < totalDiasRentado; i++) {
        String modelo = modelos[i];
        int diasRentado = diasRentadoPorModelo[i];

        System.out.printf("%-20s%d%n", modelo, diasRentado);
    }

    System.out.println("                             ----------");
    System.out.printf("%-20s%d%n%n", "Total", totalDiasRentado);
}

public static int calcularDiasRentados(String modelo, String clienteNIT) {
    int totalDiasRentado = 0;

    for (int i = 0; i < numVehiculos; i++) {
        if (vehiculos[i][2].equals(modelo)) {

            totalDiasRentado += 3;
        }
    }

    return totalDiasRentado;
}

 

public static void ingresarComoUsuario() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú Cliente ===");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    iniciarSesionCliente();
                    break;
                case 3:
                    System.out.println("Saliendo del menú cliente.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
            }
        } while (opcion != 3);
    }    
    
    public static void registrarCliente() {
        Scanner scanner = new Scanner(System.in);

        if (numClientes < MAX_CLIENTES) {
            System.out.print("Ingrese el NIT del cliente: ");
            String nit = scanner.nextLine();
            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el apellido del cliente: ");
            String apellido = scanner.nextLine();

            clientes[numClientes][0] = nit;
            clientes[numClientes][1] = nombre;
            clientes[numClientes][2] = apellido;

            numClientes++;

            System.out.println("Cliente registrado exitosamente.");
        } else {
            System.out.println("No se pueden registrar más clientes. Límite alcanzado.");
        }
    }

    public static void iniciarSesionCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el NIT del cliente: ");
        String nit = scanner.nextLine();

        boolean clienteEncontrado = false;

        for (int i = 0; i < numClientes; i++) {
            if (clientes[i][0] != null && clientes[i][0].equals(nit)) {
                System.out.println("Inicio de sesión exitoso para el cliente con NIT: " + nit);
                clienteSesion = nit; // Guardar el NIT del cliente en sesión
                clienteEncontrado = true;
                menuCliente();
                break;
            }
        }

        if (!clienteEncontrado) {
            System.out.println("No se encontró un cliente con el NIT proporcionado.");
        }
    }

    public static void menuCliente() {
    Scanner scanner = new Scanner(System.in);
    int opcion;

    do {
        System.out.println("=== Menú Cliente ===");
        System.out.println("1. Realizar Orden de Venta de Vehículos");
        System.out.println("2. Realizar Factura");
        System.out.println("3. Cerrar Sesión");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                realizarOrdenVenta();
                break;
            case 2:
                realizarFactura();
                break;
            case 3:
                clienteSesion = null; // Cerrar sesión
                System.out.println("Sesión cerrada. Volviendo al menú inicial.");
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
        }
    } while (opcion != 3);
}

public static void realizarOrdenVenta() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Realizar Orden de Venta ===");

    // Selecciona un vehiculo
    System.out.print("Ingrese la placa del vehículo: ");
    String placa = scanner.nextLine();

    // Lo busca
    int vehiculoIndex = -1;
    for (int i = 0; i < numVehiculos; i++) {
        if (vehiculos[i][3] != null && vehiculos[i][3].equals(placa)) {
            vehiculoIndex = i;
            break;
        }
    }

    if (vehiculoIndex == -1) {
        System.out.println("No se encontró un vehículo con la placa proporcionada.");
        return;
    }

    // Calcula el precio total
    System.out.print("Ingrese el número de días de alquiler: ");
    int numDias = scanner.nextInt();
    double costoPorDia = Double.parseDouble(vehiculos[vehiculoIndex][4]);
    double total = numDias * costoPorDia;

    // Enseña detalles
    System.out.println("Detalles de la Orden:");
    System.out.println("Vehículo: " + vehiculos[vehiculoIndex][0] + " " + vehiculos[vehiculoIndex][1] + " " + vehiculos[vehiculoIndex][2]);
    System.out.println("Placa: " + vehiculos[vehiculoIndex][3]);
    System.out.println("Días de alquiler: " + numDias);
    System.out.println("Costo por día: Q" + costoPorDia);
    System.out.println("Total: Q" + total);


}

public static void realizarFactura() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Realizar Factura ===");

    // Busca al cliente logeado
    int clienteIndex = -1;
    for (int i = 0; i < numClientes; i++) {
        if (clientes[i][0] != null && clientes[i][0].equals(clienteSesion)) {
            clienteIndex = i;
            break;
        }
    }

    if (clienteIndex == -1) {
        System.out.println("No se encontró un cliente con la sesión actual.");
        return;
    }

    // Calcula la cantidad a pagar



    System.out.println("Detalles de la Factura:");
    System.out.println("Cliente: " + clientes[clienteIndex][1] + " " + clientes[clienteIndex][2]);

}
}
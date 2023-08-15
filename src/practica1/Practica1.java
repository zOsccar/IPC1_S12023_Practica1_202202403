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
    System.out.println("Modelo/Marca              Total de días rentados por cliente");

    String[] marcasUnicas = new String[numVehiculos];
    double[] diasRentadoPorMarca = new double[numVehiculos];
    int totalMarcasUnicas = 0;

    for (int i = 0; i < numVehiculos; i++) {
        String marca = vehiculos[i][0];
        double diasRentado = 0.0;

        if (vehiculos[i][4] != null) {
            diasRentado = Double.parseDouble(vehiculos[i][4]);
        }

        int index = -1;
        for (int j = 0; j < totalMarcasUnicas; j++) {
            if (marcasUnicas[j] != null && marcasUnicas[j].equals(marca)) {
                index = j;
                break;
            }
        }

        if (index == -1) {
            marcasUnicas[totalMarcasUnicas] = marca;
            diasRentadoPorMarca[totalMarcasUnicas] = diasRentado;
            totalMarcasUnicas++;
        } else {
            diasRentadoPorMarca[index] += diasRentado;
        }
    }

    for (int i = 0; i < totalMarcasUnicas - 1; i++) {
        for (int j = i + 1; j < totalMarcasUnicas; j++) {
            if (diasRentadoPorMarca[j] > diasRentadoPorMarca[i]) {
                double tempDias = diasRentadoPorMarca[i];
                diasRentadoPorMarca[i] = diasRentadoPorMarca[j];
                diasRentadoPorMarca[j] = tempDias;

                String tempMarca = marcasUnicas[i];
                marcasUnicas[i] = marcasUnicas[j];
                marcasUnicas[j] = tempMarca;
            }
        }
    }

    for (int i = 0; i < totalMarcasUnicas; i++) {
        String marca = marcasUnicas[i];
        double diasRentado = diasRentadoPorMarca[i];

        System.out.printf("%-25s%.2f%n", marca, diasRentado);
    }

    System.out.println("                             ----------");
    System.out.printf("%-25s%d%n%n", "Total", totalMarcasUnicas);
}

public static void generarReporteModelos() {
    System.out.println("=== Reporte 2: Según Modelo ===");
    System.out.println("Modelo/Marca              Total de días rentados por cliente");

    String[] modelosUnicos = new String[numVehiculos];
    double[] diasRentadoPorModelo = new double[numVehiculos];
    int totalModelosUnicos = 0;

    for (int i = 0; i < numVehiculos; i++) {
        String modelo = vehiculos[i][2];
        double diasRentado = 0.0;

        if (vehiculos[i][4] != null) {
            diasRentado = Double.parseDouble(vehiculos[i][4]);
        }

        int index = -1;
        for (int j = 0; j < totalModelosUnicos; j++) {
            if (modelosUnicos[j] != null && modelosUnicos[j].equals(modelo)) {
                index = j;
                break;
            }
        }

        if (index == -1) {
            modelosUnicos[totalModelosUnicos] = modelo;
            diasRentadoPorModelo[totalModelosUnicos] = diasRentado;
            totalModelosUnicos++;
        } else {
            diasRentadoPorModelo[index] += diasRentado;
        }
    }

    for (int i = 0; i < totalModelosUnicos - 1; i++) {
        for (int j = i + 1; j < totalModelosUnicos; j++) {
            if (diasRentadoPorModelo[j] > diasRentadoPorModelo[i]) {
                double tempDias = diasRentadoPorModelo[i];
                diasRentadoPorModelo[i] = diasRentadoPorModelo[j];
                diasRentadoPorModelo[j] = tempDias;

                String tempModelo = modelosUnicos[i];
                modelosUnicos[i] = modelosUnicos[j];
                modelosUnicos[j] = tempModelo;
            }
        }
    }

    for (int i = 0; i < totalModelosUnicos; i++) {
        String modelo = modelosUnicos[i];
        double diasRentado = diasRentadoPorModelo[i];

        System.out.printf("%-25s%.2f%n", modelo, diasRentado);
    }

    System.out.println("                             ----------");
    System.out.printf("%-25s%d%n%n", "Total", totalModelosUnicos);
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
        System.out.println("2. Cerrar Sesión");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                realizarOrdenVenta();
                break;
            case 2:
                clienteSesion = null; // Cerrar sesión
                System.out.println("Sesión cerrada. Volviendo al menú inicial.");
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
        }
    } while (opcion != 2);
}

public static void realizarOrdenVenta() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Realizar Orden de Venta ===");

    // Selecciona un vehículo
    System.out.print("Ingrese la placa del vehículo: ");
    String placa = scanner.nextLine();

    // Buscar el vehículo
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

    // Calcular el precio total
    System.out.print("Ingrese el número de días de alquiler: ");
    int numDias = scanner.nextInt();
    double costoPorDia = Double.parseDouble(vehiculos[vehiculoIndex][4]);
    double total = numDias * costoPorDia;

    // Aplicar descuento si el cliente tiene uno
    double descuento = 0.0;
    for (int i = 0; i < numClientes; i++) {
        if (clientes[i][0] != null && clientes[i][0].equals(clienteSesion)) {
            descuento = porcentajeDescuento[i] * total / 100.0;
            break;
        }
    }

    double totalConDescuento = total - descuento;

    // Factura
    System.out.println("=== Factura ===");
    System.out.println("Vehículo: " + vehiculos[vehiculoIndex][0] + " " + vehiculos[vehiculoIndex][1] + " " + vehiculos[vehiculoIndex][2]);
    System.out.println("Placa: " + vehiculos[vehiculoIndex][3]);
    System.out.println("Días de alquiler: " + numDias);
    System.out.println("Costo por día: Q" + costoPorDia);
    System.out.println("Total sin descuento: Q" + total);
    System.out.println("Descuento aplicado: Q" + descuento);
    System.out.println("Total a pagar con descuento: Q" + totalConDescuento);
}
}
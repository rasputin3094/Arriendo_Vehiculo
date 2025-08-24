# Sistema de Arriendo de Vehículos Car-REnt

## Descripción

Car-REnt es un sistema informático desarrollado en Java que permite gestionar el arriendo de vehículos con un sistema de cuotas. El sistema está implementado usando el patrón de diseño MVC (Modelo-Vista-Controlador) y Programación Orientada a Eventos (POE) con interfaces gráficas Swing.

## Características Principales

- **Gestión de Clientes**: Agregar y validar clientes del sistema
- **Gestión de Vehículos**: Visualizar, agregar, modificar y eliminar vehículos
- **Arriendo con Cuotas**: Crear arriendos con sistema de pagos fraccionados
- **Pago de Cuotas**: Procesar pagos individuales de cuotas
- **Validaciones de Negocio**: Control de disponibilidad de vehículos y vigencia de clientes
- **Interfaz Gráfica Intuitiva**: Diseño basado en las especificaciones del proyecto

## Estructura del Proyecto

```
src/
├── App.java                           # Clase principal
├── modelo/                            # Capa del Modelo
│   ├── Cliente.java                   # Entidad Cliente
│   ├── Vehiculo.java                  # Entidad Vehículo
│   ├── Arriendo.java                  # Entidad Arriendo
│   ├── CuotaArriendo.java            # Entidad Cuota
│   ├── InterfazClientes.java         # Interfaz para clientes
│   ├── InterfazArriendoConCuotas.java # Interfaz para arriendos
│   └── InterfazPagarCuotasArriendo.java # Interfaz para pagos
├── vista/                             # Capa de la Vista
│   ├── VentanaPrincipal.java         # Ventana principal del sistema
│   ├── VentanaAgregarCliente.java    # Formulario de clientes
│   ├── VentanaArriendoConCuotas.java # Gestión de arriendos
│   ├── VentanaPagarCuotas.java       # Procesamiento de pagos
│   └── VentanaGestionVehiculos.java  # Gestión completa de vehículos
└── controlador/                       # Capa del Controlador
    └── ControladorPrincipal.java     # Controlador central del sistema
```

## Funcionalidades Implementadas

### 1. Gestión de Clientes
- Agregar nuevos clientes con validación de cédula
- Validación de campos obligatorios
- Control de duplicados
- Estado de vigencia del cliente

### 2. Gestión de Vehículos
- **Visualización**: Tabla completa con todos los vehículos y sus estados
- **Agregar**: Formulario para ingresar nuevos vehículos al sistema
- **Modificar**: Edición de marca y cambio de estado de vehículos
- **Eliminar**: Eliminación segura (no permite eliminar vehículos arrendados)
- **Estados**: Control de condiciones (Disponible, Arrendado, Mantenimiento)
- **Validaciones**: Patente única, campos obligatorios, formatos válidos

### 3. Arriendo con Cuotas
- Selección de cliente y vehículo disponible
- Cálculo automático del monto total
- Generación de cuotas con valores equitativos
- Validación de disponibilidad del vehículo
- Cambio automático del estado del vehículo a "Arrendado"

### 4. Pago de Cuotas
- Selección de cliente y sus arriendos activos
- Visualización de cuotas pendientes y pagadas
- Procesamiento individual de pagos
- Liberación automática del vehículo al completar todos los pagos

## Métodos Principales por Requerimiento

### a) Obtener Monto a Pagar
```java
public int obtenerMonto() {
    return diasArriendo * precioDia;
}
```

### b) Evaluar Arriendo
```java
public boolean evaluarArriendo() {
    // Valida cliente vigente, vehículo disponible y datos válidos
    return cliente.isVigente() && vehiculo.getCondicion() == 'D' && 
           diasArriendo > 0 && precioDia > 0;
}
```

### c) Generar Cuotas
```java
public ArrayList<CuotaArriendo> generarCuotas(int numCuotas) {
    // Crea cuotas equitativas basadas en el monto total
    int valorCuota = obtenerMonto() / numCuotas;
    // ... lógica de generación
}
```

### d) Ingresar Arriendo con Cuotas
```java
public boolean ingresarArriendoConCuota(int numCuotas) {
    if (!evaluarArriendo()) return false;
    vehiculo.setCondicion('A');
    generarCuotas(numCuotas);
    return true;
}
```

### e) Pagar Cuota
```java
public boolean pagarCuota(int numCuota) {
    // Busca y marca la cuota como pagada
    // Retorna true si fue exitoso
}
```

## Validaciones Implementadas

- **Cédula**: Formato válido (12345678-9)
- **Campos Obligatorios**: Nombre, cédula, fecha, días, precio
- **Valores Numéricos**: Días > 0, precio > 0, cuotas > 0
- **Cliente Vigente**: Solo clientes activos pueden arrendar
- **Vehículo Disponible**: Solo vehículos con condición 'D' (Disponible)
- **Duplicados**: Control de clientes existentes

## Cómo Ejecutar

### Compilación
```bash
javac -cp ".\src" -d ".\lib" src\*.java src\modelo\*.java src\vista\*.java src\controlador\*.java
```

### Ejecución
```bash
java -cp ".\lib" App
```

## Datos de Prueba

El sistema se inicializa con datos de ejemplo:

**Clientes:**
- Juan Pérez (12345678-9) - Vigente
- María González (98765432-1) - Vigente  
- Pedro Rodríguez (11111111-1) - No vigente

**Vehículos:**
- Toyota Corolla (ABC123) - Disponible
- Honda Civic (XYZ789) - Disponible
- Nissan Sentra (DEF456) - En mantenimiento

## Principios de POO Aplicados

- **Encapsulación**: Atributos privados con métodos getter/setter
- **Herencia**: No aplicada directamente pero estructura preparada
- **Polimorfismo**: Implementación de interfaces múltiples
- **Abstracción**: Interfaces que definen contratos claros

## Patrón MVC

- **Modelo**: Entidades de negocio e interfaces de datos
- **Vista**: Interfaces gráficas Swing con eventos
- **Controlador**: Lógica de negocio y coordinación entre capas

## Autor

Desarrollado para el curso de Programación Avanzada - Ingeniería en Ciberseguridad

---

© 2025 - Sistema Car-REnt - Todos los derechos reservados

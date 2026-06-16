# clientes-cuentas-microservicio
![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green) ![Tests](https://img.shields.io/badge/maven-3.9.x-blue)

#### Microservicio REST con Spring Boot y Maven para gestionar los clientes de un banco y sus cuentas.

---

## 🚀 Cómo ejecutar el proyecto

Requisitos:
- Java 21
- Maven 3.9+ (Maven Wrapper 3.9.16 incluido en proyecto)
- H2 Database (embebido)
- PowerShell console (recomendado)

1. Clonado del proyecto con Git:
```pwsh
git clone https://github.com/javi13martinez/clientes-cuentas-microservicio.git
```

2. Debemos asegurarnos de que nuestro entorno usa Java 21. En caso de querer activar la JDK localmente (solo a nivel de consola de comandos), podemos hacerlo, en PowerShell, con:
```pwsh
> $env:JAVA_HOME="C:\Program Files\Java\jdk-21.0.11" (u otra dirección donde esté almacenada la JDK)
> $env:Path="$env:JAVA_HOME\bin;$env:Path"
```

3. En la misma consola, arrancamos el proyecto usando el Maven Wrapper incluido en el mismo, con:
```pwsh
> ./mvnw -s maven-settings.xml clean spring-boot:run
```

Y el proyecto Spring estaría lanzado localmente en el puerto http://localhost:8080.

---

## 🧪 Cómo ejecutar los tests

De nuevo, se recomienda el uso del Maven Wrapper y Powershell. Los tests se pueden ejecutar con:

```pwsh
./mvnw -s maven-settings.xml test
```

---

## 📡 Descripción de la API

El proyecto tiene Swagger implementado, por lo que, una vez lanzado, se puede consultar una descripción detallada de la API en el enlace: http://localhost:8080/swagger-ui/index.html

---

## 📤 Ejemplos peticiones posibles

- GET http://localhost:8080/clientes → Obtener lista de clientes activos junto con las cuentas que poseen.
- GET http://localhost:8080/clientes/mayores-de-edad → Obtener lista de clientes mayores de edad, junto con las cuentas que poseen.
- GET http://localhost:8080/clientes/con-cuenta-superior-a/100000 → Obtener lista de clientes con más de 100000 € en el banco, junto con las cuentas que poseen.
- GET http://localhost:8080/clientes/11111111A → Obtener cliente con dni 11111111A, junto con las cuentas que posee.

- POST http://localhost:8080/cuentas → Crear una nueva cuenta bancaria, indicando DNI del cliente, tipo de cuenta y saldo. Si el cliente no existe por el DNI indicado, lo crea. Devuelve los datos de la cuenta creada.
  - Body:
  ```json
  {
    "dniCliente": "11111111A",
    "tipoCuenta": "PREMIUM",
    "total": 1000
  }
  ```

- PUT http://localhost:8080/cuentas/1 → Actualizar saldo de la cuenta bancaria con ID 1 a 100000 €. Devuelve los datos de la cuenta actualizada.
  - Body:
  ```json
  {
    "total": 100000
  }
  ```
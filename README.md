# JavaSoket2 — Sockets TCP en Java

Práctica de la **Sesión 2** del curso **Desarrollo de Servicios Web II** (Cibertec).

Cliente y servidor TCP básicos que intercambian mensajes de texto usando sockets de Java (`java.net`).

## Stack

- **Java 21** (Eclipse Temurin / JDK 21)
- **Maven** (proyecto `cibertec:JavaSoket2:1.0-SNAPSHOT`)
- **IntelliJ IDEA** (proyecto configurado) — también compatible con Eclipse
- Sin dependencias externas: solo librería estándar (`java.io`, `java.net`)

## Estructura

```
src/main/java/cibertec/
├── ServerStream.java   # Servidor TCP (puerto 5000)
├── ClientStream.java   # Cliente TCP (se conecta a localhost:5000)
└── Main.java           # Plantilla de IntelliJ (no participa en los sockets)
```

## Cómo ejecutar

1. Ejecuta primero el **servidor**:
   ```
   cibertec.ServerStream
   # Servidor escuchando en puerto 5000
   ```
2. Luego ejecuta el **cliente** (en otra terminal o proceso):
   ```
   cibertec.ClientStream
   # Conectado al servidor: localhost:5000
   # Mensaje enviado: Hola desde el cliente!
   # Respuesta del servidor: Hola cliente, procesare tu mensaje: 'Hola desde el cliente!'
   ```

## Flujo

Cliente saluda → servidor muestra el mensaje en consola y responde confirmando su recepción → se cierra la conexión. El servidor sigue vivo y atiende al siguiente cliente.

## Diferencias con el código del profesor

El código base es el dictado en clase por el profesor. Se adoptaron sus mejoras cosméticas, con dos diferencias propias:

**Adoptado del profesor:**
- Respuesta del servidor más elaborada (`"Hola cliente, procesare tu mensaje: '...'"`) en vez de un eco seco.
- El cliente imprime el mensaje que envía (`"Mensaje enviado: ..."`).
- Captura de `Exception` genérica con mensaje amable (`"Error en el servidor/cliente: ..."`) además del `printStackTrace()`.

**Diferencias propias (se mantienen):**
- El servidor corre en un bucle `while (true)`: atiende clientes uno tras otro sin detenerse. El del profesor acepta **un solo cliente y termina**.
- Uso de constantes (`HOST`, `PUERTO`) y paquete `cibertec` en vez de variables locales sin paquete.

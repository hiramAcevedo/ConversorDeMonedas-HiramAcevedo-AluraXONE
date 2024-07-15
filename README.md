# 🌎 Conversor de Monedas - ONE Next Education X Alura 💱

Desafío del programa ONE Next Education X Alura consumiendo una API de tasas de cambio. Construido en Java por Hiram Acevedo.

## 🚀 Características

- Convierte entre USD, ARS, BRL y COP
- Utiliza la API de Exchange Rate para obtener tasas de cambio en tiempo real
- Interfaz de línea de comandos fácil de usar

## 🧠 Lógica del Programa

1. 🔑 Verifica la API key al inicio
2. 📊 Obtiene las tasas de cambio más recientes de la API
3. 🔄 Presenta un menú de opciones de conversión al usuario
4. 🧮 Realiza la conversión basada en la selección del usuario
5. 📝 Muestra el resultado de la conversión

## 🛠️ Tecnologías Utilizadas

- Java 17
- Gson para el manejo de JSON
- HttpClient para las peticiones a la API

## 📋 Requisitos Previos

- Java Development Kit (JDK) 17 o superior
- Conexión a Internet para acceder a la API
- Una API key de [Exchange Rate API](https://www.exchangerate-api.com/)

## 🚀 Cómo Ejecutar

1. Clona este repositorio:
   ```
   git clone https://tu-repositorio.git
   ```

2. Navega al directorio del proyecto:
   ```
   cd conversor-de-monedas
   ```

3. Compila el programa:
   ```
   javac -cp path/to/gson.jar CurrencyConverter.java
   ```

4. Ejecuta el programa:
   ```
   java -cp .:path/to/gson.jar CurrencyConverter
   ```

5. Si no has configurado tu API key en el código, el programa te pedirá que la ingreses.

## 💡 Uso

1. Elige una opción del menú (1-6) para seleccionar el tipo de conversión.
2. Ingresa la cantidad que deseas convertir.
3. ¡Observa el resultado de la conversión!
4. Repite o selecciona la opción 7 para salir.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue primero para discutir qué te gustaría cambiar.

## 📜 Licencia

[MIT](https://choosealicense.com/licenses/mit/)

---

Hecho con ❤️ y ☕ por Hiram Acevedo

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    // Reemplaza el valor de API_KEY con tu clave API real de Exchange Rate API
    private static String API_KEY = "TU_API_KEY_AQUI";
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String API_ENDPOINT = "/latest/USD";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();
    private static Map<String, Double> exchangeRates;

    public static void main(String[] args) {
        if (API_KEY.equals("TU_API_KEY_AQUI")) {
            System.out.println("No se ha configurado una API key válida en el código.");
            System.out.print("Por favor, ingrese su API key ahora o presione Enter para salir: ");
            Scanner scanner = new Scanner(System.in);
            String inputKey = scanner.nextLine().trim();
            if (inputKey.isEmpty()) {
                System.out.println("No se proporcionó una API key. El programa se cerrará.");
                return;
            }
            API_KEY = inputKey;
        }

        try {
            fetchExchangeRates();
            runConverterMenu();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }
    }

    private static void fetchExchangeRates() throws IOException, InterruptedException {
        String apiUrl = API_BASE_URL + API_KEY + API_ENDPOINT;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error en la respuesta de la API: " + response.body());
        }

        JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
        JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");

        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("ARS", rates.get("ARS").getAsDouble());
        exchangeRates.put("BRL", rates.get("BRL").getAsDouble());
        exchangeRates.put("COP", rates.get("COP").getAsDouble());
    }

    private static void runConverterMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int option = scanner.nextInt();
            if (option == 7) {
                System.out.println("¡Gracias por usar el Conversor de Moneda!");
                break;
            }
            processOption(option, scanner);
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("***************************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
        System.out.println("1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real brasileño");
        System.out.println("4) Real brasileño => Dólar");
        System.out.println("5) Dólar =>> Peso colombiano");
        System.out.println("6) Peso colombiano =>> Dólar");
        System.out.println("7) Salir");
        System.out.println("Elija una opción válida:");
        System.out.println("***************************************************");
    }

    private static void processOption(int option, Scanner scanner) {
        System.out.println("Ingrese la cantidad a convertir:");
        double amount = scanner.nextDouble();
        double result;

        switch (option) {
            case 1:
                result = convertCurrency(amount, "USD", "ARS");
                System.out.printf("%.2f USD = %.2f ARS%n", amount, result);
                break;
            case 2:
                result = convertCurrency(amount, "ARS", "USD");
                System.out.printf("%.2f ARS = %.2f USD%n", amount, result);
                break;
            case 3:
                result = convertCurrency(amount, "USD", "BRL");
                System.out.printf("%.2f USD = %.2f BRL%n", amount, result);
                break;
            case 4:
                result = convertCurrency(amount, "BRL", "USD");
                System.out.printf("%.2f BRL = %.2f USD%n", amount, result);
                break;
            case 5:
                result = convertCurrency(amount, "USD", "COP");
                System.out.printf("%.2f USD = %.2f COP%n", amount, result);
                break;
            case 6:
                result = convertCurrency(amount, "COP", "USD");
                System.out.printf("%.2f COP = %.2f USD%n", amount, result);
                break;
            default:
                System.out.println("Opción no válida. Por favor, intente de nuevo.");
        }
    }

    private static double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);
        return amount * (toRate / fromRate);
    }
}


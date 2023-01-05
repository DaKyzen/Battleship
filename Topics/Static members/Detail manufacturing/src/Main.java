import java.util.Scanner;

class ManufacturingController {
    // here you may declare a field
    private static int NUMBER_OF_REQUESTED_PRODUCTS = 0;

    public static String requestProduct(String product) {
        // write your code here
        NUMBER_OF_REQUESTED_PRODUCTS++;
        return String.format("%d. Requested %s", NUMBER_OF_REQUESTED_PRODUCTS, product);
    }

    public static int getNumberOfProducts() {
        // write your code here
        return NUMBER_OF_REQUESTED_PRODUCTS;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String product = scanner.nextLine();
            System.out.println(ManufacturingController.requestProduct(product));
            System.out.println(ManufacturingController.getNumberOfProducts());
        }
    }
}
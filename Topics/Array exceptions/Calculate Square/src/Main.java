class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        try {
            int numberToSquare = array[index];
            System.out.println(numberToSquare * numberToSquare);
        } catch (Exception e) {
            System.out.println("Exception!");
        }

    }
}
class Cat {

    // write static and instance variables
    private static int counter = 0;
    private String name;
    private int age;

    public Cat(String name, int age) {
        // implement the constructor
        // do not forget to check the number of cats
        this.name = name;
        this.age = age;
        counter++;

        if (counter > 5)
            System.out.println("You have too many cats");
    }

    public static int getNumberOfCats() {
        // implement the static method
        return counter;
    }
}
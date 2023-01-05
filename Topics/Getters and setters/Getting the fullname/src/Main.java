class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public static void main(String[] args) {
        User tim = new User();
        tim.setFirstName("Tim");
        tim.setLastName("Towler");
        System.out.println(tim.getFullName()); // Tim Towler

        User katie = new User();
        katie.setFirstName("Katie");
        katie.setLastName(null);
        System.out.println(katie.getFullName()); // Katie (without additional spaces)

        User karl = new User();
        katie.setFirstName("");
        katie.setLastName(null);
        System.out.println(karl.getFullName()); // Katie (without additional spaces)
    }

    public void setFirstName(String firstName) {
        if (!isNullOrEmpty(firstName))
            this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (!isNullOrEmpty(lastName))
            this.lastName = lastName;

    }

    public String getFullName() {
        String fullname = ((isNullOrEmpty(firstName) ? "" : firstName) + " " + (isNullOrEmpty(lastName) ? "" : lastName)).trim();
        return (isNullOrEmpty(fullname) ? "Unknown" : fullname);
    }

    private boolean isNullOrEmpty(String string) {
        return "".equals(string) || string == null;
    }
}
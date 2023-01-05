class Publication {

    private String title;

    public Publication(String title) {
        this.title = title;
    }

    public final String getInfo() {
        return getDetails().isBlank() ?
                String.format("%s: %s", getType(), this.title) :
                String.format("%s (%s): %s", getType(), getDetails(), this.title);
    }

    public String getType() {
        return "Publication";
    }

    public String getDetails() {
        return "";
    }

}

class Newspaper extends Publication {

    private String source;

    public Newspaper(String title, String source) {
        super(title);
        this.source = source;
    }

    @Override
    public String getType() {
        return "Newspaper";
    }

    @Override
    public String getDetails() {
        return String.format("source - %s", this.source);
    }
}

class Article extends Publication {

    private String author;

    public Article(String title, String author) {
        super(title);
        this.author = author;
    }

    @Override
    public String getType() {
        return "Article";
    }
    @Override
    public String getDetails() {
        return String.format("author - %s", this.author);
    }
}

class Announcement extends Publication {

    private int daysToExpire;

    public Announcement(String title, int daysToExpire) {
        super(title);
        this.daysToExpire = daysToExpire;
    }
    @Override
    public String getType() {
        return "Announcement";
    }
    @Override
    public String getDetails() {
        return String.format("days to expire - %d", this.daysToExpire);
    }

}
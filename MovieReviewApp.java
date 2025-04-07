import java.util.*;

class Review {
    String user;
    int rating;
    String comment;

    public Review(String user, int rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return user + " (" + rating + "/5): " + comment;
    }
}

class Movie {
    String title;
    List<Review> reviews;

    public Movie(String title) {
        this.title = title;
        this.reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) return 0;
        int total = 0;
        for (Review r : reviews) total += r.rating;
        return (double) total / reviews.size();
    }

    public void showReviews() {
        if (reviews.isEmpty()) {
            System.out.println("No reviews yet for " + title);
            return;
        }
        for (Review r : reviews) {
            System.out.println(" - " + r);
        }
    }
}

public class MovieReviewApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Movie> movieMap = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n🎬 Movie Review App 🎬");
            System.out.println("1. Add Movie");
            System.out.println("2. Submit Review");
            System.out.println("3. View All Movies");
            System.out.println("4. View Reviews for a Movie");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> addMovie();
                case "2" -> submitReview();
                case "3" -> viewAllMovies();
                case "4" -> viewReviews();
                case "5" -> {
                    System.out.println("Exiting Movie Review App 🎥");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addMovie() {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine().trim();
        if (movieMap.containsKey(title)) {
            System.out.println("Movie already exists.");
        } else {
            movieMap.put(title, new Movie(title));
            System.out.println("Movie added.");
        }
    }

    private static void submitReview() {
        System.out.print("Movie title: ");
        String title = scanner.nextLine().trim();
        Movie movie = movieMap.get(title);
        if (movie == null) {
            System.out.println("Movie not found.");
            return;
        }

        System.out.print("Your name: ");
        String user = scanner.nextLine();
        System.out.print("Rating (1-5): ");
        int rating = Integer.parseInt(scanner.nextLine());
        System.out.print("Comment: ");
        String comment = scanner.nextLine();

        movie.addReview(new Review(user, rating, comment));
        System.out.println("Review added!");
    }

    private static void viewAllMovies() {
        if (movieMap.isEmpty()) {
            System.out.println("No movies added yet.");
            return;
        }
        System.out.println("\n🎞️ All Movies:");
        for (String title : movieMap.keySet()) {
            double avg = movieMap.get(title).getAverageRating();
            System.out.printf("- %s (Avg Rating: %.2f)\n", title, avg);
        }
    }

    private static void viewReviews() {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine().trim();
        Movie movie = movieMap.get(title);
        if (movie == null) {
            System.out.println("Movie not found.");
            return;
        }
        System.out.println("Reviews for " + title + ":");
        movie.showReviews();
    }
}

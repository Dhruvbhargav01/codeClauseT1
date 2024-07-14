package CodeClauseProjects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Movie {
    private String title;
    private String genre;
    private int duration;
    private List<Showtime> showtimes;

    public Movie(String title, String genre, int duration, List<Showtime> showtimes) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.showtimes = showtimes;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ", " + duration + " mins)";
    }
}

class Showtime {
    private LocalDateTime time;
    private Theater theater;

    public Showtime(LocalDateTime time, Theater theater) {
        this.time = time;
        this.theater = theater;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Theater getTheater() {
        return theater;
    }

    @Override
    public String toString() {
        return time.toString() + " at " + theater.getName();
    }
}

class Theater {
    private String name;
    private String location;
    private List<Seat> seats;

    public Theater(String name, String location, List<Seat> seats) {
        this.name = name;
        this.location = location;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
}

class Seat {
    private int seatNumber;
    private boolean isAvailable;

    public Seat(int seatNumber, boolean isAvailable) {
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Seat " + seatNumber + (isAvailable ? " (Available)" : " (Booked)");
    }
}

class Reservation {
    private Movie movie;
    private Showtime showtime;
    private List<Seat> seats;
    private User user;

    public Reservation(Movie movie, Showtime showtime, List<Seat> seats, User user) {
        this.movie = movie;
        this.showtime = showtime;
        this.seats = seats;
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Reservation for " + movie.getTitle() + " at " + showtime.getTime() + "\nSeats: " + seats + "\nUser: " + user.getName();
    }
}

class User {
    private String name;
    private String contactInfo;

    public User(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public String toString() {
        return name + " (" + contactInfo + ")";
    }
}

public class movieBookingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create sample data
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            seats.add(new Seat(i, true));
        }

        Theater theater = new Theater("Downtown Cinema", "123 Main St", seats);

        List<Showtime> showtimes = new ArrayList<>();
        showtimes.add(new Showtime(LocalDateTime.now().plusDays(1).withHour(19).withMinute(0), theater));
        showtimes.add(new Showtime(LocalDateTime.now().plusDays(1).withHour(21).withMinute(30), theater));

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Inception", "Sci-Fi", 148, showtimes));
        movies.add(new Movie("Titanic", "Romance", 195, showtimes));

        List<Reservation> reservations = new ArrayList<>();

        System.out.println("Welcome to the Movie Ticket Booking System!");
        System.out.println("Available Movies:");

        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }

        System.out.print("Choose a movie by number: ");
        int movieChoice = scanner.nextInt();
        Movie selectedMovie = movies.get(movieChoice - 1);

        System.out.println("Showtimes for " + selectedMovie.getTitle() + ":");

        List<Showtime> availableShowtimes = selectedMovie.getShowtimes();
        for (int i = 0; i < availableShowtimes.size(); i++) {
            System.out.println((i + 1) + ". " + availableShowtimes.get(i));
        }

        System.out.print("Choose a showtime by number: ");
        int showtimeChoice = scanner.nextInt();
        Showtime selectedShowtime = availableShowtimes.get(showtimeChoice - 1);

        System.out.println("Available Seats:");
        List<Seat> availableSeats = selectedShowtime.getTheater().getSeats();
        for (Seat seat : availableSeats) {
            System.out.println(seat);
        }

        System.out.print("Choose seat numbers (comma separated): ");
        String seatChoices = scanner.next();
        String[] seatNumbers = seatChoices.split(",");
        List<Seat> selectedSeats = new ArrayList<>();
        for (String seatNumber : seatNumbers) {
            int seatNum = Integer.parseInt(seatNumber.trim());
            Seat seat = availableSeats.get(seatNum - 1);
            if (seat.isAvailable()) {
                seat.setAvailable(false);
                selectedSeats.add(seat);
            } else {
                System.out.println("Seat " + seatNum + " is already booked.");
            }
        }

        System.out.print("Enter your name: ");
        String userName = scanner.next();
        System.out.print("Enter your contact info: ");
        String contactInfo = scanner.next();
        User user = new User(userName, contactInfo);

        Reservation reservation = new Reservation(selectedMovie, selectedShowtime, selectedSeats, user);
        reservations.add(reservation);

        System.out.println("Booking Confirmed!");
        System.out.println(reservation);
    }
}


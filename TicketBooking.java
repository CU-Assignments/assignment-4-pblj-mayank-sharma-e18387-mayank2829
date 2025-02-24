import java.util.*;

class TicketBookingSystem {
    private int availableSeats;

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    public synchronized boolean bookSeat(String customerName) {
        if (availableSeats > 0) {
            System.out.println(customerName + " successfully booked a seat. Remaining seats: " + (availableSeats - 1));
            availableSeats--;
            return true;
        } else {
            System.out.println(customerName + " failed to book a seat. No seats available.");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem bookingSystem;
    private String customerName;

    public BookingThread(TicketBookingSystem bookingSystem, String customerName, int priority) {
        this.bookingSystem = bookingSystem;
        this.customerName = customerName;
        setPriority(priority);
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(customerName);
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5); // 5 available seats

        List<BookingThread> customers = new ArrayList<>();
        customers.add(new BookingThread(system, "VIP Customer 1", Thread.MAX_PRIORITY));
        customers.add(new BookingThread(system, "VIP Customer 2", Thread.MAX_PRIORITY));
        customers.add(new BookingThread(system, "Regular Customer 1", Thread.NORM_PRIORITY));
        customers.add(new BookingThread(system, "Regular Customer 2", Thread.NORM_PRIORITY));
        customers.add(new BookingThread(system, "Regular Customer 3", Thread.NORM_PRIORITY));
        customers.add(new BookingThread(system, "Regular Customer 4", Thread.NORM_PRIORITY));

        for (BookingThread customer : customers) {
            customer.start();
        }
    }
}

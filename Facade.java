public class Facade {
    private Hotel hotel;
    private Rental rental;
    private Flight flight;
    private Payment payment;
    private Reservation reservation;

    public Facade(Hotel hotel, Rental rental, Flight flight, Payment payment, Reservation reservation) {
        this.hotel = new Hotel();
        this.rental = new Rental();
        this.flight = new Flight();
        this.payment = new Payment();
        this.reservation = new Reservation();
    }

    public String bookTrip(Hotel hotelInfo, Rental rentalInfo, Flight flightInfo, Payment paymentInfo, Reservation reservationInfo) {
        boolean hotelAvailable = hotel.isAvailable(hotelInfo);
        boolean flightAvailable = flight.isAvailable(flightInfo);

        if (hotelAvailable && flightAvailable) {
            boolean paymentProcessed = payment.processPayment(paymentInfo);

            if (paymentProcessed) {
                boolean hasRental = rental.wasRequested(rentalInfo);

                if (hasRental) {
                    reservation.createReservation(hotelInfo, flightInfo, rentalInfo, paymentInfo);
                } else {
                    reservation.createReservation(hotelInfo, flightInfo, null, paymentInfo);
                }
                return "Reserva efetuada com sucesso!";
            }
        }
        return "Não foi possível efetuar a reserva.";
    }
}

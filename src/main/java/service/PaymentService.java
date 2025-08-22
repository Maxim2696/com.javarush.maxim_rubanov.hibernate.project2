package service;

import entity.Customer;
import entity.Payment;
import entity.Rental;
import entity.Staff;
import repository.entity_rep.PaymentRep;

import java.math.BigDecimal;

public class PaymentService {
    private final PaymentRep paymentRep;

    public PaymentService(PaymentRep paymentRep) {
        this.paymentRep = paymentRep;
    }

    public void addPaymentByCustomerByRental(Customer customer, Staff staff, Rental rental, Double amount) {
        Payment payment = new Payment();
        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setRental(rental);
        payment.setAmount(new BigDecimal(amount));
        paymentRep.create(payment);
    };
}

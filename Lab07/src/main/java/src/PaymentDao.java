package src;

import java.util.List;

public interface PaymentDao {
    public void addPayment (Payment p);
    public List<Payment> getAllPayments();
    public Payment getPayment(int id);
    public void updatePayment (Payment p);
    public void deletePayment(int id);
}

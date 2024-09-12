// Kelas utama aplikasi pembayaran
public class BasicPaymentApp {

    // Method untuk memproses pembayaran berdasarkan jenis metode pembayaran
    public void processPayment(String paymentType, double amount) throws PaymentException {
        if (paymentType.equals("CREDIT_CARD")) {
            processCreditCardPayment(amount);  // Logika pembayaran via Kartu Kredit
        } else if (paymentType.equals("BANK_TRANSFER")) {
            processBankTransferPayment(amount);  // Logika pembayaran via Transfer Bank
        } else if (paymentType.equals("E_WALLET")) {
            processEWalletPayment(amount);  // Logika pembayaran via E-Wallet
        } else {
            // Jika metode pembayaran tidak dikenali, lempar pengecualian
            throw new PaymentException("Payment method not supported.");
        }
    }

    // Method untuk memproses pembayaran via Kartu Kredit
    private void processCreditCardPayment(double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Invalid amount for credit card payment.");
        }
        System.out.println("Payment of $" + amount + " processed via Credit Card.");
    }

    // Method untuk memproses pembayaran via Transfer Bank
    private void processBankTransferPayment(double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Invalid amount for bank transfer payment.");
        }
        System.out.println("Payment of $" + amount + " processed via Bank Transfer.");
    }

    // Method untuk memproses pembayaran via E-Wallet
    private void processEWalletPayment(double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Invalid amount for e-wallet payment.");
        }
        System.out.println("Payment of $" + amount + " processed via E-Wallet.");
    }

    // Method main untuk menjalankan aplikasi
    public static void main(String[] args) {
        // Buat instance dari BasicPaymentApp
        BasicPaymentApp app = new BasicPaymentApp();

        // Proses beberapa pembayaran
        try {
            app.processPayment("CREDIT_CARD", 150.00);
            app.processPayment("E_WALLET", 200.00);
            app.processPayment("BANK_TRANSFER", 300.00);
        } catch (PaymentException e) {
            e.printStackTrace();
        }
    }
}

// Kelas Exception untuk menangani error pembayaran
class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
}

import java.util.HashMap;
import java.util.Map;

// Kelas utama aplikasi pembayaran
public class PaymentApp {

    // Interface PaymentMethod: setiap metode pembayaran harus mengimplementasikan interface ini
    public interface PaymentMethod {
        // Method untuk memproses pembayaran, menerima jumlah pembayaran dan mengembalikan objek Payment
        Payment processPayment(double amount) throws PaymentException;
    }

    // Kelas Payment: menyimpan informasi tentang pembayaran yang berhasil
    public class Payment {
        private double amount; // Jumlah pembayaran

        // Konstruktor untuk menginisialisasi objek Payment dengan jumlah tertentu
        public Payment(double amount) {
            this.amount = amount;
        }

        // Method untuk mendapatkan jumlah pembayaran
        public double getAmount() {
            return amount;
        }

        // Mengembalikan representasi string dari pembayaran yang berhasil
        @Override
        public String toString() {
            return "Payment of $" + amount + " processed.";
        }
    }

    // Kelas Exception PaymentException: digunakan untuk menangani kesalahan pembayaran
    public class PaymentException extends Exception {
        // Konstruktor yang menerima pesan error
        public PaymentException(String message) {
            super(message);
        }
    }

    // Implementasi metode pembayaran via Credit Card
    public class CreditCardPayment implements PaymentMethod {
        @Override
        public Payment processPayment(double amount) throws PaymentException {
            // Jika jumlah tidak valid, lemparkan PaymentException
            if (amount <= 0) {
                throw new PaymentException("Invalid amount for credit card payment.");
            }
            // Jika jumlah valid, kembalikan objek Payment
            return new Payment(amount);
        }
    }

    // Implementasi metode pembayaran via Bank Transfer
    public class BankTransferPayment implements PaymentMethod {
        @Override
        public Payment processPayment(double amount) throws PaymentException {
            // Jika jumlah tidak valid, lemparkan PaymentException
            if (amount <= 0) {
                throw new PaymentException("Invalid amount for bank transfer payment.");
            }
            // Jika jumlah valid, kembalikan objek Payment
            return new Payment(amount);
        }
    }

    // Implementasi metode pembayaran via E-Wallet
    public class EWalletPayment implements PaymentMethod {
        @Override
        public Payment processPayment(double amount) throws PaymentException {
            // Jika jumlah tidak valid, lemparkan PaymentException
            if (amount <= 0) {
                throw new PaymentException("Invalid amount for e-wallet payment.");
            }
            // Jika jumlah valid, kembalikan objek Payment
            return new Payment(amount);
        }
    }

    // Enum untuk jenis pembayaran yang tersedia
    public enum PaymentSelection {
        CREDIT_CARD, BANK_TRANSFER, E_WALLET; // Pilihan metode pembayaran
    }

    // Kelas untuk mengelola aplikasi pembayaran
    public class BasicPaymentApp {
        private Map<PaymentSelection, PaymentMethod> paymentMethods; // Peta metode pembayaran

        // Konstruktor yang menerima peta metode pembayaran
        public BasicPaymentApp(Map<PaymentSelection, PaymentMethod> paymentMethods) {
            this.paymentMethods = paymentMethods;
        }

        // Method untuk memproses pembayaran dengan jenis metode dan jumlah yang dipilih
        public Payment processPayment(PaymentSelection selection, double amount) throws PaymentException {
            // Cari metode pembayaran berdasarkan pilihan
            PaymentMethod method = this.paymentMethods.get(selection);
            // Jika metode tidak ditemukan, lempar PaymentException
            if (method == null) {
                throw new PaymentException("Payment method not supported.");
            }
            // Proses pembayaran dan kembalikan hasil
            Payment payment = method.processPayment(amount);
            System.out.println("Payment is successful!"); // Tampilkan pesan keberhasilan
            return payment;
        }
    }

    // Method main untuk menjalankan aplikasi
    public static void main(String[] args) {
        // Buat instance dari PaymentApp
        PaymentApp appInstance = new PaymentApp();
        
        // Buat peta metode pembayaran yang tersedia
        Map<PaymentSelection, PaymentMethod> methods = new HashMap<>();
        methods.put(PaymentSelection.CREDIT_CARD, appInstance.new CreditCardPayment()); // Tambahkan CreditCardPayment
        methods.put(PaymentSelection.BANK_TRANSFER, appInstance.new BankTransferPayment()); // Tambahkan BankTransferPayment
        methods.put(PaymentSelection.E_WALLET, appInstance.new EWalletPayment()); // Tambahkan EWalletPayment

        // Buat objek BasicPaymentApp dengan metode pembayaran yang tersedia
        BasicPaymentApp app = appInstance.new BasicPaymentApp(methods);

        // Proses pembayaran menggunakan kartu kredit
        try {
            Payment payment = app.processPayment(PaymentSelection.CREDIT_CARD, 150.00); // Bayar $150 dengan kartu kredit
            System.out.println(payment); // Tampilkan hasil pembayaran
        } catch (PaymentException e) {
            e.printStackTrace(); // Tangani jika terjadi error pada pembayaran
        }
    }
}

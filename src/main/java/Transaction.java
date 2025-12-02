import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

enum Status {
    FAILED,
    SUCCESSEFUL,
    WAITS
}

public class Transaction {
    private String receiverBankInformation;
    private String payerInformation;
    private double value;
    private String currency;
    private Status status;
    private LocalDate dateOfTransaction;

    private Transaction processedBy;
    private Set<Transaction> processedTransaction=new HashSet<>();

    public Transaction(String receiverBankInformation, String payerInformation, double value, String currency, Status status, LocalDate dateOfTransaction) {
        this.receiverBankInformation = receiverBankInformation;
        this.payerInformation = payerInformation;
        this.value = value;
        this.currency = currency;
        this.status = status;
        this.dateOfTransaction = dateOfTransaction;

    }

    public Transaction getProcessedBy() {
        return processedBy;
    }

    public Set<Transaction> getProcessedTransaction() {
        return processedTransaction;
    }

    public void setProcessedBy(Transaction parent){
        if (parent==this){
            throw new IllegalArgumentException("Transaction cannot process itself");
        }

        if (this.processedBy==parent){
            return;
        }

        if(this.processedBy!=null){
            this.processedBy.processedTransaction.remove(this);
        }

        if (parent!=null){
            if (parent.processedTransaction.contains(this)){
                throw new IllegalStateException("Duplicate processed transaction");
            }
            parent.processedTransaction.add(this);
        }
    }

    public void addProcessedTransaction(Transaction child) {
        if (child == null) {
            throw new IllegalArgumentException("Child transaction cannot be null");
        }

        if (child == this) {
            throw new IllegalArgumentException("Transaction cannot process itself");
        }

        if (processedTransaction.contains(child)) {
            throw new IllegalStateException("This transaction already processes the given transaction");
        }

        processedTransaction.add(child);
        child.setProcessedBy(this);
    }

    public void removeProcessedTransaction(Transaction child) {
        if (child == null) return;

        if (!processedTransaction.contains(child)) {
            throw new IllegalStateException("Transaction not found in processed list");
        }

        processedTransaction.remove(child);
        child.processedBy = null;
    }
}

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

enum Status{
    FAILED,
    SUCCESSFUL,
    WAITS
}
public class Transaction {
    private String receiverBankInformation;
    private String payerInformation;
    private double value;
    private String currency;
    private LocalDate dateOfTransaction;

    private Transaction parent;

    private final List<Transaction> children=new ArrayList<>();

    public Transaction(String receiver, String payer, double value, String currency) {
        this.receiverBankInformation = receiver;
        this.payerInformation = payer;
        this.value = value;
        this.currency = currency;
        this.dateOfTransaction = LocalDate.now();
    }

    public void addChild(Transaction child) {

        if (child == null)
            throw new IllegalArgumentException("Child transaction cannot be null");

        if (child == this)
            throw new IllegalStateException("Transaction cannot be child of itself");

        if (children.contains(child))
            throw new IllegalStateException("This child is already connected");

        if (child.getParent() != null)
            throw new IllegalStateException("This transaction already has a parent and cannot be reassigned.");

        if (isAncestor(child))
            throw new IllegalStateException("Cycle detected in reflexive association");

        children.add(child);

        child.setParent(this);
    }

    public void removeChild(Transaction child) {
        if (!children.contains(child))
            throw new IllegalArgumentException("Child not found in this parent");

        children.remove(child);

        child.setParent(null);
    }

    public void setParent(Transaction newParent) {
        if (newParent == this) {
            throw new IllegalStateException("Transaction cannot be its own parent");
        }

        if (newParent != null && newParent.isAncestor(this)) {
            throw new IllegalStateException("Cycle detected in reflexive association");
        }

        if (this.parent == newParent) {
            return;
        }

        if (this.parent != null) {
            this.parent.children.remove(this);
        }

        this.parent = newParent;

        if (newParent != null && !newParent.children.contains(this)) {
            newParent.children.add(this);
        }
    }


    public Transaction getParent() {
        return parent;
    }

    public List<Transaction> getChildren() {
        return new ArrayList<>(children);
    }

    private boolean isAncestor(Transaction candidate) {
        Transaction current = this;
        while (current != null) {
            if (current == candidate){
                return true;
            }
            current = current.parent;
        }
        return false;
    }

    public String getReceiverBankInformation() {
        return receiverBankInformation;
    }

    public String getPayerInformation() {
        return payerInformation;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }
}

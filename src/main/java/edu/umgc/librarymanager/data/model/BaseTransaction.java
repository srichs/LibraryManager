/*
 * Filename: BaseTransaction.java
 * Author: Scott
 * Date Created: 11/26/2020
 */

package edu.umgc.librarymanager.data.model;

import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.ILibraryItem;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.IUser;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * This class is used to create a transaction that can be stored in the
 * database. The class has annotations so that a transaction will be stored
 * in the hibernate database.
 * @author Scott
 */
@Entity
@Indexed
@Table(name = "base_transaction")
public class BaseTransaction implements ILibraryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long id;

    @IndexedEmbedded
    @OneToOne(cascade = CascadeType.ALL)
    private Library library;

    @IndexedEmbedded
    @OneToOne(cascade = CascadeType.ALL)
    private BaseItem item;

    @IndexedEmbedded
    @OneToOne(cascade = CascadeType.ALL)
    private BaseUser user;

    @Column(name = "transaction_date")
    private ZonedDateTime transactionDateTime;

    @Field
    @Column(name = "due_date")
    private ZonedDateTime dueDate;

    @Column(name = "fee")
    private BigDecimal fee;

    @Column(name = "renew_date")
    private ZonedDateTime renewDate;

    @Column(name = "renew_count")
    private int renewCount;

    @Field
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private TransactionType transactionType;

    /**
     * The default constructor of the class.
     */
    public BaseTransaction() {
        this.library = null;
        this.item = null;
        this.user = null;
        this.transactionDateTime = null;
        this.dueDate = null;
        this.fee = null;
        this.renewDate = null;
        this.renewCount = 0;
        this.transactionType = null;
    }

    /**
     * A constructor with parameters for each field of the class.
     * @param library A Library that is conducting the transaction.
     * @param item An item that is invovled in the transaction.
     * @param user The user that is involved in the transaction.
     * @param transactionDate The date and time the transaction takes place.
     * @param dueDate The due date of the item.
     * @param fee The fee associated with the item depending on the transaction type.
     * @param renewDate The renewal date.
     * @param renewCount The renewal count.
     */
    public BaseTransaction(Library library, BaseItem item, BaseUser user, ZonedDateTime transactionDate,
            ZonedDateTime dueDate, double fee, ZonedDateTime renewDate, int renewCount, TransactionType type) {
        this.library = library;
        this.item = item;
        this.user = user;
        this.transactionDateTime = transactionDate;
        this.dueDate = dueDate;
        this.fee = new BigDecimal(fee);
        this.renewDate = renewDate;
        this.renewCount = renewCount;
        this.transactionType = type;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public Library getLibrary() {
        return this.library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public ILibraryItem getItem() {
        return this.item;
    }

    public void setItem(BaseItem item) {
        this.item = item;
    }

    @Override
    public IUser getUser() {
        return this.user;
    }

    public void setUser(BaseUser user) {
        this.user = user;
    }

    @Override
    public ZonedDateTime getTransactionDateTime() {
        return this.transactionDateTime;
    }

    public void setTransactionDateTime(ZonedDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    @Override
    public ZonedDateTime getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public double getFee() {
        return this.fee.doubleValue();
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public ZonedDateTime getRenewDate() {
        return this.renewDate;
    }

    public void setRenewDate(ZonedDateTime renewDate) {
        this.renewDate = renewDate;
    }

    @Override
    public int getRenewCount() {
        return this.renewCount;
    }

    public void setRenewCount(int renewCount) {
        this.renewCount = renewCount;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

}

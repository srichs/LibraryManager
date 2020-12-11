/*
 * Filename: BaseUser.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.user;

import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 * Models a library management system user. Uses hibernate annotations to map the
 * user's data to the hibernate database.
 * @author Scott
 */
@AnalyzerDef(name = "ngram", // Custom analyzer that uses n-grams of size 4 to analyze tokens
    tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
    filters = {
        @TokenFilterDef(factory = StandardFilterFactory.class),
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = StopFilterFactory.class),
        @TokenFilterDef(factory = NGramFilterFactory.class,
        params = {
            @Parameter(name = "minGramSize", value = "3"),
            @Parameter(name = "maxGramSize", value = "4")
        })
    }
)
@AnalyzerDef(name = "text_analyzer",
    tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
    filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = SnowballPorterFilterFactory.class,
            params = {@Parameter(name = "language", value = "English")}
        )
    }
)
@Entity
@Table(name = "base_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseUser implements IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    @Column(name = "base_user_id")
    private int id;

    @Field(name = "date_time_created")
    @DateBridge(resolution = Resolution.SECOND)
    @Column(name = "date_time_created")
    private ZonedDateTime dateTimeCreated;

    @Field(name = "first_name")
    @Analyzer(definition = "ngram")
    @Column(name = "first_name")
    private String firstName;

    @Field(name = "last_name")
    @Analyzer(definition = "ngram")
    @Column(name = "last_name")
    private String lastName;

    @IndexedEmbedded(includeEmbeddedObjectId = true)
    @OneToOne(cascade = CascadeType.ALL)
    private UserLogin login;

    @Field(name = "email")
    @Analyzer(definition = "ngram")
    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private UserType userType;

    /**
     * Default constructor of the class.
     */
    public BaseUser() {
        this.id = -1;
        this.dateTimeCreated = ZonedDateTime.now();
        this.firstName = "";
        this.lastName = "";
        this.login = new UserLogin();
        this.email = "";
        this.address = "";
        this.phone = "";
        this.userType = null;
    }

    /**
     * Constructor of the class.
     * @param cDateTime The Date and Time of creation.
     * @param fName The User's First Name.
     * @param lName The User's Last Name.
     * @param login The login information for the User.
     * @param email The User's Email Address.
     * @param address The User's Address.
     * @param phone The User's Phone Number.
     * @param uType The type of user.
     */
    public BaseUser(ZonedDateTime cDateTime, String fName, String lName, UserLogin login, String email,
            String address, String phone, UserType uType) {
        this.dateTimeCreated = cDateTime;
        this.firstName = fName;
        this.lastName = lName;
        this.login = login;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.userType = uType;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public ZonedDateTime createdDateTime() {
        return this.dateTimeCreated;
    }

    @Override
    public String getUserName() {
        return this.login.getUsername();
    }

    @Override
    public String getPassword() {
        return this.login.getPassword();
    }

    @Override
    public String getPhoneNumber() {
        return this.phone;
    }

    public void setCreatedDateTime(ZonedDateTime zdt) {
        this.dateTimeCreated = zdt;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public UserLogin getLogin() {
        return this.login;
    }

    public void setLogin(UserLogin login) {
        this.login = login;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType type) {
        this.userType = type;
    }

    /**
     * Creates a String with the base user information.
     */
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + "\nUsername: " + this.login.getUsername()
                + "\nPassword: " + this.login.getPassword() + "\nPhone: " + this.phone + "\nEmail: " + this.email
                + "\nUser Type: " + this.userType.toString();
    }

}

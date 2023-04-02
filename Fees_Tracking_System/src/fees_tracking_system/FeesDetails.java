/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "FEES_DETAILS", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "FeesDetails.findAll", query = "SELECT f FROM FeesDetails f")
    , @NamedQuery(name = "FeesDetails.findByReceiptNo", query = "SELECT f FROM FeesDetails f WHERE f.receiptNo = :receiptNo")
    , @NamedQuery(name = "FeesDetails.findByStudentName", query = "SELECT f FROM FeesDetails f WHERE f.studentName = :studentName")
    , @NamedQuery(name = "FeesDetails.findByRollNo", query = "SELECT f FROM FeesDetails f WHERE f.rollNo = :rollNo")
    , @NamedQuery(name = "FeesDetails.findByPaymentMode", query = "SELECT f FROM FeesDetails f WHERE f.paymentMode = :paymentMode")
    , @NamedQuery(name = "FeesDetails.findByChequeNo", query = "SELECT f FROM FeesDetails f WHERE f.chequeNo = :chequeNo")
    , @NamedQuery(name = "FeesDetails.findByBankName", query = "SELECT f FROM FeesDetails f WHERE f.bankName = :bankName")
    , @NamedQuery(name = "FeesDetails.findByDdNo", query = "SELECT f FROM FeesDetails f WHERE f.ddNo = :ddNo")
    , @NamedQuery(name = "FeesDetails.findByFeesName", query = "SELECT f FROM FeesDetails f WHERE f.feesName = :feesName")
    , @NamedQuery(name = "FeesDetails.findByTotalAmount", query = "SELECT f FROM FeesDetails f WHERE f.totalAmount = :totalAmount")
    , @NamedQuery(name = "FeesDetails.findByDate", query = "SELECT f FROM FeesDetails f WHERE f.date = :date")
    , @NamedQuery(name = "FeesDetails.findByAmount", query = "SELECT f FROM FeesDetails f WHERE f.amount = :amount")
    , @NamedQuery(name = "FeesDetails.findByTotalInWords", query = "SELECT f FROM FeesDetails f WHERE f.totalInWords = :totalInWords")
    , @NamedQuery(name = "FeesDetails.findByRemark", query = "SELECT f FROM FeesDetails f WHERE f.remark = :remark")
    , @NamedQuery(name = "FeesDetails.findByYear1", query = "SELECT f FROM FeesDetails f WHERE f.year1 = :year1")
    , @NamedQuery(name = "FeesDetails.findByYear2", query = "SELECT f FROM FeesDetails f WHERE f.year2 = :year2")})
public class FeesDetails implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RECEIPT_NO")
    private Integer receiptNo;
    @Column(name = "STUDENT_NAME")
    private String studentName;
    @Column(name = "ROLL_NO")
    private String rollNo;
    @Column(name = "PAYMENT_MODE")
    private String paymentMode;
    @Column(name = "CHEQUE_NO")
    private String chequeNo;
    @Column(name = "BANK_NAME")
    private String bankName;
    @Column(name = "DD_NO")
    private String ddNo;
    @Column(name = "FEE_NAME")
    private String feesName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "TOTAL_IN_WORDS")
    private String totalInWords;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "YEAR1")
    private Integer year1;
    @Column(name = "YEAR2")
    private Integer year2;

    public FeesDetails() {
    }

    public FeesDetails(Integer receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Integer getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(Integer receiptNo) {
        Integer oldReceiptNo = this.receiptNo;
        this.receiptNo = receiptNo;
        changeSupport.firePropertyChange("receiptNo", oldReceiptNo, receiptNo);
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        String oldStudentName = this.studentName;
        this.studentName = studentName;
        changeSupport.firePropertyChange("studentName", oldStudentName, studentName);
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        String oldRollNo = this.rollNo;
        this.rollNo = rollNo;
        changeSupport.firePropertyChange("rollNo", oldRollNo, rollNo);
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        String oldPaymentMode = this.paymentMode;
        this.paymentMode = paymentMode;
        changeSupport.firePropertyChange("paymentMode", oldPaymentMode, paymentMode);
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        String oldChequeNo = this.chequeNo;
        this.chequeNo = chequeNo;
        changeSupport.firePropertyChange("chequeNo", oldChequeNo, chequeNo);
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        String oldBankName = this.bankName;
        this.bankName = bankName;
        changeSupport.firePropertyChange("bankName", oldBankName, bankName);
    }

    public String getDdNo() {
        return ddNo;
    }

    public void setDdNo(String ddNo) {
        String oldDdNo = this.ddNo;
        this.ddNo = ddNo;
        changeSupport.firePropertyChange("ddNo", oldDdNo, ddNo);
    }

    public String getFeesName() {
        return feesName;
    }

    public void setFeesName(String feesName) {
        String oldFeesName = this.feesName;
        this.feesName = feesName;
        changeSupport.firePropertyChange("feesName", oldFeesName, feesName);
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        Double oldTotalAmount = this.totalAmount;
        this.totalAmount = totalAmount;
        changeSupport.firePropertyChange("totalAmount", oldTotalAmount, totalAmount);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        Date oldDate = this.date;
        this.date = date;
        changeSupport.firePropertyChange("date", oldDate, date);
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        Double oldAmount = this.amount;
        this.amount = amount;
        changeSupport.firePropertyChange("amount", oldAmount, amount);
    }

    public String getTotalInWords() {
        return totalInWords;
    }

    public void setTotalInWords(String totalInWords) {
        String oldTotalInWords = this.totalInWords;
        this.totalInWords = totalInWords;
        changeSupport.firePropertyChange("totalInWords", oldTotalInWords, totalInWords);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        String oldRemark = this.remark;
        this.remark = remark;
        changeSupport.firePropertyChange("remark", oldRemark, remark);
    }

    public Integer getYear1() {
        return year1;
    }

    public void setYear1(Integer year1) {
        Integer oldYear1 = this.year1;
        this.year1 = year1;
        changeSupport.firePropertyChange("year1", oldYear1, year1);
    }

    public Integer getYear2() {
        return year2;
    }

    public void setYear2(Integer year2) {
        Integer oldYear2 = this.year2;
        this.year2 = year2;
        changeSupport.firePropertyChange("year2", oldYear2, year2);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receiptNo != null ? receiptNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeesDetails)) {
            return false;
        }
        FeesDetails other = (FeesDetails) object;
        if ((this.receiptNo == null && other.receiptNo != null) || (this.receiptNo != null && !this.receiptNo.equals(other.receiptNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fees_tracking_system.FeesDetails[ receiptNo=" + receiptNo + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

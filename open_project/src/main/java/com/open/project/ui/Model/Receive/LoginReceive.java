package com.open.project.ui.Model.Receive;

public class LoginReceive {

    private String Status;
    private String Message;
    private String CustomerNumber;
    private String FirstName;
    private String LastName;
    private String Token;
    private String TicketId;
    private String UserName;
    private String Hash;
    private String Signature;
    private String Profile_URL;
    private String BigPoint;
    private String userEmail;
    private String salutation;
    private String MobilePhone;
    private String phoneNo;
    private String countryCode;
    private String dob;
    private String AddressLine1;
    private String AddressLine2;
    private String City;
    private String ProvinceStateCode;
    private String PostalCode;
    private String LoginDate;

    private String TierCode;
    private String TierImage;
    private String TierName;
    private String Welcome;
    private String StatusTier;
    private String TierTextP1;
    private String TierTextP2;
    private Boolean RedirectToTnc;
    private String PromoCategoryId;

    public String getPromoCategoryId() {
        return PromoCategoryId;
    }

    public void setPromoCategoryId(String promoCategoryId) {
        PromoCategoryId = promoCategoryId;
    }


    public String getTierName() {
        return TierName;
    }

    public void setTierName(String tierName) {
        TierName = tierName;
    }

    public String getWelcome() {
        return Welcome;
    }

    public void setWelcome(String welcome) {
        Welcome = welcome;
    }

    public String getStatusTier() {
        return StatusTier;
    }

    public void setStatusTier(String statusTier) {
        StatusTier = statusTier;
    }

    public Boolean getRedirectToTnc() {
        return RedirectToTnc;
    }

    public void setRedirectToTnc(Boolean redirectToTnc) {
        RedirectToTnc = redirectToTnc;
    }

    public String getTierCode() {
        return TierCode;
    }

    public void setTierCode(String tierCode) {
        TierCode = tierCode;
    }

    public String getTierImage() {
        return TierImage;
    }

    public void setTierImage(String tierImage) {
        TierImage = tierImage;
    }

    public String getTierTextP1() {
        return TierTextP1;
    }

    public void setTierTextP1(String tierTextP1) {
        TierTextP1 = tierTextP1;
    }

    public String getTierTextP2() {
        return TierTextP2;
    }

    public void setTierTextP2(String tierTextP2) {
        TierTextP2 = tierTextP2;
    }

    public String getTicketId() {
        return TicketId;
    }

    public void setTicketId(String ticketId) {
        TicketId = ticketId;
    }

    public String getLoginDate() {
        return LoginDate;
    }

    public void setLoginDate(String loginDate) {
        LoginDate = loginDate;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvinceStateCode() {
        return ProvinceStateCode;
    }

    public void setProvinceStateCode(String provinceStateCode) {
        ProvinceStateCode = provinceStateCode;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getBigPoint() {
        return BigPoint;
    }

    public void setBigPoint(String bigPoint) {
        BigPoint = bigPoint;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }


    public LoginReceive(LoginReceive returnData) {
        Message = returnData.getMessage();
        Status = returnData.getStatus();
        CustomerNumber = returnData.getCustomerNumber();
        FirstName = returnData.getFirstName();
        LastName = returnData.getLastName();
        Token = returnData.getToken();
        TicketId = returnData.getTicketId();
        UserName = returnData.getUserName();
        Hash = returnData.getHash();
        Profile_URL = returnData.getProfile_URL();
        Signature = returnData.getSignature();
        userEmail = returnData.getUserEmail();
        salutation = returnData.getSalutation();
        phoneNo = returnData.getPhoneNo();
        countryCode = returnData.getCountryCode();
        dob = returnData.getDob();
        MobilePhone = returnData.getMobilePhone();
        AddressLine1 = returnData.getAddressLine1();
        AddressLine2 = returnData.getAddressLine2();
        City = returnData.getCity();
        ProvinceStateCode = returnData.getProvinceStateCode();
        PostalCode = returnData.getPostalCode();
        LoginDate = returnData.getLoginDate();

        TierCode = returnData.getTierCode();
        TierImage = returnData.getTierImage();
        TierName = returnData.getTierName();
        Welcome = returnData.getWelcome();
        StatusTier = returnData.getStatusTier();
        TierTextP1 = returnData.getTierTextP1();
        TierTextP2 = returnData.getTierTextP2();
        RedirectToTnc = returnData.getRedirectToTnc();

        PromoCategoryId = returnData.getPromoCategoryId();

    }

    public String getProfile_URL() {
        return Profile_URL;
    }

    public void setProfile_URL(String profile_URL) {
        Profile_URL = profile_URL;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getCustomerNumber() {
        return CustomerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        CustomerNumber = customerNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String hash) {
        Hash = hash;
    }

}

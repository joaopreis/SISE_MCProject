package pt.sise.mc_project;

public class InternalProtocol {

    public static final String LOG= "SISE";

    public static final String SESSION_ID = "SESSION_ID";
    public static final int LOG_OUT_REQUEST = 2;
    public static final String USERNAME = "USERNAME";
    public static final String CONTEXT = "CONTEXT";

    // relevant for the new claim activity
    public static final int    NEW_CLAIM_REQUEST   = 1;
    public static final String KEY_NEW_CLAIM_ID = "CLAIM_ID";
    public static final String KEY_NEW_CLAIM_TITLE = "CLAIM_TITLE";
    public static final String KEY_NEW_CLAIM_PLATE_NUMBER  = "CLAIM_PLATE_NUMBER";
    public static final String KEY_NEW_CLAIM_DATE  = "CLAIM_DATE";
    public static final String KEY_NEW_CLAIM_DESCRIPTION  = "CLAIM_DESCRIPTION";

    public static final int CLAIM_INFORMATION_REQUEST  = 3;

    // relevant for the claim information activity
    public static final String READ_CLAIM_INDEX    = "CLAIM_INDEX";

    // relevant for the customer information activity
    public static final String CUSTOMER_NAME="CUSTOMER_NAME";
    public static final String CUSTOMER_BIRTHDATE="CUSTOMER_BIRTHDATE";
    public static final String CUSTOMER_NIF="CUSTOMER_NIF";
    public static final String CUSTOMER_ADDRESS="CUSTOMER_ADDRESS";
    public static final String CUSTOMER_POLICY_NUMBER="CUSTOMER_POLICY_NUMBER";

}

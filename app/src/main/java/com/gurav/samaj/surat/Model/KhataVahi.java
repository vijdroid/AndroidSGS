package com.gurav.samaj.surat.Model;

import java.util.ArrayList;
import java.util.List;

public class KhataVahi {
    public String status;
    public List<AccountsData> Accounts = new ArrayList<>();
    public List<DengiDetailData> DengiDetail = new ArrayList<>();
    public List<InterestData> Interest = new ArrayList<>();
    public List<LoanData> Loan = new ArrayList<>();
    public List<ExtraExpData> ExtraExp = new ArrayList<>();
    public List<FormIncomeData> FormIncome = new ArrayList<>();
    public List<ExtraIncomeData> ExtraIncome = new ArrayList<>();
    public List<ExtraFoundData> ExtraFound = new ArrayList<>();
}

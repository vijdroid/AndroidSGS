package com.gurav.samaj.surat.Model;

import java.util.ArrayList;
import java.util.List;

public class MiniStatmentModel {
    public String status;
    public String LastMonthTotal;
    public String ExtraIncome;
    public List<AccountsData> Accounts = new ArrayList();
    public List<AccountTotalData> AccountTotal = new ArrayList();
}

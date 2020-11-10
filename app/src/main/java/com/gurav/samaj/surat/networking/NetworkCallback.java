package com.gurav.samaj.surat.networking;

public interface NetworkCallback {
    void onNetworkError();

    void onNetworkResult(String str);
}

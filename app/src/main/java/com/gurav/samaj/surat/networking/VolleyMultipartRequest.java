package com.gurav.samaj.surat.networking;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

public class VolleyMultipartRequest extends Request<NetworkResponse> {
    private final String boundary;
    private final String lineEnd = "\r\n";
    private ErrorListener mErrorListener;
    private Map<String, String> mHeaders;
    private Listener<NetworkResponse> mListener;
    private final String twoHyphens = "--";

    public class DataPart {
        private byte[] content;
        private String fileName;
        private String type;

        public DataPart() {
        }

        public DataPart(String name, byte[] data) {
            this.fileName = name;
            this.content = data;
        }

        /* access modifiers changed from: 0000 */
        public String getFileName() {
            return this.fileName;
        }

        /* access modifiers changed from: 0000 */
        public byte[] getContent() {
            return this.content;
        }

        /* access modifiers changed from: 0000 */
        public String getType() {
            return this.type;
        }
    }

    public VolleyMultipartRequest(int method, String url, Listener<NetworkResponse> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        StringBuilder sb = new StringBuilder();
        sb.append("apiclient-");
        sb.append(System.currentTimeMillis());
        this.boundary = sb.toString();
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return this.mHeaders != null ? this.mHeaders : super.getHeaders();
    }

    public String getBodyContentType() {
        StringBuilder sb = new StringBuilder();
        sb.append("multipart/form-data;boundary=");
        sb.append(this.boundary);
        return sb.toString();
    }

    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        try {
            Map<String, String> params = getParams();
            if (params != null && params.size() > 0) {
                textParse(dos, params, getParamsEncoding());
            }
            Map<String, DataPart> data = getByteData();
            if (data != null && data.size() > 0) {
                dataParse(dos, data);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(this.boundary);
            sb.append("--");
            sb.append("\r\n");
            dos.writeBytes(sb.toString());
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, DataPart> getByteData() throws AuthFailureError {
        return null;
    }

    /* access modifiers changed from: protected */
    public Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(response, HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError((Throwable) e));
        }
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(NetworkResponse response) {
        this.mListener.onResponse(response);
    }

    public void deliverError(VolleyError error) {
        this.mErrorListener.onErrorResponse(error);
    }

    private void textParse(DataOutputStream dataOutputStream, Map<String, String> params, String encoding) throws IOException {
        try {
            for (Entry<String, String> entry : params.entrySet()) {
                buildTextPart(dataOutputStream, (String) entry.getKey(), (String) entry.getValue());
            }
        } catch (UnsupportedEncodingException uee) {
            StringBuilder sb = new StringBuilder();
            sb.append("Encoding not supported: ");
            sb.append(encoding);
            throw new RuntimeException(sb.toString(), uee);
        }
    }

    private void dataParse(DataOutputStream dataOutputStream, Map<String, DataPart> data) throws IOException {
        for (Entry<String, DataPart> entry : data.entrySet()) {
            buildDataPart(dataOutputStream, (DataPart) entry.getValue(), (String) entry.getKey());
        }
    }

    private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(this.boundary);
        sb.append("\r\n");
        dataOutputStream.writeBytes(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Content-Disposition: form-data; name=\"");
        sb2.append(parameterName);
        sb2.append("\"");
        sb2.append("\r\n");
        dataOutputStream.writeBytes(sb2.toString());
        dataOutputStream.writeBytes("\r\n");
        StringBuilder sb3 = new StringBuilder();
        sb3.append(parameterValue);
        sb3.append("\r\n");
        dataOutputStream.writeBytes(sb3.toString());
    }

    private void buildDataPart(DataOutputStream dataOutputStream, DataPart dataFile, String inputName) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(this.boundary);
        sb.append("\r\n");
        dataOutputStream.writeBytes(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Content-Disposition: form-data; name=\"");
        sb2.append(inputName);
        sb2.append("\"; filename=\"");
        sb2.append(dataFile.getFileName());
        sb2.append("\"");
        sb2.append("\r\n");
        dataOutputStream.writeBytes(sb2.toString());
        if (dataFile.getType() != null && !dataFile.getType().trim().isEmpty()) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Content-Type: ");
            sb3.append(dataFile.getType());
            sb3.append("\r\n");
            dataOutputStream.writeBytes(sb3.toString());
        }
        dataOutputStream.writeBytes("\r\n");
        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(dataFile.getContent());
        int bufferSize = Math.min(fileInputStream.available(), 1048576);
        byte[] buffer = new byte[bufferSize];
        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize);
            bufferSize = Math.min(fileInputStream.available(), 1048576);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }
        dataOutputStream.writeBytes("\r\n");
    }
}

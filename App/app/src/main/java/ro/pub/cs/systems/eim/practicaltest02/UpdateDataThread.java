package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class UpdateDataThread extends Thread {
    ServerThread serverThread = null;

    public UpdateDataThread(ServerThread serverThread) {
        this.serverThread = serverThread;
    }

    @Override
    public void run() {
        while(Thread.currentThread().isInterrupted()) {
            try {
                HttpClient httpClient = new DefaultHttpClient();
                String pageSourceCode = "";

                HttpGet httpGet = new HttpGet(Constants.WEB_SERVICE_ADDRESS + Constants.WEB_SERVICE_MODE);

                HttpResponse httpGetResponse = httpClient.execute(httpGet);
                HttpEntity httpGetEntity = httpGetResponse.getEntity();
                if (httpGetEntity != null) {
                    pageSourceCode = EntityUtils.toString(httpGetEntity);
                }

                if (pageSourceCode == null) {
                    Log.e(Constants.TAG, "[COMMUNICATION THREAD] Error getting the information from the webservice!");
                    return;
                } else
                    Log.i(Constants.TAG, pageSourceCode );

                JSONObject content = new JSONObject(pageSourceCode);
                JSONObject bpi = content.getJSONObject(Constants.bpi);
                double eur = bpi.getDouble(Constants.EUR);
                double usd = bpi.getDouble(Constants.USD);

                serverThread.setData(eur, usd);

                Thread.sleep(60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

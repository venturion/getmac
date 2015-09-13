package com.example.getmac;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.webkit.WebSettings.PluginState;

public class WifiInfoPlugin extends CordovaPlugin {
    public static final String SSID_NAME = "WifiInfo";

    @Override
    public boolean execute(String action, JSONArray args,
            CallbackContext callbackContext) throws JSONException {
        if (SSID_NAME.equals(action)) {
            String wifiInfo = this.getWifiInfo();
            Log.e("Wifi SSID", wifiInfo);
            if(wifiInfo != ""){
                JSONObject jsonResult = new JSONObject();
            try {
                    jsonResult.put("Wifi SSID", wifiInfo);
                    PluginResult r= new PluginResult(PluginResult.Status.OK,jsonResult);
                    callbackContext.success(wifiInfo);
                    r.setKeepCallback(true);
                    return true;

            } catch (JSONException e) {
                PluginResult r = new PluginResult(PluginResult.Status.JSON_EXCEPTION);
                callbackContext.error("error");
                r.setKeepCallback(true);
                callbackContext.sendPluginResult(r);
                return true;
            }
        }
        }
        return false;
    }

    private String getWifiInfo() {

        WifiManager manager = (WifiManager)this.cordova.getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        //String address = info.getMacAddress();
        String address = info.getSSID ();
        Log.e("ssid address", address);
        return address;
    }

}
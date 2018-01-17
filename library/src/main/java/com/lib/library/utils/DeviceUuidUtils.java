package com.lib.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * 获取唯一码 DeviceUuidFactory
 * Created by Administrator on 2017\9\18 0018.
 * <p>
 * 为了实现在设备上更通用的获取设备唯一标识，我们可以实现这样的一个类，为每个设备产生唯一的UUID，以ANDROID_ID为基础，
 * 在获取失败时以TelephonyManager.getDeviceId()为备选方法，如果再失败，使用UUID的生成策略。
 * 重申下，以下方法是生成Device ID，在大多数情况下Installtion ID能够满足我们的需求，但是如果确实需要用到Device ID，那可以通过以下方式实现：
 */
public class DeviceUuidUtils {

    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";

    protected static UUID uuid;


    public DeviceUuidUtils(Context context) {

        if (uuid == null) {
            synchronized (DeviceUuidUtils.class) {
                if (uuid == null) {
                    final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
                    final String id = prefs.getString(PREFS_DEVICE_ID, null);

                    if (id != null) {
                        // Use the ids previously computed and stored in the prefs file
                        uuid = UUID.fromString(id);

                    } else {

                        final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

                        // Use the Android ID unless it's broken, in which case fallback on deviceId,
                        // unless it's not available, then fallback on a random number which we store
                        // to a prefs file
                        try {
                            if (!"9774d56d682e549c".equals(androidId)) {
                                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                            } else {
                                final String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                                uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
                            }
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                        // Write the value out to the prefs file
                        prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();
                    }

                }
            }
        }

    }

    public UUID getDeviceUuid() {
        return uuid;
    }

}
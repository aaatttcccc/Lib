package com.lib.library.webservice;

import android.content.Context;


import com.lib.library.log.PrintToFileLogger;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * 一个Webservice的配置类
 *
 * @测试 http://14.29.84.252:8089/services/integralRulesService/
 * getOkUseIntegralByMoney?money=3000
 */
public class WebserviceConfig {

    /**
     * 用webservice返回远程数据库数据
     *
     * @param methodName    方法名
     * @param propertyInfos 参数
     * @return
     */
    public static String getObject(Context context, String nameSpace, String webserviceUrl, String pageName, String methodName,
                                   PropertyInfo[] propertyInfos) {
        SoapPrimitive soapPrimitive = (SoapPrimitive) GetWebServiceData(context,
                nameSpace, webserviceUrl + "/" + pageName,
                nameSpace + methodName, methodName,
                propertyInfos);
        if (soapPrimitive != null) {
            return soapPrimitive.toString();
        } else {
            return null;
        }
    }

    private static Object GetWebServiceData(Context context, String iNameSpace,
                                            String iWebserviceURL, String iSoapAction, String iMethodName,
                                            PropertyInfo[] iPropertyInfo) {
        Object result = null;
        try {
            SoapObject rpc = new SoapObject(iNameSpace, iMethodName);
            for (int i = 0; i < iPropertyInfo.length; i++) {
                rpc.addProperty(iPropertyInfo[i]);
            }
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = rpc;
            envelope.dotNet = true;// 设置是否调用的是.Net开发的WebService
            envelope.setOutputSoapObject(rpc);
            HttpTransportSE ht = new HttpTransportSE(iWebserviceURL, Integer.MAX_VALUE);
            ht.debug = true;
            ht.call(iSoapAction, envelope);
            result = envelope.getResponse();
        } catch (Exception e) {
            PrintToFileLogger printToFileLogger = new PrintToFileLogger();
            printToFileLogger.println(e, null);
        }
        return result;
    }


}

package com.example.levi.hangdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver
    {
    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent)
        {
        // TODO: This method is called when the BroadcastReceiver is receiving

        final Bundle bundle = intent.getExtras();

        try
            {
            if (bundle != null)
                {
                Log.d("MYLOG", "Bundle: " + bundle);

                //  get pdu from bundle
                final Object[] pdus = (Object[]) bundle.get("pdus");

                // get format of bundle
                String format = bundle.getString("format");

                for (int i = 0; i < pdus.length; i++)
                    {
                    //retrieves the current text
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

                    //assigns the phone number to the text
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    //assigns the sms message text to a string
                    String message = currentMessage.getDisplayMessageBody();

                    //log to check information
                    Log.d("MYLOG", "phone: " + phoneNumber + "; message: " + message);

                    //pulls from shared preferences
                    SharedPreferences preferences = context.getSharedPreferences("TEXT_MSGS", Context.MODE_PRIVATE);

                    //creates the shared preferences editor
                    SharedPreferences.Editor editor = preferences.edit();

                    //log for error checking
                    Log.d("MYLOG", "TextedWord: " + message);

                    //adds the message and phone number to the shared preferences through the editor
                    editor.putString("TextedWord", message);
                    editor.putString("TexterPhone", phoneNumber);

                    //commits the changes made through the editor
                    editor.commit();
                    }

                }
            //catch for any exceptions
            } catch (Exception e)
            {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
            }
        }
    }

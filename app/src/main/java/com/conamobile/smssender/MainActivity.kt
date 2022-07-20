package com.conamobile.smssender

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.SubscriptionManager
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendSmsDualSim(0)
    }

    @SuppressLint("MissingPermission")
    fun sendSmsDualSim(simNumber: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            val simCardList: ArrayList<Int> = ArrayList()
            val subscriptionManager = SubscriptionManager.from(this)
            val subscriptionInfoList = subscriptionManager
                .activeSubscriptionInfoList
            for (subscriptionInfo in subscriptionInfoList) {
                val subscriptionId = subscriptionInfo.subscriptionId
                simCardList.add(subscriptionId)
            }
            val smsToSendFrom = simCardList[simNumber]
            SmsManager.getSmsManagerForSubscriptionId(smsToSendFrom)
                .sendTextMessage("phoneNumber", null, "messageText", null, null)

        }
    }

}
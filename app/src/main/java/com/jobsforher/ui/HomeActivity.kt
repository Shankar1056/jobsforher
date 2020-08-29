package com.jobsforher.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.jobsforher.R
import com.jobsforher.utility.ClsGeneral
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()

        subscribeActivity1.setOnClickListener {
            if (subscribeActivity1.text.toString().trim().equals(getString(R.string.subscribe_to_activity1))){
                subscribeActivity(getString(R.string.activity_1))
            } else{
                unSubscribeActivity(getString(R.string.activity_1))
            }

        }

        subscribeActivity2.setOnClickListener {
            if (subscribeActivity2.text.toString().trim().equals(getString(R.string.subscribe_to_activity2))){
                subscribeActivity(getString(R.string.activity_2))
            } else{
                unSubscribeActivity(getString(R.string.activity_2))
            }
            Log.d(TAG, "Subscribing to Activity1 topic")
            subscribeActivity2.setText(getString(R.string.ubsubscribe_to_activity2))

        }

    }

    private fun init() {
        if (ClsGeneral.getBooleanPreferences(this, "activity1")){
            subscribeActivity1.setText(getString(R.string.ubsubscribe_to_activity1))
        } else{
            subscribeActivity1.setText(getString(R.string.subscribe_to_activity1))
        }

        if (ClsGeneral.getBooleanPreferences(this, "activity2")){
            subscribeActivity2.setText(getString(R.string.ubsubscribe_to_activity2))
        } else{
            subscribeActivity2.setText(getString(R.string.subscribe_to_activity2))
        }
    }

    private fun unSubscribeActivity(topic: String) {
        // [START Unsubscribe_topics]
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        getString(R.string.msg_unsubscribe_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext,
                        getString(R.string.msg_subscribed),
                        Toast.LENGTH_SHORT
                    ).show()

                    if (topic.equals(getString(R.string.activity_1))){
                        ClsGeneral.setBooleanPreference(this@HomeActivity, "activity1", false)
                        subscribeActivity1.setText(getString(R.string.subscribe_to_activity1))
                    }else{
                        ClsGeneral.setBooleanPreference(this@HomeActivity, "activity2", false)
                        subscribeActivity2.setText(getString(R.string.subscribe_to_activity2))
                    }
                }
            }
        // [END Unsubscribe_topics]
    }

    private fun subscribeActivity(topic: String) {
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        getString(R.string.msg_subscribe_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext,
                        getString(R.string.msg_subscribed),
                        Toast.LENGTH_SHORT
                    ).show()
                    if (topic.equals(getString(R.string.activity_1))){
                        ClsGeneral.setBooleanPreference(this@HomeActivity, "activity1", true)
                        subscribeActivity1.setText(getString(R.string.ubsubscribe_to_activity1))
                    }else{
                        ClsGeneral.setBooleanPreference(this@HomeActivity, "activity2", true)
                        subscribeActivity2.setText(getString(R.string.ubsubscribe_to_activity2))
                    }

                }
            }
        // [END subscribe_topics]
    }

    companion object {

        private const val TAG = "HOmeActivity"
    }

}
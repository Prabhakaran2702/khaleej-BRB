package com.prabhakaran.bookstore.ui.activity

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.prabhakaran.bookstore.R
import com.prabhakaran.bookstore.model.CartItem
import io.github.inflationx.viewpump.ViewPumpContextWrapper


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    val channelId = "com.prabhakaran.bookstore"
    val notificationId = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

         navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard, R.id.navigation_notifications,  R.id.navigation_wish_list,  R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        createNotificationChannel(channelId)

        if(!isNetworkAvailable(this)){
            showAlert()
        }
        showNotification()
        showNotification()



    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }


    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }


    private fun showAlert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Internet connection")
        builder.setMessage("Please turn on your internet connection.")
        builder.setCancelable(false)
        builder.setNeutralButton("Okay") { dialog, which ->
            finish()
        }
        builder.show()

    }


    private fun createNotificationChannel(channelId: String) {
        // Create the NotificationChannel, but only on API 26+ (Android 8.0) because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "My Channel"
            val channelDescription = "Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, name, importance)
            channel.apply {
                description = channelDescription
            }

            // Finally register the channel with system
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun showNotification(){

        val myIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            myIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.download)
            .setContentTitle("BRB Book Store")
            .setContentText("Here we have a 30% discount on all the items. Don't miss it !!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    // Provide the bitmap to be used as the payload for the BigPicture notification.
                    .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.notification_img))
                    .bigLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.download))
                    .setBigContentTitle("BBRB Book Store")
                    .setSummaryText("Here we have a 30% discount on all the items. Don't miss it !!")

            )
            .setContentIntent(pendingIntent)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setAutoCancel(true)


        with(NotificationManagerCompat.from(this)){
            notify(notificationId, notificationBuilder.build())
        }
    }

}
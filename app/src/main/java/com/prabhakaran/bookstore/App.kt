package com.prabhakaran.bookstore

import android.app.Application
import com.prabhakaran.bookstore.di.*
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(databaseModule)
            modules(repositoryModule)
            modules(viewModelModule)
            modules(networkModule)
            modules(apiModule)
        }

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                            .build()
                    )
                )
                .build()
        )
    }

}
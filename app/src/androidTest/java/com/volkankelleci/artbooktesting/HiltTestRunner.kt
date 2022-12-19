package com.volkankelleci.artbooktesting

import android.app.Activity
import android.content.Intent
import androidx.test.runner.AndroidJUnitRunner

class HiltTestRunner:AndroidJUnitRunner() {
    override fun newActivity(cl: ClassLoader?, className: String?, intent: Intent?): Activity {
        return super.newActivity(cl, HiltTestActivity::class.java.name, intent)
    }
}
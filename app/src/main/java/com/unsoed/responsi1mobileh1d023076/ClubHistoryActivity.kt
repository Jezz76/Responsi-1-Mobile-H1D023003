package com.unsoed.responsi1mobileh1d023003

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.unsoed.responsi1mobileh1d023003.databinding.ActivityClubHistoryBinding

class ClubHistoryActivity : ComponentActivity() {
    private lateinit var binding: ActivityClubHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = "63. Fulham FC's History"
    }
}
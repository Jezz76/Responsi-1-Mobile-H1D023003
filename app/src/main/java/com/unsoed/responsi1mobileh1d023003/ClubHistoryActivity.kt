package com.unsoed.responsi1mobileh1d023003

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.unsoed.responsi1mobileh1d023003.databinding.ActivityClubHistoryBinding
import com.unsoed.responsi1mobileh1d023003.viewmodel.TeamViewModel

class ClubHistoryActivity : ComponentActivity() {
    private lateinit var binding: ActivityClubHistoryBinding
    private lateinit var viewModel: TeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]

        val teamId = 58
        viewModel.loadTeam(teamId, "05f3bba899d14e489201897aac8a4dc4")

        viewModel.teamData.observe(this) { team ->
            binding.tvTitle.text = "${team.name} - History"
            binding.tvContent.text = getString(R.string.club_history_text)
        }

        viewModel.error.observe(this) { error ->
            binding.tvContent.text = "Error: $error"
        }
    }
}
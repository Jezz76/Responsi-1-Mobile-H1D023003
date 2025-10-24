package com.unsoed.responsi1mobileh1d023003

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.unsoed.responsi1mobileh1d023003.databinding.ActivityMainBinding
import com.unsoed.responsi1mobileh1d023003.viewmodel.TeamViewModel

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TeamViewModel
    private val apiToken = "05f3bba899d14e489201897aac8a4dc4"
    private val teamId = 58

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)

        binding.btnHistory.setOnClickListener {
            startActivity(Intent(this, ClubHistoryActivity::class.java))
        }

        binding.btnCoach.setOnClickListener {
            startActivity(Intent(this, HeadCoachActivity::class.java))
        }

        binding.btnSquad.setOnClickListener {
            startActivity(Intent(this, TeamSquadActivity::class.java))
        }

        loadTeamData()
    }

    private fun loadTeamData() {
        viewModel.loadTeam(teamId, apiToken)

        viewModel.teamData.observe(this) { team ->
            binding.tvClubName.text = team.name
            binding.tvDescription.text = getString(R.string.club_description)
        }

        viewModel.error.observe(this) { errorMessage ->
            binding.tvClubName.text = "Error: $errorMessage"
            binding.tvDescription.text = "Failed to load team data. Please check your internet connection."
        }
    }
}
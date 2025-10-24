package com.unsoed.responsi1mobileh1d023003

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.unsoed.responsi1mobileh1d023003.databinding.ActivityHeadCoachBinding
import com.unsoed.responsi1mobileh1d023003.viewmodel.TeamViewModel

class HeadCoachActivity : ComponentActivity() {
    private lateinit var binding: ActivityHeadCoachBinding
    private lateinit var viewModel: TeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]

        val teamId = 63
//        val token = "Bearer ${BuildConfig.API_KEY}"
        viewModel.loadTeam(teamId, "5d363938c2ea47d1b40b050dc0055840")

        viewModel.teamData.observe(this) { team ->
            val coach = team.coach
            binding.tvCoachName.text = coach.name
            binding.tvCoachBirth.text = coach.dateOfBirth ?: "-"
            binding.tvCoachNationality.text = coach.nationality

            Glide.with(this)
                .load(R.drawable.coach)
                .into(binding.imgCoach)
        }

        viewModel.error.observe(this) { error ->
            binding.tvCoachName.text = error
        }
    }
}
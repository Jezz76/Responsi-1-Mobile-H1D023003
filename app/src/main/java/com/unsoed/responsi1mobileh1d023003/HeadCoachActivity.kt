package com.unsoed.responsi1mobileh1d023003

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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

        val teamId = 58
        viewModel.loadTeam(teamId, "05f3bba899d14e489201897aac8a4dc4")

        viewModel.teamData.observe(this) { team ->
            val coach = team.coach
            binding.tvCoachName.text = coach.name
            binding.tvCoachBirth.text = coach.dateOfBirth ?: "-"
            binding.tvCoachNationality.text = coach.nationality

            @Suppress("DEPRECATION")
            Glide.with(this)
                .load(R.drawable.coach)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.imgCoach)
        }

        viewModel.error.observe(this) { error ->
            binding.tvCoachName.text = error
        }
    }
}
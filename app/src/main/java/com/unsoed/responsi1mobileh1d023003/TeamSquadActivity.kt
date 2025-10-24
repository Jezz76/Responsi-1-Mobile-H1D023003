package com.unsoed.responsi1mobileh1d023003

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsoed.responsi1mobileh1d023003.data.model.Player
import com.unsoed.responsi1mobileh1d023003.databinding.ActivityTeamSquadBinding
import com.unsoed.responsi1mobileh1d023003.ui.adapter.PlayerAdapter
import com.unsoed.responsi1mobileh1d023003.ui.dialog.PlayerDetailBottomSheet
import com.unsoed.responsi1mobileh1d023003.viewmodel.TeamViewModel

class TeamSquadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamSquadBinding
    private lateinit var viewModel: TeamViewModel
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamSquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]

        binding.rvPlayers.layoutManager = LinearLayoutManager(this)

        val teamId = 58
        viewModel.loadTeam(teamId, "05f3bba899d14e489201897aac8a4dc4")

        viewModel.teamData.observe(this) { team ->
            binding.tvTitle.text = "${team.name} - Squad"

            playerAdapter = PlayerAdapter(team.squad) { player ->
                showPlayerDetailDialog(player)
            }
            binding.rvPlayers.adapter = playerAdapter
        }
    }

    private fun showPlayerDetailDialog(player: Player) {
        val bottomSheet = PlayerDetailBottomSheet.newInstance(player)
        bottomSheet.show(supportFragmentManager, "PlayerDetail")
    }
}
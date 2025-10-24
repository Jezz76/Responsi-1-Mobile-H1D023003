package com.unsoed.responsi1mobileh1d023003.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobileh1d023003.data.model.Player
import com.unsoed.responsi1mobileh1d023003.databinding.ItemPlayerCardBinding

class PlayerAdapter(
    playerList: List<Player>,
    private val onPlayerClick: (Player) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private val sortedPlayerList: List<Player> = sortPlayersByPosition(playerList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(sortedPlayerList[position])
    }

    override fun getItemCount(): Int = sortedPlayerList.size

    private fun sortPlayersByPosition(players: List<Player>): List<Player> {
        return players.sortedBy { player ->
            when {
                isGoalkeeper(player.position) -> 0
                isDefender(player.position) -> 1
                isMidfielder(player.position) -> 2
                isForward(player.position) -> 3
                else -> 4
            }
        }
    }

    private fun isGoalkeeper(position: String?): Boolean {
        if (position == null) return false
        val posUpper = position.uppercase()
        return posUpper.equals("GK") || posUpper.contains("GOALKEEPER")
    }

    private fun isDefender(position: String?): Boolean {
        if (position == null) return false
        val posUpper = position.uppercase()
        val posLower = position.lowercase()
        return posUpper.contains("DEF") || posUpper.contains("CB") || 
                posUpper.contains("LB") || posUpper.contains("RB") ||
                posUpper.contains("DEFENDER") || posUpper.contains("FULLBACK") ||
                (posUpper.contains("BACK") && !posUpper.contains("LW") && !posUpper.contains("RW") &&
                !posLower.contains("left winger") && !posLower.contains("right winger"))
    }

    private fun isMidfielder(position: String?): Boolean {
        if (position == null) return false
        val posUpper = position.uppercase()
        return posUpper.contains("MID") || posUpper.contains("CM") || 
                posUpper.contains("DM") || posUpper.contains("AM") ||
                posUpper.contains("MIDFIELDER")
    }

    private fun isForward(position: String?): Boolean {
        if (position == null) return false
        val posUpper = position.uppercase()
        val posLower = position.lowercase()
        return posUpper.contains("LW") || posUpper.contains("RW") || 
                posUpper.contains("LF") || posUpper.contains("RF") ||
                posUpper.contains("FW") || posUpper.contains("ST") || 
                posUpper.contains("CF") || posUpper.contains("FORWARD") ||
                posUpper.contains("ATTACKER") ||
                posLower.contains("left winger") || posLower.contains("right winger") ||
                posLower.contains("left wing") || posLower.contains("right wing") ||
                posLower.contains("winger")
    }

    inner class PlayerViewHolder(private val binding: ItemPlayerCardBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(player: Player) {
            binding.tvPlayerName.text = player.name
            binding.tvPlayerPosition.text = player.nationality
            binding.tvPlayerDateOfBirth.visibility = android.view.View.GONE
            binding.tvPlayerNationality.visibility = android.view.View.GONE

            val cardColor = getColorByPosition(player.position)
            binding.playerCardView.setCardBackgroundColor(cardColor)
            
            val textColor = if (isLightColor(cardColor)) Color.BLACK else Color.WHITE
            binding.tvPlayerName.setTextColor(textColor)
            binding.tvPlayerPosition.setTextColor(textColor)
            
            binding.root.setOnClickListener {
                onPlayerClick(player)
            }
        }

        private fun getColorByPosition(position: String?): Int {
            if (position == null) return Color.GRAY
            
            val posUpper = position.uppercase()
            val posLower = position.lowercase()
            
            if (posUpper.contains("LW") || posUpper.contains("RW") || 
                posUpper.contains("LF") || posUpper.contains("RF") ||
                posUpper.contains("FW") || posUpper.contains("ST") || 
                posUpper.contains("CF") || posUpper.contains("FORWARD") ||
                posUpper.contains("ATTACKER") ||
                posLower.contains("left winger") || posLower.contains("right winger") ||
                posLower.contains("left wing") || posLower.contains("right wing") ||
                posLower.contains("winger")) {
                return Color.parseColor("#F44336")
            }
            
            if (posUpper.equals("GK") || posUpper.contains("GOALKEEPER")) {
                return Color.parseColor("#FFEB3B")
            }
            
            if (posUpper.contains("DEF") || posUpper.contains("CB") || 
                posUpper.contains("LB") || posUpper.contains("RB") ||
                posUpper.contains("DEFENDER") || posUpper.contains("FULLBACK") ||
                (posUpper.contains("BACK") && !posUpper.contains("LW") && !posUpper.contains("RW") &&
                !posLower.contains("left winger") && !posLower.contains("right winger"))) {
                return Color.parseColor("#2196F3")
            }
            
            if (posUpper.contains("MID") || posUpper.contains("CM") || 
                posUpper.contains("DM") || posUpper.contains("AM") ||
                posUpper.contains("MIDFIELDER")) {
                return Color.parseColor("#4CAF50")
            }
            
            return Color.GRAY
        }

        private fun isLightColor(color: Int): Boolean {
            val r = Color.red(color)
            val g = Color.green(color)
            val b = Color.blue(color)
            val luminance = (0.299 * r + 0.587 * g + 0.114 * b) / 255
            return luminance > 0.5
        }
    }
}
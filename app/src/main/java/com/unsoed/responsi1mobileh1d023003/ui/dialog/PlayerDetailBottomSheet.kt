package com.unsoed.responsi1mobileh1d023003.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.unsoed.responsi1mobileh1d023003.data.model.Player
import com.unsoed.responsi1mobileh1d023003.databinding.DialogPlayerDetailBinding

class PlayerDetailBottomSheet : BottomSheetDialogFragment() {
    private var _binding: DialogPlayerDetailBinding? = null
    private val binding get() = _binding!!
    private var player: Player? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        @Suppress("DEPRECATION")
        player = arguments?.getParcelable("player")
        
        player?.let { p ->
            binding.dialogPlayerName.text = p.name
            binding.dialogPlayerPosition.text = p.position ?: "N/A"
            binding.dialogPlayerDateOfBirth.text = p.dateOfBirth ?: "N/A"
            binding.dialogPlayerNationality.text = p.nationality
        }

        binding.dialogCloseButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(player: Player): PlayerDetailBottomSheet {
            return PlayerDetailBottomSheet().apply {
                arguments = Bundle().apply {
                    putParcelable("player", player)
                }
            }
        }
    }
}

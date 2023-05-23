package com.vmn.aisle.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.vmn.aisle.core.BaseAdapter
import com.vmn.aisle.core.BaseViewHolder
import com.vmn.aisle.databinding.ItemInviteBinding
import com.vmn.domain.entity.InviteProfileEntity

class InvitesAdapter : BaseAdapter<BaseViewHolder<InviteProfileEntity>, InviteProfileEntity>() {

    inner class InviteViewHolder(
        private val binding: ItemInviteBinding
    ) : BaseViewHolder<InviteProfileEntity>(binding.root) {
        override fun bindData(t: InviteProfileEntity) {
            binding.data = t
            binding.executePendingBindings()

        }
    }

    override fun createHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<InviteProfileEntity> {
        val binding =
            ItemInviteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return InviteViewHolder(binding)
    }
}
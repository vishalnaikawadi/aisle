package com.vmn.aisle.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.vmn.aisle.core.BaseAdapter
import com.vmn.aisle.core.BaseViewHolder
import com.vmn.aisle.databinding.ItemUserWhoLikedBinding
import com.vmn.aisle.utils.ScreenUtil
import com.vmn.domain.entity.LikesProfileEntity

class LikesAdapter : BaseAdapter<BaseViewHolder<LikesProfileEntity>, LikesProfileEntity>() {

    inner class LikeViewHolder(
        private val binding: ItemUserWhoLikedBinding
    ) : BaseViewHolder<LikesProfileEntity>(binding.root) {
        override fun bindData(t: LikesProfileEntity) {
            binding.data = t
            binding.itemWidth = ScreenUtil.getHalfScreenWidth(binding.root.context)
            binding.executePendingBindings()
        }
    }

    override fun createHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<LikesProfileEntity> {
        val binding =
            ItemUserWhoLikedBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LikeViewHolder(binding)
    }
}
package com.caojing.runactivity.adapter

import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.caojing.runactivity.R
import com.caojing.runactivity.entity.BannerData

//class BannerAdapter(fragment: Fragment, bannerList: List<BannerData> = mutableListOf()) :
//    BannerImageAdapter<BannerData>(bannerList) {
//    private var mFragment = fragment
//    override fun onBindView(
//        holder: BannerImageHolder,
//        data: BannerData,
//        position: Int,
//        size: Int
//    ) {
//        Glide
//            .with(mFragment)
//            .load(data.imagePath).error(R.mipmap.ic_launcher)
//            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
//            .centerCrop()
//            .into(holder.imageView)
//    }
//}
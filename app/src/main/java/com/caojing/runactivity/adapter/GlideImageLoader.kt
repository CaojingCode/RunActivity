package com.caojing.runactivity.adapter

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.caojing.runactivity.R
import com.caojing.runactivity.entity.BannerData
import com.youth.banner.loader.ImageLoader

class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        var bannerData = path as BannerData
        if (!(context as AppCompatActivity).isDestroyed)
            Glide
                .with(context)
                .load(bannerData.imagePath).error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .centerCrop()
                .into(imageView)
    }
}
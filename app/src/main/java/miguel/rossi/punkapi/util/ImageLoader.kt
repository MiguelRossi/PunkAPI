package miguel.rossi.punkapi.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun loadImageFitCenter(imageView: ImageView, url: String, @DrawableRes errorDrawableId: Int) {
    Glide
        .with(imageView.context)
        .load(url)
        .fitCenter()
        .error(errorDrawableId)
        .into(imageView)
}

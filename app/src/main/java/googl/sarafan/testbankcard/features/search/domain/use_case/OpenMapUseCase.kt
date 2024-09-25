package googl.sarafan.testbankcard.features.search.domain.use_case

import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface OpenMapUseCase {
    fun invoke(latitude: Float, longitude: Float)

    class Base @Inject constructor(@ApplicationContext private val context: Context) :
        OpenMapUseCase {
        override fun invoke(latitude: Float, longitude: Float) {
            val geoUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, geoUri).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            try {
                context.startActivity(mapIntent)
            } catch (_: Exception) {
            }


        }

    }

}
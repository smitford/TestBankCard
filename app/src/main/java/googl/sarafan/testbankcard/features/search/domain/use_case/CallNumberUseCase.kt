package googl.sarafan.testbankcard.features.search.domain.use_case

import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface CallNumberUseCase {
    fun invoke(phoneNumber: String)
    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : CallNumberUseCase {
        override fun invoke(phoneNumber: String) {
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            try {
                context.startActivity(callIntent)
            } catch (_: Exception) {
            }
        }
    }
}
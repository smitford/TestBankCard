package googl.sarafan.testbankcard.features.search.domain.use_case

import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface OpenLinkUseCase {
    fun invoke(url: String)
    class Base @Inject constructor(@ApplicationContext private val context: Context) :
        OpenLinkUseCase {
        override fun invoke(url: String) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            try {
                context.startActivity(browserIntent)
            } catch (_: Exception) {
            }
        }
    }
}
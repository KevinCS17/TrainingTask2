package com.example.trainingtask2.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.trainingtask2.IdleTimer
import com.example.trainingtask2.R
import com.example.trainingtask2.constants.BundleConstants
import kotlinx.android.synthetic.main.cartoon_item.view.*
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {

    override fun onPause() {
        super.onPause()
        IdleTimer.timer.cancel()
    }

    override fun onResume() {
        super.onResume()
        IdleTimer.timer.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString(BundleConstants.KEY_NAME)
        val gender = arguments?.getString(BundleConstants.KEY_GENDER)
        val species = arguments?.getString(BundleConstants.KEY_SPECIES)
        val status = arguments?.getString(BundleConstants.KEY_STATUS)
        val image = arguments?.getString(BundleConstants.KEY_IMAGE)

        cartoonName.text = context?.getText(R.string.details_name_text, name)
        cartoonGender.text = context?.getText(R.string.details_gender_text, gender)
        cartoonSpecies.text = context?.getText(R.string.details_species_text, species)
        cartoonStatus.text = context?.getText(R.string.details_status_text, status)

        Glide.with(this)
            .load(image)
            .transform(RoundedCorners(30))
            .into(cartoonImage)
    }

    /**
     * Create a formatted CharSequence from a string resource containing arguments and HTML formatting
     *
     * The string resource must be wrapped in a CDATA section so that the HTML formatting is conserved.
     *
     * Example of an HTML formatted string resource:
     * <string name="html_formatted"><![CDATA[ bold text: <B>%1$s</B> ]]></string>
     */
    private fun Context.getText(@StringRes id: Int, vararg args: Any?): CharSequence =
        HtmlCompat.fromHtml(String.format(getString(id), *args), HtmlCompat.FROM_HTML_MODE_COMPACT)
}
package uz.behzoddev.ui_toast

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.CountDownTimer
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.content.ContextCompat
import uz.behzoddev.ui_toast.databinding.CustomLayoutToastBinding

class XToast {

    companion object {
        const val LONG_DURATION = 5000L
        const val SHORT_DURATION = 2000L
    }

    class Builder(
        private var context: Context,
        private var title: String = "Ошибка",
        private var message: String = "",
        private var style: XToastStyle = XToastStyle.Success,
        private var duration: Long = 0
    ) {
        private lateinit var layoutInflater: LayoutInflater

        /**
         * Set toast title text
         *
         * @param value
         */
        fun title(value: String) = apply { this.title = value }

        /**
         * Set toast message text
         *
         * @param value
         */
        fun message(value: String) = apply { this.message = value }

        fun style(value: XToastStyle) = apply {
            this.style = value
        }

        fun duration(value: Long) = apply {
            this.duration = value
        }

        fun build(): XToast {
            layoutInflater = LayoutInflater.from(context)

            val binding = CustomLayoutToastBinding.inflate(layoutInflater)
            val layout = binding.root

            when (style) {
                XToastStyle.Error -> {
                    binding.ivToast.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_warning
                        )
                    )
                    binding.root.setBackgroundResource(R.color.mammary)

                    binding.tvTitle.text =
                        title.ifEmpty { "Success" }
                    binding.tvDesc.text = message
                    val toast = Toast(context.applicationContext)

                    val drawable = ContextCompat.getDrawable(context, R.drawable.bg_round)
                    drawable?.colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(context, R.color.mammary),
                        PorterDuff.Mode.MULTIPLY
                    )

                    layout.background = drawable

                    startTimer(duration, toast)

                    toast.setGravity(Gravity.BOTTOM, 0, 100)
                    toast.view = layout
                    toast.show()

                }
                XToastStyle.Info -> {
                    binding.ivToast.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_info
                        )
                    )
                    binding.root.setBackgroundResource(R.color.bellagio)

                    binding.tvTitle.text =
                        title.ifEmpty { "Success" }
                    binding.tvDesc.text = message
                    val toast = Toast(context.applicationContext)

                    val drawable = ContextCompat.getDrawable(context, R.drawable.bg_round)
                    drawable?.colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(context, R.color.bellagio),
                        PorterDuff.Mode.MULTIPLY
                    )

                    layout.background = drawable

                    startTimer(duration, toast)

                    toast.setGravity(Gravity.BOTTOM, 0, 100)
                    toast.view = layout
                    toast.show()
                }
                XToastStyle.Success -> {
                    binding.ivToast.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_success
                        )
                    )
                    binding.root.setBackgroundResource(R.color.banana)

                    binding.tvTitle.text =
                        title.ifEmpty { "Success" }
                    binding.tvDesc.text = message
                    val toast = Toast(context.applicationContext)

                    val drawable = ContextCompat.getDrawable(context, R.drawable.bg_round)
                    drawable?.colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(context, R.color.banana),
                        PorterDuff.Mode.MULTIPLY
                    )

                    layout.background = drawable

                    startTimer(duration, toast)

                    toast.setGravity(Gravity.BOTTOM, 0, 100)
                    toast.view = layout
                    toast.show()
                }
                XToastStyle.Warning -> {
                    binding.ivToast.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_warning
                        )
                    )
                    binding.root.setBackgroundResource(R.color.carrot_orange)

                    binding.tvTitle.text =
                        title.ifEmpty { "Warning" }
                    binding.tvDesc.text = message
                    val toast = Toast(context.applicationContext)

                    val drawable = ContextCompat.getDrawable(context, R.drawable.bg_round)
                    drawable?.colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(context, R.color.carrot_orange),
                        PorterDuff.Mode.MULTIPLY
                    )

                    layout.background = drawable

                    startTimer(duration, toast)

                    toast.setGravity(Gravity.BOTTOM, 0, 100)
                    toast.view = layout
                    toast.show()
                }
            }
            return XToast()
        }


        private fun startTimer(duration: Long, toast: Toast) {
            val timer = object : CountDownTimer(duration, 1000L) {
                override fun onTick(p0: Long) {}

                override fun onFinish() {
                    toast.cancel()
                }
            }
            timer.start()
        }

    }

}
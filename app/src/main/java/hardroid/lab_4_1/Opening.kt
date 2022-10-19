package hardroid.lab_4_1

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

/*
THIS FILE IS ONLY FOR LOTTIE ANIMATION ON START
 */

class Opening : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opening)
        var OpenLottie: LottieAnimationView = findViewById(R.id.opening)
        OpenLottie.playAnimation()

        OpenLottie.addAnimatorListener(object : Animation.AnimationListener, AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                try {
                    var intent = Intent(this@Opening, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }catch (exception:Exception){}


            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

    }
}
package com.example.news24.activities

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.news24.R
import com.example.news24.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navController by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        // rounding Image
        binding.imvCircular.roundedCornerDrawable(250f)

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeMain ->{
                    navController.navigate(R.id.mainFragment)
                    binding.ConsLayout.isVisible = true
                }
                R.id.searchQuery ->{
                    navController.navigate(R.id.searchFragment)
                    binding.ConsLayout.isVisible = false
                }
                R.id.savedAll ->{
                    navController.navigate(R.id.savedFragment)
                    binding.ConsLayout.isVisible = true
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun ImageView.roundedCornerDrawable(radius: Float) {
        val bitmap = (drawable as? BitmapDrawable)?.bitmap ?: return
        val outputBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(outputBitmap)

        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawRoundRect(rectF, radius, radius, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        setImageDrawable(BitmapDrawable(resources, outputBitmap))
    }
}
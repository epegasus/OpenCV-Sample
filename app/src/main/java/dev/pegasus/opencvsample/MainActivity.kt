package dev.pegasus.opencvsample

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.pegasus.opencvsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val openCvManager by lazy { OpenCvManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fullScreen()
        initOpenCv()

        binding.mbResize.setOnClickListener { resizeBitmap() }
    }

    private fun fullScreen() {
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initOpenCv() {
        openCvManager.initOpenCv { binding.mbResize.isEnabled = true }
    }

    private fun resizeBitmap() {
        val imageBitmap = BitmapFactory.decodeResource(resources, R.drawable.img_dummy)
        val bitmap = openCvManager.getResizedBitmap(imageBitmap, 720, 312)
        binding.imageViewResized.setImageBitmap(bitmap)
    }
}
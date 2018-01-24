package ruolan.com.lazyloadfragment.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ruolan.com.lazyloadfragment.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnLazy.setOnClickListener {
            intent.setClass(this, LazyloadActivity::class.java)
            startActivity(intent)
        }

    }
}

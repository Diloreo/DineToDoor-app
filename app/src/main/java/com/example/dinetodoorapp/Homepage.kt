package com.example.dinetodoorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay

class Homepage : AppCompatActivity()
{

    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)


        //link image to product page
        val imagelink1= findViewById<ImageView>(R.id.imgproduct1)
        imagelink1.setOnClickListener {
            val intent = Intent(this,ProductPage::class.java)
            startActivity(intent)
        }
        val imagelink2= findViewById<ImageView>(R.id.imgproduct2)
        imagelink2.setOnClickListener {
            val intent = Intent(this,ProductPage::class.java)
            startActivity(intent)
        }
        val imagelink3= findViewById<ImageView>(R.id.imgproduct3)
        imagelink3.setOnClickListener {
            val intent = Intent(this,ProductPage::class.java)
            startActivity(intent)
        }
        val imagelink4= findViewById<ImageView>(R.id.imgproduct4)
        imagelink4.setOnClickListener {
            val intent = Intent(this,ProductPage::class.java)
            startActivity(intent)
        }
        val imagelink5= findViewById<ImageView>(R.id.imgproduct5)
        imagelink5.setOnClickListener {
            val intent = Intent(this,ProductPage::class.java)
            startActivity(intent)
        }



        // Logout from Home
        val Backpage= findViewById<Button>(R.id.logoutbtn)
        Backpage.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }




       //Navigation control (Home, product, Cart)
        val homebtn = findViewById<Button>(R.id.homebtn)
        homebtn.setOnClickListener {
            val intent = Intent(this,Userprofile::class.java)
            startActivity(intent)
        }

        val Button2 = findViewById<Button>(R.id.productbtn)
        Button2.setOnClickListener {
            val intent = Intent(this,ProductPage::class.java)
            startActivity(intent)
        }
        val Button3 = findViewById<Button>(R.id.cartbtn)
        Button3.setOnClickListener {
            val intent = Intent(this,CartPage::class.java)
            startActivity(intent)
        }



        //Image Auto Slider
        viewPager = findViewById(R.id.viewpager2)
        val imageList = listOf(R.drawable.pic2, R.drawable.pic3, R.drawable.pic4)
        val adapter = PhotoAdapter(imageList)
        viewPager.adapter = adapter
        viewPager.autoScroll(lifecycleScope, 5000)


    }
    private fun ViewPager2.autoScroll(lifecycleCoroutineScope: LifecycleCoroutineScope, interval: Long){
        lifecycleCoroutineScope.launchWhenResumed{
            scrollIndefinitely(interval)
        }
    }


    private suspend fun ViewPager2.scrollIndefinitely(interval:Long)
    {
        delay(interval)
        val numberOfItems = adapter?.itemCount ?:0
        val lastIndex = if(numberOfItems>0) numberOfItems-1 else 0
        val nextItem = if(currentItem==lastIndex)0 else currentItem+1

        setCurrentItem(nextItem,true)
        scrollIndefinitely(interval)

    }

}
package com.assignment.githubtakehome

import android.widget.ImageView
import com.assignment.githubtakehome.ui.adapter.ImageBindingAdapter
import com.bumptech.glide.Glide
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions
import org.mockito.MockitoAnnotations

/**
 * @Author: Jijo
 * @Date: 10-06-2024
 */
class ImageBindingAdapterTest {

    @Mock
    private lateinit var imageView: ImageView

    private val imageUrl = "https://example.com/image.jpg"

    @Before
    fun setUp() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadImage_withValidUrl_loadsImageIntoImageView() {
        // Call the loadImage function with a valid URL
        ImageBindingAdapter.loadImage(imageView, imageUrl)

        // Verify that Glide is called with the correct parameters
        verify(Glide.with(imageView.context))
            .load(imageUrl)
            .into(imageView)
    }

    @Test
    fun loadImage_withNullUrl_doesNotLoadImage() {
        // Call the loadImage function with a null URL
        ImageBindingAdapter.loadImage(imageView, null)

        // Verify that Glide is not called
        verifyZeroInteractions(Glide.with(imageView.context))
    }
}
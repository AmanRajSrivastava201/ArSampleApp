package com.example.arsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var modelRenderable: ModelRenderable
    private lateinit var arFragment: ArFragment
    private lateinit var anchorNode: AnchorNode
    private var currentValue = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment
        setUpModel(currentValue)
        setUpPlane()
        img_next.setOnClickListener {
            if (currentValue <= 4) {
                anchorNode.anchor?.detach()
                setUpModel(currentValue + 1)
                setUpPlane()
                currentValue += 1
            } else {
                currentValue = 1
                anchorNode.anchor?.detach()
                setUpModel(currentValue)
                setUpPlane()
            }

        }
    }


    private fun setUpPlane() {
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            //trial
            anchorNode = AnchorNode(anchor)
            // val anchorNode: AnchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)
            createModel(anchorNode)
        }
    }

    private fun createModel(anchorNode: AnchorNode) {
        val node: TransformableNode = TransformableNode(arFragment.transformationSystem)
        //scaling
        node.scaleController.maxScale = 0.06f
        node.scaleController.minScale = 0.01f

        node.setParent(anchorNode)
        node.renderable = modelRenderable
        node.select()
    }

    private fun setUpModel(count: Int) {
        when (count) {
            1 -> {
                ModelRenderable.builder()
                    .setSource(
                        this,
                        R.raw.pizza
                    )
                    .build()
                    .thenAccept { renderable: ModelRenderable ->
                        modelRenderable = renderable
                    }
                    .exceptionally { throwable: Throwable? ->
                        Toast.makeText(this, "Unable to load model", Toast.LENGTH_SHORT).show()
                        null
                    }
            }
            2 -> {
                ModelRenderable.builder()
                    .setSource(
                        this,
                        R.raw.burger
                    )
                    .build()
                    .thenAccept { renderable: ModelRenderable ->
                        modelRenderable = renderable
                    }
                    .exceptionally { throwable: Throwable? ->
                        Toast.makeText(this, "Unable to load model", Toast.LENGTH_SHORT).show()
                        null
                    }
            }
            3 -> {
                ModelRenderable.builder()
                    .setSource(
                        this,
                        R.raw.wine
                    )
                    .build()
                    .thenAccept { renderable: ModelRenderable ->
                        modelRenderable = renderable
                    }
                    .exceptionally { throwable: Throwable? ->
                        Toast.makeText(this, "Unable to load model", Toast.LENGTH_SHORT).show()
                        null
                    }
            }
            4 -> {
                ModelRenderable.builder()
                    .setSource(
                        this,
                        R.raw.muffin
                    )
                    .build()
                    .thenAccept { renderable: ModelRenderable ->
                        modelRenderable = renderable
                    }
                    .exceptionally { throwable: Throwable? ->
                        Toast.makeText(this, "Unable to load model", Toast.LENGTH_SHORT).show()
                        null
                    }
            }
        }

    }
}
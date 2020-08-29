package com.example.arsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class MainActivity : AppCompatActivity() {
    private lateinit var modelRenderable: ModelRenderable
    private lateinit var arFragment: ArFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment
        setUpModel()
        setUpPlane()
    }

    private fun setUpPlane() {
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            val anchorNode: AnchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)
            createModel(anchorNode)


        }
    }

    private fun createModel(anchorNode: AnchorNode) {
        val node: TransformableNode = TransformableNode(arFragment.transformationSystem)
        node.setParent(anchorNode)
        node.renderable = modelRenderable
        node.select()
    }

    private fun setUpModel() {
        ModelRenderable.builder()
            .setSource(
                this,
                R.raw.raw
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
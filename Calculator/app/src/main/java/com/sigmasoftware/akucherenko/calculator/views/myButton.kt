package com.sigmasoftware.akucherenko.calculator.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class myButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {

    private var myButtonWidth = 0f
    private var myButtonHeight = 0f
    private val paint = Paint()
    private val paintText = Paint()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        myButtonWidth = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        myButtonHeight = MeasureSpec.getSize(heightMeasureSpec).toFloat()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val s = super.getText().toString()

        canvas.drawColor(Color.TRANSPARENT)

        paint.style = Paint.Style.FILL
        paint.color = Color.BLUE

        val textSize = super.getTextSize()
        paintText.textSize = textSize
        paintText.typeface = Typeface.DEFAULT_BOLD
        paintText.color = Color.WHITE

        canvas.drawOval(
            0f,
            0f, width.toFloat(),
            height.toFloat(), paint
        )

        canvas.drawText(
            s, ((width - textSize / 2) / 2),
            ((height + textSize) / 2), paintText
        )

    }
}